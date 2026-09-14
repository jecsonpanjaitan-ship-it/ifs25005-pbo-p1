import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        // Membaca matriks
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Kasus khusus 1x1
        if (n == 1) {
            int tengah = matrix[0][0];

            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + tengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + tengah);

            scanner.close();
            return;
        }

        // Kasus khusus 2x2
        if (n == 2) {
            int total = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    total += matrix[i][j];
                }
            }

            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + total);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + total);

            scanner.close();
            return;
        }

        // Menghitung Nilai L
        int nilaiL = 0;

        // Seluruh kolom pertama
        for (int i = 0; i < n; i++) {
            nilaiL += matrix[i][0];
        }

        // Baris terakhir, kecuali pojok kanan bawah
        for (int j = 1; j < n - 1; j++) {
            nilaiL += matrix[n - 1][j];
        }

        // Menghitung Nilai Kebalikan L
        int nilaiKebalikanL = 0;

        // Seluruh kolom terakhir
        for (int i = 0; i < n; i++) {
            nilaiKebalikanL += matrix[i][n - 1];
        }

        // Baris pertama, kecuali pojok kiri atas
        for (int j = 1; j < n - 1; j++) {
            nilaiKebalikanL += matrix[0][j];
        }

        // Menghitung Nilai Tengah
        int nilaiTengah;

        if (n % 2 == 1) {
            // Matriks ganjil
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            // Matriks genap
            int tengah = n / 2;

            nilaiTengah = matrix[tengah - 1][tengah - 1]
                    + matrix[tengah - 1][tengah]
                    + matrix[tengah][tengah - 1]
                    + matrix[tengah][tengah];
        }

        // Menghitung perbedaan absolut
        int perbedaan = Math.abs(nilaiL - nilaiKebalikanL);

        // Menentukan dominan
        int dominan;

        if (perbedaan == 0) {
            dominan = nilaiTengah;
        } else {
            dominan = Math.max(nilaiL, nilaiKebalikanL);
        }

        // Menampilkan hasil
        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);

        scanner.close();
    }
}