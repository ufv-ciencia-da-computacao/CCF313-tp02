package model;

public class Tabuleiro {
    private Carta[][] matriz;

    public Tabuleiro(Carta[][] matriz) {
        this.matriz = matriz;
    }

    public Carta[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Carta[][] matriz) {
        this.matriz = matriz;
    }
}
