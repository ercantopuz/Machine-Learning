import java.io.File;
import java.io.IOException;
import java.util.*;
public class ComplatedProject {

    public static ArrayList kelimeAyir(String cumle) {
        ArrayList<String> dizi = new ArrayList<String>();

        int konum = 0;

        for (int i = 0; i < cumle.length(); i++) {
            String kelime = "";
            if (cumle.charAt(i) == ' ' || i == cumle.length() - 1
                    || cumle.charAt(i) == '/' || cumle.charAt(i) == ')'
                    || cumle.charAt(i) == '(' || cumle.charAt(i) == ','
                    || cumle.charAt(i) == '.') {

                for (int j = konum; j < i; j++) {

                    String a = String.valueOf(cumle.charAt(j));

                    kelime = kelime + a;

                }
                konum = i + 1;
                
                if (!kelime.equals("the") && !kelime.equals("and")
                        && !kelime.equals("for") && !kelime.equals("of")
                        && !kelime.equals("to") && !kelime.equals("is")
                        && !kelime.equals("are") && !kelime.equals("at")
                        && !kelime.equals("more") && !kelime.equals("by")
                        && !kelime.equals("that") && !kelime.equals("use")
                        && !kelime.equals("in") && !kelime.equals("")) {

                    dizi.add(kelime);

                }

            }

        }
        return dizi;

    }

