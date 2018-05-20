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

    public static JPanel panel_gracza = new JPanel();
    public static PanelMapa panel_mapa = new PanelMapa();
    public static JTextArea siema = new JTextArea("Witam");


    public Okno() {
        super("SOLAR SYSTEM");

        Mapa Map = new Mapa();

        Map.lista_krawedzi.get(0).is_empty = false;
        Map.lista_krawedzi.get(0).kolor_gracza = Color.BLUE;

        Map.lista_krawedzi.get(1).is_empty = false;
        Map.lista_krawedzi.get(1).kolor_gracza = Color.BLACK;

        Map.lista_krawedzi.get(2).is_empty = false;
        Map.lista_krawedzi.get(2).kolor_gracza = Color.RED;


        Map.lista_wierzcholkow.get(0).is_empty = false;
        Map.lista_wierzcholkow.get(0).budynek = 1;
        Map.lista_wierzcholkow.get(0).kolor_gracza = Color.BLUE;

        Map.lista_wierzcholkow.get(1).is_empty = false;
        Map.lista_wierzcholkow.get(1).budynek = 2;
        Map.lista_wierzcholkow.get(1).kolor_gracza = new Color(0, 0, 0);



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1700, 1000);
        setBackground(Color.BLACK);

        add(panel_gracza);
        panel_gracza.setSize(123, 123);
        panel_gracza.setBackground(Color.ORANGE);
        panel_gracza.add(siema);
        add(panel_mapa, BorderLayout.CENTER);
        panel_mapa.setBackground(new Color(162, 222, 255));

        setVisible(true);

    }

}
