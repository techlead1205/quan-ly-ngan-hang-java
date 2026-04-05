package entity;

import java.sql.Timestamp;

public class GiaoDich {
    private int id;
    private int taiKhoanId;
    private int nhanVienId;
    private String loaiGiaoDich;
    private double soTien;
    private Timestamp ngayGiaoDich;
    private String ghiChu;

    // Constructor rong
    public GiaoDich() {}

    // Pheu 1: Dung khi lay du lieu LICH SU tu MySQL len (Day du 7 cot)
    public GiaoDich(int id, int taiKhoanId, int nhanVienId, String loaiGiaoDich, double soTien, Timestamp ngayGiaoDich, String ghiChu) {
        this.id = id;
        this.taiKhoanId = taiKhoanId;
        this.nhanVienId = nhanVienId;
        this.loaiGiaoDich = loaiGiaoDich;
        this.soTien = soTien;
        this.ngayGiaoDich = ngayGiaoDich;
        this.ghiChu = ghiChu;
    }

    // Pheu 2: Dung khi Giao dich vien vua thuc hien NAP/RUT/CHUYEN KHOAN xong
    // (Khong can truyen id va ngayGiaoDich vi MySQL tu dong luu thoi gian)
    public GiaoDich(int taiKhoanId, int nhanVienId, String loaiGiaoDich, double soTien, String ghiChu) {
        this.taiKhoanId = taiKhoanId;
        this.nhanVienId = nhanVienId;
        this.loaiGiaoDich = loaiGiaoDich;
        this.soTien = soTien;
        this.ghiChu = ghiChu;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTaiKhoanId() { return taiKhoanId; }
    public void setTaiKhoanId(int taiKhoanId) { this.taiKhoanId = taiKhoanId; }

    public int getNhanVienId() { return nhanVienId; }
    public void setNhanVienId(int nhanVienId) { this.nhanVienId = nhanVienId; }

    public String getLoaiGiaoDich() { return loaiGiaoDich; }
    public void setLoaiGiaoDich(String loaiGiaoDich) { this.loaiGiaoDich = loaiGiaoDich; }

    public double getSoTien() { return soTien; }
    public void setSoTien(double soTien) { this.soTien = soTien; }

    public Timestamp getNgayGiaoDich() { return ngayGiaoDich; }
    public void setNgayGiaoDich(Timestamp ngayGiaoDich) { this.ngayGiaoDich = ngayGiaoDich; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
    
    // Ham in thong tin de test
    @Override
    public String toString() {
        return "GiaoDich{" +
                "id=" + id +
                ", taiKhoanId=" + taiKhoanId +
                ", nhanVienId=" + nhanVienId +
                ", loaiGiaoDich='" + loaiGiaoDich + '\'' +
                ", soTien=" + soTien +
                ", ngayGiaoDich=" + ngayGiaoDich +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}