package elalkbead;

import java.util.ArrayList;
import java.util.Scanner;

public class ElAlkBead {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Válassz feladatot ====");
        System.out.println("1 - Királyok");
        System.out.println("2 - Henger számítás");
        System.out.println("3 - Lakás adatok");
        System.out.print("Írd be a számot: ");

        int valasz = sc.nextInt();
        System.out.println();

        switch (valasz) {
            case 1:
                Kiralyok.main(null);
                break;
            case 2:
                HengerGUI.main(null);
                break;
            case 3:
                String fajlNev = "src/elalkbead/hasznalt.json";
                ArrayList<Lakas> lakasok = Lakas.beolvas(fajlNev);
                Lakas.feldolgoz(lakasok);
                break;
            default:
                System.out.println("Hibás választás! Próbáld újra.");
                break;
        }

        sc.close();
    }
}
