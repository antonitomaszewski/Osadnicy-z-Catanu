import java.awt.Color;

public class Main extends Thread {
public static void main(String args[]) {
        String [] imiona = {"Antoni", "Andrzej", "Mistrz", "Filip"};
        Color[] kolory = {Color.darkGray, Color.PINK, new Color(240,70,85), new Color(120, 159, 249)};
        (new Gra(imiona, kolory)).start();
}
}
