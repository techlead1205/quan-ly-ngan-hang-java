package view;

import java.util.List;
import java.util.Scanner;

import dao.NhanVienDao;
import entity.NhanVien;

public class AdminView {

    // MENU ADMIN
    public static void showMenuAdmin(Scanner scanner) {
        boolean isRunning = true;
        NhanVienDao dao = new NhanVienDao(); 

        while (isRunning) {
            System.out.println("\n========== MENU QUAN TRI VIEN ==========");
            System.out.println("1. Xem danh sach nhan vien");
            System.out.println("2. Them nhan vien moi");
            System.out.println("3. Xoa nhan vien");
            System.out.println("4. Xem KPI / Nang suat Giao dich vien (Nghiep vu)");
            System.out.println("0. Dang xuat (Quay lai man hinh dang nhap)");
            System.out.println("========================================");
            System.out.print("[-] Moi ban chon chuc nang (0-4): ");

            String choice = scanner.nextLine(); // Doc lua chon cua nguoi dung

            switch (choice) {
                case "1":
                    System.out.println("\n[*] Tinh nang: Xem danh sach nhan vien");
                    List<NhanVien> ds = dao.getAllNhanVien();
                    
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.printf("%-5s | %-20s | %-15s | %-15s | %-10s\n", "ID", "Ho Ten", "Username", "Vai Tro", "Trang Thai");
                    System.out.println("-------------------------------------------------------------------------");
                    
                    for (NhanVien n : ds) {
                        // Da tra ve dung: 1 la Admin, 2 la Giao Dich Vien theo CSDL
                        String roleStr = (n.getRole() == 1) ? "Admin" : "Giao Dich Vien"; 
                        String statusStr = (n.getStatus() == 1) ? "Dang lam" : "Nghi viec";
                        
                        System.out.printf("%-5d | %-20s | %-15s | %-15s | %-10s\n",  
                                n.getId(), n.getName(), n.getUsername(), roleStr, statusStr);
                    }
                    System.out.println("-------------------------------------------------------------------------");
                    break;

                case "2":
                    System.out.println("\n[*] Tinh nang: Them nhan vien moi");
                    System.out.print("[-] Nhap ho ten: ");  
                    String ten = scanner.nextLine();
                    System.out.print("[-] Nhap username: ");
                    String user = scanner.nextLine();
                    String pass = "123456";
                    System.out.println("[*] He thong tu dong cap mat khau mac dinh: 123456");                                
                    
                    // Da sua lai thanh 1 (Giao dich vien)
                    int vaiTro = 2; 
                    System.out.println("[*] Tu dong gan vai tro: GIAO DICH VIEN");
                    
                    int trangThai = 1; // Mac dinh luon la Dang lam viec
                    System.out.println("[*] Tu dong gan trang thai: DANG LAM VIEC");

                    NhanVien nvMoi = new NhanVien(0, ten, user, pass, vaiTro, trangThai);
                    
                    boolean ketQua = dao.addNhanVien(nvMoi);
                    
                    if (ketQua) { // Viet ngan gon thay vi ketQua == true
                        System.out.println("\n[+] THEM NHAN VIEN THANH CONG!");
                    } else {
                        System.out.println("\n[-] THEM NHAN VIEN THAT BAI! Vui long kiem tra lai.");
                    }
                    break;


                case "3":
                    System.out.println("\n[*] Tinh nang: Xoa nhan vien");
                    
                    System.out.print("[-] Nhap ID nhan vien can xoa: ");
                    int idXoa = Integer.parseInt(scanner.nextLine());
                    
                    // Goi DAO di thuc thi (khong can tao moi doi tuong DAO nua)
                    boolean ketQuaXoa = dao.deleteNhanVien(idXoa);
                    
                    if (ketQuaXoa) {
                        System.out.println("\n[+] XOA NHAN VIEN THANH CONG!");
                    } else {
                        System.out.println("\n[-] XOA THAT BAI! (Khong tim thay ID, hoac ban dang co xoa Admin)");
                    }
                    break;


                case "4":
                    System.out.println("\n[*] Tinh nang: Xem KPI Giao dich vien");
                    System.out.print("[-] Nhap ID Nhan vien can kiem tra: ");
                    int idCheck = Integer.parseInt(scanner.nextLine());
                    
                    dao.NhanVienDao daoKPI = new dao.NhanVienDao();
                    daoKPI.inKPIThangHienTai(idCheck);
                    break;

                case "0":
                    System.out.println("\n[+] Da dang xuat tai khoan Admin.");
                    isRunning = false; 
                    break;
                    
                default:
                    System.out.println("\n[!] Lua chon khong hop le. Vui long chon lai!");
            }

            if (isRunning) { 
                System.out.print("\n(Nhan Enter de tiep tuc...)");
                scanner.nextLine();
            }
        }
    } 
}