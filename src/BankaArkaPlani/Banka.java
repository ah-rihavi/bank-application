package BankaArkaPlani;
import java.util.ArrayList;
import java.util.List;
abstract public class Banka {
    public List<String> kampanyalar = new ArrayList<>();
    public List<Musteri>  musteriler = new ArrayList<>();
    protected int bankaKodu;
    protected String bankaIsmi;
    public int getBankaKodu(){
        return this.bankaKodu;
    }
    public void musteriEkle(Musteri musteri){
        musteriler.add(musteri);
    }
}