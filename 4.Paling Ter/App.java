import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Map<Integer, Integer> frekuensi = bacaInput();
        if (frekuensi != null && !frekuensi.isEmpty()) {
            int[] hasil = proses(frekuensi);
            cetakOutput(frekuensi, hasil);
        }
    }

    private static Map<Integer, Integer> bacaInput() {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> frekuensi = new HashMap<>();
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) break;
            if (input.isEmpty()) continue;
            
            try {
                int nilai = Integer.parseInt(input);
                frekuensi.put(nilai, frekuensi.getOrDefault(nilai, 0) + 1);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, harap masukkan angka.");
            }
        }
        return frekuensi;
    }

    private static int[] proses(Map<Integer, Integer> frekuensi) {
        int tertinggi = Integer.MIN_VALUE;
        int terendah = Integer.MAX_VALUE;
        int terbanyak = 0, tersedikit = 0;
        int nilaiJumlahTertinggi = 0, nilaiJumlahTerendah = 0;
        boolean pertama = true;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int jumlah = entry.getValue();
            int hasilKali = nilai * jumlah;

            if (nilai > tertinggi) tertinggi = nilai;
            if (nilai < terendah) terendah = nilai;

            if (pertama) {
                terbanyak = nilai; tersedikit = nilai;
                nilaiJumlahTertinggi = nilai; nilaiJumlahTerendah = nilai;
            } else {
                if (jumlah > frekuensi.get(terbanyak) || (jumlah == frekuensi.get(terbanyak) && nilai > terbanyak)) {
                    terbanyak = nilai;
                }
                if (jumlah < frekuensi.get(tersedikit) || (jumlah == frekuensi.get(tersedikit) && nilai < tersedikit)) {
                    tersedikit = nilai;
                }
                if (hasilKali > (nilaiJumlahTertinggi * frekuensi.get(nilaiJumlahTertinggi)) || 
                    (hasilKali == (nilaiJumlahTertinggi * frekuensi.get(nilaiJumlahTertinggi)) && nilai > nilaiJumlahTertinggi)) {
                    nilaiJumlahTertinggi = nilai;
                }
                if (hasilKali < (nilaiJumlahTerendah * frekuensi.get(nilaiJumlahTerendah)) || 
                    (hasilKali == (nilaiJumlahTerendah * frekuensi.get(nilaiJumlahTerendah)) && nilai < nilaiJumlahTerendah)) {
                    nilaiJumlahTerendah = nilai;
                }
            }
            pertama = false;
        }
        return new int[]{tertinggi, terendah, terbanyak, tersedikit, nilaiJumlahTertinggi, nilaiJumlahTerendah};
    }

    private static void cetakOutput(Map<Integer, Integer> frekuensi, int[] hasil) {
        System.out.println("Tertinggi: " + hasil[0]);
        System.out.println("Terendah: " + hasil[1]);
        System.out.println("Terbanyak: " + hasil[2] + " (" + frekuensi.get(hasil[2]) + "x)");
        System.out.println("Tersedikit: " + hasil[3] + " (" + frekuensi.get(hasil[3]) + "x)");
        System.out.println("Jumlah Tertinggi: " + hasil[4] + " * " + frekuensi.get(hasil[4]) + " = " + (hasil[4] * frekuensi.get(hasil[4])));
        System.out.println("Jumlah Terendah: " + hasil[5] + " * " + frekuensi.get(hasil[5]) + " = " + (hasil[5] * frekuensi.get(hasil[5])));
    }
}