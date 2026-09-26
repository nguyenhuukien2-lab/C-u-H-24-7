package com.example.roadside.utils;

public class Constants {
    public static final boolean DEBUG = true;
    public static final String SUPABASE_URL = "https://mqnnzobfzbtolvmatuwr.supabase.co";
    public static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1xbm56b2JmemJ0b2x2bWF0dXdyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTA0MzMyNjEsImV4cCI6MjEwNjAwOTI2MX0.qSarVuJKPUg1yoigVVa8F23caqokgaMVly7kfFrMbXE";
    public static final String BASE_URL = SUPABASE_URL + "/rest/v1/";

    public static final String HOTLINE_NUMBER = "19006868";
    public static final String PREF_NAME = "roadside_prefs";
    public static final String PREFS_NAME = "roadside_prefs";

    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_IS_LOGGED_IN = "is_logged_in";
    public static final String KEY_AUTH_TOKEN = "auth_token";
    public static final String KEY_PHONE_NUMBER = "phone_number";

    public static final String EXTRA_REQUEST_ID = "extra_request_id";
    public static final String EXTRA_PROVIDER_ID = "extra_provider_id";

    public static final String VEHICLE_CAR = "CAR";
    public static final String VEHICLE_TRUCK = "TRUCK";
    public static final String VEHICLE_MOTORBIKE = "MOTORBIKE";
    public static final String VEHICLE_EV = "EV";

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_ACCEPTED = "ACCEPTED";
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1001;
}
