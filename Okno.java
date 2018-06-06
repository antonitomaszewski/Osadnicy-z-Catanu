import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;



public class Okno extends JFrame{

    public static int size_width = 1680;
    public static int size_hight = 985;
    public static int label_gracza_width = (int)size_width/6;

    public static ArrayList<LabelGracza> lista_labeli_graczy = new ArrayList<LabelGracza>();

    public static JButton button_los = new JButton("RZUĆ KOŚĆMI");
    public static JButton button_koniec_tury = new JButton("KONIEC TURY");
    public static JButton button_buduj_droge = new JButton("BUDUJ DROGĘ");
    public static JButton button_buduj_osade = new JButton("BUDUJ OSADĘ");
    public static JButton button_buduj_miasto = new JButton("BUDUJ MIASTO");
    public static JButton button_wymiana = new JButton("WYMIEŃ SUROWCE");




    public static PanelMapa label_mapa = new PanelMapa();

    public ActionListener action_los = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Gra.czy_mozna_losowac) {
                Gra.wylosowano = true;
                Gra.czy_mozna_losowac = false;
                Gra.wylosuj_i_rozdaj_surowce();
                button_los.setText(Integer.toString(Gra.kostka_pierwsza) + "  " + Integer.toString(Gra.kostka_druga));
            }
        }
    };
    public ActionListener action_koniec = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Gra.budujemy_droge = false;
            Gra.budujemy_miasto = false;
            Gra.budujemy_osade = false;
            Gra.koniec_kolejki = true;
            Gra.wylosowano = false;
            Gra.wybrano_wspolrzedne = false;
            button_los.setText("RZUĆ KOŚĆMI");
            repaint();
        }
    };
    public ActionListener action_droga = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Gra.budujemy_droge = true;
            Gra.budujemy_osade = false;
            Gra.budujemy_miasto = false;
            repaint();
        }
    };
    public ActionListener action_osada = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Gra.budujemy_droge = false;
            Gra.budujemy_osade = true;
            Gra.budujemy_miasto = false;
            repaint();
        }
    };
    public ActionListener action_miasto = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Gra.budujemy_droge = false;
            Gra.budujemy_osade = false;
            Gra.budujemy_miasto = true;
            repaint();
        }
    };
    public ActionListener action_wymiana = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Wymiana();
        }
    };

    private MouseListener mouse_listener = new MouseAdapter()
    {

        @Override
        public void mouseClicked(MouseEvent ev)
        {
            Gra.x = ev.getX();
            Gra.y = ev.getY();
            Gra.wybrano_wspolrzedne = true;
            repaint();
        }

    };


    public Okno() {
        super("OSADNICY Z CATANU DEMO");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(10, 10);
        setSize(size_width + 20, size_hight + 40);
        setBackground(Color.BLACK);


        //LABELE GRACZY

        for(int i=0; i<Gra.liczba_graczy; i++){
            LabelGracza l = new LabelGracza(Gra.lista_graczy.get(i), i+1);
            l.setOpaque(true);
            l.setBackground(new Color(249, 249, 249));
            lista_labeli_graczy.add(l);
        }

        lista_labeli_graczy.get(0).setOpaque(true);
        lista_labeli_graczy.get(0).setBounds(0, 0, label_gracza_width, (int)size_hight/2);
        lista_labeli_graczy.get(0).setVisible(true);
        add(lista_labeli_graczy.get(0));


        lista_labeli_graczy.get(1).setOpaque(true);
        lista_labeli_graczy.get(1).setBounds(0, (int)size_hight/2, label_gracza_width, (int)size_hight/2);
        add(lista_labeli_graczy.get(1));


        if(lista_labeli_graczy.size()>2){
            lista_labeli_graczy.get(2).setOpaque(true);
            lista_labeli_graczy.get(2).setBounds(size_width - label_gracza_width, 0, label_gracza_width, (int)size_hight/2);
            add(lista_labeli_graczy.get(2));
        }

        if(lista_labeli_graczy.size()>3){
            lista_labeli_graczy.get(3).setOpaque(true);
            lista_labeli_graczy.get(3).setBounds(size_width - label_gracza_width, (int)size_hight/2, label_gracza_width, (int)size_hight/2);
            add(lista_labeli_graczy.get(3));
        }

        label_mapa.setOpaque(true);
        label_mapa.setBounds(label_gracza_width, 0, size_width - 2*label_gracza_width, size_hight);
        label_mapa.setBackground(new Color(162, 222, 255));

        label_mapa.add(button_los);
        label_mapa.add(button_koniec_tury);
        label_mapa.add(button_buduj_droge);
        label_mapa.add(button_buduj_osade);
        label_mapa.add(button_buduj_miasto);
        label_mapa.add(button_wymiana);

        button_los.setBounds((int)size_width/50, (int)3*size_hight/4 - (int)size_hight/20 - 10, (int)size_width/10, (int)size_hight/20);
        button_los.addActionListener(action_los);

        button_buduj_droge.setBounds((int)size_width/50, (int)3*size_hight/4, (int)size_width/10, (int)size_hight/20);
        button_buduj_droge.addActionListener(action_droga);

        button_buduj_osade.setBounds((int)size_width/50, (int)3*size_hight/4 + (int)size_hight/20 + 10, (int)size_width/10, (int)size_hight/20);
        button_buduj_osade.addActionListener(action_osada);

        button_buduj_miasto.setBounds((int)size_width/50, (int)3*size_hight/4 + 2*(int)size_hight/20 + 20, (int)size_width/10, (int)size_hight/20);
        button_buduj_miasto.addActionListener(action_miasto);

        button_koniec_tury.setBounds(size_width - 2*label_gracza_width - (int)size_width/50 - (int)size_width/10, (int)3*size_hight/4 + 2*(int)size_hight/20 + 20, (int)size_width/10, (int)size_hight/20);
        button_koniec_tury.addActionListener(action_koniec);

        button_wymiana.setBounds((int)size_width/50, (int)3*size_hight/4 - 2*(int)size_hight/20 - 20, (int)size_width/10, (int)size_hight/20);
        button_wymiana.addActionListener(action_wymiana);

        label_mapa.addMouseListener(mouse_listener);

        add(label_mapa);

        setResizable(true);
        setVisible(true);
        revalidate();
        repaint();

    }
}
