# Hệ Thống Quản Lý Ngân Hàng (Core Banking System)

Dự án xây dựng phần mềm quản lý nghiệp vụ lõi của ngân hàng (Core Banking) trên nền tảng Console Application. Dự án áp dụng chặt chẽ các nguyên lý Lập trình hướng đối tượng (OOP) trong Java và thao tác dữ liệu qua JDBC kết nối với MySQL.

## 🚀 Công nghệ sử dụng
* **Ngôn ngữ:** Java (JDK 8+)
* **Cơ sở dữ liệu:** MySQL
* **Kết nối DB:** JDBC (mysql-connector-j)
* **Kiến trúc:** Phân tầng cơ bản (Entity - DAO - View)

## 🎯 Chức năng chính

Hệ thống được chia làm 2 phân hệ quyền hạn rõ ràng, có luồng đăng nhập (Authentication) và theo dõi phiên người dùng (Session Tracking/Audit Trail).

### 1. Phân hệ Quản trị viên (Admin)
* Quản lý danh sách nhân viên (CRUD).
* Thêm/Khóa tài khoản Giao dịch viên (Soft Delete).
* Đánh giá KPI năng suất Giao dịch viên theo thời gian thực (số khách hàng mở, số lượng/tổng tiền luân chuyển, số sổ tiết kiệm huy động trong tháng).

### 2. Phân hệ Giao dịch viên (Teller)
* **Quản lý Khách hàng:** Tìm kiếm, tạo hồ sơ mới, tính toán tổng tài sản thực tế.
* **Quản lý Tài khoản (ATM):** Mở thẻ, Nạp tiền, Rút tiền (có chặn âm quỹ), Chuyển khoản nội bộ (áp dụng Transaction chống lỗi dữ liệu), Khóa/Mở khóa thẻ khẩn cấp.
* **Quản lý Sổ tiết kiệm:** Mở sổ (lãi suất cố định theo kỳ hạn), tra cứu tiền lãi dự tính, tất toán sổ tiết kiệm (cộng gốc + lãi).
* **Nghiệp vụ Báo cáo:** In sao kê lịch sử giao dịch (sắp xếp mới nhất), báo cáo chốt ca cuối ngày (tổng hợp dòng tiền).

## 🗂️ Cấu trúc thư mục (Packages)
* `entity`: Chứa các lớp định nghĩa đối tượng (KhachHang, NhanVien, TaiKhoan, GiaoDich, SoTietKiem).
* `dao`: Data Access Object - Chứa các lớp trực tiếp xử lý câu lệnh SQL (INSERT, UPDATE, SELECT) với Database.
* `view`: Chứa giao diện Console hiển thị Menu và bắt sự kiện nhập liệu từ bàn phím.
* `main`: File `Main.java` khởi chạy chương trình và xử lý luồng đăng nhập.

## 🛠️ Hướng dẫn cài đặt
1. Clone dự án về máy.
2. Import file `database_ngan_hang.sql` vào MySQL Workbench.
3. Import project vào IDE (Eclipse/IntelliJ).
4. Add thư viện `mysql-connector-j` vào Build Path.
5. Đổi `dbPassword` trong các file DAO cho khớp với MySQL của máy local.
6. Chạy file `Main.java`.

---
*Dự án phục vụ mục đích học tập và nghiên cứu quy trình nghiệp vụ ngân hàng thực tế.*
