-- =====================================================================
-- ResQ247 (Cứu Hộ 247) - Supabase PostgreSQL & PostGIS Schema
-- Run this SQL script in your Supabase SQL Editor.
-- =====================================================================

-- Enable PostGIS extension for geospatial queries
create extension if not exists "postgis";

-- 1. Profiles table (extends Supabase auth.users)
create table public.profiles (
  id uuid references auth.users on delete cascade primary key,
  phone_number text unique not null,
  full_name text,
  avatar_url text,
  role text default 'CUSTOMER' check (role in ('CUSTOMER', 'PROVIDER', 'ADMIN')),
  created_at timestamp with time zone default timezone('utc'::text, now()) not null
);

-- 2. Providers table (Rescue teams / tow trucks)
create table public.providers (
  id uuid default gen_random_uuid() primary key,
  user_id uuid references public.profiles(id) on delete set null,
  name text not null,
  vehicle_plate text not null,
  vehicle_type text not null,
  phone text not null,
  rating numeric(3,2) default 5.0,
  rating_count integer default 0,
  is_verified boolean default true,
  is_available boolean default true,
  -- PostGIS geography point for live GPS location (longitude, latitude)
  location geography(Point, 4326),
  created_at timestamp with time zone default timezone('utc'::text, now()) not null
);

-- 3. Requests table (Roadside assistance tickets)
create table public.requests (
  id uuid default gen_random_uuid() primary key,
  request_id text unique not null, -- e.g. '#RQ-8842'
  customer_id uuid references public.profiles(id) on delete cascade not null,
  provider_id uuid references public.providers(id) on delete set null,
  vehicle_type text not null,
  plate_number text,
  vehicle_model text,
  issue_types text[] not null,
  note text,
  pickup_address text not null,
  latitude double precision not null,
  longitude double precision not null,
  estimated_cost numeric(12,2) not null,
  status text default 'PENDING' check (status in ('PENDING', 'ACCEPTED', 'EN_ROUTE', 'ARRIVED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
  created_at timestamp with time zone default timezone('utc'::text, now()) not null
);

-- 4. Payments table
create table public.payments (
  id uuid default gen_random_uuid() primary key,
  request_id uuid references public.requests(id) on delete cascade not null,
  amount numeric(12,2) not null,
  method text not null check (method in ('MOMO', 'VNPAY', 'CARD', 'CASH')),
  status text default 'PENDING' check (status in ('PENDING', 'SUCCESS', 'FAILED')),
  transaction_id text,
  created_at timestamp with time zone default timezone('utc'::text, now()) not null
);

-- 5. Ratings table
create table public.ratings (
  id uuid default gen_random_uuid() primary key,
  request_id uuid references public.requests(id) on delete cascade not null,
  customer_id uuid references public.profiles(id) on delete cascade not null,
  provider_id uuid references public.providers(id) on delete cascade not null,
  stars integer not null check (stars between 1 and 5),
  feedback_tags text[],
  comment text,
  created_at timestamp with time zone default timezone('utc'::text, now()) not null
);

-- =====================================================================
-- PostGIS Function: Find nearby available rescue providers within radius (e.g. 10km)
-- =====================================================================
create or replace function get_nearby_providers(
  customer_lat double precision,
  customer_lng double precision,
  max_distance_km double precision default 10.0
)
returns table (
  id uuid,
  name text,
  vehicle_plate text,
  vehicle_type text,
  phone text,
  rating numeric,
  rating_count integer,
  distance_km double precision
)
language sql security definer
as $$
  select
    p.id,
    p.name,
    p.vehicle_plate,
    p.vehicle_type,
    p.phone,
    p.rating,
    p.rating_count,
    st_distance(p.location, st_setsrid(st_makepoint(customer_lng, customer_lat), 4326)::geography) / 1000.0 as distance_km
  from public.providers p
  where p.is_available = true
    and st_dwithin(p.location, st_setsrid(st_makepoint(customer_lng, customer_lat), 4326)::geography, max_distance_km * 1000)
  order by distance_km asc;
$$;

-- Enable Row Level Security (RLS) on all tables
alter table public.profiles enable row level security;
alter table public.providers enable row level security;
alter table public.requests enable row level security;
alter table public.payments enable row level security;
alter table public.ratings enable row level security;

-- Basic RLS policies (allow authenticated users to read/write their data)
create policy "Allow public read access on providers" on public.providers for select using (true);
create policy "Allow users to read own profile" on public.profiles for select using (auth.uid() = id);
create policy "Allow users to update own profile" on public.profiles for update using (auth.uid() = id);
create policy "Allow users to manage own requests" on public.requests for all using (auth.uid() = customer_id);
