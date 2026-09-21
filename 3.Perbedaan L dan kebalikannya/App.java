import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int[][] matrix = bacaInput();
        if (matrix != null) {
            int[] hasil = proses(matrix);
            cetakOutput(hasil);
        }
    }

    private static int[][] bacaInput() {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return null;
        
        int n = scanner.nextInt();
        // Validasi nilai n
        if (n <= 0) {
            System.out.println("Nilai n harus lebih dari 0");
            return null;
        }
        
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!scanner.hasNextInt()) return null;
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static int[] proses(int[][] matrix) {
        int n = matrix.length;
        
        // Kasus khusus 1x1 dan 2x2
        if (n == 1) {
            return new int[]{0, 0, matrix[0][0], 0, matrix[0][0], 1}; // 1 = flag kasus 1x1
        }
        if (n == 2) {
            int total = 0;
            for (int[] row : matrix) for (int val : row) total += val;
            return new int[]{0, 0, total, 0, total, 2}; // 2 = flag kasus 2x2
        }

        int nilaiL = 0;
        for (int i = 0; i < n; i++) nilaiL += matrix[i][0];
        for (int j = 1; j < n - 1; j++) nilaiL += matrix[n - 1][j];

        int nilaiKebalikanL = 0;
        for (int i = 0; i < n; i++) nilaiKebalikanL += matrix[i][n - 1];
        for (int j = 1; j < n - 1; j++) nilaiKebalikanL += matrix[0][j];

        int nilaiTengah;
        if (n % 2 == 1) {
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            int t = n / 2;
            nilaiTengah = matrix[t - 1][t - 1] + matrix[t - 1][t] + matrix[t][t - 1] + matrix[t][t];
        }

        int perbedaan = Math.abs(nilaiL - nilaiKebalikanL);
        int dominan = (perbedaan == 0) ? nilaiTengah : Math.max(nilaiL, nilaiKebalikanL);

        return new int[]{nilaiL, nilaiKebalikanL, nilaiTengah, perbedaan, dominan, 0}; // 0 = kasus normal
    }

    private static void cetakOutput(int[] hasil) {
        if (hasil[5] == 1 || hasil[5] == 2) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + hasil[2]);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + hasil[4]);
        } else {
            System.out.println("Nilai L: " + hasil[0]);
            System.out.println("Nilai Kebalikan L: " + hasil[1]);
            System.out.println("Nilai Tengah: " + hasil[2]);
            System.out.println("Perbedaan: " + hasil[3]);
            System.out.println("Dominan: " + hasil[4]);
        }
    }
}