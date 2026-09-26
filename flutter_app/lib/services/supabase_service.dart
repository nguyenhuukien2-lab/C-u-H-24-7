import 'package:http/http.dart' as http;
import 'dart:convert';

class SupabaseService {
  static const String supabaseUrl = "https://mqnnzobfzbtolvmatuwr.supabase.co";
  static const String anonKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1xbm56b2JmemJ0b2x2bWF0dXdyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTA0MzMyNjEsImV4cCI6MjEwNjAwOTI2MX0.qSarVuJKPUg1yoigVVa8F23caqokgaMVly7kfFrMbXE";

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
}
