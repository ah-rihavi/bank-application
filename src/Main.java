import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    static Terminal terminal = new Terminal();
    static AnaMenu anaMenu = new AnaMenu();
    static IlkMenu ilkMenu = new IlkMenu();
    static BaslangicMenusu baslangicMenusu = new BaslangicMenusu();
    static GirisMenusu girisMenusu = new GirisMenusu();
    static KayitMenusu kayitMenusu = new KayitMenusu();
    static HesapKartMenusu hesapKartMenusu = new HesapKartMenusu();
    static HesapMenusu hesapMenusu = new HesapMenusu();
    static YeniHesapMenusu yeniHesapMenusu = new YeniHesapMenusu();
    static KartMenusu kartMenusu = new KartMenusu();
    static YeniKartMenusu yeniKartMenusu = new YeniKartMenusu();
    static ParaTransferMenusu paraTransferMenusu = new ParaTransferMenusu();
    static KayitliIslemlerMenusu kayitliIslemlerMenusu = new KayitliIslemlerMenusu();
    static OdemelerMenusu odemelerMenusu = new OdemelerMenusu();
    static FaturalarMenusu faturalarMenusu = new FaturalarMenusu();
    static TrafikOdemeleriMenusu trafikOdemeleriMenusu = new TrafikOdemeleriMenusu();
    static EgitimOdemeleriMenusu egitimOdemeleriMenusu = new EgitimOdemeleriMenusu();
    static Kampanyalar kampanyalar = new Kampanyalar();
    static OdemeMenusu odemeMenusu = new OdemeMenusu();

    static void ilkMenu(){
        int secim;
        int bankaKodu = 0;

        do {
            ilkMenu.Menu();
            secim = ilkMenu.secim();

            switch (secim) {
                case 1 -> bankaKodu = 101;
                case 2 -> bankaKodu = 102;
                case 3 -> bankaKodu = 103;
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
            // Seçim 1, 2 veya 3 DEĞİLSE dönmeye devam et
        } while (secim < 1 || secim > 3);

        baslangicMenusu(bankaKodu);
    }

    static void baslangicMenusu(int bankaKodu){
        int secim;
        do {
            baslangicMenusu.Menu();
            secim = baslangicMenusu.secim();

            switch (secim) {
                case 1 -> girisMenusu(bankaKodu);
                case 2 -> kayitMenusu(bankaKodu);
                case 0 -> ilkMenu();
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        } while (secim < 0 || secim > 2);
    }

    static void girisMenusu(int bankaKodu){
        int secim;
        String kullaniciAdi;
        int sifre;
        do {
            System.out.println("Lütfen isminizi ve şifrenizi doğru bir şekilde giriniz.");
            kullaniciAdi = girisMenusu.kullaniciAdi();
            sifre = girisMenusu.sifre();

            girisMenusu.Menu();
            secim = girisMenusu.secim();

            switch (secim) {
                case 0 -> baslangicMenusu(bankaKodu);
                case -1 -> System.exit(0);
                default -> {
                    if (secim != 1) {
                        System.err.println("Lütfen geçerli bir seçim giriniz!");
                    }
                }
            }
        } while (!terminal.musteriVarMi(bankaKodu, kullaniciAdi, sifre));

        long musteriNo = terminal.musteriNoBul(bankaKodu, kullaniciAdi, sifre);
        int ilkHesapNo = terminal.hesapNoBul(bankaKodu, musteriNo, 0);
        anaMenu(bankaKodu, musteriNo, ilkHesapNo);
    }

    static void kayitMenusu(int bankaKodu) {
        int secim;
        String kullaniciAdi, kullaniciSoyadi;
        int sifre, sifreTekrar;
        long TC;

        do {
            kullaniciAdi = kayitMenusu.kullaniciAdi();
            kullaniciSoyadi = kayitMenusu.kullaniciSoyAdi();
            TC = kayitMenusu.kullaniciTC();
            sifre = kayitMenusu.kullaniciSifre();
            System.out.print("Tekrar ");
            sifreTekrar = kayitMenusu.kullaniciSifre();

            kayitMenusu.Menu();
            secim = kayitMenusu.secim();

            switch (secim){
                case 1 -> {
                    if (sifre == sifreTekrar) {
                        terminal.musteriKayit(bankaKodu, sifre, TC, kullaniciAdi, kullaniciSoyadi);
                        System.out.println("Vadesiz hesap için otomatik olarak kart tanımlanacaktır. Kart şifresi girinz.");
                        int ilkKartSifresi = yeniKartMenusu.sifre();
                        String adSoyad = kullaniciAdi + " " + kullaniciSoyadi;
                        long musteriNo = terminal.musteriNoBul(bankaKodu, adSoyad, sifre);
                        int ilkHesapNo = terminal.hesapNoBul(bankaKodu, musteriNo, 0);
                        terminal.nakitKartiOlustur(bankaKodu, musteriNo, ilkHesapNo, ilkKartSifresi, "TROY");
                    }
                    else {
                        System.err.println("Şifre tekrarı uyuşmamaktadır!");
                    }
                }
                case 0 -> baslangicMenusu(bankaKodu);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < -1 || secim > 1 || sifre != sifreTekrar);
        String adSoyad = kullaniciAdi + " " + kullaniciSoyadi;
        long musteriNo = terminal.musteriNoBul(bankaKodu, adSoyad, sifre);
        int ilkHesapNo = terminal.hesapNoBul(bankaKodu, musteriNo, 0);
        anaMenu(bankaKodu, musteriNo, ilkHesapNo);
    }

    static void anaMenu(int bankaKodu, long musteriNo, int hesapNo){
        System.out.println("HesapNo: " + hesapNo);
        System.out.println("IBAN: " + terminal.IbanNoBul(bankaKodu, musteriNo, hesapNo));
        System.out.println("Bakiye: " + terminal.hesapBakiyesi(bankaKodu, musteriNo, hesapNo));
        System.out.print("Son İşlem: ");
        terminal.sonIslem(bankaKodu, musteriNo, hesapNo);
        int secim;
        do {
            anaMenu.Menu();
            secim = anaMenu.secim();

            switch (secim){
                case 1 -> hesapKartMenusu(bankaKodu, musteriNo, hesapNo);
                case 2 -> paraTransferMenusu(bankaKodu, musteriNo, hesapNo);
                case 3 -> odemelerMenusu(bankaKodu, musteriNo, hesapNo);
                case 4 -> kayitliIslemlerMenusu(bankaKodu, musteriNo, hesapNo);
                case 5 -> kampanyalarMenusu(bankaKodu, musteriNo, hesapNo);
                case 0 -> baslangicMenusu(bankaKodu);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        } while (secim < -2 || secim > 5);

    }

    static void hesapKartMenusu(int bankaKodu, long musteriNo, int hesapNo){
        int secim;
        do {
            hesapKartMenusu.Menu();
            secim = hesapKartMenusu.secim();

            switch (secim){
                case 1 -> hesapMenusu(bankaKodu, musteriNo, hesapNo);
                case 2 -> kartMenusu(bankaKodu, musteriNo, hesapNo);
                case 3 ->  yeniHesapMenusu(bankaKodu, musteriNo, hesapNo);
                case  4 -> yeniKartMenusu(bankaKodu, musteriNo, hesapNo);
                case 0 -> anaMenu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < -1 || secim > 4);

    }

    static void hesapMenusu(int bankaKodu, long musteriNo, int hesapNo){
        terminal.hesaplariGoster(bankaKodu, musteriNo);
        int secim;
        do {
            hesapMenusu.Menu();
            secim = hesapMenusu.secim();

            switch (secim){
                case 0 -> anaMenu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                case -2 -> {
                    System.out.print("Hesap sırası giriniz: ");
                    int hesapSirasi = input.nextInt();input.nextLine();
                    hesapNo = terminal.hesapNoBul(bankaKodu, musteriNo, hesapSirasi - 1);
                    anaMenu(bankaKodu, musteriNo, hesapNo);
                }
                default -> {
                    if (secim <= terminal.hesapSayisi(bankaKodu, musteriNo) && secim > -2){
                        hesapNo = terminal.hesapNoBul(bankaKodu, musteriNo, secim - 1);
                        terminal.hesapBilgileriniGoster(bankaKodu, musteriNo, hesapNo);
                    }
                    else System.err.println("Lütfen geçerli bir seçim giriniz!");
                }
            }

        }while (true);

    }

    static void kartMenusu(int bankaKodu, long musteriNo, int hesapNo){
        terminal.kartlariGoster(bankaKodu, musteriNo, hesapNo);
        int secim;
        do {
            kartMenusu.Menu();
            secim = kartMenusu.secim();

            switch (secim){
                case 0 -> anaMenu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> {
                    if (secim <= terminal.kartSayisi(bankaKodu, musteriNo, hesapNo) && secim > -1){
                        long kartNo = terminal.kartNoBul(bankaKodu, musteriNo, hesapNo, secim - 1);
                        terminal.kartBilgileriniGoster(bankaKodu, musteriNo, hesapNo, kartNo);
                    }
                    else System.err.println("Lütfen geçerli bir seçim giriniz!");
                }
            }

        }while (true);
    }

    static void yeniHesapMenusu(int bankaKodu, long musterino, int hesapNo){

        int secim;
        do {
            yeniHesapMenusu.Menu();
            secim = yeniHesapMenusu.secim();

            switch (secim){
                case 1 -> {
                    terminal.vadesizHesapAc(bankaKodu, musterino);
                    System.out.println("Vadesiz hesap için otomatik olarak kart tanımlanacaktır. Kart şifresi giriinz.");
                    int ilkKartSifresi = yeniKartMenusu.sifre();
                    terminal.nakitKartiOlustur(bankaKodu, musterino, hesapNo, ilkKartSifresi, "TROY");
                }
                case 2 -> terminal.vadeliHesapAc(bankaKodu, musterino);
                case 0 -> anaMenu(bankaKodu, musterino, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }

        }while (secim < -1 || secim > 2);

        hesapMenusu(bankaKodu, musterino, hesapNo);
    }

    static void yeniKartMenusu(int bankaKodu, long musterino, int hesapNo){

        int secim;
        do {
            yeniKartMenusu.Menu();
            secim = yeniKartMenusu.secim();

            switch (secim){
                case 1 -> {
                    String cipTuru = yeniKartMenusu.cipTuru();
                    int sifre = yeniKartMenusu.sifre();
                    terminal.nakitKartiOlustur(bankaKodu, musterino, hesapNo, sifre, cipTuru);
                }
                case 2 -> {
                    String cipTuru = yeniKartMenusu.cipTuru();
                    int sifre = yeniKartMenusu.sifre();
                    terminal.krediKartiOlustur(bankaKodu, musterino, hesapNo, sifre, cipTuru);
                }
                case 3 -> {
                    String cipTuru = yeniKartMenusu.cipTuru();
                    int limit = yeniKartMenusu.limit();
                    terminal.sanalKartOlustur(bankaKodu, musterino, hesapNo, limit, cipTuru);
                }
                case 0 -> anaMenu(bankaKodu, musterino, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }

        }while (secim < -1 || secim > 3);

        kartMenusu(bankaKodu, musterino, hesapNo);
    }

    static void paraTransferMenusu(int bankaKodu, long musteriNo, int hesapNo){
        int secim;
        String IBAN;
        double miktar;
        do {
            IBAN = paraTransferMenusu.IBAN();
            miktar = paraTransferMenusu.miktar();

            paraTransferMenusu.Menu();
            secim = paraTransferMenusu.secim();

            switch (secim) {
                case 1 -> terminal.paraAktar(bankaKodu, musteriNo, hesapNo, IBAN, miktar);
                case 0 -> anaMenu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        } while (secim < -1 || secim > 1);

        anaMenu(bankaKodu, musteriNo, hesapNo);
    }

    static void kayitliIslemlerMenusu(int bankaKodu, long musteriNo, int hesapNo){
        int secim;
        terminal.kayitliIslemler(bankaKodu, musteriNo, hesapNo);
        do {
            kayitliIslemlerMenusu.Menu();
            secim = kayitMenusu.secim();
            switch (secim){
                case 0 -> anaMenu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < -1 || secim > 1);
    }

    static void odemelerMenusu(int bankaKodu, long musteriNo, int hesapNo){
        int secim;
        do {
            odemelerMenusu.Menu();
            secim = odemelerMenusu.secim();
            switch (secim){
                case 1 -> faturalarmenusu(bankaKodu, musteriNo, hesapNo);
                case 2 -> trafikOdemeleriMenusu(bankaKodu, musteriNo, hesapNo);
                case 3 ->  egitimOdemeleriMenusu(bankaKodu, musteriNo, hesapNo);
                case 0 -> anaMenu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < -1 || secim > 4);
    }

    static void faturalarmenusu(int bankaKodu, long musteriNo, int hesapNo){
        int secim;
        do {
            faturalarMenusu.Menu();
            secim = faturalarMenusu.secim();


            switch (secim){
                case 1 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 50, "Mobil hat Faturasi");
                    System.out.println("Mobil Hat Faturası ödendi.");
                }
                case 2 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 50, "Su Faturasi");
                    System.out.println("Su Faturası ödendi.");
                }
                case 3 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 50,  " Elektrik Faturasi");
                    System.out.println("Elektrik Faturası ödendi.");
                }
                case 4 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 50, "Doğalgaz Faturasi");
                    System.out.println("Doğalgaz Faturası ödendi.");
                }
                case 0 -> odemelerMenusu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < -1 || secim > 4);

        anaMenu(bankaKodu, musteriNo, hesapNo);
    }
    static void trafikOdemeleriMenusu(int bankaKodu, long musteriNo, int hesapNo){
        int secim;
        do {
            trafikOdemeleriMenusu.Menu();
            secim = trafikOdemeleriMenusu.secim();

            switch (secim){
                case 1 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 100, "HGS Odemesi");
                    System.out.println("HGS borcu ödendi.");
                }
                case 2 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 100, "Trafik Cezasi");
                    System.out.println("Trafik cezası ödendi.");
                }
                case 3 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 100, "MTV Vergisi");
                    System.out.println("MTV ödendi.");
                }
                case 0 -> odemelerMenusu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < -1 || secim > 4);

        anaMenu(bankaKodu, musteriNo, hesapNo);
    }
    static void egitimOdemeleriMenusu(int bankaKodu, long musteriNo, int hesapNo){

        int secim;
        do {
            egitimOdemeleriMenusu.Menu();
            secim = egitimOdemeleriMenusu.secim();
            switch (secim){
                case 1 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 75, "Univaersita Harci");
                    System.out.println("Üniversite harcı ödendi.");
                }
                case 2 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 75, "KYK Odemesi");
                    System.out.println("KYK borcu ödendi.");
                }
                case 3 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 75, "OSYM Odemesi");
                    System.out.println("ÖSYM sınav bedeli ödendi.");
                }
                case 4 -> {
                    odemeMenusu(bankaKodu, musteriNo, hesapNo, 75, "MEB Odemesi");
                    System.out.println("MEB sınav borcu ödendi.");
                }
                case 0 -> odemelerMenusu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < -1 || secim > 4);

        anaMenu(bankaKodu, musteriNo, hesapNo);
    }

    static void odemeMenusu(int bankaKodu, long musteriNo, int hesapNo, double miktar, String islemTuru){
        int secim;
        do {
            System.out.println("Ödenecek Tutar" + miktar + "tl devam etmek istiyor musunuz?");
            odemeMenusu.Menu();
            secim = odemeMenusu.secim();
            switch (secim){
                case 1 -> {
                    System.out.print("Kart No: ");
                    long kartNo = input.nextLong();input.nextLine();
                    terminal.paraHarca(bankaKodu, musteriNo, hesapNo, kartNo, miktar);
                    terminal.islemEkle(bankaKodu, musteriNo, hesapNo, -miktar, islemTuru);
                    odemelerMenusu(bankaKodu, musteriNo, hesapNo);
                }
                case 2 -> odemelerMenusu(bankaKodu, musteriNo, hesapNo);
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (secim < 1 || secim > 2);
    }
//git için test değişikliği
    static void kampanyalarMenusu(int bankaKodu, long musteriNo, int hesapNo){
        int secim;
        terminal.kampanyalariGoster(bankaKodu);
        do {
            kampanyalar.Menu();
            secim = kampanyalar.secim();

            switch (secim){
                case 0 -> anaMenu(bankaKodu, musteriNo, hesapNo);
                case -1 -> System.exit(0);
            }

        }while (secim < -1 || secim > 0);
    }
// Git için test değişikliği
    public static void main(String[] args) {

//        terminal.musteriKayit(101, 123456, 12345678910L, "ahmed", "rihavi" );
//        terminal.vadesizHesapAc(101, terminal.musteriNoBul(101, "ahmed rihavi", 123456));
//        terminal.vadeliHesapAc(101, terminal.musteriNoBul(101, "ahmed rihavi", 123456));

        terminal.musteriKayit(101, 987654, 98765432100L, "mehmed", "anbar");
        //System.out.println(terminal.getIBAN(101, terminal.musteriNoBul(101, "mehmed anbar", 987654), terminal.hesapNoBul(101, terminal.musteriNoBul(101, "mehmed anbar", 987654), 0)));
        //IBAN TR900101000000021010000201

        terminal.musteriKayit(102, 654321, 14725836900L, "Furkan", "ilaslan");


        ilkMenu();
    }
}