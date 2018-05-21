import java.*;

import java.*;
import java.*;
import java.awt.*;
import java.awt.*;
import java.awt.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.io.IOException;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javax.imageio.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.WindowConstants;
import javax.swing.event.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.*;
import javax.swing.event.*;
import java.io.IOException;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;


public class Okno extends JFrame {

public static JPanel panel_gracza = new JPanel();
public static PanelMapa panel_mapa = new PanelMapa();
public static JTextArea siema = new JTextArea("Witam");


public Okno() {
        super("SOLAR SYSTEM");



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
