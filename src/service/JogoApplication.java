package service;

import java.awt.image.BufferedImage;
import java.io.File;
import model.Carta;

import model.Jogador;
import util.StatusCarta;
import model.Tabuleiro;

import util.Posicao;
import view.Computador;
import view.interfaces.Observer;

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
    private Posicao p1;
    private Posicao p2;
    public List<Observer> observers;

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
        ));
        
        this.tabuleiro = new Tabuleiro(2, 4);
        initTabuleiro();
        jogadorDaVez = 1;

        observers = new ArrayList<>();
    }

    @Override
    public void addObservers(Observer c) {
        observers.add(c);
    }

    @Override
    public void removeObservers(Observer c) {
        observers.remove(c);
    }

    @Override
    public int getJogadorDaVez() {
        return this.jogadorDaVez;
    }

    @Override
    public void setJogadorDaVez(int i) {
        this.jogadorDaVez = 2-jogadorDaVez+1;
    }

    @Override
    public int getCartasAbertas() {
        return cartasAbertas;
    }

    public void initTabuleiro() {
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
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    private void flipCarta(Posicao p,  StatusCarta statusCarta) {
        Carta carta = tabuleiro.getCartaMatriz(p.getI(), p.getJ());

        if (carta != null) carta.setStatus(statusCarta);

        tabuleiro.setCartaMatriz(carta, p.getI(), p.getJ());
    }

    public boolean verifyIf2CartasIsEquals(Jogador jogador) {
        boolean bool = true;
        if (p1 != null && p2 != null){
            Carta carta1 = tabuleiro.getCartaMatriz(p1.getI(), p1.getJ());
            Carta carta2 = tabuleiro.getCartaMatriz(p2.getI(), p2.getJ());
            if(carta1.equals(carta2)) {
                jogador.setPontuacao(jogador.getPontuacao()+1);
                notificaObserversCartaEncontrada(p1, p2);
            } else {
                bool = false;
                flipCarta(p1, StatusCarta.FECHADA);
                flipCarta(p2, StatusCarta.FECHADA);
                jogadorDaVez = 2-jogadorDaVez+1;
            }
            p1=null;
            p2=null;
            cartasAbertas=0;
        }
        return bool;
    }

    @Override
    public void notificarCarta(int i, int j) {
        Posicao p = new Posicao(i, j);
        Carta c = tabuleiro.getCartaMatriz(p.getI(), p.getJ());
        if (c.getStatus() == StatusCarta.FECHADA) {
            flipCarta(p, StatusCarta.ABERTA);
            notificaObserversJogada(c, p);
            if (p1 == null) {
                p1 = new Posicao(p.getI(), p.getJ());
            } else if (p2 == null) {
                p2 = new Posicao(p.getI(), p.getJ());
            }
            cartasAbertas += 1;
        }
    }

    private void notificaObserversJogada(Carta c, Posicao p) {
        for (Observer o: observers) {
            o.notificarCartaAberta(c, p);
        }
    }

    private void notificaObserversCartaEncontrada(Posicao p1, Posicao p2) {
        for (Observer o: observers) {
            o.notificarCartaEncontrada(p1, p2);
        }
    }

    @Override
    public List<Posicao> getPosicoesCartasFechadas() {
        List<Posicao> posicoes = new ArrayList<>();
        for (int i = 0; i < this.tabuleiro.getLinhaMax(); i++) {
            for (int j = 0; j < this.tabuleiro.getColunaMax(); j++) {
                if (this.tabuleiro.getCartaMatriz(i, j).getStatus() == StatusCarta.FECHADA) {
                    posicoes.add(new Posicao(i, j));
                }
            }
        }
        return posicoes;
    }
}
