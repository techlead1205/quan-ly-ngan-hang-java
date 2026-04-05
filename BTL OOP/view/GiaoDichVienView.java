package view;

import java.util.Scanner;

public class GiaoDichVienView {
    
    // MENU GIAO DICH VIEN 
    public static void showMenuGiaoDichVien(Scanner scanner, int theID) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n========== MENU GIAO DICH VIEN ==========");
            
            System.out.println("--- 1. QUAN LY KHACH HANG ---");
            System.out.println("1. Tim kiem khach hang (Tra cuu CCCD/SDT)");
            System.out.println("2. Mo ho so khach hang moi");
            System.out.println("3. Tinh tong tai san khach hang (Nghiep vu)");
            
            System.out.println("\n--- 2. TAI KHOAN & GIAO DICH ---");
            System.out.println("4. Mo tai khoan thanh toan (The ATM)");
            System.out.println("5. Nap tien vao tai khoan");
            System.out.println("6. Rut tien mat");
            System.out.println("7. Khoa / Mo khoa the khan cap (Nghiep vu)");
            System.out.println("8. Chuyen khoan noi bo");
            System.out.println("9. In sao ke / Lich su giao dich");
            System.out.println("10. Chot ca / Doi soat cuoi ngay (Nghiep vu)");
            
            System.out.println("\n--- 3. SO TIET KIEM ---");
            System.out.println("11. Mo so tiet kiem moi");
            System.out.println("12. Tat toan so (Rut ca goc lan lai)");
            System.out.println("13. Tra cuu tien lai du tinh (Nghiep vu)");
            
            System.out.println("\n0. Dang xuat (Quay lai man hinh dang nhap)");
            System.out.println("=========================================");
            System.out.print("[-] Moi ban chon chuc nang (0-13): ");

            String choice = scanner.nextLine();

