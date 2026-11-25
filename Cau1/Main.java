import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV.trim();
        this.hoTen = chuanHoaHoTen(hoTen);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaHoTen(String s) {
        String[] words = s.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                sb.append(Character.toUpperCase(words[i].charAt(0)))
                  .append(words[i].substring(1));
                if (i < words.length - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    private String chuanHoaNgaySinh(String s) {
        String[] parts = s.trim().split("/");
        String ngay = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
        String thang = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
        String nam = parts[2];
        return ngay + "/" + thang + "/" + nam;
    }

    @Override
    public String toString() {
        return maSV + " " + hoTen + " " + ngaySinh;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        int n = Integer.parseInt(sc.nextLine().trim());

        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String ns = sc.nextLine();
            ds.add(new SinhVien(ma, ten, ns));
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
        sc.close();
    }
}