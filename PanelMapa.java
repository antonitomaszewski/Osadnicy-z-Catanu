import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class PanelMapa extends JPanel {
    public PanelMapa() {

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);


        //rysujemy wszystkie pola


        for(Pole p : Mapa.lista_pol){
            Polygon szesciakat = new Polygon();
            for(Wierzcholek w : p.lista_wierzcholkow){
                szesciakat.addPoint(w.x, w.y);
            }
            g2d.setColor(p.kolor);
            g2d.fill(szesciakat);
        }



        //rysujemy wszystkie drogi
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(15));
        for(Krawedz k : Mapa.lista_krawedzi){
            g2d.drawLine(k.x1, k.y1, k.x2, k.y2);
        }




        //rysujemy zajete drogi
        g2d.setStroke(new BasicStroke(7));
        for(Krawedz k : Mapa.lista_krawedzi){
            if(!k.is_empty){
                Color c = new Color(0,0,255); //! DO ZMIANY!
                g2d.setColor(c);
                g2d.drawLine(k.x1, k.y1, k.x2, k.y2);
            }
        }

        //rysujemy osady



        //rysujemy drogi


    }
}

