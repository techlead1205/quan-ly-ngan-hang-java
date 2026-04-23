package entity;

import java.sql.Timestamp;

public class KhachHang {
    private int id;
    private String hoTen;
    private String cccd;
    private String soDienThoai;
    private String diaChi;
    private Timestamp ngayTao;
    private int nguoiTaoId;

    public KhachHang() {}

    // Dung khi lay du lieu tu CSDL len
    public KhachHang(int id, String hoTen, String cccd, String soDienThoai, String diaChi, Timestamp ngayTao, int nguoiTaoId) {
        this.id = id;
        this.hoTen = hoTen;
        this.cccd = cccd;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.nguoiTaoId = nguoiTaoId;
    }

    // Dung khi them khach hang moi - id va ngayTao se do CSDL tu dong sinh ra
    public KhachHang(String hoTen, String cccd, String soDienThoai, String diaChi, int nguoiTaoId) {
        this.hoTen = hoTen;
        this.cccd = cccd;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.nguoiTaoId = nguoiTaoId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getCccd() { return cccd; }
    public void setCccd(String cccd) { this.cccd = cccd; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public Timestamp getNgayTao() { return ngayTao; }
    public void setNgayTao(Timestamp ngayTao) { this.ngayTao = ngayTao; }

    public int getNguoiTaoId() { return nguoiTaoId; }
    public void setNguoiTaoId(int nguoiTaoId) { this.nguoiTaoId = nguoiTaoId; }

    @Override
    public String toString() {
        return "KhachHang{" +
                "id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", cccd='" + cccd + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", ngayTao=" + ngayTao +
                ", nguoiTaoId=" + nguoiTaoId +
                '}';
    }
}