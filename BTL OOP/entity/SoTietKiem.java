package entity;

import java.sql.Timestamp;

public class SoTietKiem {
    private int id;
    private int khachHangId;
    private double soTienGoc;
    private int kyHan;
    private double laiSuat;
    private Timestamp ngayGui;
    private int trangThai;
    private int nhanVienId;

    // Them Constructor rong (Chuan OOP)
    public SoTietKiem() {}

    // Pheu 1: Dung de map du lieu tu Database len (Full 8 tham so)
    public SoTietKiem(int id, int khachHangId, double soTienGoc, int kyHan, double laiSuat, Timestamp ngayGui, int trangThai, int nhanVienId) {
        this.id = id;
        this.khachHangId = khachHangId;
        this.soTienGoc = soTienGoc;
        this.kyHan = kyHan;
        this.laiSuat = laiSuat;
        this.ngayGui = ngayGui;
        this.trangThai = trangThai;
        this.nhanVienId = nhanVienId;
    }

    // Pheu 2: Dung de tao So moi o Menu Giao Dich Vien (Bo id va ngay_gui)
    public SoTietKiem(int khachHangId, double soTienGoc, int kyHan, double laiSuat, int trangThai, int nhanVienId) {
        this.khachHangId = khachHangId;
        this.soTienGoc = soTienGoc;
        this.kyHan = kyHan;
        this.laiSuat = laiSuat;
        this.trangThai = trangThai;
        this.nhanVienId = nhanVienId;
    }

    // --- GETTERS VA SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getKhachHangId() { return khachHangId; }
    public void setKhachHangId(int khachHangId) { this.khachHangId = khachHangId; }

    public double getSoTienGoc() { return soTienGoc; }
    public void setSoTienGoc(double soTienGoc) { this.soTienGoc = soTienGoc; }

    public int getKyHan() { return kyHan; }
    public void setKyHan(int kyHan) { this.kyHan = kyHan; }

    public double getLaiSuat() { return laiSuat; }
    public void setLaiSuat(double laiSuat) { this.laiSuat = laiSuat; }

    public Timestamp getNgayGui() { return ngayGui; }
    public void setNgayGui(Timestamp ngayGui) { this.ngayGui = ngayGui; }

    public int getTrangThai() { return trangThai; }
    public void setTrangThai(int trangThai) { this.trangThai = trangThai; }

    public int getNhanVienId() { return nhanVienId; }
    public void setNhanVienId(int nhanVienId) { this.nhanVienId = nhanVienId; }
    

    // Them ham in thong tin de test
    @Override
    public String toString() {
        return "SoTietKiem{" +
                "id=" + id +
                ", khachHangId=" + khachHangId +
                ", soTienGoc=" + soTienGoc +
                ", kyHan=" + kyHan +
                ", laiSuat=" + laiSuat +
                ", ngayGui=" + ngayGui +
                ", trangThai=" + trangThai +
                '}';
    }
}