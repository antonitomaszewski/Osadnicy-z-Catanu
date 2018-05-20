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
                g2d.setColor(k.kolor_gracza);
                g2d.drawLine(k.x1, k.y1, k.x2, k.y2);
            }
        }

        //rysujemy osady
        for(Wierzcholek w : Mapa.lista_wierzcholkow){
            if(!w.is_empty && w.budynek == 1){
                g2d.setColor(w.kolor_gracza);
                Polygon osada = new Polygon();
                osada.addPoint(w.x - 30, w.y + 20);
                osada.addPoint(w.x - 30, w.y - 20);
                osada.addPoint(w.x, w.y - 40);
                osada.addPoint(w.x + 30, w.y - 20);
                osada.addPoint(w.x + 30, w.y + 20);
                g2d.fill(osada);
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(5));
                g2d.draw(osada);
            }
        }

        //rysujemy miasta
        for(Wierzcholek w : Mapa.lista_wierzcholkow){
            if(!w.is_empty && w.budynek == 2){
                g2d.setColor(w.kolor_gracza);
                Polygon miasto = new Polygon();
                miasto.addPoint(w.x - 40, w.y + 30);
                miasto.addPoint(w.x - 40, w.y - 20);
                miasto.addPoint(w.x - 20, w.y - 40);
                miasto.addPoint(w.x + 3 , w.y - 20);
                miasto.addPoint(w.x + 3, w.y - 5);
                miasto.addPoint(w.x + 30, w.y - 5);
                miasto.addPoint(w.x + 30, w.y + 30);
                g2d.fill(miasto);
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(5));
                g2d.draw(miasto);
            }
        }


    }
}

