package model;

import util.Posicao;

public class Tabuleiro {
    private Carta[][] matriz;
    private int linhaMax;
    private int colunaMax;

    public Tabuleiro(int linhas, int colunas) {
        this.linhaMax = linhas;
        this.colunaMax = colunas;
        this.matriz = new Carta[linhas][colunas];
    }

    public Carta[][] getMatriz() {
        return matriz;
    }

    public Carta getCartaMatriz(int i, int j) {
        return this.matriz[i][j];
    }

    public void setCartaMatriz(Carta carta, int i, int j) {
        this.matriz[i][j] = carta;
    }

    public int getLinhaMax() {
        return linhaMax;
    }

    public int getColunaMax() {
        return colunaMax;
    }
}
