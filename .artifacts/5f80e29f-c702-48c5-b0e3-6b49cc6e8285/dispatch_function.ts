// =====================================================================
// Supabase Edge Function (Deno / TypeScript): Auto-dispatch nearest provider
// Deploy to Supabase Functions as 'dispatch-request'
// =====================================================================

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"

serve(async (req) => {
  try {
    const { requestId, latitude, longitude, vehicleType } = await req.json()

    // Initialize Supabase Admin Client
    const supabaseAdmin = createClient(
      Deno.env.get('SUPABASE_URL') ?? '',
      Deno.env.get('SUPABASE_SERVICE_ROLE_KEY') ?? ''
    )

    // Call PostGIS function to find the closest available provider within 15km
    const { data: providers, error: geoError } = await supabaseAdmin
      .rpc('get_nearby_providers', {
        customer_lat: latitude,
        customer_lng: longitude,
        max_distance_km: 15.0
      })

    if (geoError || !providers || providers.length === 0) {
      return new Response(JSON.stringify({ success: false, message: 'No nearby rescue providers found.' }), {
        headers: { 'Content-Type': 'application/json' },
        status: 404,
      })
    }

    // Select the nearest provider
    const nearestProvider = providers[0]

    // Update request with assigned provider and change status to ACCEPTED
    const { error: updateError } = await supabaseAdmin
      .from('requests')
      .update({
        provider_id: nearestProvider.id,
        status: 'ACCEPTED'
      })
      .eq('id', requestId)

    if (updateError) {
      throw updateError
    }

    return new Response(JSON.stringify({
      success: true,
      assignedProvider: nearestProvider
    }), {
      headers: { 'Content-Type': 'application/json' },
      status: 200,
    })

  } catch (err) {
    return new Response(JSON.stringify({ error: err.message }), {
      headers: { 'Content-Type': 'application/json' },
      status: 500,
    })
  }
})
