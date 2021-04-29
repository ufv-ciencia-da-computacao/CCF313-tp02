package service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import model.Carta;

import model.Jogador;
import util.StatusCarta;
import model.Tabuleiro;

import util.Posicao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import view.Computador;
import view.TelaJogo;

public class JogoApplication {
    private List<Carta> cartas;
    private final Tabuleiro tabuleiro;
    private final Jogador jogador1;
    private final Jogador jogador2;
    private int jogadorDaVez;
    private final TelaJogo jogo;
    private int jogada;
    
    public JogoApplication() throws Exception {
        BufferedImage fundo = ImageIO.read(new File("./src/img/babu-de-pijama.jpeg"));
        BufferedImage dummy = ImageIO.read(new File("./src/img/dummy.jpeg"));
        
        this.cartas = new ArrayList<Carta>(Arrays.asList(
                new Carta(1, dummy, fundo), 
                new Carta(2, dummy, fundo), 
                new Carta(3, dummy, fundo), 
                new Carta(4, dummy, fundo),
                new Carta(5, dummy, fundo),
                new Carta(6, dummy, fundo)
        ));
        
        this.tabuleiro = new Tabuleiro(2, 4);
        this.jogador1 = new Jogador("Jogador 1");
        this.jogador2 = new Computador();
        
        this.jogo = new TelaJogo(this.jogador1);
        this.jogo.setVisible(true);
        
        initTabuleiro();
        
        
        notificarJogada();
    }
    
    private void notificarJogada() {
        if(jogadorDaVez == 1) {
            // notificar
        } else {
            // notificar
        }
        // jogadorDaVez = 2 - jogadorDaVez;
    }
    
    public void aceitarJogada(Jogador jogador, int i, int j) {
        if(jogadorDaVez == 1 && jogador.equals(jogador1)) {
            // do somethig
        }
        
        if(jogadorDaVez == 2 && jogador.equals(jogador2)) {
            // do something else
        }
        
        // else ignore because some stupid player made a invalid move
    }

    public void initTabuleiro() {
        Collections.shuffle(cartas);
        
        List<Carta> duplicado = new ArrayList<>();
        for(int i=0; i<4; i++) {
            duplicado.add(cartas.get(i));
            duplicado.add(cartas.get(i));
        }
        Collections.shuffle(cartas);
        
        int maxLinhas = tabuleiro.getLinhaMax();
        int maxColunas = tabuleiro.getColunaMax();
        int index = 0;
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                tabuleiro.setCartaMatriz(duplicado.get(index++), i, j);
            }
        }
        this.jogo.notificarTabuleiro(tabuleiro);
        
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
