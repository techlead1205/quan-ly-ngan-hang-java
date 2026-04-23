package view;

import java.util.Scanner;

public class GiaoDichVienView {

    // MENU GIAO DICH VIEN 
    public static void showMenuGiaoDichVien(Scanner sc, int theID) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n========== MENU GIAO DICH VIEN ==========");
            
            System.out.println("--- 1. QUAN LY KHACH HANG ---");
            System.out.println("1. Tim kiem khach hang (Tra cuu CCCD/SDT)");
            System.out.println("2. Mo ho so khach hang moi");
            System.out.println("3. Cap nhat thong tin khach hang");
            System.out.println("4. Tinh tong tai san khach hang (Nghiep vu)");
            
            System.out.println("\n--- 2. QUAN LY TAI KHOAN ---");
            System.out.println("5. Mo tai khoan thanh toan (The ATM)");
            System.out.println("6. Nap tien vao tai khoan");
            System.out.println("7. Rut tien mat");
            System.out.println("8. Khoa / Mo khoa the khan cap (Nghiep vu)");

            System.out.println("\n--- 3. QUAN LY GIAO DICH ---");
            System.out.println("9. Chuyen khoan noi bo");
            System.out.println("10. In sao ke / Lich su giao dich");
            System.out.println("11. Chot ca / Doi soat cuoi ngay (Nghiep vu)");
            
            System.out.println("\n--- 4. SO TIET KIEM ---");
            System.out.println("12. Mo so tiet kiem moi");
            System.out.println("13. Tat toan so (Rut ca goc lan lai)");
            System.out.println("14. Tra cuu tien lai du tinh (Nghiep vu)");
            
            System.out.println("\n0. Dang xuat (Quay lai man hinh dang nhap)");
            System.out.println("=========================================");
            System.out.print("[-] Moi ban chon chuc nang (0-14): ");

            String choice = sc.nextLine().trim();

            switch (choice) {

                // KHACH HANG
                case "1":
                    System.out.println("\n[*] Tinh nang: Tim kiem khach hang");
                    String keyword = getString(sc, "[-] Nhap so CCCD hoac So dien thoai can tim: ");
                    
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
                    }
                    break;

                case "2":
                    System.out.println("\n[*] Tinh nang: Mo ho so khach hang moi");
                    
                    String tenKH = getString(sc, "[-] Nhap ho ten: ");
                    String cccd = getString(sc, "[-] Nhap so CCCD: ");
                    String sdt = getString(sc, "[-] Nhap so dien thoai: ");
                    String diaChi = getString(sc, "[-] Nhap dia chi: ");
    
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
                    System.out.println("\n[*] Tinh nang: Cap nhat thong tin Khach hang");
                    
                    int idSua = getInt(sc, "[-] Nhap ID Khach hang can sua (Nhap 0 de huy): ");
                    
                    if (idSua == 0) {
                        System.out.println("[-] Da huy thao tac.");
                        break;
                    }

                    String tenMoi = getString(sc, "[-] Nhap Ho ten moi: ");
                    String cccdMoi = getString(sc, "[-] Nhap CCCD moi: ");
                    String sdtMoi = getString(sc, "[-] Nhap So dien thoai moi: ");
                    String diaChiMoi = getString(sc, "[-] Nhap Dia chi moi: ");
                    
                    System.out.println("\n[+] DANG DONG BO DU LIEU LEN HE THONG...");
                    
                    dao.KhachHangDao daoKHSua = new dao.KhachHangDao();
                    boolean suaThanhCong = daoKHSua.updateKhachHang(idSua, tenMoi, cccdMoi, sdtMoi, diaChiMoi);
                    
