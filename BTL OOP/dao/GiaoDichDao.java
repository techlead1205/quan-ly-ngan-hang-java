package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.GiaoDich;

public class GiaoDichDao {
    private String dbUrl = "jdbc:mysql://localhost:3306/quan_ly_ngan_hang";
    private String dbUser = "root";
    private String dbPassword = "123456";

    // Ham ket noi dung chung
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // 1. HAM MOI THEM: Luu lich su vao bang giao_dich
    public boolean luuGiaoDich(GiaoDich gd) {
        boolean isSuccess = false;
        String sql = "INSERT INTO giao_dich (tai_khoan_id, nhan_vien_id, loai_giao_dich, so_tien, ghi_chu) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setInt(1, gd.getTaiKhoanId());
            pst.setInt(2, gd.getNhanVienId());
            pst.setString(3, gd.getLoaiGiaoDich());
            pst.setDouble(4, gd.getSoTien());
            pst.setString(5, gd.getGhiChu());
            
            int rowAffected = pst.executeUpdate(); 
            if (rowAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("[-] Loi luu lich su giao dich: " + e.getMessage());
        }
        return isSuccess;
    }
    
    // 2. Ham in sao ke
    public List<GiaoDich> getLichSuBySoTaiKhoan(String soTaiKhoan) {
        List<GiaoDich> ds = new ArrayList<>();
        String sql = "SELECT gd.* FROM giao_dich gd JOIN tai_khoan tk ON gd.tai_khoan_id = tk.id "
                   + "WHERE tk.so_tai_khoan = ? ORDER BY gd.ngay_giao_dich DESC";
                   
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, soTaiKhoan);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    GiaoDich gd = new GiaoDich(
                        rs.getInt("id"),
                        rs.getInt("tai_khoan_id"),
                        rs.getInt("nhan_vien_id"),
                        rs.getString("loai_giao_dich"),
                        rs.getDouble("so_tien"),
                        rs.getTimestamp("ngay_giao_dich"),
                        rs.getString("ghi_chu")
                    );
                    ds.add(gd);
                }
            }
        } catch (Exception e) {
            System.out.println("[-] Loi lay sao ke: " + e.getMessage());
        }
        return ds;
    }

    // 3. Ham chot ca
    public void baoCaoChotCaNgayHomNay() {
        String sql = "SELECT loai_giao_dich, SUM(so_tien) as tong_tien, COUNT(id) as so_luong "
                   + "FROM giao_dich WHERE DATE(ngay_giao_dich) = CURDATE() GROUP BY loai_giao_dich";
                   
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
             
            System.out.println("--------------------------------------------------");
            System.out.printf("%-15s | %-10s | %-15s\n", "Loai Giao Dich", "So Luong", "Tong Tien (VND)");
            System.out.println("--------------------------------------------------");
            
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("%-15s | %-10d | %-15d\n", 
                    rs.getString("loai_giao_dich"), 
                    rs.getInt("so_luong"), 
                    (long)rs.getDouble("tong_tien"));
            }
            if (!hasData) System.out.println("[-] Hom nay chi nhanh chua co giao dich nao.");
            System.out.println("--------------------------------------------------");
            
        } catch (Exception e) {
            System.out.println("[-] Loi chot ca: " + e.getMessage());
        }
    }
}