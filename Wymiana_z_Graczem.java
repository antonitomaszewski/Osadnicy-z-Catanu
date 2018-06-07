import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Wymiana_z_Graczem extends JFrame {
public int Wymiana_width = 700;
public int Wymiana_high = 500;

public static JLabel label_caly_obaszar;
public static JPanel panel_kup = new JPanel();
// public JPanel panel_kup_ile = new JPanel();
public JPanel panel_sprzedaj = new JPanel();
// public JPanel panel_sprzedaj_ile = new JPanel();
public JPanel panel_wybierz_gracza = new JPanel();

public JSpinner kup_owca = new JSpinner();
public JSpinner kup_siano = new JSpinner();
public JSpinner kup_drewno = new JSpinner();
public JSpinner kup_cegla = new JSpinner();
public JSpinner kup_kamien = new JSpinner();

public JSpinner sprzedaj_owca = new JSpinner();
public JSpinner sprzedaj_siano = new JSpinner();
public JSpinner sprzedaj_drewno = new JSpinner();
public JSpinner sprzedaj_cegla = new JSpinner();
public JSpinner sprzedaj_kamien = new JSpinner();

// public static ArrayList<JCheckBox> lista_graczy_do_transakcji = new ArrayList<JCheckBox>();
public static JCheckBox Gracz_1 = new JCheckBox();
public static JCheckBox Gracz_2 = new JCheckBox();
public static JCheckBox Gracz_3 = new JCheckBox();
public static JCheckBox Gracz_4 = new JCheckBox();

public static JButton button_potwierdz_transakcje = new JButton("Potwierdz transakcję");


public void checkboxy(){
        if (Gra.liczba_graczy > 0) {
                Gracz_1 = new JCheckBox(Gra.lista_graczy.get(0).imie);
                panel_wybierz_gracza.add(Gracz_1);
        }
        if (Gra.liczba_graczy > 1) {
                Gracz_2 = new JCheckBox(Gra.lista_graczy.get(1).imie);
                panel_wybierz_gracza.add(Gracz_2);
        }
        if (Gra.liczba_graczy > 2) {
                Gracz_3 = new JCheckBox(Gra.lista_graczy.get(2).imie);
                panel_wybierz_gracza.add(Gracz_3);
        }
        if (Gra.liczba_graczy > 3) {
                Gracz_4 = new JCheckBox(Gra.lista_graczy.get(3).imie);
                panel_wybierz_gracza.add(Gracz_4);
        }
}
public Wymiana_z_Graczem(){

        super("Wymiana z Graczem");
        setSize(Wymiana_width, Wymiana_high);
        setLocation(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // setDefaultCloseOperation(WindowConstants.setAlwaysOnTop(false));


        label_caly_obaszar = new JLabel(){
                protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setFont(new Font("ArialBlack", Font.PLAIN, 30));
                        FontMetrics metrics = g.getFontMetrics(g2d.getFont());
                        int xs = (Wymiana_width - metrics.stringWidth("Wybierz surowce do kupienia wraz z ilościami"))/2;
                        g2d.drawString("Wybierz surowce do kupienia wraz z ilościami", xs, 30);

                        g2d.setFont(new Font("ArialBlack", Font.PLAIN, 20));
                        metrics = g.getFontMetrics(g2d.getFont());
                        xs = (Wymiana_width - metrics.stringWidth("owca              siano              drewno              cegła              kamień"))/2;
                        g2d.drawString("owca              siano              drewno              cegła              kamień", xs, 70);

                        g2d.setFont(new Font("ArialBlack", Font.PLAIN, 30));
                        metrics = g.getFontMetrics(g2d.getFont());
                        xs = (Wymiana_width - metrics.stringWidth("Wybierz surowce do sprzedania wraz z ilościami"))/2;
                        g2d.drawString("Wybierz surowce do sprzedania wraz z ilościami", xs, 170);

                        g2d.setFont(new Font("ArialBlack", Font.PLAIN, 20));
                        metrics = g.getFontMetrics(g2d.getFont());
                        xs = (Wymiana_width - metrics.stringWidth("owca              siano              drewno              cegła              kamień"))/2;
                        g2d.drawString("owca              siano              drewno              cegła              kamień", xs, 210);


                        revalidate();
                        repaint();
                }
        };
        // lista_graczy_do_transakcji = new ArrayList<JCheckBox>();


        label_caly_obaszar.setOpaque(true);

        panel_kup.setBackground(Color.WHITE);
        panel_kup.setSize(Wymiana_width, 50);
        panel_kup.setLocation(0, 75);
        panel_kup.setLayout(new GridLayout(1,5));
        panel_kup.add(kup_owca);
        panel_kup.add(kup_siano);
        panel_kup.add(kup_drewno);
        panel_kup.add(kup_cegla);
        panel_kup.add(kup_kamien);

        label_caly_obaszar.add(panel_kup);

        panel_sprzedaj.setBackground(Color.WHITE);
        panel_sprzedaj.setSize(Wymiana_width, 50);
        panel_sprzedaj.setLocation(0, 215);
        panel_sprzedaj.setLayout(new GridLayout(1,5));
        panel_sprzedaj.add(sprzedaj_owca);
        panel_sprzedaj.add(sprzedaj_siano);
        panel_sprzedaj.add(sprzedaj_drewno);
        panel_sprzedaj.add(sprzedaj_cegla);
        panel_sprzedaj.add(sprzedaj_kamien);

        label_caly_obaszar.add(panel_sprzedaj);


        panel_wybierz_gracza.setBackground(Color.PINK);
        panel_wybierz_gracza.setSize(Wymiana_width, 50);
        panel_wybierz_gracza.setLocation(0, 400);
        panel_wybierz_gracza.setLayout(new GridLayout(1, Gra.liczba_graczy));
        checkboxy();

        // for (Gracz G : Gra.lista_graczy) {
        //         JCheckBox imie_gracza = new JCheckBox(G.imie);
        //         imie_gracza.setVisible(true);
        //         lista_graczy_do_transakcji.add(imie_gracza);
        //         panel_wybierz_gracza.add(imie_gracza);
        // }
        // for (JCheckBox C : lista_graczy_do_transakcji) {
        //         panel_wybierz_gracza.add(C);
        // }
        label_caly_obaszar.add(panel_wybierz_gracza);

        button_potwierdz_transakcje.setFont(new Font("ArialBlack", Font.PLAIN, 17));
        button_potwierdz_transakcje.setBounds(250, 320, 200, 70);
        button_potwierdz_transakcje.addActionListener(action_potwierdz_transakcje);



        /*
           label_caly_obaszar.add(sprzedaj_owca);
           label_caly_obaszar.add(sprzedaj_siano);
           label_caly_obaszar.add(sprzedaj_drewno);
           label_caly_obaszar.add(sprzedaj_cegla);
           label_caly_obaszar.add(sprzedaj_kamien);

         */
        label_caly_obaszar.add(button_potwierdz_transakcje);

        label_caly_obaszar.setBackground(Color.lightGray);
        label_caly_obaszar.setBounds(0,0, Wymiana_width, Wymiana_high);
        add(label_caly_obaszar);




        setVisible(true);
        revalidate();
        repaint();
}

public boolean czy_mozna_przeprowadzic_transakcje() {
        Gracz G = new Gracz();
        // for (JCheckBox imie : lista_graczy_do_transakcji) {
        //         if (imie.isSelected()) {
        //                 for (Gracz G1 : Gra.lista_graczy) {
        //                         if (imie.getText() == G1.imie) {
        //                                 G = G1;
        //                         }
        //                 }
        //         }
        // }
        if (Gracz_1.isSelected()) {
                G = Gra.lista_graczy.get(0);
        }
        else if (Gracz_2.isSelected()) {
                G = Gra.lista_graczy.get(1);
        }
        else if (Gracz_3.isSelected()) {
                G = Gra.lista_graczy.get(2);
        }
        else if (Gracz_4.isSelected()) {
                G = Gra.lista_graczy.get(3);
        }
        String[] nazwa_surwca = {"owca", "siano", "drewno", "cegla", "kamien"};
        int[] kup_ile = new int[5];
        int[] sprzedaj_ile = new int[5];
        for (int i = 0; i < 5; i++) {
                kup_ile[i] = 0;
                sprzedaj_ile[i] = 0;
        }



        kup_ile[0] = (int) kup_owca.getValue();
        kup_ile[1] = (int) kup_siano.getValue();
        kup_ile[2] = (int) kup_drewno.getValue();
        kup_ile[3] = (int) kup_cegla.getValue();
        kup_ile[4] = (int) kup_kamien.getValue();

        sprzedaj_ile[0] = (int) sprzedaj_owca.getValue();
        sprzedaj_ile[1] = (int) sprzedaj_siano.getValue();
        sprzedaj_ile[2] = (int) sprzedaj_drewno.getValue();
        sprzedaj_ile[3] = (int) sprzedaj_cegla.getValue();
        sprzedaj_ile[4] = (int) sprzedaj_kamien.getValue();


        int i = 0;
        for (i = 0; i < 5; i++) {
                if (!Gra.lista_graczy.get(Gra.kolejka).czy_mozna_wymienic(sprzedaj_ile[i], nazwa_surwca [i]) || !G.czy_mozna_wymienic(kup_ile[i], nazwa_surwca [i])) {
                        System.out.println("niewyszło " + sprzedaj_ile[i] + " " + kup_ile[i]);
                        return false;
                }
        }
        return true;
}

public ActionListener action_potwierdz_transakcje = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                if (Gra.czas_akcji_gracza && czy_mozna_przeprowadzic_transakcje()) {
                        Gracz G = new Gracz();
                        // for (JCheckBox imie : lista_graczy_do_transakcji) {
                        //         if (imie.isSelected()) {
                        //                 for (Gracz G1 : Gra.lista_graczy) {
                        //                         if (imie.getText() == G1.imie) {
                        //                                 G = G1;
                        //                         }
                        //                 }
                        //         }
                        // }
                        if (Gracz_1.isSelected()) {
                                G = Gra.lista_graczy.get(0);
                        }
                        else if (Gracz_2.isSelected()) {
                                G = Gra.lista_graczy.get(1);
                        }
                        else if (Gracz_3.isSelected()) {
                                G = Gra.lista_graczy.get(2);
                        }
                        else if (Gracz_4.isSelected()) {
                                G = Gra.lista_graczy.get(3);
                        }
                        String[] nazwa_surwca = {"owca", "siano", "drewno", "cegla", "kamien"};
                        int[] kup_ile = new int[5];
                        int[] sprzedaj_ile = new int[5];
                        for (int i = 0; i < 5; i++) {
                                kup_ile[i] = 0;
                                sprzedaj_ile[i] = 0;
                        }



                        kup_ile[0] = (int) kup_owca.getValue();
                        kup_ile[1] = (int) kup_siano.getValue();
                        kup_ile[2] = (int) kup_drewno.getValue();
                        kup_ile[3] = (int) kup_cegla.getValue();
                        kup_ile[4] = (int) kup_kamien.getValue();

                        sprzedaj_ile[0] = (int) sprzedaj_owca.getValue();
                        sprzedaj_ile[1] = (int) sprzedaj_siano.getValue();
                        sprzedaj_ile[2] = (int) sprzedaj_drewno.getValue();
                        sprzedaj_ile[3] = (int) sprzedaj_cegla.getValue();
                        sprzedaj_ile[4] = (int) sprzedaj_kamien.getValue();



                        int i = 0;
                        for (i = 0; i < 5; i++) {
                                Gra.lista_graczy.get(Gra.kolejka).dodaj(kup_ile[i], nazwa_surwca[i]);
                                Gra.lista_graczy.get(Gra.kolejka).zabierz(sprzedaj_ile[i], nazwa_surwca[i]);
                                G.zabierz(kup_ile[i], nazwa_surwca[i]);
                                G.dodaj(sprzedaj_ile[i], nazwa_surwca[i]);
                                System.out.println("udało się " + i);
                        }
                        Gra.potwierdzono_transakcje_i_zamknieto = true;

                }
        }
};


}
