package dao;

import entity.NhanVien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao {

    private String dbUrl = "jdbc:mysql://localhost:3306/quan_ly_ngan_hang";
    private String dbUser = "root"; 
    private String dbPassword = "123456"; 

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Ham kiem tra dang nhap
    public NhanVien checkLogin(String username, String password) { 
        NhanVien nv = null;
        String sql = "SELECT * FROM nhan_vien WHERE username = ? AND password = ?"; 
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) { 
                    int id = rs.getInt("id");
                    String name = rs.getString("ho_ten");
                    int role = rs.getInt("role");
                    int status = rs.getInt("trang_thai");
                    nv = new NhanVien(id, name, username, password, role, status);
                } 
            } 
            
        } catch (Exception e) {
            System.out.println("Loi ket noi CSDL: " + e.getMessage());
        }
        return nv; 
    }

    // Ham doi mat khau
    public boolean changePassword(int id, String newPassword) {
        boolean isSuccess = false;
        String sql = "UPDATE nhan_vien SET password = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, newPassword);
            pst.setInt(2, id);
            
            int rowAffected = pst.executeUpdate();
            if (rowAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("[-] Loi doi mat khau: " + e.getMessage());
        }
        return isSuccess;
    }

    // Ham lay danh sach tat ca nhan vien
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> danhSach = new ArrayList<>(); 
        String sql = "SELECT * FROM nhan_vien ORDER BY trang_thai DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
             
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("ho_ten");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role = rs.getInt("role");
                int status = rs.getInt("trang_thai");
                
                NhanVien nv = new NhanVien(id, name, username, password, role, status);
                danhSach.add(nv); 
            }
        } catch (Exception e) {
            System.out.println("Loi ket noi: " + e.getMessage());
        }
        return danhSach;
    }

    // Ham them nhan vien moi
    public boolean addNhanVien(NhanVien nv) {
        boolean isSuccess = false; 
        String sql = "INSERT INTO nhan_vien (ho_ten, username, password, role, trang_thai) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nv.getName());
            pst.setString(2, nv.getUsername()); 
            pst.setString(3, nv.getPassword());
            pst.setInt(4, nv.getRole());
            pst.setInt(5, nv.getStatus());
            
            int rowAffected = pst.executeUpdate(); 
            if (rowAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("[-] Loi them nhan vien: " + e.getMessage());
        }
        return isSuccess;
    }

    // Ham xoa mem nhan vien
    public boolean deleteNhanVien(int id) {
        boolean isSuccess = false;
        String sql = "UPDATE nhan_vien SET trang_thai = 0 WHERE id = ? AND role = 2";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setInt(1, id);
            
            int rowAffected = pst.executeUpdate(); 
            if (rowAffected > 0) {
                isSuccess = true; 
            }
        } catch (Exception e) {
            System.out.println("[-] Loi xoa nhan vien: " + e.getMessage());
        }
        return isSuccess;
    }


    // Ham xem KPI theo thang hien tai 
    public boolean inKPIThangHienTai(int nhanVienId) {
        java.time.LocalDate today = java.time.LocalDate.now();
        int thangNay = today.getMonthValue(); 
        int namNay = today.getYear();
        
        String sqlCheckNhanVien = "SELECT ho_ten, trang_thai FROM nhan_vien WHERE id = ?";
        
        String sqlKhach = "SELECT COUNT(id) as kpi_khach FROM khach_hang "
                            + "WHERE nguoi_tao_id = ? AND MONTH(ngay_tao) = MONTH(CURDATE()) AND YEAR(ngay_tao) = YEAR(CURDATE())";
        
        String sqlGD = "SELECT COUNT(id) as kpi_gd, "
                         + "SUM(CASE WHEN loai_giao_dich = 'NAP_TIEN' THEN so_tien "
                         + "         WHEN loai_giao_dich = 'RUT_TIEN' THEN -so_tien "
                         + "         ELSE 0 END) as tong_tien_gd "
                         + "FROM giao_dich "
                         + "WHERE nhan_vien_id = ? "
                         + "AND loai_giao_dich IN ('NAP_TIEN', 'RUT_TIEN') "
                         + "AND MONTH(ngay_giao_dich) = MONTH(CURDATE()) AND YEAR(ngay_giao_dich) = YEAR(CURDATE())";
                         
        String sqlSTK = "SELECT COUNT(id) as kpi_stk, SUM(so_tien_goc) as tong_tien_stk FROM so_tiet_kiem "
                          + "WHERE nhan_vien_id = ? AND MONTH(ngay_gui) = MONTH(CURDATE()) AND YEAR(ngay_gui) = YEAR(CURDATE())";

        try (Connection conn = getConnection()) { 
            
            // 0. Kiem tra thong tin va trang thai nhan vien
            try (PreparedStatement pstCheck = conn.prepareStatement(sqlCheckNhanVien)) {
                pstCheck.setInt(1, nhanVienId);
                try (ResultSet rsCheck = pstCheck.executeQuery()) {
                    if (!rsCheck.next()) {
                        System.out.println("[-] LOI: Khong tim thay nhan vien voi ID " + nhanVienId + " tren he thong!");
                        return false; // Tra ve false de View biet ma bat nhap lai
                    }
                    
                    String hoTen = rsCheck.getString("ho_ten");
                    int trangThai = rsCheck.getInt("trang_thai");
                    
                    System.out.println("--------------------------------------------------");
                    System.out.println("[+] BAO CAO KPI THANG " + thangNay + "/" + namNay);
                    System.out.println("[-] Nhan vien: " + hoTen + " (ID: " + nhanVienId + ")");
                    
                    if (trangThai == 0) {
                        System.out.println("[!] LUU Y: Nhan vien nay hien DA NGHI VIEC!");
                    }
                }
            }

            // 1. KPI Khach Hang
            try (PreparedStatement pst1 = conn.prepareStatement(sqlKhach)) {
                pst1.setInt(1, nhanVienId);
                try (ResultSet rs1 = pst1.executeQuery()) {
                    if (rs1.next()) {
                        System.out.println("\n[-] So luong ho so Khach hang da mo: " + rs1.getInt("kpi_khach") + " ho so");
                    }
                }
            }
            
            // 2. KPI Giao Dich
            try (PreparedStatement pst2 = conn.prepareStatement(sqlGD)) {
                pst2.setInt(1, nhanVienId);
                try (ResultSet rs2 = pst2.executeQuery()) {
                    if (rs2.next()) {
                        int soLenh = rs2.getInt("kpi_gd");
                        long tongTien = (long) rs2.getDouble("tong_tien_gd"); 
                        System.out.println("[-] So luong giao dich Nap/Rut da xu ly: " + soLenh + " lenh");
                        System.out.println("[-] Tong dong tien thuan (Nap - Rut)   : " + tongTien + " VND");
                    }
                }
            }
            
            // 3. KPI Huy Dong Von
            try (PreparedStatement pst3 = conn.prepareStatement(sqlSTK)) {
                pst3.setInt(1, nhanVienId);
                try (ResultSet rs3 = pst3.executeQuery()) {
                    if (rs3.next()) {
                        int soLuongSo = rs3.getInt("kpi_stk");
                        long tongTienHuyDong = (long) rs3.getDouble("tong_tien_stk");
                        System.out.println("[-] So luong So tiet kiem mo  : " + soLuongSo + " so");
                        System.out.println("[-]    -> Tong tien huy dong     : " + tongTienHuyDong + " VND");
                    }
                }
            }
            System.out.println("--------------------------------------------------");
            return true;
            
        } catch (Exception e) {
            System.out.println("[-] Loi truy xuat KPI: " + e.getMessage());
            return false;
        }
    }







}