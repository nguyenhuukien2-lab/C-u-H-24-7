import 'package:flutter/material.dart';

import 'screens/login_screen.dart';
import 'screens/home_screen.dart';
import 'screens/request_form_screen.dart';
import 'screens/provider_list_screen.dart';
import 'screens/tracking_screen.dart';
import 'screens/payment_screen.dart';
import 'screens/history_screen.dart';

void main() {
  runApp(const CuuHoApp());
}

class CuuHoApp extends StatelessWidget {
  const CuuHoApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cứu Hộ 247',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFFE53935),
          primary: const Color(0xFFE53935),
          secondary: const Color(0xFF1E88E5),
          background: const Color(0xFFF5F7FA),
          surface: Colors.white,
        ),
        scaffoldBackgroundColor: const Color(0xFFF5F7FA),
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.white,
          foregroundColor: Color(0xFF2D3748),
          elevation: 0,
          centerTitle: true,
        ),
      ),
      builder: (context, child) {
        return Container(
          color: const Color(0xFF0F172A),
          child: Center(
            child: ConstrainedBox(
              constraints: const BoxConstraints(maxWidth: 480),
              child: child,
            ),
          ),
        );
      },
      initialRoute: '/home',
      routes: {
        '/login': (context) => const LoginScreen(),
        '/home': (context) => const HomeScreen(),
        '/request': (context) => const RequestFormScreen(),
        '/providers': (context) => const ProviderListScreen(),
        '/tracking': (context) => const TrackingScreen(),
        '/payment': (context) => const PaymentScreen(),
        '/history': (context) => const HistoryScreen(),
      },
    );
  }
}
