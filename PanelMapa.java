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
        int centerWidth = new Integer(Okno.panel.getWidth() / 2);
        int centerHeight = new Integer(Okno.panel.getHeight() / 2);


        Polygon osada = new Polygon();
        Polygon miasto = new Polygon();


        Polygon szesciakat = new Polygon();
        szesciakat.addPoint(600, 100);
        szesciakat.addPoint(700, 100);
        szesciakat.addPoint(730, 170);
        szesciakat.addPoint(700, 240);
        szesciakat.addPoint(600, 240);
        szesciakat.addPoint(570, 170);
        g2d.fill(szesciakat);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(15));
        g2d.drawLine(700, 100, 730, 170);
        g2d.setColor(Color.blue);
        g2d.setStroke(new BasicStroke(8));
        g2d.drawLine(700, 100, 730, 170);

        /*
    public void drawOsada(int x, int y) {
        Polygon osada = new Polygon();

    }
    */
}
}
