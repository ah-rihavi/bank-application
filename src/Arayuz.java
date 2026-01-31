import java.util.Scanner;

abstract public class Arayuz {

    Scanner input = new Scanner(System.in);
    abstract void Menu();
    private int secim = 0;
    int secim(){
        System.out.print("\nSeçim: ");
        secim = input.nextInt(); input.nextLine();
        System.out.println();
        return secim;}
}


class IlkMenu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("\t----Lütfen bankanızı seçin---");
        System.out.println("1) Bank A");
        System.out.println("2) Bank B");
        System.out.println("3) Bank C");
        System.out.println("-1) Çıkış");
    }
}

class BaslangicMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Giriş Yap");
        System.out.println("2) Kayıt Ol");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }

}
class GirisMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("\n1) Devam Et");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
    String kullaniciAdi(){
        System.out.print("Kullanıcı Adı:");
        return input.nextLine();
    }
    int sifre(){
        System.out.print("Şifre:");
        int sifre = input.nextInt(); input.nextLine();
        return sifre;
    }
}
class KayitMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("\n1) Devam Et");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }

    String kullaniciAdi(){
        System.out.print("İsim: ");
        return input.nextLine();
    }
    String kullaniciSoyAdi(){
        System.out.print("Soyisim: ");
        return input.nextLine();
    }
    long kullaniciTC (){
        System.out.print("TC Numarası: ");
        long tc = input.nextLong();input.nextLine();
        return tc;
    }
    int kullaniciSifre() {
        System.out.print("Şifre: ");
        int sifre = input.nextInt();input.nextLine();
        return sifre;
    }
}
class AnaMenu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Hesap/Kart");
        System.out.println("2) Para Transferi");
        System.out.println("3) Ödemeler");
        System.out.println("4) Kayıtlı İşlemler");
        System.out.println("5) Kampanyalar");
        //System.out.println("6) Kişisel Bilgiler");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
        //System.out.println("-2) Müşteri Kaydını Sil");
    }

}

class HesapKartMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Hesaplarım");
        System.out.println("2) Kartlarım");
        System.out.println("3) Yeni Hesap Aç");
        System.out.println("4) Kart Başvurusu");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class HesapMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("\n-2) Vadesiz Hesapla Oturumu Aç");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }

}

class YeniHesapMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Vadesiz Hesap Aç");
        System.out.println("2) Vadeli Hesap Aç");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }

//    int ilkKartSifresi(){
//        System.out.println("Yeni Vadesiz hesap oluşturmak için ");
//    }
}

class KartMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("\n0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class YeniKartMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Nakit Kart Başvurusu");
        System.out.println("2) Kredi Kartı Başvurusu");
        System.out.println("2) Sanal Kart Oluştur");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }

    int sifre(){
        System.out.print("Şifre: ");
        int sifre = input.nextInt();input.nextLine();
        return sifre;
    }

    String cipTuru(){
        int cip;
        String cipTuru = "TROY";
        do {
            System.out.println("Çip Türü: ");
            System.out.println("1) TROY");
            System.out.println("2) Mastercard");
            System.out.println("3) VISA");
            System.out.print("Secim: ");
            cip = input.nextInt();input.nextLine();
            switch (cip){
                case 1 -> cipTuru = "TROY";
                case 2 -> cipTuru = "Mastercard";
                case 3 -> cipTuru = "VISA";
                default -> System.err.println("Lütfen geçerli bir seçim giriniz!");
            }
        }while (cip < 1 || cip > 3);


        return cipTuru;
    }

    int limit(){
        System.out.println("Limit: ");
        int limit = input.nextInt();input.nextLine();
        return limit;
    }

}

class ParaTransferMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("\n1) Devam Et");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }

    String IBAN(){
        System.out.print("IBAN: ");
        return input.nextLine();
    }
    double miktar(){
        System.out.print("Miktar: ");
        double miktar = input.nextDouble();input.nextLine();
        return miktar;
    }
}

class KayitliIslemlerMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("\n0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class OdemelerMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Faturalar");
        System.out.println("2) Trafik Ödemeleri");
        System.out.println("3) Eğitim Ödemeleri");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class FaturalarMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Mobil Hat Faturrası");
        System.out.println("2) Su Farturası");
        System.out.println("3) Elektrik Fatursı");
        System.out.println("4) Doğalgaz Faturası");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class TrafikOdemeleriMenusu extends Arayuz{
    @Override
    void Menu(){
        System.out.println("1) HGS Ödemesi");
        System.out.println("2) Trafik Cezası");
        System.out.println("3) Motorlu Taşıtlar Vergisi");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class EgitimOdemeleriMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Üniversite Harcı");
        System.out.println("2) KYK");
        System.out.println("3) ÖSYM");
        System.out.println("4) MEB");
        System.out.println("0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class Kampanyalar extends Arayuz{
    @Override
    void Menu(){
        System.out.println("\n0) Geri Dön");
        System.out.println("-1) Çıkış");
    }
}

class OdemeMenusu extends Arayuz {
    @Override
    void Menu(){
        System.out.println("1) Ödeme Yap");
        System.out.println("2) İptal Et");
    }
}