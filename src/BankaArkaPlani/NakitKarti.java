package BankaArkaPlani;

public class NakitKarti extends Kart {
    public static NakitKarti nakitkartOlustur(VadesizHesap hesap, int limit, int sifre, String cipTuru) {
        NakitKarti yeniKart = new NakitKarti();
        yeniKart.setKartNo(hesap);
        int SKT_Ay = (int) (Math.random() * 12) + 1;
        int SKT_Yil = (int) (Math.random() * 10) + 24;
        yeniKart.setSKT(SKT_Ay, SKT_Yil);
        yeniKart.setCVV();
        yeniKart.setLimit(limit);
        yeniKart.setKartSifresi(sifre);
        yeniKart.setCipTuru(cipTuru);
        return yeniKart;
    }
}