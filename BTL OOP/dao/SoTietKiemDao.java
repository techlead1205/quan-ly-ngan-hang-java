package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.SoTietKiem;

public class SoTietKiemDao {
    private String dbUrl = "jdbc:mysql://localhost:3306/quan_ly_ngan_hang";
    private String dbUser = "root";
    private String dbPassword = "123456";

    // Ham ket noi dung chung
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Ham mo so tiet kiem moi
    public boolean addSoTietKiem(SoTietKiem stk) {
        boolean isSuccess = false;
        String sql = "INSERT INTO so_tiet_kiem (khach_hang_id, so_tien_goc, ky_han, lai_suat, trang_thai, nhan_vien_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setInt(1, stk.getKhachHangId());
            pst.setDouble(2, stk.getSoTienGoc());
            pst.setInt(3, stk.getKyHan());
            pst.setDouble(4, stk.getLaiSuat());
            pst.setInt(5, stk.getTrangThai());
            pst.setInt(6, stk.getNhanVienId());
            
            if (pst.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("[-] Loi mo so tiet kiem: " + e.getMessage());
        }
        return isSuccess;
    }

    // Ham tat toan so tiet kiem (Tinh lai + Dong so)
    public double tatToan(int idSo) {
        double tongTien = -1; 
        String sqlSelect = "SELECT so_tien_goc, lai_suat, ngay_gui FROM so_tiet_kiem WHERE id = ? AND trang_thai = 1";
        String sqlUpdate = "UPDATE so_tiet_kiem SET trang_thai = 0 WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstSelect = conn.prepareStatement(sqlSelect)) {
             
            pstSelect.setInt(1, idSo);
            
            try (ResultSet rs = pstSelect.executeQuery()) {
                if (rs.next()) {
                    double tienGoc = rs.getDouble("so_tien_goc");
                    double laiSuat = rs.getDouble("lai_suat");
                    java.sql.Timestamp ngayGui = rs.getTimestamp("ngay_gui");
                    
                    long diffInMillis = System.currentTimeMillis() - ngayGui.getTime();
                    long soNgayGui = diffInMillis / (1000 * 60 * 60 * 24); 
                    
                    if (soNgayGui == 0) soNgayGui = 1;
                    
                    double tienLai = (tienGoc * laiSuat * soNgayGui) / 365;
                    tongTien = tienGoc + tienLai;
                    
                    // Cap nhat trang thai so thanh 0
                    try (PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate)) {
                        pstUpdate.setInt(1, idSo);
                        pstUpdate.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("[-] Loi tat toan so: " + e.getMessage());
        }
        return tongTien;
    }

    // Ham tra cuu tien lai du tinh (Chi tinh lai, khong dong so)
    public double traCuuTienLaiDuTinh(int idSo) {
        double tienLai = -1;
        String sqlSelect = "SELECT so_tien_goc, lai_suat, ngay_gui FROM so_tiet_kiem WHERE id = ? AND trang_thai = 1";
        
        try (Connection conn = getConnection();
             PreparedStatement pstSelect = conn.prepareStatement(sqlSelect)) {
             
            pstSelect.setInt(1, idSo);
            
            try (ResultSet rs = pstSelect.executeQuery()) {
                if (rs.next()) {
                    double tienGoc = rs.getDouble("so_tien_goc");
                    double laiSuat = rs.getDouble("lai_suat");
                    java.sql.Timestamp ngayGui = rs.getTimestamp("ngay_gui");
                    
                    long diffInMillis = System.currentTimeMillis() - ngayGui.getTime();
                    long soNgayGui = diffInMillis / (1000 * 60 * 60 * 24); 
                    
                    if (soNgayGui == 0) soNgayGui = 1;
                    
                    tienLai = (tienGoc * laiSuat * soNgayGui) / 365;
                }
            }
        } catch (Exception e) {
            System.out.println("[-] Loi tra cuu lai: " + e.getMessage());
        }
        return tienLai;
    }
}