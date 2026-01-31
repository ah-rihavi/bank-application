package BankaArkaPlani;
import java.util.List;
import java.util.ArrayList;

public class Islem {

    private   String islemTuru;
    private  double paraDegisimi;
    private  String islemTarihi;

    public String getIslemTuru() {
        return islemTuru;
    }

    public double getParaDegisimi() {
        return paraDegisimi;
    }

    public String getIslemTarihi() {
        return islemTarihi;
    }

        public static Islem islemOlustur(String islemTuru, double paraDegisimi, String islemTarihi){
            Islem yeniIslem = new Islem();
            yeniIslem.islemTuru = islemTuru;
            yeniIslem.paraDegisimi = paraDegisimi;
            yeniIslem.islemTarihi = islemTarihi;
            return yeniIslem;
        }
}