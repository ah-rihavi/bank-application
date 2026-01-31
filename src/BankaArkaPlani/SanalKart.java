package BankaArkaPlani;

public class SanalKart extends Kart {
    public static SanalKart sanalKartOlustur(VadesizHesap hesap, int limit, String cipTuru) {
        SanalKart yeniKart = new SanalKart();
        int SKT_Ay = (int) (Math.random() * 12) + 1;
        int SKT_Yil = (int) (Math.random() * 10) + 24;
        yeniKart.setSKT(SKT_Ay, SKT_Yil);
        yeniKart.setCVV();
        yeniKart.setLimit(limit);
        yeniKart.setCipTuru(cipTuru);
        return yeniKart;
    }
}