import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String nim = bacaInput();
        if (nim != null) {
            String[] hasil = proses(nim);
            if (hasil != null) {
                cetakOutput(nim, hasil);
            }
        }
    }

    private static String bacaInput() {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return null;
        String nim = scanner.nextLine().trim();
        
        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return null;
        }
        
        // Validasi agar tidak crash jika mengandung non-digit di bagian angka
        try {
            Integer.parseInt(nim.substring(3, 5));
            Integer.parseInt(nim.substring(5, 8));
        } catch (NumberFormatException e) {
            System.out.println("Format NIM tidak valid (mengandung huruf di bagian angka)");
            return null;
        }
        return nim;
    }

    private static String[] proses(String nim) {
        String prefix = nim.substring(0, 3);
        String programStudi;
        
        switch (prefix) {
            case "11S": programStudi = "Sarjana Informatika"; break;
            case "12S": programStudi = "Sarjana Sistem Informasi"; break;
            case "13S": programStudi = "Sarjana Teknik Elektro"; break;
            case "21S": programStudi = "Sarjana Manajemen Rekayasa"; break;
            case "22S": programStudi = "Sarjana Teknik Metalurgi"; break;
            case "31S": programStudi = "Sarjana Teknik Bioproses"; break;
            case "32S": programStudi = "Sarjana Bioteknologi"; break;
            case "114": programStudi = "Diploma 4 Teknologi Rekayasa Perangkat Lunak"; break;
            case "113": programStudi = "Diploma 3 Teknologi Informasi"; break;
            case "133": programStudi = "Diploma 3 Teknologi Komputer"; break;
            default: 
                System.out.println("Kode tidak tersedia");
                return null;
        }
        
        int angkatan = Integer.parseInt("20" + nim.substring(3, 5));
        int urutan = Integer.parseInt(nim.substring(5, 8));
        
        return new String[]{programStudi, String.valueOf(angkatan), String.valueOf(urutan)};
    }

    private static void cetakOutput(String nim, String[] hasil) {
        // Typo "Inforamsi" diperbaiki menjadi "Informasi"
        System.out.println("Informasi NIM " + nim + ":");
        System.out.println(">> Program Studi: " + hasil[0]);
        System.out.println(">> Angkatan: " + hasil[1]);
        System.out.println(">> Urutan: " + hasil[2]);
    }
}