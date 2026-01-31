package BankaArkaPlani;
import java.util.ArrayList;
import java.util.List;

public class Hesap {
    protected int hesapNo;
    protected double  bakiye = 1000;

    public Hesap(Musteri musteri){
        this.hesapNo = setHesapNo(musteri);
    }

    //region Getters
    public int getHesapNo(){
        return this.hesapNo;
    }
    public double getBakiye(){
        return this.bakiye;
    }
    //endregion
    //region Setters
    public int setHesapNo(Musteri musteri) {
        // 1. Müşterinin bankadaki öz sırasını bulmamız lazım.
        // Müşteri No: 10100000045 ise -> Sıra No: 45'tir.
        // String parsing ile bunu çekip alabiliriz.
        String musteriNoStr = Long.toString(musteri.getMusteriNo());

        // Banka kodu (ilk 3) hariç, kalanı al (00000045)
        // substring(3) -> 3. indeksten sonuna kadar
        int musteriSiraNo = Integer.parseInt(musteriNoStr.substring(3));

        // 2. Müşterinin kaçıncı hesabı? (1, 2, 3...)
        int hesapSiraNo = musteri.hesaplar.size() + 1;

        // 3. Eşsiz Hesap No Üretimi (Max 99 hesap limitiyle)
        // Formül: MüşteriSırası'nın yanına hesabı ekle.
        // Örn: Müşteri 45, Hesap 1 -> 4501
        // Örn: Müşteri 45, Hesap 2 -> 4502

        // Müşteri Sırası çok büyükse (Örn: 99.000.000) int sınırını aşmamak için long dönmek gerekebilir.
        // Ama senin sisteminde int dönecekse en güvenlisi budur.

        // Eğer hesap numarası illaki "Banka Kodu" ile başlasın istiyorsan:
        // Formül: BankaKodu + MüşteriSıraNo(Son 4) + HesapSıra(2)
        // Ama int sınırına (2.147.xxx.xxx) dikkat etmelisin!

        // Basit ve Güvenli Yöntem:
        // Banka Kodu (101) + MüşteriSırası (00045) + Hesap (01) = 1010004501 (int sınırına sığar!)

        String bankaKodu = musteriNoStr.substring(0, 3);

        // Müşteri sırasının son 5 hanesini alalım (Sistem 100.000 müşteriye kadar destekler)
        int len = musteriNoStr.length();
        String kisaMusteriSira = musteriNoStr.substring(len - 5);

        String hesapSiraFormatli = String.format("%02d", hesapSiraNo);

        String yeniHesapNo = bankaKodu + kisaMusteriSira + hesapSiraFormatli;

        // Örnek Çıktı: 101 00045 01 -> 1 Milyar (int sınırları içinde)
        return Integer.parseInt(yeniHesapNo);
    }
    public void setBakiye(double bakiye){
        if (bakiye >= 0){
            this.bakiye = bakiye;
        }
        else {
            throw new IllegalArgumentException("Bakiye Hatalı atanmaya çalışıldı");
        }
    }
    //endregion
    public List<Islem> islemler = new ArrayList();
}