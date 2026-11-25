import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

abstract class SinhVien {
    protected static final long DON_GIA = 450000L;
    protected String maSV;
    protected String maHe;
    protected int soTinChi;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
    }

    public String getMaSV() {
        return maSV;
    }

    public abstract long hocPhi();

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi();
    }
}

class SinhVienCQ extends SinhVien {
    public SinhVienCQ(String maSV, int soTinChi) {
        super(maSV, "CQ", soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * DON_GIA;
    }
}

class SinhVienCLC extends SinhVien {
    public SinhVienCLC(String maSV, int soTinChi) {
        super(maSV, "CLC", soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) (soTinChi * DON_GIA * 1.5);
    }
}

class SinhVienLT extends SinhVien {
    public SinhVienLT(String maSV, int soTinChi) {
        super(maSV, "LT", soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * DON_GIA + 2000000L;
    }
}

class QuanLyHocPhi {
    private ArrayList<SinhVien> danhSach = new ArrayList<>();

    private boolean daTonTai(String maSV) {
        for (SinhVien sv : danhSach) {
            if (sv.getMaSV().equals(maSV)) {
                return true;
            }
        }
        return false;
    }

    public void themSinhVien(String maHe, String maSV, String tinChiStr) throws DuLieuKhongHopLeException {
        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException(maSV);
        }

        int soTinChi;
        try {
            soTinChi = Integer.parseInt(tinChiStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException(maSV);
        }

        if (soTinChi < 1 || soTinChi > 30) {
            throw new DuLieuKhongHopLeException(maSV);
        }

        if (daTonTai(maSV)) {
            throw new DuLieuKhongHopLeException(maSV);
        }

        if (maHe.equals("CQ")) {
            danhSach.add(new SinhVienCQ(maSV, soTinChi));
        } else if (maHe.equals("CLC")) {
            danhSach.add(new SinhVienCLC(maSV, soTinChi));
        } else {
            danhSach.add(new SinhVienLT(maSV, soTinChi));
        }
    }

    public void sapXep() {
        Collections.sort(danhSach, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien a, SinhVien b) {
                int cmp = Long.compare(b.hocPhi(), a.hocPhi());
                if (cmp != 0) return cmp;
                return a.getMaSV().compareTo(b.getMaSV());
            }
        });
    }

    public void inDanhSachVaTong() {
        long tong = 0;
        for (SinhVien sv : danhSach) {
            System.out.println(sv);
            tong += sv.hocPhi();
        }
        System.out.println("TONG " + tong);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        QuanLyHocPhi ql = new QuanLyHocPhi();
        ArrayList<String> dsLoi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            String tinChiStr = sc.next();

            try {
                ql.themSinhVien(maHe, maSV, tinChiStr);
            } catch (DuLieuKhongHopLeException e) {
                dsLoi.add("INVALID " + e.getMessage());
            }
        }

        for (String loi : dsLoi) {
            System.out.println(loi);
        }

        ql.sapXep();
        ql.inDanhSachVaTong();
        sc.close();
    }
}