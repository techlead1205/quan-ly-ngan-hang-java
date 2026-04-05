package entity;

import java.sql.Timestamp;

public class TaiKhoan {
    private int id;
    private String soTaiKhoan;
    private int khachHangId;
    private double soDu;
    private int trangThai;
    private Timestamp ngayMo;
    private int nhanVienId;

    // Them constructor rong (Chuan OOP)
    public TaiKhoan() {}

    // Pheu 1: Dung khi lay du lieu tu DB len (Full 7 tham so)
    public TaiKhoan(int id, String soTaiKhoan, int khachHangId, double soDu, int trangThai, Timestamp ngayMo, int nhanVienId) {
        this.id = id;
        this.soTaiKhoan = soTaiKhoan;
        this.khachHangId = khachHangId;
        this.soDu = soDu;
        this.trangThai = trangThai;
        this.ngayMo = ngayMo;
        this.nhanVienId = nhanVienId;
    }

    // Pheu 2: Dung khi Giao dich vien tao tai khoan moi (Chi can 3 thong tin)
    // (id, ngayMo tu dong sinh. So du ban dau luon la 0d nen khong can truyen)
    public TaiKhoan(String soTaiKhoan, int khachHangId, int trangThai, int nhanVienId) {
        this.soTaiKhoan = soTaiKhoan;
        this.khachHangId = khachHangId;
        this.trangThai = trangThai;
        this.nhanVienId = nhanVienId;
    }

    public int getId() { return id; }
    // Bo sung setId
    public void setId(int id) { this.id = id; }
    
    public String getSoTaiKhoan() { return soTaiKhoan; }
    public void setSoTaiKhoan(String soTaiKhoan) { this.soTaiKhoan = soTaiKhoan; }

    public int getKhachHangId() { return khachHangId; }
    // Bo sung setKhachHangId
    public void setKhachHangId(int khachHangId) { this.khachHangId = khachHangId; }
    
    public double getSoDu() { return soDu; }
    public void setSoDu(double soDu) { this.soDu = soDu; }

    public int getTrangThai() { return trangThai; }
    public void setTrangThai(int trangThai) { this.trangThai = trangThai; }

    public Timestamp getNgayMo() { return ngayMo; }
    // Bo sung setNgayMo
    public void setNgayMo(Timestamp ngayMo) { this.ngayMo = ngayMo; }

    public int getNhanVienId() { return nhanVienId; }
    public void setNhanVienId(int nhanVienId) { this.nhanVienId = nhanVienId; }

    // Ham in thong tin de test
    @Override
    public String toString() {
        return "TaiKhoan{" +
                "id=" + id +
                ", soTaiKhoan='" + soTaiKhoan + '\'' +
                ", khachHangId=" + khachHangId +
                ", soDu=" + soDu +
                ", trangThai=" + trangThai +
                ", ngayMo=" + ngayMo +
                '}';
    }
}