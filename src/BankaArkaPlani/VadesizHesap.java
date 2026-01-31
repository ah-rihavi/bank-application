package BankaArkaPlani;

import java.util.ArrayList;
import java.util.List;

public class VadesizHesap extends Hesap{
    private String IBAN;
    public VadesizHesap(Musteri musteri) {
        super(musteri);
        setIBAN(musteri.getMusteriNo());
    }
    public String getIBAN(){
        return this.IBAN;
    }
    public void setIBAN(long musteriNo) {
        // 1. ADIM: Müşteri Numarasını Sabitle (11 Hane)
        // %011d -> Sayı 11 haneden kısaysa başına sıfır koyar.
        String formatliMusteriNo = String.format("%011d", musteriNo);

        // 2. ADIM: Hesap Numarasını Sabitle (8 veya 10 Hane - Karar senin)
        // Hesap no int sınırında (2 milyar) olduğu için 10 hane güvenlidir.
        String formatliHesapNo = String.format("%010d", this.getHesapNo());

        // 3. ADIM: Birleştir
        // "TR90" (4) + "000" (3) + Musteri (11) + Hesap (10) = 28 Karakter
        this.IBAN = "TR" + "90" + "0" + formatliMusteriNo + formatliHesapNo;

        // Çıktı Örneği:
        // TR90 0 10100045001 0000000045
    }

    public List<Kart> kartlar = new ArrayList<>();
    public void kartEkle(Kart kart){
        kartlar.add(kart);
    }

    public static VadesizHesap vadesizHesapOlustur(Musteri musteri, double bakiye) {
        VadesizHesap yeniHesap = new VadesizHesap(musteri);
        yeniHesap.setHesapNo(musteri);
        yeniHesap.setBakiye(bakiye);
        yeniHesap.setIBAN(musteri.getMusteriNo());
        return yeniHesap;
    }
}