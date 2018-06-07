import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class LabelGracza extends JLabel {

    public int num;

    public LabelGracza(Gracz g, int n){
        num = n;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("ArialBlack", Font.PLAIN, 20));
        FontMetrics metrics = g.getFontMetrics(g2d.getFont());
        int xs = (Okno.label_gracza_width - metrics.stringWidth("Gracz " + Integer.toString(num)))/2;
        g2d.drawString("Gracz " + Integer.toString(num), xs, 30);
        xs = (Okno.label_gracza_width - metrics.stringWidth(Gra.lista_graczy.get(num-1).imie))/2;
        g2d.drawString(Gra.lista_graczy.get(num-1).imie, xs, 60);
        xs = (Okno.label_gracza_width - metrics.stringWidth("Punkty:  " + Integer.toString(Gra.lista_graczy.get(num-1).punkty)))/2;
        g2d.drawString("Punkty:  " + Integer.toString(Gra.lista_graczy.get(num-1).punkty), xs, 90);


        int b = Okno.label_gracza_width/7;
        int h = 120;
        g2d.setColor(new Color(144, 255, 106));
        g2d.fillRect(b, h, b, b);
        int x_pom = b*3/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(num-1).surowce.owca))/2;
        int y_pom = h+b/2 - metrics.getHeight()/2 + metrics.getAscent();
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(Gra.lista_graczy.get(num-1).surowce.owca), x_pom, y_pom);

        g2d.setColor(new Color(255, 228, 95));
        g2d.fillRect(3*b, h, b, b);
        x_pom = b*7/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(num-1).surowce.siano))/2;
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(Gra.lista_graczy.get(num-1).surowce.siano), x_pom, y_pom);

        g2d.setColor(new Color(76, 128, 20));
        g2d.fillRect(5*b, h, b, b);
        x_pom = b*11/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(num-1).surowce.drewno))/2;
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(Gra.lista_graczy.get(num-1).surowce.drewno), x_pom, y_pom);

        h += 2 * b;
        g2d.setColor(new Color(173, 92, 34));
        g2d.fillRect(2*b, h, b, b);
        x_pom = b*5/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(num-1).surowce.cegla))/2;
        y_pom += 2 * b;
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(Gra.lista_graczy.get(num-1).surowce.cegla), x_pom, y_pom);
        g2d.setColor(new Color(158, 158, 164));
        g2d.fillRect(4*b, h, b, b);
        x_pom = b*9/2 - metrics.stringWidth(Integer.toString(Gra.lista_graczy.get(num-1).surowce.kamien))/2;
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(Gra.lista_graczy.get(num-1).surowce.kamien), x_pom, y_pom);

        h = h + b + 40;
        xs = (Okno.label_gracza_width - metrics.stringWidth("Zostało dróg:  " + Integer.toString(Gra.lista_graczy.get(num-1).pozostale_drogi)))/2;
        g2d.drawString("Zostało dróg:  " + Integer.toString(Gra.lista_graczy.get(num-1).pozostale_drogi), xs, h);

        h = h + 40;
        xs = (Okno.label_gracza_width - metrics.stringWidth("Zostało osad:  " + Integer.toString(Gra.lista_graczy.get(num-1).pozostale_drogi)))/2;
        g2d.drawString("Zostało osad:  " + Integer.toString(Gra.lista_graczy.get(num-1).pozostale_osady), xs, h);

        h = h + 40;
        xs = (Okno.label_gracza_width - metrics.stringWidth("Zostało miast:  " + Integer.toString(Gra.lista_graczy.get(num-1).pozostale_drogi)))/2;
        g2d.drawString("Zostało miast:  " + Integer.toString(Gra.lista_graczy.get(num-1).pozostale_miasta), xs, h);


        g2d.setColor(Gra.lista_graczy.get(num-1).kolor);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRect(0,0, Okno.label_gracza_width, (int)Okno.size_hight/2);

        setVisible(true);
        revalidate();
        repaint();
    }
}