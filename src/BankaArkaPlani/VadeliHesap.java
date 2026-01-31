package BankaArkaPlani;

public class VadeliHesap extends Hesap{
    public VadeliHesap(Musteri musteri) {
        super(musteri);
    }
    int vadeSuresi;
    double faizOrani;

    //region Getters
    public int getVadeSuresi (){
        return this.vadeSuresi;
    }
    public double getFaizOrani () {
        return this.faizOrani;
    }
    //endregion
    //region Setters
    public void setVadeSuresi(int vadeSuresi){
        this.vadeSuresi = vadeSuresi;
    }
    public void setFaizOrani(double faizOrani){
        this.faizOrani = faizOrani;
    }
    //endregion

    public double vadeSonuGetirisiHesapla() {
        if (this.bakiye > 0 && this.faizOrani > 0) {
            return (this.bakiye * this.faizOrani * this.vadeSuresi) / 36500;
        }
        return 0;
    }

    public static VadeliHesap vadeliHesapOlustur(Musteri musteri, double bakiye, int vadeSuresi, double faizOrani){
        VadeliHesap yeniHesap = new VadeliHesap(musteri);
        yeniHesap.setHesapNo(musteri);
        yeniHesap.setBakiye(bakiye);
        yeniHesap.setVadeSuresi(vadeSuresi);
        yeniHesap.setFaizOrani(faizOrani);
        return yeniHesap;
    }
}