package model;

public class Tabuleiro {
    private Carta[][] matriz;

    public Tabuleiro(int linhas, int colunas) {
        this.matriz = new Carta[linhas][colunas];
    }

    public Carta[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Carta[][] matriz) {
        this.matriz = matriz;
    }
}
