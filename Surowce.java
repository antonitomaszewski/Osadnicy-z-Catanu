public class Surowce {
    public int owca = 0;
    public int siano = 0;
    public int drewno = 0;
    public int cegla = 0;
    public int kamien = 0;

    public Surowce(){
    };

    public void dodaj(int ile, String surowiec) {
        if (surowiec == "owca") {
            this.owca += ile;
        } else if (surowiec == "siano") {
            this.siano += ile;
        } else if (surowiec == "drewno") {
            this.drewno += ile;
        } else if (surowiec == "cegla") {
            this.cegla += ile;
        } else if (surowiec == "kamien") {
            this.kamien += ile;
        } else {
            System.out.println("Coś poszło nie tak przy dodawaniu surowca " + ile + " " + surowiec);
        }
    }
    public void zabierz(int ile, String surowiec){
        if (surowiec == "owca") {
            this.owca -= ile;
        } else if (surowiec == "siano") {
            this.siano -= ile;
        } else if (surowiec == "drewno") {
            this.drewno -= ile;
        } else if (surowiec == "cegla") {
            this.cegla -= ile;
        } else if (surowiec == "kamien") {
            this.kamien -= ile;
        } else {
            System.out.println("Coś poszło nie tak przy zabieraniu surowca " + ile + " " + surowiec);
        }
    }

}