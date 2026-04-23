package entity;

public class NhanVien {

    private int id;
    private String name;
    private String username;
    private String password;
    private int role; // role: 1 la Admin, 2 la Giao dich vien
    private int status; // status: 1 la dang hoat dong, 0 la bi khoa

    public NhanVien() {}

    public NhanVien(int id, String name, String username, String password, int role, int status) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public NhanVien(String name, String username, String password, int role, int status) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; } 

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; } 

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRole() { return role; }
    public void setRole(int role) { this.role = role; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }


    @Override
    public String toString() {
        String tenVaiTro = (role == 0) ? "Admin" : "Giao dich vien";
        String tenTrangThai = (status == 1) ? "Hoat dong" : "Bi khoa";
        
        return "NhanVien{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", role=" + tenVaiTro +
                ", status=" + tenTrangThai +
                '}';
    }
}