package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import entity.KhachHang;

public class KhachHangDao {
    private String dbUrl = "jdbc:mysql://localhost:3306/quan_ly_ngan_hang";
    private String dbUser = "root";
    private String dbPassword = "123456";

    // Ham ket noi dung chung de giam bot code lap lai
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Ham mo ho so khach hang moi
    public boolean addKhachHang(KhachHang kh, int idNhanVien) {
        boolean isSuccess = false;
        String sql = "INSERT INTO khach_hang (ho_ten, cccd, so_dien_thoai, dia_chi, nguoi_tao_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, kh.getHoTen());
            pst.setString(2, kh.getCccd());
            pst.setString(3, kh.getSoDienThoai());
            pst.setString(4, kh.getDiaChi());
            pst.setInt(5, idNhanVien);
            
            int rowAffected = pst.executeUpdate(); 
            if (rowAffected > 0) {
                isSuccess = true; 
            }
            
        } catch (SQLIntegrityConstraintViolationException e) {
            // Bat loi trung lap du lieu
            System.out.println("[-] LOI: CCCD hoac So dien thoai da ton tai trong he thong!");
        } catch (Exception e) {
            System.out.println("[-] Loi them khach hang: " + e.getMessage());
        }
        
        return isSuccess;
    }

    // Ham tim kiem khach hang bang CCCD hoac So dien thoai
    public KhachHang searchKhachHang(String keyword) {
        KhachHang kh = null;
        String sql = "SELECT * FROM khach_hang WHERE cccd = ? OR so_dien_thoai = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, keyword);
            pst.setString(2, keyword);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Da cap nhat de lay them tham so thu 7 (nguoi_tao_id)
                    kh = new KhachHang(
                        rs.getInt("id"),
                        rs.getString("ho_ten"),
                        rs.getString("cccd"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getTimestamp("ngay_tao"),
                        rs.getInt("nguoi_tao_id") // Them vao day
                    );
                }
            }
            
        } catch (Exception e) {
            System.out.println("[-] Loi tim kiem khach hang: " + e.getMessage());
        }
        
        return kh; 
    }

    // Tinh tong tai san (The ATM + So tiet kiem) cua 1 khach hang
    public double getTongTaiSan(int khachHangId) {
        double tongTien = 0;
        String sqlTaiKhoan = "SELECT SUM(so_du) as tong_atm FROM tai_khoan WHERE khach_hang_id = ? AND trang_thai = 1";
        String sqlSoTietKiem = "SELECT SUM(so_tien_goc) as tong_so FROM so_tiet_kiem WHERE khach_hang_id = ? AND trang_thai = 1";
        
        // Dung 1 ket noi (conn) cho ca 2 cau truy van de tiet kiem tai nguyen
        try (Connection conn = getConnection()) {
            
            // 1. Gom tien tu the ATM
            try (PreparedStatement pst1 = conn.prepareStatement(sqlTaiKhoan)) {
                pst1.setInt(1, khachHangId);
                try (ResultSet rs1 = pst1.executeQuery()) {
                    if (rs1.next()) {
                        tongTien += rs1.getDouble("tong_atm");
                    }
                }
            }
            
            // 2. Gom tien goc tu cac So tiet kiem dang mo
            try (PreparedStatement pst2 = conn.prepareStatement(sqlSoTietKiem)) {
                pst2.setInt(1, khachHangId);
                try (ResultSet rs2 = pst2.executeQuery()) {
                    if (rs2.next()) {
                        tongTien += rs2.getDouble("tong_so");
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("[-] Loi tinh tong tai san: " + e.getMessage());
        }
        
        return tongTien;
    }
}