package elalkbead;

import java.io.*;
import java.util.*;

public class Kiralyok {

    private final String nev;
    private final int szuletesEv;
    private final int halalozasEv;
    private final String haz;

    public Kiralyok(String nev, int szuletesEv, int halalozasEv, String haz) {
        this.nev = nev;
        this.szuletesEv = szuletesEv;
        this.halalozasEv = halalozasEv;
        this.haz = haz;
    }

    public int eletkor() {
        return halalozasEv - szuletesEv;
    }

    public String getNev() {
        return nev;
    }

    public String getHaz() {
        return haz;
    }

    @Override
    public String toString() {
        return nev + " (" + szuletesEv + "-" + halalozasEv + "), " + haz;
    }

    public static void main(String[] args) {
        List<Kiralyok> kiralyok = beolvasas("src/kiralyok.txt");

        System.out.println("1. feladat:");
        Kiralyok legtovabbElt = maxEletkor(kiralyok);
        System.out.println("\t" + legtovabbElt.getNev() + " (" + legtovabbElt.eletkor() + " év)");

        System.out.println("2. feladat:");
        int arpadHazDb = szamolArpadHaz(kiralyok);
        System.out.println("\tÁrpád-házi királyok száma: " + arpadHazDb);

        System.out.println("3. feladat:");
        kiirEgyediHazak(kiralyok);

        System.out.println("4. feladat:");
        csoportositasHazankent(kiralyok);
    }

    public static List<Kiralyok> beolvasas(String fajlNev) {
        List<Kiralyok> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fajlNev))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                String[] adatok = sor.split(",");
                String nev = adatok[0].trim();
                int szuletes = Integer.parseInt(adatok[1].trim());
                int halalozas = Integer.parseInt(adatok[2].trim());
                String haz = adatok[3].trim();
                lista.add(new Kiralyok(nev, szuletes, halalozas, haz));
            }
        } catch (IOException e) {}
        return lista;
    }


    public static Kiralyok maxEletkor(List<Kiralyok> kiralyok) {
        Kiralyok max = null;
        int maxKor = 0;
        for (int i = 0; i < kiralyok.size(); i++) {
            Kiralyok kiraly = kiralyok.get(i);
            if (kiraly.eletkor() > maxKor) {
                maxKor = kiraly.eletkor();
                max = kiraly;
            }
        }
        return max;
    }

    public static int szamolArpadHaz(List<Kiralyok> kiralyok) {
        int db = 0;
        for (int i = 0; i < kiralyok.size(); i++) {
            Kiralyok kiraly = kiralyok.get(i);
            if (kiraly.getHaz().equalsIgnoreCase("Arpad-haz")) {
                db++;
            }
        }
        return db;
    }

    public static void kiirEgyediHazak(List<Kiralyok> kiralyok) {
        Set<String> hazak = new HashSet<>();
        for (int i = 0; i < kiralyok.size(); i++) {
            Kiralyok kiraly = kiralyok.get(i);
            hazak.add(kiraly.getHaz());
        }

        List<String> hazLista = new ArrayList<>(hazak);
        for (int i = 0; i < hazLista.size(); i++) {
            System.out.println("\t" + hazLista.get(i));
        }
    }

    public static void csoportositasHazankent(List<Kiralyok> kiralyok) {
        Map<String, Integer> stat = new TreeMap<>();
        for (int i = 0; i < kiralyok.size(); i++) {
            Kiralyok kiraly = kiralyok.get(i);
            String haz = kiraly.getHaz();
            if (stat.containsKey(haz)) {
                stat.put(haz, stat.get(haz) + 1);
            } else {
                stat.put(haz, 1);
            }
        }

        List<Map.Entry<String, Integer>> bejegyzesek = new ArrayList<>(stat.entrySet());
        for (int i = 0; i < bejegyzesek.size(); i++) {
            Map.Entry<String, Integer> entry = bejegyzesek.get(i);
            System.out.println("\t" + entry.getKey() + "\t" + entry.getValue() + " fő");
        }
    }
}