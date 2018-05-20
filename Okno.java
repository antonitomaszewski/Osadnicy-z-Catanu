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

public static JPanel panel = new JPanel();
public static PanelMapa panel_mapa = new PanelMapa();


public Okno() {
        super("SOLAR SYSTEM");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(2200, 1600);
        setBackground(Color.BLACK);

        add(panel);
        panel.setBackground(Color.ORANGE);
        add(panel_mapa);
        panel_mapa.setBackground(new Color(162, 222, 255));

        setVisible(true);
        repaint();
}

}
