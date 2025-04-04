import java.util.Scanner;

class SinemaMusteriKayitSistemi {
    // Film bilgileri için diziler
    private static String[] filmAdlari = new String[10];
    private static int[] filmSureleri = new int[10];
    private static String[] filmTurleri = new String[10];
    private static int filmSayisi = 0;

    // Müşteri bilgileri için diziler
    private static String[] musteriAdlari = new String[20];
    private static String[] musteriEmailleri = new String[20];
    private static int musteriSayisi = 0;

    // Bilet bilgileri için dizi (her müşteri için bir film indeksi)
    private static int[][] biletler = new int[20][10]; // [müşteri indeksi][film indeksi]
    private static int[] biletSayilari = new int[20]; // Her müşterinin kaç bileti var

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int secim;

        do {
            menuGoster();
            System.out.print("Seçiminizi yapın: ");
            secim = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

            switch (secim) {
                case 1:
                    filmEkle();
                    break;
                case 2:
                    filmleriListele();
                    break;
                case 3:
                    musteriEkle();
                    break;
                case 4:
                    musterileriListele();
                    break;
                case 5:
                    biletOlustur();
                    break;
                case 6:
                    biletleriListele();
                    break;
                case 0:
                    System.out.println("Çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        } while (secim != 0);
    }

    private static void menuGoster() {
        System.out.println("\n==== SİNEMA MÜŞTERİ KAYIT SİSTEMİ ====");
        System.out.println("1. Film Ekle");
        System.out.println("2. Filmleri Listele");
        System.out.println("3. Müşteri Ekle");
        System.out.println("4. Müşterileri Listele");
        System.out.println("5. Bilet Oluştur");
        System.out.println("6. Biletleri Listele");
        System.out.println("0. Çıkış");
        System.out.println("=====================================");
    }

    private static void filmEkle() {
        if (filmSayisi >= 10) {
            System.out.println("Film ekleme limiti doldu! (Maksimum 10 film)");
            return;
        }

        System.out.println("\n==== FİLM EKLE ====");
        System.out.print("Film Adı: ");
        String filmAdi = scanner.nextLine();

        System.out.print("Film Süresi (dakika): ");
        int filmSuresi = scanner.nextInt();
        scanner.nextLine(); // Buffer temizleme

        System.out.print("Film Türü: ");
        String filmTuru = scanner.nextLine();

        // Film bilgilerini dizilere ekle
        filmAdlari[filmSayisi] = filmAdi;
        filmSureleri[filmSayisi] = filmSuresi;
        filmTurleri[filmSayisi] = filmTuru;
        filmSayisi++;

        System.out.println("Film başarıyla eklendi!");
    }

    private static void filmleriListele() {
        if (filmSayisi == 0) {
            System.out.println("Henüz film eklenmemiş!");
            return;
        }

        System.out.println("\n==== FİLM LİSTESİ ====");
        System.out.println("ID | Film Adı | Süre (dk) | Tür");
        System.out.println("----------------------------");

        for (int i = 0; i < filmSayisi; i++) {
            System.out.println((i + 1) + " | " + filmAdlari[i] + " | " +
                    filmSureleri[i] + " | " + filmTurleri[i]);
        }
    }

    private static void musteriEkle() {
        if (musteriSayisi >= 20) {
            System.out.println("Müşteri ekleme limiti doldu! (Maksimum 20 müşteri)");
            return;
        }

        System.out.println("\n==== MÜŞTERİ EKLE ====");
        System.out.print("Müşteri Adı: ");
        String musteriAdi = scanner.nextLine();

        System.out.print("Müşteri Email: ");
        String musteriEmail = scanner.nextLine();

        // Müşteri bilgilerini dizilere ekle
        musteriAdlari[musteriSayisi] = musteriAdi;
        musteriEmailleri[musteriSayisi] = musteriEmail;
        musteriSayisi++;

        System.out.println("Müşteri başarıyla eklendi!");
    }

    private static void musterileriListele() {
        if (musteriSayisi == 0) {
            System.out.println("Henüz müşteri eklenmemiş!");
            return;
        }

        System.out.println("\n==== MÜŞTERİ LİSTESİ ====");
        System.out.println("ID | Müşteri Adı | Email");
        System.out.println("----------------------------");

        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println((i + 1) + " | " + musteriAdlari[i] + " | " + musteriEmailleri[i]);
        }
    }

    private static void biletOlustur() {
        if (filmSayisi == 0) {
            System.out.println("Önce film eklemelisiniz!");
            return;
        }

        if (musteriSayisi == 0) {
            System.out.println("Önce müşteri eklemelisiniz!");
            return;
        }

        System.out.println("\n==== BİLET OLUŞTUR ====");

        // Müşteri seçimi
        musterileriListele();
        System.out.print("Müşteri ID seçin (1-" + musteriSayisi + "): ");
        int musteriId = scanner.nextInt() - 1; // Dizinin indeksi için -1
        scanner.nextLine(); // Buffer temizleme

        if (musteriId < 0 || musteriId >= musteriSayisi) {
            System.out.println("Geçersiz müşteri ID!");
            return;
        }

        // Film seçimi
        filmleriListele();
        System.out.print("Film ID seçin (1-" + filmSayisi + "): ");
        int filmId = scanner.nextInt() - 1; // Dizinin indeksi için -1
        scanner.nextLine(); // Buffer temizleme

        if (filmId < 0 || filmId >= filmSayisi) {
            System.out.println("Geçersiz film ID!");
            return;
        }

        // Bilet oluştur
        biletler[musteriId][biletSayilari[musteriId]] = filmId;
        biletSayilari[musteriId]++;

        System.out.println("Bilet başarıyla oluşturuldu!");
        System.out.println("Müşteri: " + musteriAdlari[musteriId]);
        System.out.println("Film: " + filmAdlari[filmId]);
    }

    private static void biletleriListele() {
        boolean biletVar = false;

        System.out.println("\n==== BİLET LİSTESİ ====");
        System.out.println("Müşteri | Film | Süre | Tür");
        System.out.println("----------------------------");

        for (int i = 0; i < musteriSayisi; i++) {
            for (int j = 0; j < biletSayilari[i]; j++) {
                int filmId = biletler[i][j];
                System.out.println(musteriAdlari[i] + " | " +
                        filmAdlari[filmId] + " | " +
                        filmSureleri[filmId] + " dk | " +
                        filmTurleri[filmId]);
                biletVar = true;
            }
        }

        if (!biletVar) {
            System.out.println("Henüz bilet oluşturulmamış!");
        }
    }
}