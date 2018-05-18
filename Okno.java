import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.*;
import javax.imageio.*;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class Okno extends JFrame{

    public static JPanel panel = new JPanel();
    public static PanelMapa panel_mapa = new PanelMapa();


    public Okno() {
        super("SOLAR SYSTEM");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1800, 1200);
        setBackground(Color.BLACK);

        add(panel);
        panel.setBackground(Color.ORANGE);
        add(panel_mapa);
        panel_mapa.setBackground(Color.PINK);

        setVisible(true);
        repaint();
    }

    public static void main(String[] args) {
        new Okno();
    }
}