            switch (choice) {

                // --- NHOM KHACH HANG ---
                case "1":
                    System.out.println("\n[*] Tinh nang: Tim kiem khach hang");
                    System.out.print("[-] Nhap so CCCD hoac So dien thoai can tim: ");
                    String keyword = scanner.nextLine();
                    
                    dao.KhachHangDao daoTimKiem = new dao.KhachHangDao();
                    entity.KhachHang khTimThay = daoTimKiem.searchKhachHang(keyword); 
                    
                    if (khTimThay != null) {
                        System.out.println("\n[+] DA TIM THAY HO SO KHACH HANG!");
                        System.out.println("----------------------------------------");
                        System.out.println("- ID Khach hang: " + khTimThay.getId());
                        System.out.println("- Ho va ten    : " + khTimThay.getHoTen());
                        System.out.println("- So CCCD      : " + khTimThay.getCccd());
                        System.out.println("- So dien thoai: " + khTimThay.getSoDienThoai());
                        System.out.println("- Dia chi      : " + khTimThay.getDiaChi());
                        System.out.println("----------------------------------------");
                        System.out.println("-> Khach hang nay da du dieu kien mo Tai khoan hoac So tiet kiem.");
                    } else {
                        System.out.println("\n[-] KHONG TIM THAY! Khach hang chua co ten tren he thong.");
                        System.out.println("-> Goi y: Hay chon Phim so 2 de mo ho so moi cho khach.");
                    }
                    break;

                case "2":
                    System.out.println("\n[*] Tinh nang: Mo ho so khach hang moi");
                    System.out.print("[-] Nhap ho ten: ");
                    String tenKH = scanner.nextLine();
                    System.out.print("[-] Nhap so CCCD: ");
                    String cccd = scanner.nextLine();
                    System.out.print("[-] Nhap so dien thoai: ");
                    String sdt = scanner.nextLine();
                    System.out.print("[-] Nhap dia chi: ");
                    String diaChi = scanner.nextLine();
                    
                    entity.KhachHang khMoi = new entity.KhachHang(tenKH, cccd, sdt, diaChi, theID);
                    dao.KhachHangDao daoKH = new dao.KhachHangDao();
                    
                    boolean moHoSoOK = daoKH.addKhachHang(khMoi, theID); 
                    
                    if (moHoSoOK) {
                        System.out.println("\n[+] MO HO SO KHACH HANG THANH CONG!");
                    } else {
                        System.out.println("\n[-] MO HO SO THAT BAI! Vui long kiem tra lai thong tin.");
                    }
                    break;

                case "3":
                    System.out.println("\n[*] Tinh nang: Tinh tong tai san khach hang");
                    System.out.print("[-] Nhap ID Khach hang can kiem tra: ");
                    
                    // Dung Integer.parseInt de tranh troi lenh
                    int idKHTaiSan = Integer.parseInt(scanner.nextLine());
                    
                    dao.KhachHangDao daoTaiSan = new dao.KhachHangDao();
                    System.out.println("\n[+] DANG TONG HOP DU LIEU TU HE THONG...");
                    
                    double tongTaiSan = daoTaiSan.getTongTaiSan(idKHTaiSan);
                    
                    System.out.println("--------------------------------------------------");
                    System.out.println("[+] KET QUA TRA CUU TONG TAI SAN:");
                    // Ep kieu (long) de so tien to khong bi hien thi chu E
                    System.out.println("[-] Khach hang ID " + idKHTaiSan + " dang co tong cong: " + (long)tongTaiSan + " VND");
                    
                    if (tongTaiSan == 0) {
                        System.out.println("[-] (Luu y: Khach hang chua co tien trong tai khoan/so, hoac ID khong ton tai)");
                    }
                    System.out.println("--------------------------------------------------");
                    break;


                // --- NHOM TAI KHOAN & GIAO DICH ---
                case "4":
                    System.out.println("\n[*] Tinh nang: Mo tai khoan thanh toan (The ATM)");
                    System.out.print("[-] Nhap ID khach hang can mo the: ");
                    int idKH = Integer.parseInt(scanner.nextLine());
                    
                    String soTK = "99" + (long)(Math.random() * 100000000L);
                    int trangThaiTK = 1; 
                    
                    entity.TaiKhoan tkMoi = new entity.TaiKhoan(soTK, idKH, trangThaiTK, theID);
                    dao.TaiKhoanDao daoTK = new dao.TaiKhoanDao();
                    
                    if (daoTK.addTaiKhoan(tkMoi)) {
                        System.out.println("\n[+] MO TAI KHOAN THANH CONG!");
                        System.out.println("[-] SO TAI KHOAN CUA KHACH LA: " + soTK);
                        System.out.println("[-] (So du ban dau: 0 VND)");
                    } else {
                        System.out.println("\n[-] MO TAI KHOAN THAT BAI! (Vui long kiem tra lai ID khach hang)");
                    }
                    break;

                case "5":
                    System.out.println("\n[*] Tinh nang: Nap tien vao tai khoan");
                    System.out.print("[-] Nhap SO TAI KHOAN can nap: ");
                    String soTkNap = scanner.nextLine();
                    System.out.print("[-] Nhap so tien muon nap (VND): ");
                    double tienNap = Double.parseDouble(scanner.nextLine());
                    
                    if (tienNap <= 0) {
                        System.out.println("[-] LOI: So tien nap phai lon hon 0!");
                        break; 
                    }
                    
                    dao.TaiKhoanDao daoNap = new dao.TaiKhoanDao();
                    boolean napOK = daoNap.napTien(soTkNap, tienNap);
                    
                    if (napOK) {
                        System.out.println("\n[+] NAP TIEN THANH CONG!");
                        System.out.println("[-] Da cong " + (long)tienNap + " VND vao tai khoan " + soTkNap);
                        
                        // --- DOAN CODE MOI: LUU LICH SU GIAO DICH ---
                        int idTK = daoNap.getIdBySoTaiKhoan(soTkNap);
                        if (idTK != -1) {
                            // theID la ID cua giao dich vien duoc truyen vao tu dau ham
                            entity.GiaoDich gdNap = new entity.GiaoDich(idTK, theID, "NAP_TIEN", tienNap, "Khach hang nap tien mat tai quay");
                            dao.GiaoDichDao daoGD = new dao.GiaoDichDao();
                            daoGD.luuGiaoDich(gdNap);
                        }
                        // --------------------------------------------
                    } else {
                        System.out.println("\n[-] NAP TIEN THAT BAI! (Sai so tai khoan hoac the da bi khoa)");
                    }
                    break;
                    
                case "6":
                    System.out.println("\n[*] Tinh nang: Rut tien mat");
                    System.out.print("[-] Nhap SO TAI KHOAN can rut: ");
                    String soTkRut = scanner.nextLine();
                    System.out.print("[-] Nhap so tien muon rut (VND): ");
                    double tienRut = Double.parseDouble(scanner.nextLine());
                    
                    if (tienRut <= 0) {
                        System.out.println("[-] LOI: So tien rut phai lon hon 0!");
                        break;
                    }
                    
                    dao.TaiKhoanDao daoRut = new dao.TaiKhoanDao();
                    boolean rutOK = daoRut.rutTien(soTkRut, tienRut);
                    
                    if (rutOK) {
                        System.out.println("\n[+] RUT TIEN THANH CONG!");
                        System.out.println("[-] Da tru " + (long)tienRut + " VND khoi tai khoan " + soTkRut);
                        System.out.println("[-] (Vui long dua tien mat cho khach hang)");
                        
                        // --- DOAN CODE MOI: LUU LICH SU GIAO DICH ---
                        int idTK = daoRut.getIdBySoTaiKhoan(soTkRut);
                        if (idTK != -1) {
                            entity.GiaoDich gdRut = new entity.GiaoDich(idTK, theID, "RUT_TIEN", tienRut, "Khach hang rut tien mat tai quay");
                            dao.GiaoDichDao daoGD = new dao.GiaoDichDao();
                            daoGD.luuGiaoDich(gdRut);
                        }
                        // --------------------------------------------
                    } else {
                        System.out.println("\n[-] RUT TIEN THAT BAI!");
                        System.out.println("[-] Nguyen nhan: Sai so tai khoan, The bi khoa, HOAC KHONG DU SO DU!");
                    }
                    break;

                case "7":
                    System.out.println("\n[*] Tinh nang: Khoa / Mo khoa the khan cap");
                    System.out.print("[-] Nhap SO TAI KHOAN can xu ly: ");
                    String soTkKhoa = scanner.nextLine();
                    
                    dao.TaiKhoanDao daoTrangThai = new dao.TaiKhoanDao();
                    boolean doiTrangThaiOK = daoTrangThai.khoaMoKhoaThe(soTkKhoa);
                    
                    if (doiTrangThaiOK) {
                        System.out.println("\n[+] THANH CONG!");
                        System.out.println("[-] Trang thai cua tai khoan " + soTkKhoa + " da duoc thay doi (Khoa <-> Mo khoa).");
                    } else {
                        System.out.println("\n[-] THAT BAI! Khong tim thay so tai khoan hoac co loi xay ra.");
                    }
                    break;

                case "8":
                    System.out.println("\n[*] Tinh nang: Chuyen khoan noi bo");
                    System.out.print("[-] Nhap so tai khoan GUI: ");
                    String tkGui = scanner.nextLine();
                    System.out.print("[-] Nhap so tai khoan NHAN: ");
                    String tkNhan = scanner.nextLine();
                    
                    if (tkGui.equals(tkNhan)) {
                        System.out.println("[-] LOI: Khong the tu chuyen khoan cho chinh minh!");
                        break;
                    }
                    
                    System.out.print("[-] Nhap so tien can chuyen (VND): ");
                    double tienChuyen = Double.parseDouble(scanner.nextLine());
                    
                    if (tienChuyen <= 0) {
                        System.out.println("[-] LOI: So tien chuyen phai lon hon 0!");
                        break;
                    }
                    
                    dao.TaiKhoanDao daoChuyen = new dao.TaiKhoanDao();
                    boolean chuyenOK = daoChuyen.chuyenKhoan(tkGui, tkNhan, tienChuyen);
                    
                    if (chuyenOK) {
                        System.out.println("\n[+] CHUYEN KHOAN THANH CONG!");
                        System.out.println("[-] Da chuyen " + (long)tienChuyen + " VND tu " + tkGui + " sang " + tkNhan);
                        
                        // --- DOAN CODE MOI: LUU 2 DONG LICH SU GIAO DICH ---
                        int idGui = daoChuyen.getIdBySoTaiKhoan(tkGui);
                        int idNhan = daoChuyen.getIdBySoTaiKhoan(tkNhan);
                        
                        if (idGui != -1 && idNhan != -1) {
                            dao.GiaoDichDao daoGD = new dao.GiaoDichDao();
                            // Dong 1: Ghi cho nguoi gui (Tru tien)
                            daoGD.luuGiaoDich(new entity.GiaoDich(idGui, theID, "CHUYEN_KHOAN", tienChuyen, "Chuyen tien den STK " + tkNhan));
                            // Dong 2: Ghi cho nguoi nhan (Cong tien)
                            daoGD.luuGiaoDich(new entity.GiaoDich(idNhan, theID, "NHAN_TIEN", tienChuyen, "Nhan tien tu STK " + tkGui));
                        }
                        // ---------------------------------------------------
                    } else {
                        System.out.println("\n[-] GIAO DICH THAT BAI!");
                        System.out.println("[-] Nguyen nhan: TK Gui khong du tien, hoac TK Nhan khong hop le!");
                    }
                    break;

                case "9":
                    System.out.println("\n[*] Tinh nang: In sao ke / Lich su giao dich");
                    System.out.print("[-] Nhap SO TAI KHOAN can in sao ke: ");
                    String soTkSaoKe = scanner.nextLine();
                    
                    System.out.println("\n[+] DANG TRUY XUAT DU LIEU TU MAY CHU...");
                    dao.GiaoDichDao daoSaoKe = new dao.GiaoDichDao();
                    java.util.List<entity.GiaoDich> listGD = daoSaoKe.getLichSuBySoTaiKhoan(soTkSaoKe);
                    
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.printf("%-20s | %-15s | %-15s | %-15s\n", "Thoi gian", "Loai GD", "So tien (VND)", "Ghi chu");
                    System.out.println("-------------------------------------------------------------------------");
                    
                    if (listGD.isEmpty()) {
                        System.out.println("[-] Tai khoan chua co phat sinh giao dich nao.");
                    } else {
                        for (entity.GiaoDich gd : listGD) {
                            // Cat chuoi Timestamp de in ra nhin cho gon (VD: 2026-04-04 10:30)
                            String timeStr = gd.getNgayGiaoDich().toString().substring(0, 16);
                            System.out.printf("%-20s | %-15s | %-15d | %-15s\n", 
                                timeStr, gd.getLoaiGiaoDich(), (long)gd.getSoTien(), gd.getGhiChu());
                        }
                    }
                    System.out.println("-------------------------------------------------------------------------");
                    break;

                case "10":
                    System.out.println("\n[*] Tinh nang: Chot ca / Doi soat cuoi ngay");
                    System.out.println("[+] BAO CAO TONG HOP GIAO DICH NGAY " + java.time.LocalDate.now());
                    
                    dao.GiaoDichDao daoChotCa = new dao.GiaoDichDao();
                    daoChotCa.baoCaoChotCaNgayHomNay();
                    break;


                // --- NHOM SO TIET KIEM ---
                case "11":
                    System.out.println("\n[*] Tinh nang: Mo so tiet kiem moi");
                    System.out.print("[-] Nhap ID Khach hang: ");
                    int idKhach = Integer.parseInt(scanner.nextLine()); 
                    System.out.print("[-] Nhap so tien gui (VND): ");
                    double tienGui = Double.parseDouble(scanner.nextLine());
                    System.out.print("[-] Chon ky han (1, 6, 12 thang): ");
                    int kyHan = Integer.parseInt(scanner.nextLine());
                    
                    double laiSuat = 0.0;
                    if (kyHan == 1) laiSuat = 0.03;       
                    else if (kyHan == 6) laiSuat = 0.05;  
                    else if (kyHan == 12) laiSuat = 0.065;
                    
                    // --- DOAN CODE MOI: LUU SO VAO DATABASE ---
                    int trangThaiSo = 1; // 1 la dang hoat dong
                    entity.SoTietKiem stkMoi = new entity.SoTietKiem(idKhach, tienGui, kyHan, laiSuat, trangThaiSo, theID);
                    dao.SoTietKiemDao daoSTK = new dao.SoTietKiemDao();
                    
                    if (daoSTK.addSoTietKiem(stkMoi)) {
                        System.out.println("\n[+] MO SO TIET KIEM THANH CONG!");
                        System.out.println("[-] Lai suat ap dung: " + (laiSuat * 100) + "% / nam");
                    } else {
                        System.out.println("\n[-] MO SO THAT BAI! Vui long kiem tra lai ID Khach hang.");
                    }
                    break;
                
                case "12":
                    System.out.println("\n[*] Tinh nang: Tat toan so tiet kiem");
                    System.out.print("[-] Nhap ID So tiet kiem can tat toan: ");
                    int idSo = Integer.parseInt(scanner.nextLine());
                    
                    dao.SoTietKiemDao daoTatToan = new dao.SoTietKiemDao();
                    System.out.println("\n[+] DANG TINH TOAN TIEN LAI TU HE THONG...");
                    
                    double tongTienNhan = daoTatToan.tatToan(idSo);
                    
                    if (tongTienNhan != -1) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("[+] TAT TOAN THANH CONG!");
                        System.out.println("[-] TONG TIEN KHACH NHAN DUOC (GOC + LAI) LA: " + (long)tongTienNhan + " VND");
                        System.out.println("[-] (Trang thai so da tu dong chuyen sang: Da dong)");
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("\n[-] TAT TOAN THAT BAI!");
                        System.out.println("[-] Nguyen nhan: Sai ID hoac So nay da duoc tat toan tu truoc do.");
                    }
                    break;

                case "13":
                    System.out.println("\n[*] Tinh nang: Tra cuu tien lai du tinh");
                    System.out.print("[-] Nhap ID So tiet kiem: ");
                    int idSoTraCuu = Integer.parseInt(scanner.nextLine());
                    
                    dao.SoTietKiemDao daoTraCuu = new dao.SoTietKiemDao();
                    System.out.println("\n[+] DANG TINH TOAN TIEN LAI TU HE THONG...");
                    
                    double laiDuTinh = daoTraCuu.traCuuTienLaiDuTinh(idSoTraCuu);
                    
                    if (laiDuTinh != -1) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("[+] TRA CUU THANH CONG!");
                        System.out.println("[-] TIEN LAI DU TINH DEN HOM NAY LA: " + (long)laiDuTinh + " VND");
                        System.out.println("[-] (So van dang hoat dong binh thuong)");
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("\n[-] TRA CUU THAT BAI!");
                        System.out.println("[-] Nguyen nhan: Sai ID hoac So da bi tat toan tu truoc.");
                    }
                    break;


                // --- DANG XUAT ---
                case "0":
                    System.out.println("\n[+] Da dang xuat tai khoan Giao dich vien.");
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