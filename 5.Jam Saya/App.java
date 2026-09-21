import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int[] waktu = bacaInput();
        if (waktu != null) {
            proses(waktu);
        }
    }

    private static int[] bacaInput() {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return null;
        
        String jamAwal = scanner.nextLine().trim();
        String[] bagianJam = jamAwal.split(":");
        
        if (bagianJam.length != 2) {
            System.out.println("Jam tidak valid");
            return null;
        }
        
        try {
            int jam = Integer.parseInt(bagianJam[0]);
            int menit = Integer.parseInt(bagianJam[1]);
            
            if (jam < 0 || jam > 23 || menit < 0 || menit > 59) {
                System.out.println("Jam tidak valid");
                return null;
            }
            return new int[]{jam, menit};
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            return null;
        }
    }

    private static void proses(int[] waktuAwal) {
        Scanner scanner = new Scanner(System.in);
        int waktuSekarang = waktuAwal[0] * 60 + waktuAwal[1];
        int totalMenit = 0;
        int pergantianHari = 0;
        
        while (scanner.hasNextLine()) {
            String perintah = scanner.nextLine().trim();
            if (perintah.equals("---")) break;
            
            if (perintah.length() < 2 || (perintah.charAt(0) != '+' && perintah.charAt(0) != '-')) {
                System.out.println("Perintah tidak valid");
                continue;
            }
            
            String angkaStr = perintah.substring(1);
            // Perketat validasi: pastikan setelah +/- HANYA berisi digit (menolak ++5 atau +a)
            if (!angkaStr.matches("\\d+")) {
                System.out.println("Perintah tidak valid");
                continue;
            }
            
            int nilai = Integer.parseInt(angkaStr);
            if (perintah.charAt(0) == '-') nilai = -nilai;
            
            totalMenit += nilai;
            waktuSekarang += nilai;
            
            while (waktuSekarang >= 1440) {
                waktuSekarang -= 1440;
                pergantianHari++;
            }
            while (waktuSekarang < 0) {
                waktuSekarang += 1440;
                pergantianHari++;
            }
        }
        cetakOutput(waktuAwal[0], waktuAwal[1], waktuSekarang, totalMenit, pergantianHari);
    }

    private static void cetakOutput(int jamAwal, int menitAwal, int waktuAkhir, int totalMenit, int pergantianHari) {
        System.out.printf("Jam Awal: %02d:%02d%n", jamAwal, menitAwal);
        System.out.printf("Jam Akhir: %02d:%02d%n", waktuAkhir / 60, waktuAkhir % 60);
        System.out.println("Total Menit: " + (totalMenit > 0 ? "+" : "") + totalMenit);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}