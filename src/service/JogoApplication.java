package service;

import model.Carta;
import model.Jogador;
import model.StatusCarta;
import model.Tabuleiro;

import utilidades.Posicao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JogoApplication {
    List<Carta> cartas;
    Tabuleiro tabuleiro;

    public JogoApplication(Jogador jogador1, Jogador jogador2) {
        this.cartas = new ArrayList<Carta>();
        this.tabuleiro = new Tabuleiro(8,8);
        jogador1 = jogador1;
        jogador2 = jogador2;
    }

    public void initTabuleiro() {
        List<Carta> shuffledListCartas = new ArrayList<Carta>(cartas);
        Collections.shuffle(shuffledListCartas);

        int maxLinhas = tabuleiro.getLinhaMax();
        int maxColunas = tabuleiro.getColunaMax();
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                tabuleiro.setCartaMatriz(shuffledListCartas.get(i+j), i, j);
            }
        }
    }

    void flipCarta(Posicao p,  StatusCarta statusCarta) {
        Carta carta = tabuleiro.getCartaMatriz(p.getI(), p.getJ());

        if (carta != null) carta.setStatus(statusCarta);
    }

    boolean verifyIf2CartasIsEquals(Posicao p1, Posicao p2) {
        Carta carta1 = tabuleiro.getCartaMatriz(p1.getI(), p2.getJ());
        Carta carta2 = tabuleiro.getCartaMatriz(p2.getI(), p2.getJ());

        if (carta1 != null && carta2 != null) return carta1.equals(carta2);
        return false;
    }
}
