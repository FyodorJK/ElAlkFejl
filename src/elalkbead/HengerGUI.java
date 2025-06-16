package elalkbead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class HengerGUI {
    private static final DecimalFormat KETTO_TIZEDES = new DecimalFormat("#.00");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HengerGUI::letrehozGUI);
    }

    private static void letrehozGUI() {
        JFrame ablak = new JFrame("Henger");
        ablak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ablak.setSize(500, 200);
        ablak.setLayout(null);

        Font betutipus = new Font("SansSerif", Font.PLAIN, 12);

        JLabel feliratR = new JLabel("R:");
        feliratR.setFont(betutipus);
        feliratR.setBounds(30, 20, 20, 20);
        ablak.add(feliratR);

        JLabel feliratM = new JLabel("M:");
        feliratM.setFont(betutipus);
        feliratM.setBounds(130, 20, 20, 20);
        ablak.add(feliratM);

        JTextField mezoR = new JTextField();
        mezoR.setFont(betutipus);
        mezoR.setBounds(30, 45, 90, 20);
        ablak.add(mezoR);

        JTextField mezoM = new JTextField();
        mezoM.setFont(betutipus);
        mezoM.setBounds(130, 45, 90, 20);
        ablak.add(mezoM);

        JLabel terfogatFelirat = new JLabel("V=0cm3");
        terfogatFelirat.setFont(betutipus);
        terfogatFelirat.setBounds(270, 45, 100, 20);
        ablak.add(terfogatFelirat);

        JLabel feluletFelirat = new JLabel("A=0cm2");
        feluletFelirat.setFont(betutipus);
        feluletFelirat.setBounds(370, 45, 100, 20);
        ablak.add(feluletFelirat);

        JButton szamolGomb = new JButton("Számol");
        szamolGomb.setBounds(30, 90, 80, 30);
        ablak.add(szamolGomb);

        szamolGomb.addActionListener((ActionEvent e) -> {
            String rSzoveg = mezoR.getText();
            String mSzoveg = mezoM.getText();

            if (rSzoveg.isEmpty() || mSzoveg.isEmpty()) return;

            try {
                double R = Double.parseDouble(rSzoveg);
                double M = Double.parseDouble(mSzoveg);

                double terfogat = Math.PI * R * R * M;
                double felszin = 2 * Math.PI * R * (R + M);

                terfogatFelirat.setText("V=" + KETTO_TIZEDES.format(terfogat) + "cm3");
                feluletFelirat.setText("A=" + KETTO_TIZEDES.format(felszin) + "cm2");
            } catch (NumberFormatException ex) {}
        });
        ablak.setVisible(true);
    }
}
