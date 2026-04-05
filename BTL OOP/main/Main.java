package main;

import java.util.Scanner;
import entity.NhanVien;
import dao.NhanVienDao;
import static view.AdminView.showMenuAdmin;
import static view.GiaoDichVienView.showMenuGiaoDichVien;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NhanVienDao dao = new NhanVienDao(); 

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("   CHAO MUNG DEN HE THONG NGAN HANG");
            System.out.println("=========================================");
            System.out.println("Huong dan: Nhap '0' vao Username de thoat phan mem.");
            System.out.print("[-] Username: ");
            String username = scanner.nextLine();

            if (username.equals("0")) {
                System.out.println("Da thoat chuong trinh. Tam biet!");
                break;
            }

            System.out.print("[-] Password: ");
            String password = scanner.nextLine();

            System.out.println("Dang ket noi he thong. Vui long doi...\n");

            NhanVien nv = dao.checkLogin(username, password);

            if (nv != null) {
                // Kiem tra xem tai khoan co bi khoa khong (status = 0)
                if (nv.getStatus() == 0) {
                    System.out.println("[-] Tai khoan cua ban da bi khoa hoac bi xoa. Vui long lien he Admin!");
                    continue; // Quay lai vong lap dang nhap
                }

                // Kiem tra dang nhap lan dau (pass la 123456 va la Giao dich vien - role 2)
                if (nv.getPassword().equals("123456") && nv.getRole() == 2) {
                    System.out.println("\n[!] CANH BAO: DAY LA LAN DANG NHAP DAU TIEN!");
                    System.out.println("[!] BAN BAT BUOC PHAI DOI MAT KHAU DE TIEP TUC.");
                    
                    System.out.print("[-] Nhap mat khau MOI cua ban: ");
                    String passMoi = scanner.nextLine();
                    
                    // Su dung dung ten ham changePassword
                    boolean doiPassOK = dao.changePassword(nv.getId(), passMoi);
                    
                    if (doiPassOK) {
                        System.out.println("[+] DOI MAT KHAU THANH CONG! He thong tiep tuc dang nhap...\n");
                    } else {
                        System.out.println("[-] DOI MAT KHAU THAT BAI. Vui long lien he Admin.");
                        continue; 
                    }
                }

                // Kiem tra quyen han theo dung Entity (0: Admin, 1: Giao dich vien)
                if (nv.getRole() == 1) {
                    System.out.println("Quyen han: QUAN TRI VIEN (ADMIN)");
                    System.out.println("-> (Dang chuyen huong vao Menu Admin...)");
                    showMenuAdmin(scanner); 
                    
                } else if (nv.getRole() == 2) {
                    System.out.println("Quyen han: GIAO DICH VIEN");
                    System.out.println("-> (Dang chuyen huong vao Menu Giao dich...)");
                    showMenuGiaoDichVien(scanner, nv.getId()); 
                } else {
                    System.out.println("[!] Tai khoan cua ban bi loi quyen han!");
                }
                
            } else {
                System.out.println("[-] Sai ten dang nhap hoac mat khau! Vui long thu lai.");
            }
        }
        scanner.close();
    }
}