package service;

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
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

public class JogoApplication implements IJogoController {
    private List<Carta> cartas;
    private Tabuleiro tabuleiro;
    private int jogadorDaVez;
    private int cartasAbertas;
    private int jogada;
    private Posicao p1;
    private Posicao p2;
    
    public JogoApplication() throws Exception {
        BufferedImage fundo = ImageIO.read(new File("./src/img/fundo.jpg"));
        BufferedImage babu = ImageIO.read(new File("./src/img/babu-de-pijama.jpeg"));
        BufferedImage dummy = ImageIO.read(new File("./src/img/dummy.jpeg"));
        BufferedImage gil = ImageIO.read(new File("./src/img/gil-do-vigor.png"));
        BufferedImage monstro = ImageIO.read(new File("./src/img/monstro-caio-gil-arthur.jpg"));
        BufferedImage prior = ImageIO.read(new File("./src/img/priorbbb.jpg"));

        cartasAbertas = 0;
        
        this.cartas = new ArrayList<Carta>(Arrays.asList(
                new Carta(1, dummy, fundo), 
                new Carta(2, gil, fundo),
                new Carta(3, monstro, fundo),
                new Carta(4, prior, fundo),
                new Carta(5, babu, fundo)
//                new Carta(6, dummy, fundo)
        ));
        
        this.tabuleiro = new Tabuleiro(2, 4);
        initTabuleiro();
//        notificarJogada();
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
//        if(jogadorDaVez == 1 && jogador.equals(jogador1)) {
//            // do somethig
//        }
//
//        if(jogadorDaVez == 2 && jogador.equals(jogador2)) {
//            // do something else
//        }
//
//        // else ignore because some stupid player made a invalid move
    }

    public Tabuleiro initTabuleiro() {
        Collections.shuffle(cartas);

        List<Carta> duplicado = new ArrayList<>();
        for(int i=0; i<4; i++) {
            duplicado.add((Carta) cartas.get(i).clone());
            duplicado.add((Carta) cartas.get(i).clone());
        }
        Collections.shuffle(duplicado);
        
        int maxLinhas = tabuleiro.getLinhaMax();
        int maxColunas = tabuleiro.getColunaMax();
        int index = 0;
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                tabuleiro.setCartaMatriz(duplicado.get(index++), i, j);
            }
        }
        return tabuleiro;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    private void flipCarta(Posicao p,  StatusCarta statusCarta) {
        Carta carta = tabuleiro.getCartaMatriz(p.getI(), p.getJ());

        if (carta != null) carta.setStatus(statusCarta);

        tabuleiro.setCartaMatriz(carta, p.getI(), p.getI());
    }

    private boolean verifyIf2CartasIsEquals(Posicao p1, Posicao p2) {
        Carta carta1 = tabuleiro.getCartaMatriz(p1.getI(), p2.getJ());
        Carta carta2 = tabuleiro.getCartaMatriz(p2.getI(), p2.getJ());

        if (carta1 != null && carta2 != null) return carta1.equals(carta2);
        return false;
    }

    public void notificarCarta(int i, int j) throws InterruptedException {
        Posicao p = new Posicao(i, j);
        Carta c = tabuleiro.getCartaMatriz(p.getI(), p.getJ());

        if (c.getStatus() == StatusCarta.FECHADA)
            flipCarta(p, StatusCarta.ABERTA);

        //notifica jogada

        if (p1 == null) p1 = p;
        else if (p2 == null) p2 = p;

//        if(p1 != null && p2 != null) {
//            if(verifyIf2CartasIsEquals(p1, p2)) {
////                notificaPontoJogador();
//            } else {
//                flipCarta(p1, StatusCarta.FECHADA);
//                flipCarta(p2, StatusCarta.FECHADA);
//            }
//            p1=null;
//            p2=null;
//        }
    }
}
