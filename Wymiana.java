import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Wymiana extends JFrame {


    public JLabel label_caly_obaszar;
    public JPanel panel_kup = new JPanel();
    public JPanel panel_sprzedaj = new JPanel();
    public int Wymiana_width = 700;
    public int Wymiana_high = 400;

    public static JCheckBox kup_owca = new JCheckBox("Owca");
    public static JCheckBox kup_siano = new JCheckBox("Siano");
    public static JCheckBox kup_drewno = new JCheckBox("Drewno");
    public static JCheckBox kup_cegla = new JCheckBox("Cegła");
    public static JCheckBox kup_kamien = new JCheckBox("Kamien");


    public static JCheckBox sprzedaj_owca = new JCheckBox("Owca");
    public static JCheckBox sprzedaj_siano = new JCheckBox("Siano");
    public static JCheckBox sprzedaj_drewno = new JCheckBox("Drewno");
    public static JCheckBox sprzedaj_cegla = new JCheckBox("Cegła");
    public static JCheckBox sprzedaj_kamien = new JCheckBox("Kamien");

    public static JButton button_potwierdz_transakcje = new JButton("Potwierdz transakcję");

    public ActionListener action_potwierdz_transakcje = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Gra.czas_akcji_gracza) {
                String kup = "";
                int licznik = 0;
                if(kup_owca.isSelected()) {
                    licznik++;
                    kup = "owca";
                }
                if(kup_siano.isSelected()) {
                    licznik++;
                    kup = "siano";
                }
                if(kup_drewno.isSelected()) {
                    licznik++;
                    kup = "drewno";
                }
                if(kup_cegla.isSelected()) {
                    licznik++;
                    kup = "cegla";
                }
                if(kup_kamien.isSelected()) {
                    licznik++;
                    kup = "kamien";
                }

                String sprzedaj = "";
                int licznik_sprzedaj = 0;
                if(sprzedaj_owca.isSelected()) {
                    licznik_sprzedaj++;
                    sprzedaj = "owca";
                }
                if(sprzedaj_siano.isSelected()) {
                    licznik_sprzedaj++;
                    sprzedaj = "siano";
                }
                if(sprzedaj_drewno.isSelected()) {
                    licznik_sprzedaj++;
                    sprzedaj = "drewno";
                }
                if(sprzedaj_cegla.isSelected()) {
                    licznik_sprzedaj++;
                    sprzedaj = "cegla";
                }
                if(sprzedaj_kamien.isSelected()) {
                    licznik_sprzedaj++;
                    sprzedaj = "kamien";
                }

                if (licznik == 1 && licznik_sprzedaj == 1) {
                    Gra.lista_graczy.get(Gra.kolejka).czy_mozna_wymienic_wymien(kup, sprzedaj);
                    Gra.ustaw_poczatkowe_listy_dostepnych_drog_osad_i_miast();
                    System.out.println("Udało się wymienić");
                }
                else
                    System.out.println("NIE Udało się wymienić");
                Gra.potwierdzono_transakcje_i_zamknieto = true;
            }

        }
    };

    public Wymiana(){

        super("Wymiana z Bankiem");
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
                int xs = (Wymiana_width - metrics.stringWidth("Wybierz surowiec do kupienia"))/2;
                g2d.drawString("Wybierz surowiec do kupienia", xs, 30);

                xs = (Wymiana_width - metrics.stringWidth("Wybierz surowiec do sprzedania"))/2;
                g2d.drawString("Wybierz surowiec do sprzedania", xs, 140);

                revalidate();
                repaint();
            }
        };


        label_caly_obaszar.setOpaque(true);

        panel_kup.setBackground(Color.WHITE);
        panel_kup.setSize(Wymiana_width, 50);
        panel_kup.setLocation(0, 45);
        panel_kup.setLayout(new GridLayout(1,5));
        panel_kup.add(kup_owca);
        panel_kup.add(kup_siano);
        panel_kup.add(kup_drewno);
        panel_kup.add(kup_cegla);
        panel_kup.add(kup_kamien);

        label_caly_obaszar.add(panel_kup);

        panel_sprzedaj.setBackground(Color.WHITE);
        panel_sprzedaj.setSize(Wymiana_width, 50);
        panel_sprzedaj.setLocation(0, 155);
        panel_sprzedaj.setLayout(new GridLayout(1,5));
        panel_sprzedaj.add(sprzedaj_owca);
        panel_sprzedaj.add(sprzedaj_siano);
        panel_sprzedaj.add(sprzedaj_drewno);
        panel_sprzedaj.add(sprzedaj_cegla);
        panel_sprzedaj.add(sprzedaj_kamien);

        button_potwierdz_transakcje.setFont(new Font("ArialBlack", Font.PLAIN, 17));
        button_potwierdz_transakcje.setBounds(250, 220, 200, 70);
        button_potwierdz_transakcje.addActionListener(action_potwierdz_transakcje);
        label_caly_obaszar.add(panel_sprzedaj);

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
}