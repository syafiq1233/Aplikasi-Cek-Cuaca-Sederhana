package aplikasicekcuacasederhana;

import java.util.Scanner;

public class AplikasiCekCuacaSederhana {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        clearScreen();
        tampilkanHeader();

        while (true) {
            tampilkanMenu();
            System.out.print("Pilih menu (1-3): ");
            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    cekCuaca(input);
                    break;

                case "2":
                    tampilkanInfoAplikasi();
                    break;

                case "3":
                    System.out.println("\nTerima kasih telah menggunakan aplikasi ini!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid, coba lagi.\n");
            }
        }
    }

    // ========================
    //     FITUR UTAMA
    // ========================

    public static void cekCuaca(Scanner input) {
        System.out.print("\nMasukkan nama kota: ");
        String kota = input.nextLine();

        loading();

        String cuaca = getCuacaByKota(kota);
        String rekomendasi = getRekomendasiByCuaca(cuaca);

        System.out.println("=========================================");
        System.out.println("        HASIL CEK CUACA KOTA " + kota.toUpperCase());
        System.out.println("=========================================");
        System.out.println("Cuaca: " + cuaca);
        System.out.println("Rekomendasi: " + rekomendasi);
        System.out.println("=========================================\n");
    }

    // ========================
    //     DATA CUACA
    // ========================

    public static String getCuacaByKota(String kota) {
        if (kota == null) return "Tidak ada data cuaca.";
        kota = kota.toLowerCase().trim();

        switch (kota) {
            case "jakarta":
                return "Cerah Berawan, 32°C";
            case "bandung":
                return "Hujan Ringan, 24°C";
            case "surabaya":
                return "Panas Terik, 35°C";
            case "medan":
                return "Berawan, 30°C";
            case "yogyakarta":
                return "Cerah, 29°C";
            case "bali":
                return "Cerah, 31°C";
            default:
                return "Tidak ada data cuaca.";
        }
    }

    public static String getRekomendasiByCuaca(String cuaca) {
        if (cuaca == null) return "Tetap waspada terhadap kondisi cuaca sekitar.";

        if (cuaca.toLowerCase().contains("hujan")) {
            return "Bawa payung dan hindari berkendara terlalu cepat.";
        } else if (cuaca.toLowerCase().contains("panas")) {
            return "Gunakan sunscreen dan minum banyak air.";
        } else if (cuaca.toLowerCase().contains("cerah")) {
            return "Cuaca bagus untuk berolahraga atau pergi keluar!";
        } else if (cuaca.toLowerCase().contains("berawan")) {
            return "Cocok untuk aktivitas santai di luar.";
        } else {
            return "Tetap waspada terhadap kondisi cuaca sekitar.";
        }
    }

    // ========================
    //     TAMPILAN UI
    // ========================

    public static void tampilkanHeader() {
        System.out.println("===============================================");
        System.out.println("        APLIKASI CEK CUACA SEDERHANA");
        System.out.println("===============================================");
        System.out.println("           Dibuat dengan Java CLI");
        System.out.println("===============================================\n");
    }

    public static void tampilkanMenu() {
        System.out.println("Menu:");
        System.out.println("1. Cek Cuaca Kota");
        System.out.println("2. Tentang Aplikasi");
        System.out.println("3. Keluar\n");
    }

    public static void tampilkanInfoAplikasi() {
        System.out.println("\n======================================");
        System.out.println("      INFORMASI APLIKASI");
        System.out.println("======================================");
        System.out.println("Aplikasi ini dibuat menggunakan Java.");
        System.out.println("Fitur:");
        System.out.println("- Cek cuaca sederhana");
        System.out.println("- Rekomendasi aktivitas");
        System.out.println("- Tampilan UI yang lebih menarik");
        System.out.println("======================================\n");
    }

    // ========================
    //     ANIMASI LOADING
    // ========================

    public static void loading() {
        System.out.print("\nMengambil data cuaca");
        for (int i = 0; i < 3; i++) {
            sleep(400);
            System.out.print(".");
        }
        System.out.println("\n");
    }

    // ========================
    //   UTILITAS SYSTEM
    // ========================

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Clear screen yang lebih aman:
     * - Coba jalankan perintah OS (cls untuk Windows, atau escape ANSI untuk Unix)
     * - Jika gagal (mis. permission atau environment), lakukan fallback print baris kosong
     */
    private static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                // gunakan cmd /c cls pada Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // escape sequence ANSI untuk terminal Unix-like
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // fallback sederhana: cetak beberapa baris kosong
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
