import BankaArkaPlani.*;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
    List<Banka> bankalar = new ArrayList<>();
    static Banka bank_a = new Bank_A();
    static Banka bank_b = new Bank_B();
    static Banka bank_c = new Bank_C();
    public Terminal(){
        bankalar.add(bank_a);
        bankalar.add(bank_b);
        bankalar.add(bank_c);
    }


    private  Banka bankayiDon(int bankaKodu) {
        Banka banka = null;
        for (Banka b : bankalar){
            if (b.getBankaKodu() == bankaKodu){
                banka = b;
            }
        }

        return banka;
    }

    public List<String> kampanyalar(int bankaKodu){
        Banka bank = bankayiDon(bankaKodu);
        return bank.kampanyalar;
    }

    public  void musteriKayit(int bankaKodu, int musteriSifre, long musteriTC, String ad, String soyad) {
        Banka bank = bankayiDon(bankaKodu);
        Musteri yeniMusteri =  Musteri.musteriOlustur(bank, musteriSifre, musteriTC, ad, soyad);
        yeniMusteri.hesaplar.get(0).setBakiye(1000);
        bank.musteriEkle(yeniMusteri);
        System.out.println("Müşteri başarıyla kaydedildi.\n");
    }
    public  boolean musteriVarMi(int bankaKodu, String kullaniciAdi, int sifre){
        Banka bank = bankayiDon(bankaKodu);
        int i = 0;
        Musteri musteri = null;
        while (musteri == null){
            if (bank.musteriler.get(i).getMusteriAdSoyad().equals(kullaniciAdi)){
               musteri = bank.musteriler.get(i);
                if (musteri.getMusteriSifre() == sifre) {
                    return true;
                }
            }
            else {
                i++;
            }
        }

        return false;
    }
    public  long musteriNoBul(int bankaKodu, String kullaniciAdi, int sifre){
        // 1. Bankayı güvenli şekilde al
        Banka bank = bankayiDon(bankaKodu);

        // Eğer banka yoksa veya müşteri listesi boşsa hemen -1 dön
        if (bank == null || bank.musteriler == null) {
            return -1;
        }

        // 2. GÜVENLİ DÖNGÜ: İndeks (i) ile uğraşma, listeyi baştan sona gez
        for (Musteri m : bank.musteriler) {
            // İsim kontrolü
            if (m.getMusteriAdSoyad().equals(kullaniciAdi)) {
                // Şifre kontrolü
                if (m.getMusteriSifre() == sifre) {
                    return m.getMusteriNo(); // Bulduk!
                }
            }
        }
        // Döngü bitti ve return çalışmadıysa, müşteri yok demektir.
        return -1;
    }

     double hesapBakiyesi(int bankaKodu, long musteriNo, int hesapNo){
        // 1. Bankayı Bul ve Kontrol Et
        Banka bank = bankayiDon(bankaKodu);
        if (bank == null) return -1; // Banka yoksa işlem iptal

        // 2. Müşteriyi Bul ve Kontrol Et
        Musteri musteri = musteriyiDon(bank, musteriNo);
        if (musteri == null) return -1; // Müşteri yoksa işlem iptal

        // 3. Hesabı Bul ve Kontrol Et
        // Not: hesabiDon metodunuzun parametrelerine dikkat edin (Musteri, int)
        Hesap hesap = hesabiDon(musteri, hesapNo);
        if (hesap == null) return -1; // Hesap yoksa işlem iptal

        // 4. Her şey tamamsa bakiyeyi ver
        return hesap.getBakiye();
    }

    private  Musteri musteriyiDon(Banka banka, long musteriNo) {
        // 1. Güvenlik: Banka boşsa veya liste boşsa hemen null dön (Hata vermez)
        if (banka == null || banka.musteriler == null) {
            return null;
        }

        // 2. MODERN DÖNGÜ:
        // "i" sayacı yok, "get(i)" yok. Java listeyi senin yerine gezer.
        for (Musteri m : banka.musteriler) {

            // Eğer aradığımız numara bu müşterideyse...
            if (m.getMusteriNo() == musteriNo) {
                return m; // Müşteriyi bulduk, paketleyip gönderiyoruz.
            }
        }

        // 3. Döngü bittiyse ve return çalışmadıysa, müşteri yok demektir.
        return null;
    }
    private  Hesap hesabiDon(Musteri musteri, int hesapNo) {
        // 1. Güvenlik: Müşteri yoksa veya hesap listesi boşsa null dön
        if (musteri == null || musteri.hesaplar == null) {
            return null;
        }

        // 2. DOĞRU MANTIK: Sadece o müşterinin hesaplarını gez
        // "i" sayacı yok, for-each var.
        for (Hesap h : musteri.hesaplar) {

            if (h.getHesapNo() == hesapNo) {
                return h; // Hesabı bulduk!
            }
        }
        // 3. Bulunamadıysa
        return null;
    }


    private VadesizHesap vadesizHesabiDon(Musteri musteri, int hesapNo){
        // Güvenlik kontrolü
        if (musteri == null || musteri.hesaplar == null) {
            return null;
        }

        // 1. DÖNGÜYÜ "Hesap" (Genel Sınıf) OLARAK KUR
        // Çünkü listenin içinde her çeşit hesap olabilir.
        for (Hesap h : musteri.hesaplar) {

            // 2. KİMLİK KONTROLÜ (instanceof)
            // "Bu hesap, bir VadesizHesap mıdır?"
            if (h instanceof VadesizHesap) {

                // 3. NUMARA KONTROLÜ
                if (h.getHesapNo() == hesapNo) {
                    // 4. GÜVENLİ DÖNÜŞTÜRME (Casting)
                    // Java artık bunun VadesizHesap olduğundan emin.
                    return (VadesizHesap) h;
                }
            }
        }
        return null;
    }

    private  int musteriIndeksiBul(Banka banka, Musteri musteri){
        return -1;
    }

    private static int hesapIndeksiBul(Musteri musteri, Hesap hesap){
        return -1;
    }

     int hesapNoBul(int bankaKodu, long musteriNo, int hesapSirasi){
        // 1. Bankayı bul
        Banka bank = bankayiDon(bankaKodu); // Banka nesnesini String'den bul
        if (bank == null) return -1; // Banka yoksa hata

        // 2. Müşteriyi bul
        Musteri musteri = musteriyiDon(bank, musteriNo);
        if (musteri == null) return -1; // Müşteri yoksa hata

        // 3. GÜVENLİK KONTROLÜ (En Önemlisi!)
        // İstenen sıra, hesap sayısından büyük veya eşitse hata verir.
        // Örn: 1 hesap var (size=1), sen 1. indexi (2. hesabı) istersen engeller.
        if (hesapSirasi >= musteri.hesaplar.size() || hesapSirasi < 0) {
            System.out.println("Hata: Müşterinin bu sırada bir hesabı yok!");
            return -1;
        }

        // 4. Direkt erişim (Banka listesine tekrar gitmeye gerek yok)
        return musteri.hesaplar.get(hesapSirasi).getHesapNo();
    }

    public long kartNoBul(int bankaKodu, long musteriNo, int hesapNo, int kartSirasi){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);
        return hesap.kartlar.get(kartSirasi).getKartNo();
    }

    public String IbanNoBul(int bankaKodu, long musteriNo, int hesapNo) {
        Banka bank = bankayiDon(bankaKodu);
        if (bank == null) return null; // Banka yoksa işlem iptal

        // 2. Müşteriyi Bul ve Kontrol Et
        Musteri musteri = musteriyiDon(bank, musteriNo);
        if (musteri == null) return null; // Müşteri yoksa işlem iptal

        // 3. Hesabı Bul ve Kontrol Et
        // Not: hesabiDon metodunuzun parametrelerine dikkat edin (Musteri, int)
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);
        if (hesap == null) return null; // Hesap yoksa işlem iptal

        return hesap.getIBAN();
    }

    public void hesaplariGoster(int bankaKodu, long musteriNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        int i = 1;
        int vadesizSayac = 1;
        int vadeliSayac = 1;
        for (Hesap h : musteri.hesaplar) {
            if (h instanceof VadesizHesap){
                System.out.println(i++ + ")Vadesiz Hesap" + vadesizSayac++);
            }
            else if (h instanceof VadeliHesap){
                System.out.println(i++ + ")Vadeli Hesap" + vadeliSayac++);
            }
        }
    }

    public void kartlariGoster(int bankaKodu, long musteriNo, int hesapNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri,hesapNo);
        int i = 1;
        int nakitSayac = 1;
        int krediSayac = 1;
        int sanalSayac = 1;
        for (Kart k : hesap.kartlar){
            if (k instanceof NakitKarti){
                System.out.println(i++ + ") Nakit Kartı" + nakitSayac++);
            }
            else if (k instanceof KrediKarti){
                System.out.println(i++ + ") Kredi Kartı" + krediSayac++);
            }
            else if (k instanceof SanalKart){
                System.out.println(i++ + ") Sanal Kart" + sanalSayac++);
            }
        }
        if (hesap.kartlar.isEmpty()){
            System.out.println("Kart Yok");
        }
    }

    public void hesapBilgileriniGoster(int bankaKodu, long musteriNo, int hesapNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        Hesap hesap = hesabiDon(musteri, hesapNo);
        if (hesap instanceof VadesizHesap){
            System.out.println("Hesap No: " + hesapNo);
            System.out.println("Bakiye: " + hesap.getBakiye());
            System.out.println("IBAN: " + ((VadesizHesap) hesap).getIBAN());
        }
        else if (hesap instanceof VadeliHesap) {
            System.out.println("Faiz Oranı: " + ((VadeliHesap) hesap).getFaizOrani());
            System.out.println("Vade Süresi: " + ((VadeliHesap) hesap).getVadeSuresi());
            System.out.println("Vade Sonu Getirisi: " + ((VadeliHesap) hesap).vadeSonuGetirisiHesapla());
        }
        else {
            System.out.println("Hesap türü tanımlanamadı!");
        }
    }

    private Kart kartiDon(VadesizHesap hesap ,long kartNo){
        if (hesap == null || hesap.kartlar == null) {
            return null;
        }

        // 2. MODERN DÖNGÜ:
        // "i" sayacı yok, "get(i)" yok. Java listeyi senin yerine gezer.
        for (Kart k : hesap.kartlar) {

            // Eğer aradığımız numara bu müşterideyse...
            if (k.getKartNo() == kartNo) {
                return k;
            }
        }

        return null;
    }

    public void kartBilgileriniGoster(int bankaKodu, long musteriNo, int hesapNo, long kartNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);
        Kart kart = kartiDon(hesap, kartNo);
        if (kart instanceof NakitKarti || kart instanceof SanalKart){
            System.out.println("Kart No: " + kart.getKartNo());
            System.out.println("SKT: " + kart.getSKT());
            System.out.println("CVV: " + kart.getCVV());
            System.out.println("Limit: " + kart.getLimit());
            System.out.println("Çip Türü: " + kart.getCipTuru());
        }
        else if (kart instanceof KrediKarti){
            System.out.println("Kart No: " + kart.getKartNo());
            System.out.println("SKT: " + kart.getSKT());
            System.out.println("CVV: " + kart.getCVV());
            System.out.println("Limit: " + kart.getLimit());
            System.out.println("Çip Türü: " + kart.getCipTuru());
            System.out.println("Asgari: " + ((KrediKarti) kart).getAsgari());
            System.out.println("Borç: " + ((KrediKarti) kart).getBorc());
        }
    }

    public int hesapSayisi(int bankaKodu, long musteriNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        return musteri.hesaplar.size();
    }

    public int kartSayisi(int bankaKodu, long musteriNo, int hesapNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);
        return hesap.kartlar.size();
    }

    public void vadesizHesapAc(int bankaKodu, long musteriNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap yeniHesap = VadesizHesap.vadesizHesapOlustur(musteri, 1000);
        musteri.hesapEkle(yeniHesap);
        System.out.println("Hesap başarıyla oluşturuldu.\n");
    }
    public void vadeliHesapAc(int bankaKodu, long musteriNo) {
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadeliHesap hesap = VadeliHesap.vadeliHesapOlustur(musteri,1000,32, 40);
        musteri.hesapEkle(hesap);
        System.out.println("Hesap başarıyla oluşturuldu.\n");
    }

    public void nakitKartiOlustur(int bankaKodu, long musteriNo, int hesapNo, int sifre, String cipTuru){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri,hesapNo);
        NakitKarti kart = NakitKarti.nakitkartOlustur(hesap, 10000, sifre, cipTuru);
        hesap.kartEkle(kart);
        System.out.println("Nakit kartınız başarıyla uygulamaya tanımlanmıştır.\n");
        kartBilgileriniGoster(bankaKodu, musteriNo, hesapNo, kart.getKartNo());
    }

    public void krediKartiOlustur(int bankaKodu, long musteriNo, int hesapNo, int sifre, String cipTuru){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri,hesapNo);
        KrediKarti kart = KrediKarti.krediKartiOlustur(hesap, 10000, sifre, cipTuru);
        hesap.kartEkle(kart);
        System.out.println("Kredi kartınız başarıyla uygulamaya tanımlanmıştır.\n");
        kartBilgileriniGoster(bankaKodu, musteriNo, hesapNo, kart.getKartNo());
    }

    public void sanalKartOlustur(int bankaKodu, long musteriNo, int hesapNo, int limit, String cipTuru){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri,hesapNo);
        SanalKart kart = SanalKart.sanalKartOlustur(hesap, limit, cipTuru);
        hesap.kartEkle(kart);
        System.out.println("Sanal kartınız başarıyla uygulamaya tanımlanmıştır.\n");
        kartBilgileriniGoster(bankaKodu, musteriNo, hesapNo, kart.getKartNo());
    }

    public  void sonIslem(int bankaKodu, long musteriNo, int hesapNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        Hesap hesap = hesabiDon(musteri, hesapNo);

        if (!hesap.islemler.isEmpty()) {
            Islem sonIslem = hesap.islemler.get(hesap.islemler.size() - 1);

            System.out.println(sonIslem.getIslemTuru());
            System.out.println(sonIslem.getIslemTarihi());
            System.out.println(sonIslem.getParaDegisimi());
        }
        else {
            System.out.println(" --- Boş ---");
        }
    }

    public void kayitliIslemler(int bankaKodu, long musteriNo, int hesapNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        Hesap hesap = hesabiDon(musteri, hesapNo);

        if (!hesap.islemler.isEmpty()) {
            for (Islem i : hesap.islemler){
                System.out.println(i.getIslemTuru());
                System.out.println(i.getIslemTarihi());
                System.out.println(i.getParaDegisimi());
            }
        }
        else {
            System.out.println(" --- Boş ---");
        }
    }

    public void paraAktar(int bankaKodu, long musteriNo, int hesapNo ,String IBAN, double miktar){//TR90 0 10100045001 0000000045
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);

        int bankaKodu2 = Integer.parseInt(IBAN.substring(5,8));
        long musteriNo2 = Long.parseLong(IBAN.substring(5,16));
        int hesapNo2 = Integer.parseInt(IBAN.substring(16));

        Banka bank2 = bankayiDon(bankaKodu2);
        Musteri musteri2 = musteriyiDon(bank2, musteriNo2);
        VadesizHesap hesap2 = vadesizHesabiDon(musteri2, hesapNo2);

        if (bankaKodu == bankaKodu2){
            hesap.setBakiye(hesap.getBakiye() - miktar);
            hesap2.setBakiye(hesap2.getBakiye() + miktar);

            Islem yeniIslem = Islem.islemOlustur("Para Gönderimi", -miktar, "31/12/2025");
            hesap.islemler.add(yeniIslem);
            Islem yeniIslem2 = Islem.islemOlustur("Para Alındı", miktar, "31/12/2025");
            hesap2.islemler.add(yeniIslem2);

            System.out.println("Para " + hesapNo2 + " numaralı hesaba başarıyla aktarıldı.\n");
        }
        else {
            hesap.setBakiye(hesap.getBakiye() - miktar - 10);
            hesap2.setBakiye(hesap2.getBakiye() + miktar);

            Islem yeniIslem = Islem.islemOlustur("Para Gönderimi", -miktar, "31/12/2025");
            hesap.islemler.add(yeniIslem);
            Islem yeniIslem2 = Islem.islemOlustur("Para Alındı", miktar, "31/12/2025");
            hesap2.islemler.add(yeniIslem2);

            System.out.println("Para " + hesapNo2 + " numaralı hesaba başarıyla aktarıldı.\n");
        }
    }

    String getIBAN(int bankaKodu, long musteriNo, int hesapNo){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);
        return hesap.getIBAN();
    }

    void kisiselBilgiler(int bankaKodu, long musteriNo){

    }
    
    void paraHarca(int bankaKodu, long musteriNo, int hesapNo, long kartNo, double miktar){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);
        Kart kart = kartiDon(hesap, kartNo);
        
        if (kart instanceof NakitKarti || kart instanceof SanalKart){
            hesap.setBakiye(hesap.getBakiye() - miktar);
            kart.paraHarca(miktar);
        }
        else if (kart instanceof KrediKarti){
            hesap.setBakiye(hesap.getBakiye() - miktar);
            kart.paraHarca(miktar);
        }
        
    }
    
    public void islemEkle(int bankaKodu, long musteriNo, int hesapNo, double paraDegisimi, String islemTuru){
        Banka bank = bankayiDon(bankaKodu);
        Musteri musteri = musteriyiDon(bank, musteriNo);
        VadesizHesap hesap = vadesizHesabiDon(musteri, hesapNo);
        Islem yeniIslem = Islem.islemOlustur(islemTuru, paraDegisimi, "31/12/2025");
        hesap.islemler.add(yeniIslem);
    }

    public void kampanyalariGoster(int bankaKodu){
        Banka bank = bankayiDon(bankaKodu);
        if (!bank.kampanyalar.isEmpty()){
            for (String s : bank.kampanyalar){
                System.out.println(s);
            }
        }
        else {
            System.out.println("Kampanya bulunmamaktadır!");
        }
    }
}