    public static void kmeanskumeleme(ArrayList<HashMap> hash) {

        ArrayList<String> Kelimeler = new ArrayList<String>();

        Kelimeler.addAll(hash.get(0).keySet());
        ArrayList<Double> kumelerinoklidleri = new ArrayList<Double>();

        HashMap<Double, HashMap<String, Double>> kumelendirmesonuclari = new HashMap<Double, HashMap<String, Double>>();

        kumelerinoklidleri.add(0, 999999.0);
        int bigg = 0;
        while (bigg != 5) {
            double buyuktoplam = 0;
            int mmmakale1 = (int) (Math.random() * 19);
            int mmmakale2;
            int mmmakale3;
            do {
                mmmakale2 = (int) (Math.random() * 19);
            } while (mmmakale1 == mmmakale2);
            do {
                mmmakale3 = (int) (Math.random() * 19);
            } while (mmmakale3 == mmmakale1||mmmakale3==mmmakale2 );

            HashMap k1 = hash.get(mmmakale1);
            HashMap k2 = hash.get(mmmakale2);
            HashMap k3 = hash.get(mmmakale3);

            ArrayList<Double> oklidler1 = new ArrayList<Double>();
            ArrayList<Double> oklidler2 = new ArrayList<Double>();
            for (int i = 0; i < 20; i++) {
                oklidler1.add(0.0);
            }

            int big = 1;
            while (true) {
                System.out.println(big + ". iterasyon başladı: ");
                oklidler2.clear();

                big++;

                ArrayList<HashMap> secilenler = new ArrayList<HashMap>();
                secilenler.add(k1);
                secilenler.add(k2);
                secilenler.add(k3);

                ArrayList<ArrayList> Kicinoklidler = new ArrayList<ArrayList>();


                int zz = 0;
                while (zz != 3) {
                    ArrayList<Double> oklid = new ArrayList<Double>();
                    ArrayList<Double> temp = new ArrayList<Double>();
                    temp.addAll(secilenler.get(zz).values());

                    System.out.println("");
                    System.out.println("k" + (zz + 1) + "' e olan öklid uzaklıkları:");
                    int i = 0;
                    while (i != hash.size()) {

                        ArrayList<Double> incimetin = new ArrayList<Double>();

                        incimetin.addAll(hash.get(i).values());
                        int top = 0;
                        for (int j = 0; j < incimetin.size(); j++) {
                            double mutlak = Math.pow((temp.get(j) - incimetin.get(j)), 2);
                            top += mutlak;

                        }
                        double oklidimiz = Math.pow(top, 0.5);
                        oklidimiz = Math.floor(oklidimiz * 1000) / 1000;
                        System.out.println(i + ".makale :" + oklidimiz);
                        oklid.add(oklidimiz);

                        i++;

                    }

                    Kicinoklidler.add(oklid);
                    zz++;

                }
               
                ArrayList<ArrayList> k1kumesi = new ArrayList<ArrayList>();
                ArrayList<ArrayList> k2kumesi = new ArrayList<ArrayList>();
                ArrayList<ArrayList> k3kumesi = new ArrayList<ArrayList>();

                HashMap<String, Double> kumeler = new HashMap<String, Double>();
                for (int i = 0; i < 20; i++) {
                    double a = (double) Kicinoklidler.get(0).get(i);
                    double b = (double) Kicinoklidler.get(1).get(i);
                    double c = (double) Kicinoklidler.get(2).get(i);
                    if (a < b && a < c) {
                        kumeler.put(i + ".makale k1 kümesindedir. öklid uzaklıgı =" + "", a);
                        ArrayList<Double> tempp = new ArrayList<Double>();
                        tempp.addAll(hash.get(i).values());
                        k1kumesi.add(tempp);
                        oklidler2.add(a);

                    } else if (b < a && b < c) {
                        kumeler.put(i + ".makale k2 kümesindedir. öklid uzaklıgı =" + "", b);
                        ArrayList<Double> tempp = new ArrayList<Double>();
                        tempp.addAll(hash.get(i).values());
                        k2kumesi.add(tempp);
                        oklidler2.add(b);

                    } else {
                        kumeler.put(i + ".makale k3 kümesindedir. öklid uzaklıgı =" + "", c);

                        ArrayList<Double> tempp = new ArrayList<Double>();
                        tempp.addAll(hash.get(i).values());
                        k3kumesi.add(tempp);
                        oklidler2.add(c);
                    }

                }
                for (String name : kumeler.keySet()) {
                    String key = name.toString();
                    String value = kumeler.get(name).toString();

                    System.out.println(key + "\t" + value);

                }
                
                HashMap<String, Double> yenik1 = new HashMap<String, Double>();
                HashMap<String, Double> yenik2 = new HashMap<String, Double>();
                HashMap<String, Double> yenik3 = new HashMap<String, Double>();

                System.out.println("");
                System.out.println("Yeni kume merkezlerinin değerleri ");

                int o = 0;
                while (o != k1kumesi.get(0).size()) {
                    double top = 0;
                    for (int j = 0; j < k1kumesi.size(); j++) {
                        top += (double) k1kumesi.get(j).get(o);

                    }
                    double ortalama = top / k1kumesi.size();
                    ortalama = Math.floor(ortalama * 1000) / 1000;

                    yenik1.put((String) Kelimeler.get(o), ortalama);
                    o++;

                }
                System.out.println("k1 küme merkezinin yenideğerleri" + yenik1);

                int p = 0;
                while (p != k2kumesi.get(0).size()) {
                    double top = 0;
                    for (int j = 0; j < k2kumesi.size(); j++) {
                        top += (double) k2kumesi.get(j).get(p);
                    }
                    double ortalama = top / k2kumesi.size();
                    ortalama = Math.floor(ortalama * 1000) / 1000;
                    yenik2.put((String) Kelimeler.get(p), ortalama);

                    p++;

                }
                System.out.println("k2 küme merkezinin yenideğerleri" + yenik2);

                int n = 0;
                while (n != k3kumesi.get(0).size()) {
                    double top = 0;
                    for (int j = 0; j < k3kumesi.size(); j++) {
                        top += (double) k3kumesi.get(j).get(n);

                    }
                    double ortalama = top / k3kumesi.size();
                    ortalama = Math.floor(ortalama * 1000) / 1000;
                    yenik3.put((String) Kelimeler.get(n), ortalama);

                    n++;

                }
                System.out.println("k3 küme merkezinin yenideğerleri" + yenik3);

                k1 = yenik1;
                k2 = yenik2;
                k3 = yenik3;
                System.out.println("");
                boolean bayrak = true;
                for (int i = 0; i < 20; i++) {
                    if (!oklidler1.contains(oklidler2.get(i))) {
                        oklidler1.clear();
                        oklidler1.addAll(oklidler2);
                        bayrak = false;
                      
                    }

                }
                if (bayrak) {
                    for (String name : kumeler.keySet()) {
                        String key = name.toString();
                        String value = kumeler.get(name).toString();
                        buyuktoplam += Double.parseDouble(value);

                    }
                    kumelendirmesonuclari.put(buyuktoplam, kumeler);

                    break;
                }

            }

            
            System.out.println("her bir küme elemanının küme merkezine olan uzaklıkları toplamı "+buyuktoplam);
            System.out.println( "###############################" + (bigg+1) + ".kisisinin kumelendirmesi yukarıdadır" + "#########################################################");
            kumelerinoklidleri.add(1, buyuktoplam);
            if (kumelerinoklidleri.get(1) < kumelerinoklidleri.get(0)) {
                double temp1 = kumelerinoklidleri.get(1);

                kumelerinoklidleri.remove(1);
                kumelerinoklidleri.add(0, temp1);

            }

            
            bigg++;

        }
        System.out.println("");
        System.out.println("");
        

        System.out.println("************************* 5 KÜMELEME ARASINDAN EN İYİSİ ********************************");
        for (String name : kumelendirmesonuclari.get(kumelerinoklidleri.get(0)).keySet()) {
            String key = name.toString();
            String value = kumelendirmesonuclari.get(kumelerinoklidleri.get(0)).get(name).toString();
            System.out.println(key + "\t" + value);

        }
        System.out.println("her bir küme elemanının küme merkezine olan uzaklıkları toplamı EN İYİ DURUM "+kumelerinoklidleri.get(0));

    }