                    if (suaThanhCong) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("[+] CAP NHAT THONG TIN THANH CONG!");
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("\n[-] CAP NHAT THAT BAI!");
                        System.out.println("[-] Nguyen nhan: Khong tim thay ID Khach hang tren he thong.");
                    }
                    break;

                case "4":
                    System.out.println("\n[*] Tinh nang: Tinh tong tai san khach hang");
                    int idKHTaiSan = getInt(sc, "[-] Nhap ID Khach hang can kiem tra: ");
                    
                    dao.KhachHangDao daoTaiSan = new dao.KhachHangDao();
                    System.out.println("\n[+] DANG TONG HOP DU LIEU TU HE THONG...");
                    
                    double tongTaiSan = daoTaiSan.getTongTaiSan(idKHTaiSan);
                    
                    System.out.println("--------------------------------------------------");
                    System.out.println("[+] KET QUA TRA CUU TONG TAI SAN:");
                    System.out.println("[-] Khach hang ID " + idKHTaiSan + " dang co tong cong: " + formatMoney(tongTaiSan) + " VND");
                    
                    if (tongTaiSan == 0) {
                        System.out.println("[-] (Luu y: Khach hang chua co tien trong tai khoan/so, hoac ID khong ton tai)");
                    }
                    System.out.println("--------------------------------------------------");
                    break;


                // TAI KHOAN
                case "5":
                    System.out.println("\n[*] Tinh nang: Mo tai khoan thanh toan (The ATM)");
                    dao.TaiKhoanDao daoTK = new dao.TaiKhoanDao();
                    int idKH = -1;

                    while (true) {
                        idKH = getInt(sc, "[-] Nhap ID khach hang can mo the (Nhap 0 de huy): ");
                        
                        if (idKH == 0) {
                            System.out.println("[-] Da huy thao tac mo the.");
                            break; 
                        }
                        
                        if (!daoTK.kiemTraKhachHangTonTai(idKH)) {
                            System.out.println("\n[-] LOI: Khong tim thay khach hang co ID " + idKH + " tren he thong!");
                            System.out.println("[-] (Vui long nhap lai ID khac, hoac nhap 0 de di toi muc Mo ho so moi)");
                        } else {
                            break;
                        }
                    }
                    
                    if (idKH != 0) {
                        String soTK = "99" + (long)(Math.random() * 100000000L);
                        int trangThaiTK = 1; 
                        
                        entity.TaiKhoan tkMoi = new entity.TaiKhoan(soTK, idKH, trangThaiTK, theID);
                        
                        if (daoTK.addTaiKhoan(tkMoi)) {
                            System.out.println("\n[+] MO TAI KHOAN THANH CONG!");
                            System.out.println("[-] SO TAI KHOAN CUA KHACH LA: " + soTK);
                            System.out.println("[-] (So du ban dau: 0 VND)");
                        } else {
                            System.out.println("\n[-] MO TAI KHOAN THAT BAI! Co loi xay ra trong qua trinh tao the.");
                        }
                    }
                    break;

                case "6":
                    System.out.println("\n[*] Tinh nang: Nap tien vao tai khoan");
                    dao.TaiKhoanDao daoNap = new dao.TaiKhoanDao();
                    int idKhachNap = -1;
                    boolean coTaiKhoan = false;
                    while (true) {
                        idKhachNap = getInt(sc, "[-] Nhap ID Khach hang can giao dich (Nhap 0 de huy): ");
                        
                        if (idKhachNap == 0) {
                            System.out.println("[-] Da huy thao tac nap tien.");
                            break; 
                        }           
                        coTaiKhoan = daoNap.hienThiTaiKhoanCuaKhach(idKhachNap);
                        if (!coTaiKhoan) {
                            System.out.println("\n[-] LOI: Khong tim thay khach hang, hoac khach hang chua co tai khoan (the ATM)!");
                            System.out.println("[-] (Vui long nhap lai ID khac, hoac nhap 0 de huy)");
                        } else {
                            break;
                        }
                    }
                    
                    if (idKhachNap != 0 && coTaiKhoan) {
                        String soTkNap = getString(sc, "\n[-] Nhap SO TAI KHOAN can nap (tu danh sach tren): ");
                        double tienNap = getDouble(sc, "[-] Nhap so tien muon nap (VND): ");
                        
                        boolean napOK = daoNap.napTien(soTkNap, tienNap);
        
                        if (napOK) {
                            System.out.println("\n[+] NAP TIEN THANH CONG!");
                            System.out.println("[-] Da cong " + formatMoney(tienNap) + " VND vao tai khoan " + soTkNap);
            
                            // Luu lich su giao dich 
                            int idTK = daoNap.getIdBySoTaiKhoan(soTkNap);
                            if (idTK != -1) {
                                entity.GiaoDich gdNap = new entity.GiaoDich(idTK, theID, "NAP_TIEN", tienNap, "Khach hang nap tien mat tai quay");
                                dao.GiaoDichDao daoGD = new dao.GiaoDichDao();
                                daoGD.luuGiaoDich(gdNap);
                            }
                        } else {
                            System.out.println("\n[-] NAP TIEN THAT BAI! (Sai so tai khoan hoac tai khoan da bi khoa)");
                        }
                    }
                    break;
                    
                case "7":
                    System.out.println("\n[*] Tinh nang: Rut tien mat");
                    dao.TaiKhoanDao daoRut = new dao.TaiKhoanDao();
                    int idKhachRut = -1;
                    boolean coTaiKhoanRut = false;
                    while (true) {
                        idKhachRut = getInt(sc, "[-] Nhap ID Khach hang can giao dich (Nhap 0 de huy): ");
                        
                        if (idKhachRut == 0) {
                            System.out.println("[-] Da huy thao tac rut tien.");
                            break;
                        }
                        coTaiKhoanRut = daoRut.hienThiTaiKhoanCuaKhach(idKhachRut);
                        if (!coTaiKhoanRut) {
                            System.out.println("\n[-] LOI: Khong tim thay khach hang, hoac khach hang nay chua co tai khoan (the ATM)!");
                            System.out.println("[-] (Vui long nhap lai ID khac, hoac nhap 0 de huy)");
                        } else {
                            break;
                        }
                    }
                    if (idKhachRut != 0 && coTaiKhoanRut) {
                        String soTkRut = getString(sc, "\n[-] Nhap SO TAI KHOAN can rut (tu danh sach tren): ");
                        double tienRut = getDouble(sc, "[-] Nhap so tien muon rut (VND): ");

                        boolean rutOK = daoRut.rutTien(soTkRut, tienRut);

                        if (rutOK) {
                            System.out.println("\n[+] RUT TIEN THANH CONG!");
                            System.out.println("[-] Da tru " + formatMoney(tienRut) + " VND khoi tai khoan " + soTkRut);
                            System.out.println("[-] (Vui long dua tien mat cho khach hang)");

                            int idTK = daoRut.getIdBySoTaiKhoan(soTkRut);
                            if (idTK != -1) {
                                entity.GiaoDich gdRut = new entity.GiaoDich(idTK, theID, "RUT_TIEN", tienRut, "Khach hang rut tien mat tai quay");
                                dao.GiaoDichDao daoGD = new dao.GiaoDichDao();
                                daoGD.luuGiaoDich(gdRut);
                            }
                        } else {
                            System.out.println("\n[-] RUT TIEN THAT BAI!");
                            System.out.println("[-] Nguyen nhan: Sai so tai khoan, Tai khoan bi khoa, HOAC KHONG DU SO DU!");
                        }
                    }
                    break;

                case "8":
                    System.out.println("\n[*] Tinh nang: Khoa / Mo khoa the khan cap");
                    dao.TaiKhoanDao daoTrangThai = new dao.TaiKhoanDao();
                    int idKhachKhoa = -1;
                    boolean coTaiKhoanKhoa = false;
                    while (true) {
                        idKhachKhoa = getInt(sc, "[-] Nhap ID Khach hang can xu ly (Nhap 0 de huy): ");
                        
                        if (idKhachKhoa == 0) {
                            System.out.println("[-] Da huy thao tac khoa/mo khoa the.");
                            break;
                        }
                        coTaiKhoanKhoa = daoTrangThai.hienThiTaiKhoanCuaKhach(idKhachKhoa);
                        if (!coTaiKhoanKhoa) {
                            System.out.println("\n[-] LOI: Khong tim thay khach hang, hoac khach hang nay chua co tai khoan!");
                            System.out.println("[-] (Vui long nhap lai ID khac, hoac nhap 0 de huy)");
                        } else {
                            break; 
                        }
                    }
                    if (idKhachKhoa != 0 && coTaiKhoanKhoa) {
                        String soTkKhoa = getString(sc, "\n[-] Nhap SO TAI KHOAN can xu ly (tu danh sach tren): ");
                        boolean doiTrangThaiOK = daoTrangThai.khoaMoKhoaThe(soTkKhoa);
                        
                        if (doiTrangThaiOK) {
                            System.out.println("\n[+] THANH CONG!");
                            System.out.println("[-] Trang thai cua tai khoan " + soTkKhoa + " da duoc thay doi (Khoa <-> Mo khoa).");
                        } else {
                            System.out.println("\n[-] THAT BAI! Sai so tai khoan hoac co loi xay ra.");
                        }
                    }
                    break;

                case "9":
                    System.out.println("\n[*] Tinh nang: Chuyen khoan noi bo");
                    String tkGui = "";
                    String tkNhan = "";
                    boolean huyGiaoDich = false;

                    while (true) {
                        tkGui = getString(sc, "[-] Nhap so tai khoan GUI (Nhap 0 de huy): ");
                        if (tkGui.equals("0")) {
                            huyGiaoDich = true;
                            break;
                        }

                        tkNhan = getString(sc, "[-] Nhap so tai khoan NHAN (Nhap 0 de huy): ");
                        if (tkNhan.equals("0")) {
                            huyGiaoDich = true;
                            break;
                        }

                        if (tkGui.equals(tkNhan)) {
                            System.out.println("\n[-] LOI: Khong the tu chuyen khoan cho chinh minh!");
                            System.out.println("[-] (Vui long nhap lai thong tin)");
                        } else {
                            break;
                        }
                    }

                    if (huyGiaoDich) {
                        System.out.println("[-] Da huy thao tac chuyen khoan.");
                        break;
                    }

                    double tienChuyen = getDouble(sc, "[-] Nhap so tien can chuyen (VND): ");

                    dao.TaiKhoanDao daoChuyen = new dao.TaiKhoanDao();
                    boolean chuyenOK = daoChuyen.chuyenKhoan(tkGui, tkNhan, tienChuyen);

                    if (chuyenOK) {
                        System.out.println("\n[+] CHUYEN KHOAN THANH CONG!");
                        System.out.println("[-] Da chuyen " + formatMoney(tienChuyen) + " VND tu " + tkGui + " sang " + tkNhan);

                        int idGui = daoChuyen.getIdBySoTaiKhoan(tkGui);
                        int idNhan = daoChuyen.getIdBySoTaiKhoan(tkNhan);

                        if (idGui != -1 && idNhan != -1) {
                            dao.GiaoDichDao daoGD = new dao.GiaoDichDao();
                            // Dong 1: Ghi cho nguoi gui (Tru tien)
                            daoGD.luuGiaoDich(new entity.GiaoDich(idGui, theID, "CHUYEN_KHOAN", tienChuyen, "Chuyen tien den STK " + tkNhan));
                            // Dong 2: Ghi cho nguoi nhan (Cong tien)
                            daoGD.luuGiaoDich(new entity.GiaoDich(idNhan, theID, "NHAN_TIEN", tienChuyen, "Nhan tien tu STK " + tkGui));
                        }
                    } else {
                        System.out.println("\n[-] GIAO DICH THAT BAI!");
                        System.out.println("[-] Nguyen nhan: TK Gui khong du tien, hoac TK Nhan/Gui khong hop le!");
                    }
                    break;
                
                // GIAO DICH    
                case "10":
                    System.out.println("\n[*] Tinh nang: In sao ke / Lich su giao dich");
                    dao.TaiKhoanDao daoTKSaoKe = new dao.TaiKhoanDao();
                    int idKhachSaoKe = -1;
                    boolean coTaiKhoanSaoKe = false;
                    while (true) {
                        idKhachSaoKe = getInt(sc, "[-] Nhap ID Khach hang can in sao ke (Nhap 0 de huy): ");
                        if (idKhachSaoKe == 0) {
                            System.out.println("[-] Da huy thao tac in sao ke.");
                            break;
                        }
                        coTaiKhoanSaoKe = daoTKSaoKe.hienThiTaiKhoanCuaKhach(idKhachSaoKe);
                        if (!coTaiKhoanSaoKe) {
                            System.out.println("\n[-] LOI: Khong tim thay khach hang, hoac khach hang nay chua co tai khoan!");
                            System.out.println("[-] (Vui long nhap lai ID khac, hoac nhap 0 de huy)");
                        } else {
                            break;
                        }
                    }
                    if (idKhachSaoKe != 0 && coTaiKhoanSaoKe) {
                        String soTkSaoKe = getString(sc, "\n[-] Nhap SO TAI KHOAN can in sao ke (tu danh sach tren): ");
                        
                        System.out.println("\n[+] DANG TRUY XUAT DU LIEU TU MAY CHU...");
                        dao.GiaoDichDao daoSaoKe = new dao.GiaoDichDao();
                        java.util.List<entity.GiaoDich> listGD = daoSaoKe.getLichSuBySoTaiKhoan(soTkSaoKe);
                        
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.printf("%-20s | %-15s | %-15s | %-15s\n", "Thoi gian", "Loai GD", "So tien (VND)", "Ghi chu");
                        System.out.println("-------------------------------------------------------------------------");
                        
                        if (listGD.isEmpty()) {
                            System.out.println("[-] Tai khoan chua co phat sinh giao dich nao (Hoac sai so tai khoan).");
                        } else {
                            for (entity.GiaoDich gd : listGD) {
                                String timeStr = gd.getNgayGiaoDich().toString().substring(0, 16);
                                System.out.printf("%-20s | %-15s | %-,15d | %-15s\n", 
                                    timeStr, gd.getLoaiGiaoDich(), (long)gd.getSoTien(), gd.getGhiChu());
                            }
                        }
                        System.out.println("-------------------------------------------------------------------------");
                    }
                    break;

                case "11":
                    System.out.println("\n[*] Tinh nang: Chot ca / Doi soat cuoi ngay");
                    System.out.println("[+] BAO CAO TONG HOP GIAO DICH NGAY " + java.time.LocalDate.now());
                    
                    dao.GiaoDichDao daoChotCa = new dao.GiaoDichDao();
                    daoChotCa.baoCaoChotCaNgayHomNay();
                    break;


                // SO TIET KIEM
                case "12":
                    System.out.println("\n[*] Tinh nang: Mo so tiet kiem moi");
                    int idKhach = getInt(sc, "[-] Nhap ID Khach hang: ");
                    double tienGui = getDouble(sc, "[-] Nhap so tien gui (VND): ");
            
                    int kyHan = 0;
                    double laiSuat = 0.0;
                    while (true) {
                        kyHan = getInt(sc, "[-] Chon ky han (1, 6, 12 thang): ");
                        
                        if (kyHan == 1) { 
                            laiSuat = 0.03; 
                            break;
                        } else if (kyHan == 6) { 
                            laiSuat = 0.05; 
                            break; 
                        } else if (kyHan == 12) { 
                            laiSuat = 0.065; 
                            break; 
                        } else {
                            System.out.println("[-] LOI: Ky han khong hop le! Vui long chi nhap 1, 6 hoac 12.");
                        }
                    }
                    
                    int trangThaiSo = 1;
                    entity.SoTietKiem stkMoi = new entity.SoTietKiem(idKhach, tienGui, kyHan, laiSuat, trangThaiSo, theID);
                    dao.SoTietKiemDao daoSTK = new dao.SoTietKiemDao();
                    
                    if (daoSTK.addSoTietKiem(stkMoi)) {
                        System.out.println("\n[+] MO SO TIET KIEM THANH CONG!");
                        System.out.println("[-] Lai suat ap dung: " + (laiSuat * 100) + "% / nam");
                    } else {
                        System.out.println("\n[-] MO SO THAT BAI! Vui long kiem tra lai ID Khach hang.");
                    }
                    break;
                
                case "13":
                    System.out.println("\n[*] Tinh nang: Tat toan so tiet kiem");
                    
                    System.out.print("[-] Nhap ID Khach hang de tra cuu so (Hoac an Enter de bo qua): ");
                    String inputKhachTatToan = sc.nextLine().trim(); 
                    
                    if (!inputKhachTatToan.isEmpty()) {
                        try {
                            int idKhachCheckTatToan = Integer.parseInt(inputKhachTatToan);
                            dao.SoTietKiemDao daoCheck = new dao.SoTietKiemDao();
                            boolean coSoKhong = daoCheck.inDanhSachSoTheoKhach(idKhachCheckTatToan);
                            
                            if (!coSoKhong) {
                                System.out.println("[-] Da huy thao tac Tat toan do khong tim thay so phu hop.");
                                break; 
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("[-] LOI: ID Khach hang phai la so! (Bo qua buoc tra cuu)");
                        }
                    }
                    
                    int idSo = getInt(sc, "[-] Nhap ID So tiet kiem can tat toan (Nhap 0 de huy): ");
                    
                    if (idSo == 0) {
                        System.out.println("[-] Da huy thao tac.");
                        break;
                    }
                    
                    dao.SoTietKiemDao daoTatToan = new dao.SoTietKiemDao();
                    System.out.println("\n[+] DANG TINH TOAN TIEN LAI TU HE THONG...");
                    
                    double tongTienNhan = daoTatToan.tatToan(idSo);
                    
                    if (tongTienNhan != -1) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("[+] TAT TOAN THANH CONG!");
                        System.out.println("[-] TONG TIEN KHACH NHAN DUOC (GOC + LAI) LA: " + formatMoney(tongTienNhan) + " VND");
                        System.out.println("[-] (Trang thai so da tu dong chuyen sang: Da dong)");
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("\n[-] TAT TOAN THAT BAI!");
                        System.out.println("[-] Nguyen nhan: Sai ID hoac So nay da duoc tat toan tu truoc do.");
                    }
                    break;

                case "14":
                    System.out.println("\n[*] Tinh nang: Tra cuu tien lai du tinh");
                    
                    System.out.print("[-] Nhap ID Khach hang de tra cuu so (Hoac an Enter de bo qua): ");
                    String inputKhach = sc.nextLine().trim(); 
                    if (!inputKhach.isEmpty()) {
                        try {
                            int idKhachCheck = Integer.parseInt(inputKhach);
                            dao.SoTietKiemDao daoCheck = new dao.SoTietKiemDao();
                            boolean coSoKhong = daoCheck.inDanhSachSoTheoKhach(idKhachCheck);
                            
                            if (!coSoKhong) {
                                System.out.println("[-] Da huy thao tac Tra cuu do khong tim thay so phu hop.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("[-] LOI: ID Khach hang phai la so! (Bo qua buoc tra cuu)");
                        }
                    }

                    int idSoTraCuu = getInt(sc, "[-] Nhap ID So tiet kiem can tra cuu (Nhap 0 de huy): "); 
                    
                    if (idSoTraCuu == 0) {
                        System.out.println("[-] Da huy thao tac.");
                        break;
                    }
                    
                    dao.SoTietKiemDao daoTraCuu = new dao.SoTietKiemDao(); 
                    double laiDuTinh = daoTraCuu.traCuuTienLaiDuTinh(idSoTraCuu);
                    
                    if (laiDuTinh != -1) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("[+] TRA CUU THANH CONG!");
                        System.out.println("[-] TIEN LAI DU TINH DEN HOM NAY LA: " + formatMoney(laiDuTinh) + " VND");
                        System.out.println("[-] (So van dang hoat dong binh thuong)");
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("\n[-] TRA CUU THAT BAI!");
                        System.out.println("[-] Nguyen nhan: Sai ID hoac So da bi tat toan tu truoc.");
                    }
                    break;


                // DANG XUAT
                case "0":
                    System.out.println("\n[+] Da dang xuat tai khoan Giao dich vien.");
                    isRunning = false;
                    break;
                    
                default:
                    System.out.println("\n[!] Lua chon khong hop le. Vui long chon lai!");
            }

            if (isRunning) {
                System.out.print("\n(Nhan Enter de tiep tuc...)");
                sc.nextLine();
            }
        }
    }









































    private static String formatMoney(double amount) {
        return String.format(java.util.Locale.US, "%,d", (long) amount);
    }

    private static int getInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("[-] LOI: Khong duoc de trong!");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[-] LOI: Vui long chi nhap so nguyen!");
            }
        }
    }

    private static double getDouble(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("[-] LOI: Khong duoc de trong!");
                continue;
            }
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("[-] LOI: So tien phai lon hon 0!");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("[-] LOI: Vui long chi nhap so hop le!");
            }
        }
    }

    private static String getString(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("[-] LOI: Khong duoc de trong!");
        }
    }

















    
}