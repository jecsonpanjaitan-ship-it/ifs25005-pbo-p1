import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membaca 6 bobot komponen
        int bobotPA = scanner.nextInt();
        int bobotT = scanner.nextInt();
        int bobotK = scanner.nextInt();
        int bobotP = scanner.nextInt();
        int bobotUTS = scanner.nextInt();
        int bobotUAS = scanner.nextInt();

        scanner.nextLine();

        // Memastikan total bobot = 100
        int totalBobot = bobotPA + bobotT + bobotK
                + bobotP + bobotUTS + bobotUAS;

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        // Total bobot yang benar-benar muncul pada data
        int totalPA = 0;
        int totalT = 0;
        int totalK = 0;
        int totalP = 0;
        int totalUTS = 0;
        int totalUAS = 0;

        // Total perolehan masing-masing komponen
        int perolehanPA = 0;
        int perolehanT = 0;
        int perolehanK = 0;
        int perolehanP = 0;
        int perolehanUTS = 0;
        int perolehanUAS = 0;

        // Membaca data komponen sampai ---
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("---")) {
                break;
            }

            String[] bagian = line.split("\\|");

            // Format harus terdiri dari 3 bagian
            if (bagian.length != 3) {
                System.out.println(
                    "Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai"
                );
                continue;
            }

            String simbol = bagian[0].trim();

            int bobot;
            int perolehan;

            // Mengubah bobot dan perolehan menjadi angka
            try {
                bobot = Integer.parseInt(bagian[1].trim());
                perolehan = Integer.parseInt(bagian[2].trim());
            } catch (NumberFormatException e) {
                System.out.println(
                    "Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai"
                );
                continue;
            }

            // Validasi simbol dan sekaligus menyimpan data
            switch (simbol) {
                case "PA":
                    perolehan = Math.max(0, Math.min(perolehan, bobot));
                    totalPA += bobot;
                    perolehanPA += perolehan;
                    break;

                case "T":
                    perolehan = Math.max(0, Math.min(perolehan, bobot));
                    totalT += bobot;
                    perolehanT += perolehan;
                    break;

                case "K":
                    perolehan = Math.max(0, Math.min(perolehan, bobot));
                    totalK += bobot;
                    perolehanK += perolehan;
                    break;

                case "P":
                    perolehan = Math.max(0, Math.min(perolehan, bobot));
                    totalP += bobot;
                    perolehanP += perolehan;
                    break;

                case "UTS":
                    perolehan = Math.max(0, Math.min(perolehan, bobot));
                    totalUTS += bobot;
                    perolehanUTS += perolehan;
                    break;

                case "UAS":
                    perolehan = Math.max(0, Math.min(perolehan, bobot));
                    totalUAS += bobot;
                    perolehanUAS += perolehan;
                    break;

                default:
                    System.out.println("Simbol tidak dikenal");
                    continue;
            }
        }

        // Menghitung persentase perolehan
        int persenPA = totalPA == 0 ? 0 : (perolehanPA * 100) / totalPA;
        int persenT = totalT == 0 ? 0 : (perolehanT * 100) / totalT;
        int persenK = totalK == 0 ? 0 : (perolehanK * 100) / totalK;
        int persenP = totalP == 0 ? 0 : (perolehanP * 100) / totalP;
        int persenUTS = totalUTS == 0 ? 0 : (perolehanUTS * 100) / totalUTS;
        int persenUAS = totalUAS == 0 ? 0 : (perolehanUAS * 100) / totalUAS;

        // Menghitung kontribusi terhadap nilai akhir
        double kontribusiPA = (persenPA / 100.0) * bobotPA;
        double kontribusiT = (persenT / 100.0) * bobotT;
        double kontribusiK = (persenK / 100.0) * bobotK;
        double kontribusiP = (persenP / 100.0) * bobotP;
        double kontribusiUTS = (persenUTS / 100.0) * bobotUTS;
        double kontribusiUAS = (persenUAS / 100.0) * bobotUAS;

        double nilaiAkhir = kontribusiPA
                + kontribusiT
                + kontribusiK
                + kontribusiP
                + kontribusiUTS
                + kontribusiUAS;

        // Menentukan grade
        String grade;

        if (nilaiAkhir >= 79.5) {
            grade = "A";
        } else if (nilaiAkhir >= 72) {
            grade = "AB";
        } else if (nilaiAkhir >= 64.5) {
            grade = "B";
        } else if (nilaiAkhir >= 57) {
            grade = "BC";
        } else if (nilaiAkhir >= 49.5) {
            grade = "C";
        } else if (nilaiAkhir >= 34) {
            grade = "D";
        } else {
            grade = "E";
        }

        // Menampilkan hasil
        System.out.println("Perolehan Nilai:");
        System.out.printf(">> Partisipatif: %d/100 (%.2f/%d)%n",
                persenPA, kontribusiPA, bobotPA);
        System.out.printf(">> Tugas: %d/100 (%.2f/%d)%n",
                persenT, kontribusiT, bobotT);
        System.out.printf(">> Kuis: %d/100 (%.2f/%d)%n",
                persenK, kontribusiK, bobotK);
        System.out.printf(">> Proyek: %d/100 (%.2f/%d)%n",
                persenP, kontribusiP, bobotP);
        System.out.printf(">> UTS: %d/100 (%.2f/%d)%n",
                persenUTS, kontribusiUTS, bobotUTS);
        System.out.printf(">> UAS: %d/100 (%.2f/%d)%n",
                persenUAS, kontribusiUAS, bobotUAS);

        System.out.printf("%n>> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + grade);

        scanner.close();
    }
}