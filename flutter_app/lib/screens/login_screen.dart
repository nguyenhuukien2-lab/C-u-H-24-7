import 'package:flutter/material.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF7F7FA),
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Top Bar
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Row(
                    children: [
                      IconButton(
                        icon: const Icon(Icons.arrow_back, color: Color(0xFF1A1A1A)),
                        onPressed: () {
                          if (Navigator.canPop(context)) {
                            Navigator.pop(context);
                          } else {
                            Navigator.pushReplacementNamed(context, '/home');
                          }
                        },
                        padding: EdgeInsets.zero,
                        constraints: const BoxConstraints(),
                      ),
                      const SizedBox(width: 8),
                      const Icon(Icons.car_crash, color: Color(0xFFC1121F), size: 28),
                      const SizedBox(width: 8),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: const [
                          Text('Cứu Hộ 247', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16, color: Color(0xFF1A1A1A))),
                          Text('ResQ247 SOS Dispatch', style: TextStyle(fontSize: 10, color: Color(0xFFC1121F), fontWeight: FontWeight.bold)),
                        ],
                      ),
                    ],
                  ),
                  Container(
                    padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
                    decoration: BoxDecoration(
                      color: const Color(0xFFC1121F),
                      borderRadius: BorderRadius.circular(100),
                    ),
                    child: Row(
                      children: const [
                        Icon(Icons.phone, color: Colors.white, size: 14),
                        SizedBox(width: 4),
                        Text('1900 6868', style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 12)),
                      ],
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 16),

              // Emergency Banner
              Container(
                padding: const EdgeInsets.all(12),
                decoration: BoxDecoration(
                  color: const Color(0xFFFBE4DC),
                  borderRadius: BorderRadius.circular(16),
                ),
                child: Row(
                  children: [
                    Container(
                      padding: const EdgeInsets.all(8),
                      decoration: const BoxDecoration(color: Color(0xFFC1121F), shape: BoxShape.circle),
                      child: const Icon(Icons.warning_amber_rounded, color: Colors.white, size: 24),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: const [
                          Text('TRỢ GIÚP KHẨN CẤP 24/7', style: TextStyle(color: Color(0xFFC1121F), fontWeight: FontWeight.bold, fontSize: 11)),
                          Text('Gọi tổng đài: 1900 6868', style: TextStyle(color: Color(0xFF1A1A1A), fontWeight: FontWeight.bold, fontSize: 15)),
                        ],
                      ),
                    ),
                    Container(
                      padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(100),
                      ),
                      child: const Text('Miễn phí', style: TextStyle(color: Color(0xFFC1121F), fontWeight: FontWeight.bold, fontSize: 11)),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 16),

              // Login Card
              Card(
                elevation: 2,
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
                color: Colors.white,
                child: Padding(
                  padding: const EdgeInsets.all(20),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text('Đăng nhập hoặc Tạo tài khoản', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Color(0xFF1A1A1A))),
                      const SizedBox(height: 4),
                      const Text('Nhập số điện thoại để kết nối đội cứu hộ gần nhất', style: TextStyle(fontSize: 13, color: Color(0xFF6B7280))),
                      const SizedBox(height: 20),

                      const Text('Số điện thoại di động', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: Color(0xFF1A1A1A))),
                      const SizedBox(height: 8),

                      Container(
                        height: 52,
                        padding: const EdgeInsets.symmetric(horizontal: 16),
                        decoration: BoxDecoration(
                          color: const Color(0xFFF7F7FA),
                          borderRadius: BorderRadius.circular(12),
                          border: Border.all(color: const Color(0xFFEDEDED)),
                        ),
                        child: Row(
                          children: [
                            const Text('🇻🇳 +84', style: TextStyle(fontWeight: FontWeight.bold, color: Color(0xFF1A1A1A))),
                            const SizedBox(width: 12),
                            Container(width: 1, height: 24, color: const Color(0xFFEDEDED)),
                            const SizedBox(width: 12),
                            Expanded(
                              child: TextField(
                                keyboardType: TextInputType.phone,
                                decoration: const InputDecoration(
                                  border: InputBorder.none,
                                  hintText: '0912 345 678',
                                  hintStyle: TextStyle(color: Color(0xFF9CA3AF)),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                      const SizedBox(height: 20),

                      SizedBox(
                        width: double.infinity,
                        height: 52,
                        child: ElevatedButton(
                          onPressed: () => Navigator.pushReplacementNamed(context, '/home'),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: const Color(0xFFC1121F),
                            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(100)),
                          ),
                          child: const Text('Tiếp tục / Gửi mã OTP', style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 15)),
                        ),
                      ),
                      const SizedBox(height: 16),

                      const Center(child: Text('CHỌN NHANH ĐẦU SỐ NHÀ MẠNG', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280), fontWeight: FontWeight.bold))),
                      const SizedBox(height: 8),

                      Row(
                        children: const [
                          Expanded(child: _PrefixChip(text: '098')),
                          SizedBox(width: 6),
                          Expanded(child: _PrefixChip(text: '090')),
                          SizedBox(width: 6),
                          Expanded(child: _PrefixChip(text: '091')),
                          SizedBox(width: 6),
                          Expanded(child: _PrefixChip(text: '077')),
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _PrefixChip extends StatelessWidget {
  final String text;
  const _PrefixChip({required this.text});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 10),
      decoration: BoxDecoration(
        color: const Color(0xFFEEF0FA),
        borderRadius: BorderRadius.circular(10),
      ),
      alignment: Alignment.center,
      child: Text(text, style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: Color(0xFF1A1A1A))),
    );
  }
}
