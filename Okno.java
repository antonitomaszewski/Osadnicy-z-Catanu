import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Okno extends JFrame{

    public static int size_width = 1680;
    public static int size_hight = 985;
    public static int label_gracza_width = (int)size_width/6;

    public JLabel label_gracza;
    public JLabel label_gracza2;
    public JLabel label_gracza3;
    public JLabel label_gracza4;

    public static JButton button_los = new JButton("RZUĆ KOŚĆMI");
    public static JButton button_koniec_tury = new JButton("KONIEC TURY");
    public static JButton button_buduj_droge = new JButton("BUDUJ DROGĘ");
    public static JButton button_buduj_osade = new JButton("BUDUJ OSADĘ");
    public static JButton button_buduj_miasto = new JButton("BUDUJ MIASTO");
    public static JButton button_wymiana = new JButton("WYMIEŃ SUROWCE");






    public static JPanel panel_gracza2 = new JPanel();
    public static PanelMapa label_mapa = new PanelMapa();
    public JTextArea siema = new JTextArea("Witam");
    public static JTextArea siema2 = new JTextArea("Witam2");

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

        label_gracza = new JLabel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("ArialBlack", Font.PLAIN, 20));
                FontMetrics metrics = g.getFontMetrics(g2d.getFont());
                int xs = (label_gracza_width - metrics.stringWidth("Gracz 1"))/2;
                g2d.drawString("Gracz 1", xs, 30);
                xs = (label_gracza_width - metrics.stringWidth(Gra.lista_graczy.get(0).imie))/2;
                g2d.drawString(Gra.lista_graczy.get(0).imie, xs, 60);

                int b = label_gracza_width/7;
                int h = 90;
                g2d.setColor(new Color(144, 255, 106));
                g2d.fillRect(b, h, b, b);
                int x_pom = b*3/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(0).surowce.owca))/2;
                int y_pom = h+b/2 - metrics.getHeight()/2 + metrics.getAscent();
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(0).surowce.owca), x_pom, y_pom);

                g2d.setColor(new Color(255, 228, 95));
                g2d.fillRect(3*b, h, b, b);
                x_pom = b*7/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(0).surowce.siano))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(0).surowce.siano), x_pom, y_pom);

                g2d.setColor(new Color(76, 128, 20));
                g2d.fillRect(5*b, h, b, b);
                x_pom = b*11/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(0).surowce.drewno))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(0).surowce.drewno), x_pom, y_pom);

                h += 2 * b;
                g2d.setColor(new Color(173, 92, 34));
                g2d.fillRect(2*b, h, b, b);
                x_pom = b*5/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(0).surowce.cegla))/2;
                y_pom += 2 * b;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(0).surowce.cegla), x_pom, y_pom);
                g2d.setColor(new Color(158, 158, 164));
                g2d.fillRect(4*b, h, b, b);
                x_pom = b*9/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(0).surowce.kamien))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(0).surowce.kamien), x_pom, y_pom);

                g2d.setColor(Gra.lista_graczy.get(0).kolor);
                g2d.setStroke(new BasicStroke(10));
                g2d.drawRect(0,0, label_gracza_width, (int)size_hight/2);

                revalidate();
                repaint();
            }
        };

        label_gracza.setOpaque(true);
        label_gracza.setBounds(0, 0, label_gracza_width, (int)size_hight/2);
        label_gracza.setBackground(new Color(249, 249, 249));
        add(label_gracza);

        label_gracza2 = new JLabel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("ArialBlack", Font.PLAIN, 20));
                FontMetrics metrics = g.getFontMetrics(g2d.getFont());
                int xs = (label_gracza_width - metrics.stringWidth("Gracz 2"))/2;
                g2d.drawString("Gracz 2", xs, 30);
                xs = (label_gracza_width - metrics.stringWidth(Gra.lista_graczy.get(1).imie))/2;
                g2d.drawString(Gra.lista_graczy.get(1).imie, xs, 60);

                int b = label_gracza_width/7;
                int h = 90;
                g2d.setColor(new Color(144, 255, 106));
                g2d.fillRect(b, h, b, b);
                int x_pom = b*3/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(1).surowce.owca))/2;
                int y_pom = h+b/2 - metrics.getHeight()/2 + metrics.getAscent();
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(1).surowce.owca), x_pom, y_pom);

                g2d.setColor(new Color(255, 228, 95));
                g2d.fillRect(3*b, h, b, b);
                x_pom = b*7/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(1).surowce.siano))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(1).surowce.siano), x_pom, y_pom);

                g2d.setColor(new Color(76, 128, 20));
                g2d.fillRect(5*b, h, b, b);
                x_pom = b*11/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(1).surowce.drewno))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(1).surowce.drewno), x_pom, y_pom);

                h += 2 * b;
                g2d.setColor(new Color(173, 92, 34));
                g2d.fillRect(2*b, h, b, b);
                x_pom = b*5/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(1).surowce.cegla))/2;
                y_pom += 2 * b;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(1).surowce.cegla), x_pom, y_pom);
                g2d.setColor(new Color(158, 158, 164));
                g2d.fillRect(4*b, h, b, b);
                x_pom = b*9/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(1).surowce.kamien))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(1).surowce.kamien), x_pom, y_pom);

                g2d.setColor(Gra.lista_graczy.get(1).kolor);
                g2d.setStroke(new BasicStroke(10));
                g2d.drawRect(0,0, label_gracza_width, (int)size_hight/2);

                revalidate();
                repaint();
            }
        };
        label_gracza2.setOpaque(true);
        label_gracza2.setBounds(0, (int)size_hight/2, label_gracza_width, (int)size_hight/2);
        label_gracza2.setBackground(new Color(249, 249, 249));
        add(label_gracza2);

        label_gracza3 = new JLabel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("ArialBlack", Font.PLAIN, 20));
                FontMetrics metrics = g.getFontMetrics(g2d.getFont());
                int xs = (label_gracza_width - metrics.stringWidth("Gracz 3"))/2;
                g2d.drawString("Gracz 3", xs, 30);
                xs = (label_gracza_width - metrics.stringWidth(Gra.lista_graczy.get(2).imie))/2;
                g2d.drawString(Gra.lista_graczy.get(2).imie, xs, 60);

                int b = label_gracza_width/7;
                int h = 90;
                g2d.setColor(new Color(144, 255, 106));
                g2d.fillRect(b, h, b, b);
                int x_pom = b*3/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(2).surowce.owca))/2;
                int y_pom = h+b/2 - metrics.getHeight()/2 + metrics.getAscent();
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(2).surowce.owca), x_pom, y_pom);

                g2d.setColor(new Color(255, 228, 95));
                g2d.fillRect(3*b, h, b, b);
                x_pom = b*7/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(2).surowce.siano))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(2).surowce.siano), x_pom, y_pom);

                g2d.setColor(new Color(76, 128, 20));
                g2d.fillRect(5*b, h, b, b);
                x_pom = b*11/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(2).surowce.drewno))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(2).surowce.drewno), x_pom, y_pom);

                h += 2 * b;
                g2d.setColor(new Color(173, 92, 34));
                g2d.fillRect(2*b, h, b, b);
                x_pom = b*5/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(2).surowce.cegla))/2;
                y_pom += 2 * b;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(2).surowce.cegla), x_pom, y_pom);
                g2d.setColor(new Color(158, 158, 164));
                g2d.fillRect(4*b, h, b, b);
                x_pom = b*9/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(2).surowce.kamien))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(2).surowce.kamien), x_pom, y_pom);

                g2d.setColor(Gra.lista_graczy.get(2).kolor);
                g2d.setStroke(new BasicStroke(10));
                g2d.drawRect(0,0, label_gracza_width, (int)size_hight/2);

                revalidate();
                repaint();
            }
        };
        label_gracza3.setOpaque(true);
        label_gracza3.setBounds(size_width - label_gracza_width, 0, label_gracza_width, (int)size_hight/2);
        label_gracza3.setBackground(new Color(249, 249, 249));
        add(label_gracza3);

        label_gracza4 = new JLabel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("ArialBlack", Font.PLAIN, 20));
                FontMetrics metrics = g.getFontMetrics(g2d.getFont());
                int xs = (label_gracza_width - metrics.stringWidth("Gracz 4"))/2;
                g2d.drawString("Gracz 4", xs, 30);
                xs = (label_gracza_width - metrics.stringWidth(Gra.lista_graczy.get(3).imie))/2;
                g2d.drawString(Gra.lista_graczy.get(3).imie, xs, 60);

                int b = label_gracza_width/7;
                int h = 90;
                g2d.setColor(new Color(144, 255, 106));
                g2d.fillRect(b, h, b, b);
                int x_pom = b*3/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(3).surowce.owca))/2;
                int y_pom = h+b/2 - metrics.getHeight()/2 + metrics.getAscent();
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(3).surowce.owca), x_pom, y_pom);

                g2d.setColor(new Color(255, 228, 95));
                g2d.fillRect(3*b, h, b, b);
                x_pom = b*7/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(3).surowce.siano))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(3).surowce.siano), x_pom, y_pom);

                g2d.setColor(new Color(76, 128, 20));
                g2d.fillRect(5*b, h, b, b);
                x_pom = b*11/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(3).surowce.drewno))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(3).surowce.drewno), x_pom, y_pom);

                h += 2 * b;
                g2d.setColor(new Color(173, 92, 34));
                g2d.fillRect(2*b, h, b, b);
                x_pom = b*5/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(3).surowce.cegla))/2;
                y_pom += 2 * b;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(3).surowce.cegla), x_pom, y_pom);
                g2d.setColor(new Color(158, 158, 164));
                g2d.fillRect(4*b, h, b, b);
                x_pom = b*9/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(3).surowce.kamien))/2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(Gra.lista_graczy.get(3).surowce.kamien), x_pom, y_pom);

                g2d.setColor(Gra.lista_graczy.get(3).kolor);
                g2d.setStroke(new BasicStroke(10));
                g2d.drawRect(0,0, label_gracza_width, (int)size_hight/2);

                revalidate();
                repaint();
            }
        };

        label_gracza4.setOpaque(true);
        label_gracza4.setBounds(size_width - label_gracza_width, (int)size_hight/2, label_gracza_width, (int)size_hight/2);
        label_gracza4.setBackground(new Color(249, 249, 249));
        add(label_gracza4);

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
        repaint();

    }
}
