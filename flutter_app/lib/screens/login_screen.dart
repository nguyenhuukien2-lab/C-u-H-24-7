import 'package:flutter/material.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  bool isLoginTab = true;

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
                      const Text('ResQ247', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 18, color: Color(0xFF1A1A1A))),
                    ],
                  ),
                  Row(
                    children: [
                      Container(
                        padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
                        decoration: BoxDecoration(
                          color: const Color(0xFFC1121F),
                          borderRadius: BorderRadius.circular(100),
                        ),
                        child: Row(
                          children: const [
                            Icon(Icons.phone, color: Colors.white, size: 14),
                            SizedBox(width: 6),
                            Text('1900 6868', style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 12)),
                          ],
                        ),
                      ),
                      const SizedBox(width: 8),
                      Container(
                        padding: const EdgeInsets.all(8),
                        decoration: const BoxDecoration(color: Color(0xFFC1121F), shape: BoxShape.circle),
                        child: const Icon(Icons.person, color: Colors.white, size: 18),
                      ),
                    ],
                  ),
                ],
              ),
              const SizedBox(height: 24),

              // Main Header
              Center(
                child: Column(
                  children: [
                    Container(
                      padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
                      decoration: BoxDecoration(
                        color: const Color(0xFFFBE4DC),
                        borderRadius: BorderRadius.circular(100),
                      ),
                      child: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: const [
                          Icon(Icons.location_on, color: Color(0xFFC1121F), size: 14),
                          SizedBox(width: 4),
                          Text('HỆ THỐNG CỨU HỘ TOÀN QUỐC', style: TextStyle(color: Color(0xFFC1121F), fontSize: 11, fontWeight: FontWeight.bold)),
                        ],
                      ),
                    ),
                    const SizedBox(height: 12),
                    const Text('Cứu Hộ Giao Thông 24/7', style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold, color: Color(0xFF1A1A1A))),
                    const SizedBox(height: 6),
                    RichText(
                      textAlign: TextAlign.center,
                      text: const TextSpan(
                        style: TextStyle(fontSize: 13, color: Color(0xFF6B7280)),
                        children: [
                          TextSpan(text: 'Kết nối đội thợ và xe cứu hộ gần nhất chỉ trong '),
                          TextSpan(text: '15 phút', style: TextStyle(color: Color(0xFFC1121F), fontWeight: FontWeight.bold)),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 24),

              // Tab Switcher (Đăng nhập / Đăng ký mới)
              Container(
                padding: const EdgeInsets.all(6),
                decoration: BoxDecoration(
                  color: const Color(0xFFEEF0FA),
                  borderRadius: BorderRadius.circular(100),
                ),
                child: Row(
                  children: [
                    Expanded(
                      child: InkWell(
                        onTap: () => setState(() => isLoginTab = true),
                        borderRadius: BorderRadius.circular(100),
                        child: Container(
                          padding: const EdgeInsets.symmetric(vertical: 12),
                          decoration: BoxDecoration(
                            color: isLoginTab ? Colors.white : Colors.transparent,
                            borderRadius: BorderRadius.circular(100),
                            boxShadow: isLoginTab ? [BoxShadow(color: Colors.black.withOpacity(0.05), blurRadius: 4, offset: const Offset(0, 2))] : null,
                          ),
                          alignment: Alignment.center,
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Icon(Icons.login, size: 16, color: isLoginTab ? const Color(0xFFC1121F) : const Color(0xFF6B7280)),
                              const SizedBox(width: 6),
                              Text('Đăng nhập', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: isLoginTab ? const Color(0xFFC1121F) : const Color(0xFF6B7280))),
                            ],
                          ),
                        ),
                      ),
                    ),
                    Expanded(
                      child: InkWell(
                        onTap: () => setState(() => isLoginTab = false),
                        borderRadius: BorderRadius.circular(100),
                        child: Container(
                          padding: const EdgeInsets.symmetric(vertical: 12),
                          decoration: BoxDecoration(
                            color: !isLoginTab ? Colors.white : Colors.transparent,
                            borderRadius: BorderRadius.circular(100),
                            boxShadow: !isLoginTab ? [BoxShadow(color: Colors.black.withOpacity(0.05), blurRadius: 4, offset: const Offset(0, 2))] : null,
                          ),
                          alignment: Alignment.center,
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Icon(Icons.person_add_outlined, size: 16, color: !isLoginTab ? const Color(0xFFC1121F) : const Color(0xFF6B7280)),
                              const SizedBox(width: 6),
                              Text('Đăng ký mới', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: !isLoginTab ? const Color(0xFFC1121F) : const Color(0xFF6B7280))),
                            ],
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 20),

              // Main Input Card
              Container(
                padding: const EdgeInsets.all(20),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(24),
                  boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 10, offset: const Offset(0, 4))],
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: const [
                        Text('Số điện thoại di động', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: Color(0xFF1A1A1A))),
                        Text('🔵 Định danh tức thì', style: TextStyle(fontSize: 11, color: Color(0xFF1565C0), fontWeight: FontWeight.bold)),
                      ],
                    ),
                    const SizedBox(height: 10),

                    // Phone Input Field
                    Container(
                      height: 52,
                      padding: const EdgeInsets.symmetric(horizontal: 14),
                      decoration: BoxDecoration(
                        color: const Color(0xFFF7F7FA),
                        borderRadius: BorderRadius.circular(14),
                        border: Border.all(color: const Color(0xFFE5E7EB)),
                      ),
                      child: Row(
                        children: [
                          const Text('🇻🇳 +84', style: TextStyle(fontWeight: FontWeight.bold, color: Color(0xFF1A1A1A))),
                          const Icon(Icons.arrow_drop_down, color: Color(0xFF6B7280)),
                          const SizedBox(width: 10),
                          Container(width: 1, height: 24, color: const Color(0xFFE5E7EB)),
                          const SizedBox(width: 14),
                          const Expanded(
                            child: TextField(
                              keyboardType: TextInputType.phone,
                              decoration: InputDecoration(
                                border: InputBorder.none,
                                hintText: '0912 345 678',
                                hintStyle: TextStyle(color: Color(0xFF9CA3AF), fontSize: 14),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    const SizedBox(height: 12),

                    // Quick Chips
                    Row(
                      children: const [
                        Text('Gợi ý nhanh: ', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                        SizedBox(width: 4),
                        Expanded(
                          child: SingleChildScrollView(
                            scrollDirection: Axis.horizontal,
                            child: Row(
                              children: [
                                _NetworkChip(label: '098 Viettel'),
                                SizedBox(width: 6),
                                _NetworkChip(label: '090 Mobi'),
                                SizedBox(width: 6),
                                _NetworkChip(label: '091 Vina'),
                                SizedBox(width: 6),
                                _NetworkChip(label: '077'),
                              ],
                            ),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 20),

                    // Continue Button
                    SizedBox(
                      width: double.infinity,
                      height: 52,
                      child: ElevatedButton(
                        onPressed: () => Navigator.pushReplacementNamed(context, '/home'),
                        style: ElevatedButton.styleFrom(
                          backgroundColor: const Color(0xFFC1121F),
                          foregroundColor: Colors.white,
                          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(100)),
                          elevation: 2,
                        ),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: const [
                            Text('Tiếp tục / Nhận mã OTP', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 15)),
                            SizedBox(width: 6),
                            Icon(Icons.arrow_forward, size: 16),
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(height: 12),

                    Center(
                      child: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: const [
                          Icon(Icons.bolt, color: Color(0xFFC1121F), size: 14),
                          SizedBox(width: 4),
                          Text('Mã OTP gửi tự động qua Zalo hoặc SMS trong 5s', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                        ],
                      ),
                    ),
                    const SizedBox(height: 20),

                    // Divider
                    Row(
                      children: const [
                        Expanded(child: Divider(color: Color(0xFFE5E7EB))),
                        Padding(
                          padding: EdgeInsets.symmetric(horizontal: 10),
                          child: Text('HOẶC ĐĂNG NHẬP NHANH QUA', style: TextStyle(fontSize: 10, color: Color(0xFF6B7280), fontWeight: FontWeight.bold)),
                        ),
                        Expanded(child: Divider(color: Color(0xFFE5E7EB))),
                      ],
                    ),
                    const SizedBox(height: 16),

                    // Social Login Buttons
                    Row(
                      children: [
                        Expanded(child: _SocialBtn(icon: Icons.chat, label: 'Zalo', color: const Color(0xFF0068FF))),
                        const SizedBox(width: 8),
                        Expanded(child: _SocialBtn(icon: Icons.g_mobiledata, label: 'Google', color: const Color(0xFF1A1A1A))),
                        const SizedBox(width: 8),
                        Expanded(child: _SocialBtn(icon: Icons.apple, label: 'Apple', color: const Color(0xFF1A1A1A))),
                      ],
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 16),

              // Emergency Quick Guest Banner
              Container(
                padding: const EdgeInsets.all(14),
                decoration: BoxDecoration(
                  color: const Color(0xFFFBE4DC),
                  borderRadius: BorderRadius.circular(16),
                  border: Border.all(color: const Color(0xFFC1121F).withOpacity(0.2)),
                ),
                child: Row(
                  children: [
                    Container(
                      padding: const EdgeInsets.all(8),
                      decoration: const BoxDecoration(color: Color(0xFFC1121F), shape: BoxShape.circle),
                      child: const Icon(Icons.warning_amber_rounded, color: Colors.white, size: 20),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: const [
                          Text('Cần cứu hộ xe ngay lập tức?', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: Color(0xFFC1121F))),
                          SizedBox(height: 2),
                          Text('Bỏ qua bước đăng nhập • Gọi thợ trong 3 giây', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                        ],
                      ),
                    ),
                    ElevatedButton(
                      onPressed: () => Navigator.pushNamed(context, '/request'),
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color(0xFFC1121F),
                        foregroundColor: Colors.white,
                        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(100)),
                        padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
                        elevation: 0,
                      ),
                      child: Row(
                        children: const [
                          Text('Gọi ngay', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 12)),
                          SizedBox(width: 2),
                          Icon(Icons.arrow_forward, size: 12),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 20),

              // Footer Security & Links
              Center(
                child: Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: const [
                        Icon(Icons.verified_user_outlined, color: Color(0xFF1565C0), size: 14),
                        SizedBox(width: 6),
                        Text('Bảo mật thông tin chuẩn mã hóa viễn thông • ResQ247', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                      ],
                    ),
                    const SizedBox(height: 10),
                    Wrap(
                      alignment: WrapAlignment.center,
                      spacing: 8,
                      runSpacing: 4,
                      children: const [
                        Text('Điều khoản dịch vụ', style: TextStyle(fontSize: 11, color: Color(0xFF1565C0), decoration: TextDecoration.underline)),
                        Text('•', style: TextStyle(color: Color(0xFF9CA3AF))),
                        Text('Chính sách bảo mật', style: TextStyle(fontSize: 11, color: Color(0xFF1565C0), decoration: TextDecoration.underline)),
                        Text('•', style: TextStyle(color: Color(0xFF9CA3AF))),
                        Text('Hỗ trợ tài xế', style: TextStyle(fontSize: 11, color: Color(0xFF1565C0), decoration: TextDecoration.underline)),
                      ],
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 16),
            ],
          ),
        ),
      ),
    );
  }
}

class _NetworkChip extends StatelessWidget {
  final String label;
  const _NetworkChip({required this.label});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 5),
      decoration: BoxDecoration(
        color: const Color(0xFFEEF0FA),
        borderRadius: BorderRadius.circular(8),
      ),
      child: Text(label, style: const TextStyle(fontSize: 11, fontWeight: FontWeight.bold, color: Color(0xFF1A1A1A))),
    );
  }
}

class _SocialBtn extends StatelessWidget {
  final IconData icon;
  final String label;
  final Color color;

  const _SocialBtn({required this.icon, required this.label, required this.color});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 10),
      decoration: BoxDecoration(
        color: const Color(0xFFEEF0FA),
        borderRadius: BorderRadius.circular(12),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(icon, color: color, size: 18),
          const SizedBox(width: 6),
          Text(label, style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: Color(0xFF1A1A1A))),
        ],
      ),
    );
  }
}
