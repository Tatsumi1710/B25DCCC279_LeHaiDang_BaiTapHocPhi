import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(3000000L, hocPhi);
    }
}

abstract class SinhVien {
    protected static final long DON_GIA = 450000L;
    protected String maSV;
    protected String maHe;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaHe() {
        return maHe;
    }

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public abstract long hocPhi();

    public long soTienGiam() {
        if (mienGiam == null) {
            return 0;
        }
        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - soTienGiam();
    }

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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
        Map<String, SinhVien> mapSV = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            SinhVien sv = null;
            if (maHe.equals("CQ")) {
                sv = new SinhVienCQ(maSV, soTinChi);
            } else if (maHe.equals("CLC")) {
                sv = new SinhVienCLC(maSV, soTinChi);
            } else if (maHe.equals("LT")) {
                sv = new SinhVienLT(maSV, soTinChi);
            }

            if (sv != null) {
                ds.add(sv);
                mapSV.put(maSV, sv);
            }
        }

        int m = sc.nextInt();
        List<String> notFoundList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maCS = sc.next();

            SinhVien sv = mapSV.get(maSV);
            if (sv == null) {
                notFoundList.add("NOT FOUND " + maSV);
            } else {
                if (maCS.equals("CS")) {
                    sv.setMienGiam(new ConChinhSach());
                } else if (maCS.equals("HN")) {
                    sv.setMienGiam(new HoNgheo());
                } else if (maCS.equals("HB")) {
                    sv.setMienGiam(new HocBong());
                }
            }
        }

        for (String nf : notFoundList) {
            System.out.println(nf);
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }

        String[] cacHe = {"CQ", "CLC", "LT"};
        for (String he : cacHe) {
            int count = 0;
            long tongPhaiNop = 0;
            for (SinhVien sv : ds) {
                if (sv.getMaHe().equals(he)) {
                    count++;
                    tongPhaiNop += sv.phaiNop();
                }
            }
            if (count > 0) {
                System.out.println(he + " " + count + " " + tongPhaiNop);
            }
        }

        sc.close();
    }
}