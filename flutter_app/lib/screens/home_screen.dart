import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

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
                      const Icon(Icons.car_crash, color: Color(0xFFC1121F), size: 28),
                      const SizedBox(width: 8),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: const [
                          Text('ResQ247 24/7', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16, color: Color(0xFF1A1A1A))),
                          Text('Trang Chủ', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                        ],
                      ),
                    ],
                  ),
                  Row(
                    children: [
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
                            Text('HOTLINE 1900 6868', style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 11)),
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
              const SizedBox(height: 16),

              // Location Card
              Container(
                padding: const EdgeInsets.all(14),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(16),
                  boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.03), blurRadius: 6, offset: const Offset(0, 2))],
                ),
                child: Row(
                  children: [
                    Container(
                      padding: const EdgeInsets.all(10),
                      decoration: BoxDecoration(color: const Color(0xFFE8F1FB), borderRadius: BorderRadius.circular(12)),
                      child: const Icon(Icons.my_location, color: Color(0xFF1565C0), size: 22),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            children: const [
                              Icon(Icons.fiber_manual_record, color: Color(0xFF1565C0), size: 10),
                              SizedBox(width: 4),
                              Text('VỊ TRÍ GPS THỜI GIAN THỰC', style: TextStyle(fontSize: 10, color: Color(0xFF1565C0), fontWeight: FontWeight.bold)),
                            ],
                          ),
                          const SizedBox(height: 2),
                          const Text('Km 14+200, Cao tốc Pháp V...', style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold, color: Color(0xFF1A1A1A))),
                          const SizedBox(height: 2),
                          const Text('Xã Liên Ninh, Thanh Trì, Hà Nội', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                        ],
                      ),
                    ),
                    OutlinedButton(
                      onPressed: () {},
                      style: OutlinedButton.styleFrom(
                        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(100)),
                        side: const BorderSide(color: Color(0xFFE5E7EB)),
                        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                      ),
                      child: const Text('Thay đổi', style: TextStyle(color: Color(0xFF1A1A1A), fontSize: 12, fontWeight: FontWeight.bold)),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 16),

              // Nearby Alert Banner
              Container(
                padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 16),
                decoration: BoxDecoration(
                  color: const Color(0xFFFBE4DC),
                  borderRadius: BorderRadius.circular(100),
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: const [
                    Icon(Icons.wifi_tethering, color: Color(0xFFC1121F), size: 18),
                    SizedBox(width: 8),
                    Text('Hơn 42 xe cứu hộ sẵn sàng trong bán kính 5km', style: TextStyle(color: Color(0xFFC1121F), fontSize: 12, fontWeight: FontWeight.bold)),
                  ],
                ),
              ),
              const SizedBox(height: 16),

              // Giant SOS Button Card
              Container(
                width: double.infinity,
                padding: const EdgeInsets.symmetric(vertical: 28, horizontal: 20),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(24),
                  boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 10, offset: const Offset(0, 4))],
                ),
                child: Column(
                  children: [
                    Stack(
                      alignment: Alignment.center,
                      children: [
                        Container(
                          width: 170,
                          height: 170,
                          decoration: BoxDecoration(
                            shape: BoxShape.circle,
                            color: const Color(0xFFC1121F).withOpacity(0.1),
                          ),
                        ),
                        Container(
                          width: 140,
                          height: 140,
                          decoration: BoxDecoration(
                            shape: BoxShape.circle,
                            color: const Color(0xFFC1121F).withOpacity(0.2),
                          ),
                        ),
                        GestureDetector(
                          onTap: () => Navigator.pushNamed(context, '/request'),
                          child: Container(
                            width: 110,
                            height: 110,
                            decoration: const BoxDecoration(
                              shape: BoxShape.circle,
                              gradient: LinearGradient(
                                colors: [Color(0xFFC1121F), Color(0xFF8E0F1A)],
                                begin: Alignment.topLeft,
                                end: Alignment.bottomRight,
                              ),
                              boxShadow: [BoxShadow(color: Color(0xFFC1121F), blurRadius: 16, offset: const Offset(0, 6))],
                            ),
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: const [
                                Icon(Icons.warning_amber_rounded, color: Colors.white, size: 36),
                                SizedBox(height: 4),
                                Text('CỨU HỘ', style: TextStyle(color: Colors.white, fontSize: 14, fontWeight: FontWeight.bold, letterSpacing: 0.5)),
                                Text('NGAY', style: TextStyle(color: Colors.white, fontSize: 14, fontWeight: FontWeight.bold, letterSpacing: 0.5)),
                              ],
                            ),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 16),
                    const Text('CHẠM 1 LẦN', style: TextStyle(color: Color(0xFFC1121F), fontSize: 16, fontWeight: FontWeight.bold)),
                    const SizedBox(height: 12),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: const [
                        Icon(Icons.bolt, color: Color(0xFFC1121F), size: 16),
                        SizedBox(width: 4),
                        Text('Tự động kết nối trạm trực gần nhất sau ', style: TextStyle(color: Color(0xFF6B7280), fontSize: 12)),
                        Text('3 giây', style: TextStyle(color: Color(0xFF1A1A1A), fontWeight: FontWeight.bold, fontSize: 12)),
                      ],
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 24),

              // Quick Services Section
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [
                  Text('Dịch vụ cứu hộ nhanh', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold, color: Color(0xFF1A1A1A))),
                  Text('Chuẩn giá niêm yết', style: TextStyle(color: Color(0xFF1565C0), fontWeight: FontWeight.bold, fontSize: 12)),
                ],
              ),
              const SizedBox(height: 12),

              // Detailed Service Cards Grid
              GridView.count(
                crossAxisCount: 2,
                shrinkWrap: true,
                physics: const NeverScrollableScrollPhysics(),
                mainAxisSpacing: 12,
                crossAxisSpacing: 12,
                childAspectRatio: 0.82,
                children: [
                  _DetailedServiceCard(
                    icon: Icons.donut_large,
                    title: 'Vá vỏ / Thay lốp',
                    eta: '15-20 Phút',
                    desc: 'Xử lý lốp cán đinh, nổ lốp, thay lốp sơ-cua',
                    price: '150.000đ',
                    onTap: () => Navigator.pushNamed(context, '/request'),
                  ),
                  _DetailedServiceCard(
                    icon: Icons.bolt,
                    title: 'Kích bình ắc quy',
                    eta: '10-15 Phút',
                    desc: 'Kích nguồn 12V/24V hoặc thay ắc quy mới GS/Đốn...',
                    price: '120.000đ',
                    onTap: () => Navigator.pushNamed(context, '/request'),
                  ),
                  _DetailedServiceCard(
                    icon: Icons.local_gas_station,
                    title: 'Tiếp xăng / Dầu',
                    eta: 'Giao tận nơi',
                    desc: 'Tiếp ứng xăng Ron 95-V, Diesel tận xe trên cao tốc',
                    price: '15 Phút',
                    isTime: true,
                    onTap: () => Navigator.pushNamed(context, '/request'),
                  ),
                  _DetailedServiceCard(
                    icon: Icons.car_repair,
                    title: 'Kéo xe chuyên dụng',
                    eta: '24/7',
                    desc: 'Xe sàn trượt hạ gầm & cẩu 3 chân cứu hộ va chạm',
                    price: '600.000đ',
                    isTheoKm: true,
                    onTap: () => Navigator.pushNamed(context, '/request'),
                  ),
                ],
              ),
              const SizedBox(height: 24),

              // Safety Tips Card
              Container(
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(20),
                  boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.03), blurRadius: 6, offset: const Offset(0, 2))],
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    ClipRRect(
                      borderRadius: const BorderRadius.vertical(top: Radius.circular(20)),
                      child: Container(
                        height: 120,
                        width: double.infinity,
                        color: const Color(0xFF1A1A1A),
                        child: Stack(
                          fit: StackFit.expand,
                          children: [
                            Container(
                              decoration: const BoxDecoration(
                                gradient: LinearGradient(
                                  colors: [Colors.black54, Colors.black87],
                                  begin: Alignment.topCenter,
                                  end: Alignment.bottomCenter,
                                ),
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.all(16),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                mainAxisAlignment: MainAxisAlignment.end,
                                children: [
                                  Row(
                                    children: const [
                                      Icon(Icons.warning, color: Color(0xFFF5A623), size: 16),
                                      SizedBox(width: 6),
                                      Text('CẨM NANG AN TOÀN TÍNH MẠNG', style: TextStyle(color: Color(0xFFF5A623), fontSize: 11, fontWeight: FontWeight.bold)),
                                    ],
                                  ),
                                  const SizedBox(height: 4),
                                  const Text('3 Bước tự bảo vệ khi dừng xe trên cao tốc', style: TextStyle(color: Colors.white, fontSize: 15, fontWeight: FontWeight.bold)),
                                ],
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(16),
                      child: Column(
                        children: const [
                          _SafetyStep(step: '1', title: 'Bật đèn khẩn cấp (Hazard) & Đỗ sát lề phải', desc: 'Đánh lái vào làn dừng khẩn cấp hoặc mép đường phụ'),
                          SizedBox(height: 12),
                          _SafetyStep(step: '2', title: 'Đặt tam giác phản quang cách đuôi xe 100m - 150m', desc: 'Cảnh báo từ xa cho phương tiện chạy tốc độ cao'),
                          SizedBox(height: 12),
                          _SafetyStep(step: '3', title: 'Đứng ngoài dải hộ lan tôn sóng an toàn', desc: 'Tuyệt đối không ngồi trong cabin khi xe hỏng trên cao tốc'),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 24),

              // Commitment Card
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: const Color(0xFFE8F1FB),
                  borderRadius: BorderRadius.circular(20),
                  border: Border.all(color: const Color(0xFF1565C0).withOpacity(0.2)),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: const [
                        Icon(Icons.verified, color: Color(0xFF1565C0), size: 20),
                        SizedBox(width: 8),
                        Text('Cam kết dịch vụ ResQ247', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 15, color: Color(0xFF1A1A1A))),
                      ],
                    ),
                    const SizedBox(height: 4),
                    const Text('Bảo vệ quyền lợi tối đa cho tài xế', style: TextStyle(fontSize: 12, color: Color(0xFF6B7280))),
                    const SizedBox(height: 14),
                    Row(
                      children: [
                        Expanded(
                          child: Container(
                            padding: const EdgeInsets.all(12),
                            decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(12)),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: const [
                                Text('15 - 30', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 18, color: Color(0xFF1565C0))),
                                Text('Phút', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: Color(0xFF1565C0))),
                                SizedBox(height: 2),
                                Text('Tốc độ tiếp cận', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                              ],
                            ),
                          ),
                        ),
                        const SizedBox(width: 10),
                        Expanded(
                          child: Container(
                            padding: const EdgeInsets.all(12),
                            decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(12)),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: const [
                                Text('Minh bạch', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16, color: Color(0xFF1A1A1A))),
                                SizedBox(height: 2),
                                Text('Báo giá trước 100%', style: TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
                              ],
                            ),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 10),
                    Container(
                      padding: const EdgeInsets.all(12),
                      decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(12)),
                      child: Row(
                        children: [
                          const Icon(Icons.shield_outlined, color: Color(0xFF1565C0), size: 20),
                          const SizedBox(width: 8),
                          const Expanded(
                            child: Text('Liên kết Cảnh Sát Giao Thông &\nĐội Tuần Tra Cao Tốc', style: TextStyle(fontSize: 11, color: Color(0xFF1A1A1A), fontWeight: FontWeight.bold)),
                          ),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
                            decoration: BoxDecoration(color: const Color(0xFFEEF0FA), borderRadius: BorderRadius.circular(100)),
                            child: const Text('1900 6868', style: TextStyle(color: Color(0xFF1565C0), fontWeight: FontWeight.bold, fontSize: 11)),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 24),

              // Bottom Hotline Banner
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: const Color(0xFF0B1E33),
                  borderRadius: BorderRadius.circular(20),
                ),
                child: Row(
                  children: [
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: const [
                          Text('ĐƯỜNG DÂY NÓNG HỖ TRỢ XE', style: TextStyle(color: Color(0xFFF5A623), fontSize: 10, fontWeight: FontWeight.bold)),
                          SizedBox(height: 4),
                          Text('1900 6868', style: TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold)),
                          SizedBox(height: 4),
                          Text('Phục vụ 24/7 toàn quốc\n- Kể cả mưa bão', style: TextStyle(color: Colors.white70, fontSize: 11)),
                        ],
                      ),
                    ),
                    ElevatedButton(
                      onPressed: () {},
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color(0xFFC1121F),
                        foregroundColor: Colors.white,
                        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(100)),
                        padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 12),
                      ),
                      child: Row(
                        children: const [
                          Text('Gọi ngay', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13)),
                          SizedBox(width: 4),
                          Icon(Icons.arrow_forward, size: 14),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 16),
            ],
          ),
        ),
      ),
      bottomNavigationBar: Container(
        padding: const EdgeInsets.symmetric(vertical: 8),
        decoration: BoxDecoration(
          color: Colors.white,
          boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.05), blurRadius: 10, offset: const Offset(0, -2))],
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            _NavItem(icon: Icons.home, label: 'Trang chủ', isSelected: true, onTap: () {}),
            _NavItem(icon: Icons.car_crash, label: 'Cứu hộ', badge: '1', onTap: () => Navigator.pushNamed(context, '/request')),
            _NavItem(icon: Icons.radar, label: 'Đang xử lý', onTap: () => Navigator.pushNamed(context, '/tracking')),
            _NavItem(icon: Icons.history, label: 'Lịch sử', onTap: () => Navigator.pushNamed(context, '/history')),
            _NavItem(icon: Icons.person, label: 'Tài khoản', onTap: () => Navigator.pushNamed(context, '/login')),
          ],
        ),
      ),
    );
  }
}

