import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class PanelMapa extends JPanel {
    public PanelMapa() {
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        int centerWidth = new Integer(Okno.panel.getWidth()/2);
        int centerHeight = new Integer(Okno.panel.getHeight()/2);
        public Shape s = new Shape() {
            @Override
            public Rectangle getBounds() {
                return null;
            }

            @Override
            public Rectangle2D getBounds2D() {
                return null;
            }

            @Override
            public boolean contains(double x, double y) {
                return false;
            }

            @Override
            public boolean contains(Point2D p) {
                return false;
            }

            @Override
            public boolean intersects(double x, double y, double w, double h) {
                return false;
            }

            @Override
            public boolean intersects(Rectangle2D r) {
                return false;
            }

            @Override
            public boolean contains(double x, double y, double w, double h) {
                return false;
            }

            @Override
            public boolean contains(Rectangle2D r) {
                return false;
            }

            @Override
            public PathIterator getPathIterator(AffineTransform at) {
                return null;
            }

            @Override
            public PathIterator getPathIterator(AffineTransform at, double flatness) {
                return null;
            }
        }
        g2d.fill();
    }
}
