package elalkbead;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Lakas {
    int kerulet;
    int terulet;
    int szobakSzama;
    int emelet;
    int ar;
    String allapot;

    public Lakas(int kerulet, int terulet, int szobakSzama, int emelet, int ar, String allapot) {
        this.kerulet = kerulet;
        this.terulet = terulet;
        this.szobakSzama = szobakSzama;
        this.emelet = emelet;
        this.ar = ar;
        this.allapot = allapot;
    }

    public static ArrayList<Lakas> beolvas(String fajlnev) {
        ArrayList<Lakas> lista = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(new FileReader(fajlnev));
            JSONArray lakasok = (JSONArray) obj.get("lakasok");

            for (int i = 0; i < lakasok.size(); i++) {
                JSONObject l = (JSONObject) lakasok.get(i);
                int kerulet = ((Long) l.get("kerulet")).intValue();
                int terulet = ((Long) l.get("terulet")).intValue();
                int szobak = ((Long) l.get("szobak_szama")).intValue();
                int emelet = ((Long) l.get("emelet")).intValue();
                int ar = ((Long) l.get("ar")).intValue();
                String allapot = (String) l.get("allapot");

                lista.add(new Lakas(kerulet, terulet, szobak, emelet, ar, allapot));
            }
        } catch (Exception e) {}
        return lista;
    }

    public static void feldolgoz(ArrayList<Lakas> lakasLista) {
        System.out.println("1. feladat:");
        int maxAr = 0;
        int maxKerulet = 0;
        for (int i = 0; i < lakasLista.size(); i++) {
            Lakas l = lakasLista.get(i);
            if (l.ar > maxAr) {
                maxAr = l.ar;
                maxKerulet = l.kerulet;
            }
        }
        System.out.println("\t" + maxAr + " (" + maxKerulet + " kerület)");

        System.out.println("2. feladat:");
        for (int k = 1; k <= 23; k++) {
            int db = 0;
            for (int i = 0; i < lakasLista.size(); i++) {
                if (lakasLista.get(i).kerulet == k) {
                    db++;
                }
            }
            System.out.println("\t" + k + ". kerület: " + db);
        }

        System.out.println("3. feladat:");
        int osszTerulet = 0;
        for (int i = 0; i < lakasLista.size(); i++) {
            osszTerulet += lakasLista.get(i).terulet;
        }
        int atlagTerulet;
        atlagTerulet = osszTerulet / lakasLista.size();
        System.out.println("\tÁtlagos terület: " + atlagTerulet + " m2");

        System.out.println("4. feladat:");
        int osszAr = 0;
        int osszTer6 = 0;
        int db6 = 0;
        for (int i = 0; i < lakasLista.size(); i++) {
            Lakas l = lakasLista.get(i);
            if (l.kerulet == 6) {
                osszAr += l.ar;
                osszTer6 += l.terulet;
                db6++;
            }
        }
        int atlagNMAr = osszAr / osszTer6;
        System.out.println("\tÁtlagos négyzetméter ár a 6. kerületben: " + atlagNMAr + " Ft/m2");

        System.out.println("4+1. feladat (word-ben rosszul volt ezért bizonytalanság miatt megcsináltam):");
        int atlagTer6 = osszTer6 / db6;
        System.out.println("\tÁtlagos terület a 6. kerületben: " + atlagTer6 + " m2");
    }
}
