import 'package:http/http.dart' as http;
import 'dart:convert';

class SupabaseService {
  static const String supabaseUrl = "https://mqnnzobfzbtolvmatuwr.supabase.co";
  static const String anonKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1xbm56b2JmemJ0b2x2bWF0dXdyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTA0MzMyNjEsImV4cCI6MjEwNjAwOTI2MX0.qSarVuJKPUg1yoigVVa8F23caqokgaMVly7kfFrMbXE";

  // 1. Call Quick-Task Edge Function (Auto-Dispatch Nearest Provider)
  static Future<Map<String, dynamic>?> callQuickTask({
    required double latitude,
    required double longitude,
    required String vehicleType,
  }) async {
    try {
      final response = await http.post(
        Uri.parse('$supabaseUrl/functions/v1/quick-task'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $anonKey',
          'apikey': anonKey,
        },
        body: jsonEncode({
          'latitude': latitude,
          'longitude': longitude,
          'vehicleType': vehicleType,
        }),
      );

      if (response.statusCode == 200) {
        return jsonDecode(response.body);
      }
      return null;
    } catch (e) {
      return null;
    }
  }

  // 2. Fetch Real Nearby Providers from PostGIS RPC
  static Future<List<dynamic>> fetchNearbyProviders({
    required double lat,
    required double lng,
    double maxDistanceKm = 15.0,
  }) async {
    try {
      final response = await http.post(
        Uri.parse('$supabaseUrl/rest/v1/rpc/get_nearby_providers'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $anonKey',
          'apikey': anonKey,
        },
        body: jsonEncode({
          'customer_lat': lat,
          'customer_lng': lng,
          'max_distance_km': maxDistanceKm,
        }),
      );

      if (response.statusCode == 200) {
        return jsonDecode(response.body);
      }
      return [];
    } catch (e) {
      return [];
    }
  }

  // 3. Fetch Real Rescue History Requests from Supabase REST API
  static Future<List<dynamic>> fetchUserRequests(String customerId) async {
    try {
      final response = await http.get(
        Uri.parse('$supabaseUrl/rest/v1/requests?customer_id=eq.$customerId&order=created_at.desc'),
        headers: {
          'Authorization': 'Bearer $anonKey',
          'apikey': anonKey,
        },
      );

      if (response.statusCode == 200) {
        return jsonDecode(response.body);
      }
      return [];
    } catch (e) {
      return [];
    }
  }

  // 4. Create Rescue Request on Supabase
  static Future<Map<String, dynamic>?> createRescueRequest(Map<String, dynamic> requestData) async {
    try {
      final response = await http.post(
        Uri.parse('$supabaseUrl/rest/v1/requests'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $anonKey',
          'apikey': anonKey,
          'Prefer': 'return=representation',
        },
        body: jsonEncode(requestData),
      );

      if (response.statusCode == 201 || response.statusCode == 200) {
        final list = jsonDecode(response.body);
        if (list is List && list.isNotEmpty) {
          return list[0];
        }
      }
      return null;
    } catch (e) {
      return null;
    }
  }
}
