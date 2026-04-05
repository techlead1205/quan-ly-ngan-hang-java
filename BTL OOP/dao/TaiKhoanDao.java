package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.TaiKhoan;

public class TaiKhoanDao {
    private String dbUrl = "jdbc:mysql://localhost:3306/quan_ly_ngan_hang";
    private String dbUser = "root";
    private String dbPassword = "123456";

    // Ham ket noi dung chung
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Ham mo tai khoan (them the moi)
    public boolean addTaiKhoan(TaiKhoan tk) {
        boolean isSuccess = false;
        String sql = "INSERT INTO tai_khoan (so_tai_khoan, khach_hang_id, trang_thai, nhan_vien_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, tk.getSoTaiKhoan());
            pst.setInt(2, tk.getKhachHangId());
            pst.setInt(3, tk.getTrangThai());
            pst.setInt(4, tk.getNhanVienId());
            
            int rowAffected = pst.executeUpdate(); 
            if (rowAffected > 0) {
                isSuccess = true; 
            }
        } catch (Exception e) {
            System.out.println("[-] Loi mo tai khoan: " + e.getMessage());
        }
        return isSuccess;
    }

    // Ham nap tien
    public boolean napTien(String soTK, double soTienNap) {
        boolean isSuccess = false;
        String sql = "UPDATE tai_khoan SET so_du = so_du + ? WHERE so_tai_khoan = ? AND trang_thai = 1";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setDouble(1, soTienNap);
            pst.setString(2, soTK);
            
            int rowAffected = pst.executeUpdate(); 
            if (rowAffected > 0) {
                isSuccess = true; 
            }
        } catch (Exception e) {
            System.out.println("[-] Loi nap tien: " + e.getMessage());
        }
        return isSuccess;
    }

    // Ham rut tien 
    public boolean rutTien(String soTK, double soTienRut) {
        boolean isSuccess = false;
        String sql = "UPDATE tai_khoan SET so_du = so_du - ? WHERE so_tai_khoan = ? AND trang_thai = 1 AND so_du >= ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setDouble(1, soTienRut); 
            pst.setString(2, soTK);      
            pst.setDouble(3, soTienRut); 
            
            int rowAffected = pst.executeUpdate(); 
            if (rowAffected > 0) {
                isSuccess = true; 
            }
        } catch (Exception e) {
            System.out.println("[-] Loi rut tien: " + e.getMessage());
        }
        return isSuccess;
    }

    // Ham chuyen khoan noi bo (Ap dung Transaction)
    public boolean chuyenKhoan(String tkGui, String tkNhan, double soTien) {
        boolean isSuccess = false;
        Connection conn = null;
        try {
            conn = getConnection();
            
            // 1. TAT AUTO-COMMIT
            conn.setAutoCommit(false); 
            
            // 2. TRU TIEN TAI KHOAN GUI 
            String sqlTru = "UPDATE tai_khoan SET so_du = so_du - ? WHERE so_tai_khoan = ? AND trang_thai = 1 AND so_du >= ?";
            PreparedStatement pstTru = conn.prepareStatement(sqlTru);
            pstTru.setDouble(1, soTien);
            pstTru.setString(2, tkGui);
            pstTru.setDouble(3, soTien);
            int dongTru = pstTru.executeUpdate();
            
            // 3. CONG TIEN TAI KHOAN NHAN 
            String sqlCong = "UPDATE tai_khoan SET so_du = so_du + ? WHERE so_tai_khoan = ? AND trang_thai = 1";
            PreparedStatement pstCong = conn.prepareStatement(sqlCong);
            pstCong.setDouble(1, soTien);
            pstCong.setString(2, tkNhan);
            int dongCong = pstCong.executeUpdate();
            
            // 4. PHAN QUYET
            if (dongTru > 0 && dongCong > 0) {
                conn.commit(); 
                isSuccess = true;
            } else {
                conn.rollback(); 
            }
            
            pstTru.close();
            pstCong.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("[-] Loi chuyen khoan: " + e.getMessage());
            try {
                // Neu xay ra loi exception (vi du mat mang), cung phai rollback
                if (conn != null) conn.rollback(); 
            } catch (Exception ex) {}
        }
        return isSuccess;
    }

    // Ham khoa / mo khoa the khan cap (Nghiep vu)
    public boolean khoaMoKhoaThe(String soTK) {
        boolean isSuccess = false;
        // Meo SQL: Dung 1 - trang_thai de dao nguoc gia tri (Neu 1 thi thanh 0, neu 0 thi thanh 1)
        String sql = "UPDATE tai_khoan SET trang_thai = 1 - trang_thai WHERE so_tai_khoan = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, soTK);
            
            int rowAffected = pst.executeUpdate();
            if (rowAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("[-] Loi thay doi trang thai the: " + e.getMessage());
        }
        return isSuccess;
    }

    // Ham ho tro: Lay ID cua Tai khoan thong qua so tai khoan chuoi
    public int getIdBySoTaiKhoan(String soTK) {
        int id = -1;
        String sql = "SELECT id FROM tai_khoan WHERE so_tai_khoan = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, soTK);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            System.out.println("[-] Loi tim ID tai khoan: " + e.getMessage());
        }
        return id;
    }
}