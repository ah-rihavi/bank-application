package BankaArkaPlani;
import java.util.ArrayList;
import java.util.List;

public class Musteri {

    public List<Hesap> hesaplar = new ArrayList<>();


    private long musteriNo;
    private int musteriSifre;
    private long musteriTC;
    private String musteriAd = "", musteriSoyad = "";


    public Musteri() {
    }


    public static Musteri musteriOlustur(Banka banka, int musteriSifre, long musteriTC, String ad, String soyad) {
        Musteri yeniMusteri = new Musteri();

        // Önce bilgileri set et
        yeniMusteri.setMusteriTC(musteriTC);
        yeniMusteri.setMusteriAd(ad);
        yeniMusteri.setMusteriSoyad(soyad);
        yeniMusteri.setMusteriSifre(musteriSifre);

        // En son Numarayı ata (Banka mantığıyla)
        yeniMusteri.setMusteriNo(banka);

        // BİLGİLER TAMAMLANDIKTAN SONRA İLK HESABI AÇ
        // Artık müşteri nosu olduğu için hesap no düzgün oluşur.
        VadesizHesap ilkHesap = new VadesizHesap(yeniMusteri);
        yeniMusteri.hesapEkle(ilkHesap);

        return yeniMusteri;
    }

    // İsim kontrolü (Türkçe karakter ve büyük harf desteği eklendi)
    boolean isimKontrolu(String isim) {
        if (isim == null || isim.isEmpty()) return false;
        for (char c : isim.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    // Yardımcı Metot
    private String bankaninMusteriSayisi(Banka bank) {
        // Null check eklemek iyidir
        int mevcutSayi = (bank != null && bank.musteriler != null) ? bank.musteriler.size() + 1 : 1;

        // String format ile daha kolay padding (sıfır ekleme)
        return String.format("%08d", mevcutSayi);
    }

    //region Getters
    public long getMusteriNo() { return this.musteriNo; } // long döndürüyor
    public String getMusteriAdSoyad() { return this.musteriAd + " " + this.musteriSoyad; }
    public int getMusteriSifre() {
        return this.musteriSifre;
    }
    public long getMusteriTC(){
        return this.musteriTC;
    }
    //endregion
    //region Setters
    public void setMusteriNo(Banka banka) {
        // KONTROL: Müşteri numarası daha önce atanmış mı?
        // long türünün varsayılan değeri 0'dır. Eğer 0 değilse, atanmış demektir.
        if (this.musteriNo != 0) {
            throw new SecurityException("Dikkat! Müşteri numarası sadece bir kez oluşturulabilir, değiştirilemez!");
        }

        String prefix = Integer.toString(banka.getBankaKodu());
        String fullNo = prefix + bankaninMusteriSayisi(banka);
        this.musteriNo = Long.parseLong(fullNo);
    }
    public void setMusteriTC(long musteriTC) {
        if (this.musteriTC != 0) {
            throw new SecurityException("Dikkat! Müşteri TC değiştirilemez!");
        }
        this.musteriTC = musteriTC;
    }
    public void setMusteriSifre(int sifre) {
        this.musteriSifre = sifre;
    }
    public void setMusteriAd(String ad) {
        if(isimKontrolu(ad)) this.musteriAd = ad;
    }
    public void setMusteriSoyad(String soyad) {
        if(isimKontrolu(soyad)) this.musteriSoyad = soyad;
    }
    //endregion

    public void hesapEkle(Hesap hesap) {
        hesaplar.add(hesap);
    }
}