class _DetailedServiceCard extends StatelessWidget {
  final IconData icon;
  final String title;
  final String eta;
  final String desc;
  final String price;
  final bool isTime;
  final bool isTheoKm;
  final VoidCallback onTap;

  const _DetailedServiceCard({
    required this.icon,
    required this.title,
    required this.eta,
    required this.desc,
    required this.price,
    this.isTime = false,
    this.isTheoKm = false,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(16),
      child: Container(
        padding: const EdgeInsets.all(14),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(16),
          boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.03), blurRadius: 6, offset: const Offset(0, 2))],
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Container(
                  padding: const EdgeInsets.all(8),
                  decoration: BoxDecoration(color: const Color(0xFFEEF0FA), borderRadius: BorderRadius.circular(10)),
                  child: Icon(icon, color: const Color(0xFF1565C0), size: 20),
                ),
                Container(
                  padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
                  decoration: BoxDecoration(color: const Color(0xFFE8F1FB), borderRadius: BorderRadius.circular(100)),
                  child: Text(eta, style: const TextStyle(color: Color(0xFF1565C0), fontSize: 10, fontWeight: FontWeight.bold)),
                ),
              ],
            ),
            const SizedBox(height: 10),
            Text(title, style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: Color(0xFF1A1A1A))),
            const SizedBox(height: 4),
            Text(desc, maxLines: 2, overflow: TextOverflow.ellipsis, style: const TextStyle(fontSize: 10, color: Color(0xFF6B7280))),
            const Spacer(),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(isTime ? 'Tốc độ' : (isTheoKm ? 'Theo km' : 'Chỉ từ'), style: const TextStyle(fontSize: 10, color: Color(0xFF6B7280))),
                Text(price, style: TextStyle(fontWeight: FontWeight.bold, fontSize: 13, color: isTime || isTheoKm ? const Color(0xFF1565C0) : const Color(0xFFC1121F))),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class _SafetyStep extends StatelessWidget {
  final String step;
  final String title;
  final String desc;

  const _SafetyStep({required this.step, required this.title, required this.desc});

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Container(
          width: 24,
          height: 24,
          decoration: const BoxDecoration(color: Color(0xFFFBE4DC), shape: BoxShape.circle),
          alignment: Alignment.center,
          child: Text(step, style: const TextStyle(color: Color(0xFFC1121F), fontWeight: FontWeight.bold, fontSize: 12)),
        ),
        const SizedBox(width: 12),
        Expanded(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(title, style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 12, color: Color(0xFF1A1A1A))),
              const SizedBox(height: 2),
              Text(desc, style: const TextStyle(fontSize: 11, color: Color(0xFF6B7280))),
            ],
          ),
        ),
      ],
    );
  }
}

class _NavItem extends StatelessWidget {
  final IconData icon;
  final String label;
  final String? badge;
  final bool isSelected;
  final VoidCallback onTap;

  const _NavItem({required this.icon, required this.label, this.badge, this.isSelected = false, required this.onTap});

  @override
  Widget build(BuildContext context) {
    final color = isSelected ? const Color(0xFFC1121F) : const Color(0xFF6B7280);
    return InkWell(
      onTap: onTap,
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Stack(
            children: [
              Icon(icon, color: color, size: 22),
              if (badge != null)
                Positioned(
                  right: 0,
                  top: 0,
                  child: Container(
                    padding: const EdgeInsets.all(2),
                    decoration: const BoxDecoration(color: Color(0xFFC1121F), shape: BoxShape.circle),
                    constraints: const BoxConstraints(minWidth: 12, minHeight: 12),
                    child: Text(badge!, style: const TextStyle(color: Colors.white, fontSize: 8, fontWeight: FontWeight.bold), textAlign: TextAlign.center),
                  ),
                ),
            ],
          ),
          const SizedBox(height: 2),
          Text(label, style: TextStyle(color: color, fontSize: 10, fontWeight: isSelected ? FontWeight.bold : FontWeight.normal)),
        ],
      ),
    );
  }
}