    public static void hashmapkontrol(ArrayList<String> hepsi, ArrayList<ArrayList> metinler) {//bu metod ile bir nevi kelime tablomu olusturmus oluyorum.
        ArrayList<HashMap> hashmapler = new ArrayList<HashMap>();

        HashMap<String, Double> sozluk = new HashMap<String, Double>();

        ArrayList<String> temp = new ArrayList<String>();
        temp.addAll(hepsi);

        int k = 0;
        while (k != metinler.size()) {

            hepsi.addAll(metinler.get(k));

            for (int i = 0; i < hepsi.size(); i++) {
                if (sozluk.containsKey(hepsi.get(i))) {
                    double sayi = sozluk.get(hepsi.get(i));
                    sozluk.put((String) hepsi.get(i), sayi + 1);

                } else {
                    sozluk.put((String) hepsi.get(i), 0.0);
                }
            }
            hashmapler.add(sozluk);

  
            sozluk = new HashMap<String, Double>();

            hepsi.clear();
            hepsi.addAll(temp);

            k++;

        }
        kmeanskumeleme(hashmapler);

    }

    public static void tumkelimeler(ArrayList<ArrayList> yeni) {

        ArrayList<String> hepsi = new ArrayList<String>();

        int i = 0;
        while (i != yeni.size()) {
            for (int j = 0; j < yeni.get(i).size(); j++) {
                if (!hepsi.contains(yeni.get(i).get(j))) {
                    hepsi.add((String) yeni.get(i).get(j));

                }

            }
            i++;
        }
        hashmapkontrol(hepsi, yeni);

    }

    public static void main(String[] args) throws IOException {
        
        ArrayList<ArrayList> makaleler = new ArrayList<ArrayList>();

        for (int i = 0; i < 20; i++) {
            String metin = "";

            try {
                String sad = "makale" + i + ".txt";
                Scanner oku = new Scanner(new File(sad));

                while (oku.hasNextLine()) {
                    metin += oku.nextLine();

                }
                oku.close();

            } catch (Exception e) {
            }
            ArrayList<String> deneme = kelimeAyir(metin);
            makaleler.add(deneme);
        }
        
        tumkelimeler(makaleler);

    }

}

