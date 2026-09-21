import java.util.Scanner;

public class App {
    private static final String[] SIMBOL = {"PA", "T", "K", "P", "UTS", "UAS"};
    private static final String[] NAMA = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

    public static void main(String[] args) {
        int[] bobot = bacaBobot();
        if (bobot == null) return;
        
        int[][] data = bacaData();
        if (data == null) return;
        
        proses(bobot, data);
    }

    private static int[] bacaBobot() {
        Scanner scanner = new Scanner(System.in);
        int[] bobot = new int[6];
        int total = 0;
        
        for (int i = 0; i < 6; i++) {
            if (!scanner.hasNextInt()) return null;
            bobot[i] = scanner.nextInt();
            total += bobot[i];
        }
        
        if (total != 100) {
            System.out.println("Total bobot harus 100");
            return null;
        }
        scanner.nextLine(); // consume newline
        return bobot;
    }

    private static int[][] bacaData() {
        Scanner scanner = new Scanner(System.in);
        int[] totalMuncul = new int[6];
        int[] perolehan = new int[6];
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;
            
            String[] bagian = line.split("\\|");
            if (bagian.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }
            
            String simbol = bagian[0].trim();
            int idx = getIndex(simbol);
            if (idx == -1) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }
            
            try {
                int b = Integer.parseInt(bagian[1].trim());
                int p = Integer.parseInt(bagian[2].trim());
                p = Math.max(0, Math.min(p, b)); // Batasi perolehan agar tidak melebihi bobot
                totalMuncul[idx] += b;
                perolehan[idx] += p;
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
            }
        }
        return new int[][]{totalMuncul, perolehan};
    }

    private static int getIndex(String simbol) {
        for (int i = 0; i < SIMBOL.length; i++) {
            if (SIMBOL[i].equals(simbol)) return i;
        }
        return -1;
    }

    private static void proses(int[] bobot, int[][] data) {
        int[] totalMuncul = data[0];
        int[] perolehan = data[1];
        int[] persen = new int[6];
        double[] kontribusi = new double[6];
        double nilaiAkhir = 0;
        
        for (int i = 0; i < 6; i++) {
            persen[i] = (totalMuncul[i] == 0) ? 0 : (perolehan[i] * 100) / totalMuncul[i];
            kontribusi[i] = (persen[i] / 100.0) * bobot[i];
            nilaiAkhir += kontribusi[i];
        }
        
        String grade = tentukanGrade(nilaiAkhir);
        cetakOutput(bobot, persen, kontribusi, nilaiAkhir, grade);
    }

    private static String tentukanGrade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72.0) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57.0) return "BC"; // Diperbaiki dari > 56.5 menjadi >= 57.0
        if (nilai >= 49.5) return "C";
        if (nilai >= 34.0) return "D";
        return "E";
    }

    private static void cetakOutput(int[] bobot, int[] persen, double[] kontribusi, double nilaiAkhir, String grade) {
        System.out.println("Perolehan Nilai:");
        for (int i = 0; i < 6; i++) {
            System.out.printf(">> %s: %d/100 (%.2f/%d)%n", NAMA[i], persen[i], kontribusi[i], bobot[i]);
        }
        System.out.printf("%n>> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + grade);
    }
}