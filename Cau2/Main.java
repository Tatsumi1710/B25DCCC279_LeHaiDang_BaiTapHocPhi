import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            if (maHe.equals("CQ")) {
                ds.add(new SinhVienCQ(maSV, soTinChi));
            } else if (maHe.equals("CLC")) {
                ds.add(new SinhVienCLC(maSV, soTinChi));
            } else if (maHe.equals("LT")) {
                ds.add(new SinhVienLT(maSV, soTinChi));
            }
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
        sc.close();
    }
}