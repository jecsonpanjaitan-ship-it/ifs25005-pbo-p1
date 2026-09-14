import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Integer> frekuensi = new HashMap<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            if (input.equals("---")) {
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            int nilai = Integer.parseInt(input);

            frekuensi.put(nilai, frekuensi.getOrDefault(nilai, 0) + 1);
        }

        scanner.close();

        // Jika tidak ada data
        if (frekuensi.isEmpty()) {
            return;
        }

        int tertinggi = Integer.MIN_VALUE;
        int terendah = Integer.MAX_VALUE;

        int terbanyak = 0;
        int tersedikit = 0;

        int jumlahTertinggi = 0;
        int jumlahTerendah = 0;

        boolean pertama = true;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int jumlah = entry.getValue();

            // Tertinggi
            if (nilai > tertinggi) {
                tertinggi = nilai;
            }

            // Terendah
            if (nilai < terendah) {
                terendah = nilai;
            }

            // Terbanyak
            if (pertama || jumlah > frekuensi.get(terbanyak)
                    || (jumlah == frekuensi.get(terbanyak) && nilai > terbanyak)) {
                terbanyak = nilai;
            }

            // Tersedikit
            if (pertama || jumlah < frekuensi.get(tersedikit)
                    || (jumlah == frekuensi.get(tersedikit) && nilai < tersedikit)) {
                tersedikit = nilai;
            }

            int hasil = nilai * jumlah;

            // Jumlah Tertinggi
            if (pertama || hasil > jumlahTertinggi
                    || (hasil == jumlahTertinggi && nilai > terbanyak)) {
                jumlahTertinggi = hasil;
            }

            // Jumlah Terendah
            if (pertama || hasil < jumlahTerendah
                    || (hasil == jumlahTerendah && nilai < tersedikit)) {
                jumlahTerendah = hasil;
            }

            pertama = false;
        }

        // Cari nilai yang menghasilkan jumlah tertinggi
        int nilaiJumlahTertinggi = 0;
        int nilaiJumlahTerendah = 0;

        boolean pertamaJumlah = true;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int jumlah = entry.getValue();
            int hasil = nilai * jumlah;

            if (pertamaJumlah
                    || hasil > jumlahTertinggi
                    || (hasil == jumlahTertinggi && nilai > nilaiJumlahTertinggi)) {
                jumlahTertinggi = hasil;
                nilaiJumlahTertinggi = nilai;
            }

            if (pertamaJumlah
                    || hasil < jumlahTerendah
                    || (hasil == jumlahTerendah && nilai < nilaiJumlahTerendah)) {
                jumlahTerendah = hasil;
                nilaiJumlahTerendah = nilai;
            }

            pertamaJumlah = false;
        }

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyak + " (" + frekuensi.get(terbanyak) + "x)");
        System.out.println("Tersedikit: " + tersedikit + " (" + frekuensi.get(tersedikit) + "x)");
        System.out.println("Jumlah Tertinggi: " + nilaiJumlahTertinggi + " * "
                + frekuensi.get(nilaiJumlahTertinggi) + " = " + jumlahTertinggi);
        System.out.println("Jumlah Terendah: " + nilaiJumlahTerendah + " * "
                + frekuensi.get(nilaiJumlahTerendah) + " = " + jumlahTerendah);
    }
}