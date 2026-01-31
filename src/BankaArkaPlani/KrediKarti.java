package BankaArkaPlani;

public class KrediKarti extends Kart {

    private int asgari;
    private double borc = 0;

    //region Getters
    public int getAsgari() {
        return this.asgari;
    }
    public double getBorc() {
        return this.borc;
    }
    //endregion
    //region Setters
    public void setAsgari() {
        asgari = (int) (this.limit * 0.30);
    }
    public void setBorc (double borc) {
        this.borc = borc;
    }
    //endregion

    public void borcOde(double miktar) {
        if (miktar > 0 && miktar <= this.borc) {
            this.borc -= miktar;
        } else {
            throw new IllegalArgumentException("Geçersiz borç ödeme miktarı.");
        }
    }
    @Override
    public void paraHarca(double miktar) {
        if (miktar > 0 && miktar <= this.limit && borc < this.limit) {
            this.limit -= miktar;
            this.borc += miktar;
        } else {
            throw new IllegalArgumentException("Geçersiz harcama miktarı.");
        }
    }

    public static KrediKarti krediKartiOlustur (VadesizHesap hesap, int limit, int sifre, String cipTuru){
        KrediKarti yeniKart = new KrediKarti();
        yeniKart.setKartNo(hesap);
        int SKT_Ay = (int) (Math.random() * 12) + 1;
        int SKT_Yil = (int) (Math.random() * 10) + 24;
        yeniKart.setSKT(SKT_Ay, SKT_Yil);
        yeniKart.setCVV();
        yeniKart.setAsgari();
        yeniKart.setLimit(limit);
        yeniKart.setKartSifresi(sifre);
        yeniKart.setCipTuru(cipTuru);
        return yeniKart;
    }
}