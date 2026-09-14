import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Baca jam awal
        String jamAwal = scanner.nextLine().trim();

        String[] bagianJam = jamAwal.split(":");

        // Validasi format jam
        if (bagianJam.length != 2) {
            System.out.println("Jam tidak valid");
            scanner.close();
            return;
        }

        int jam;
        int menit;

        try {
            jam = Integer.parseInt(bagianJam[0]);
            menit = Integer.parseInt(bagianJam[1]);
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            scanner.close();
            return;
        }

        // Validasi rentang jam dan menit
        if (jam < 0 || jam > 23 || menit < 0 || menit > 59) {
            System.out.println("Jam tidak valid");
            scanner.close();
            return;
        }

        // Konversi jam awal ke total menit
        int waktuSekarang = jam * 60 + menit;
        int waktuAwal = waktuSekarang;

        int totalMenit = 0;
        int pergantianHari = 0;

        // Membaca perintah
        while (scanner.hasNextLine()) {
            String perintah = scanner.nextLine().trim();

            if (perintah.equals("---")) {
                break;
            }

            // Perintah harus diawali + atau -
            if (perintah.length() < 2
                    || (perintah.charAt(0) != '+' && perintah.charAt(0) != '-')) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int nilai;

            try {
                nilai = Integer.parseInt(perintah.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            // Pastikan setelah + atau - hanya berupa angka
            if (nilai < 0) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            if (perintah.charAt(0) == '-') {
                nilai = -nilai;
            }

            totalMenit += nilai;
            waktuSekarang += nilai;

            // Hitung pergantian hari
            while (waktuSekarang >= 1440) {
                waktuSekarang -= 1440;
                pergantianHari++;
            }

            while (waktuSekarang < 0) {
                waktuSekarang += 1440;
                pergantianHari++;
            }
        }

        int jamAkhir = waktuSekarang / 60;
        int menitAkhir = waktuSekarang % 60;

        System.out.printf("Jam Awal: %02d:%02d%n", jam, menit);
        System.out.printf("Jam Akhir: %02d:%02d%n", jamAkhir, menitAkhir);

        if (totalMenit > 0) {
            System.out.println("Total Menit: +" + totalMenit);
        } else {
            System.out.println("Total Menit: " + totalMenit);
        }

        System.out.println("Pergantian Hari: " + pergantianHari);

        scanner.close();
    }
}