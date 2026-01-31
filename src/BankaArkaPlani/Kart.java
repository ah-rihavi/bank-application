package BankaArkaPlani;

import java.util.ArrayList;
import java.util.List;

public abstract class Kart {
    protected long KartNo;
    protected int SKT_Ay, SKT_Yil, CVV, kartSifresi, limit;
    protected String cipTuru = "TROY";
    public String getSKT() {
        return this.SKT_Ay + "/" + this.SKT_Yil;
    }
    public List<Islem> islemler = new ArrayList();

    //region Getters
    public long getKartNo() {
        return this.KartNo;
    }
    public int getCVV() {
        return this.CVV;
    }
    public int getKartSifresi() {
        return this.kartSifresi;
    }
    public int getLimit() {
        return this.limit;
    }
    public String getCipTuru (){
        return this.cipTuru;
    }
    //endregion
    //region Setters
    public void setKartNo(VadesizHesap hesap) {//0101 0055 0003 0004
        String bankaKodu = "0" + Integer.toString(hesap.getHesapNo()).substring(0, 3);
        String musteriNoSon2 = "00" + Integer.toString(hesap.getHesapNo()).substring(9);
        String hesapNoSon3 = "0" + Integer.toString(hesap.getHesapNo()).substring(5);

        StringBuilder kartSayisi = new StringBuilder(Integer.toString(hesap.kartlar.size() + 1));
        while (kartSayisi.length() < 4) {
            kartSayisi.insert(0, "0");
        }
        this.KartNo = Long.parseLong(bankaKodu + musteriNoSon2 + hesapNoSon3 + kartSayisi.toString());
    }
    public void setSKT(int SKT_Ay, int SKT_Yil) {
        if (SKT_Ay >= 1 && SKT_Ay <= 12 && SKT_Yil >= 24) {
            this.SKT_Ay = SKT_Ay;
            this.SKT_Yil = SKT_Yil;
        } else {
            throw new IllegalArgumentException("SKT Hatalı atanmaya çalışıldı");
        }
    }
    public void setCVV() {
        this.CVV = (int) (Math.random() * 900) + 100;
    }
    public void setKartSifresi(int kartSifresi) {//4 haneli
        String number = Integer.toString(kartSifresi);
        if (number.length() == 4) {
            this.kartSifresi = kartSifresi;
        } else {
            throw new IllegalArgumentException("kartSifresi Hatalı atanmaya çalışıldı");
        }
    }
    public void setLimit(int limit) {
        if (limit >= 0) {
            this.limit = limit;
        } else {
            throw new IllegalArgumentException("limit Hatalı atanmaya çalışıldı");
        }
    }
    public void setCipTuru (String cipTuru) {
        this.cipTuru = cipTuru;
    }
    //endregion

    public void paraHarca(double miktar){
        if (miktar > 0 && miktar <= this.limit ){
            this.limit -= miktar;
        }
        else {
            throw new IllegalArgumentException("Geçersiz harcama miktarı.");
        }
    }
}


