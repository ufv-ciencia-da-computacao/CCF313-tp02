package model;

import computador.Computador;
import model.Jogador;
import model.Tabuleiro;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private int jogadorDaVez;

    public Jogo(int linhas, int colunas, String nomeJogador) {
        this.tabuleiro = new Tabuleiro(linhas, colunas);
        this.jogador1 = new Jogador(nomeJogador);
        this.jogadorDaVez = 0;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    public int getJogadorDaVez() {
        return jogadorDaVez;
    }

    public void setJogadorDaVez(int jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }
}
