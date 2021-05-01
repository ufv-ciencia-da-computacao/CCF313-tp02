package main.jogo.service;

import main.jogo.model.Jogador;
import main.jogo.model.Tabuleiro;
import main.jogo.util.Posicao;
import main.jogo.view.interfaces.Observer;

import java.util.List;

public interface IJogoController {
    public void addObservers(Observer c);
    public void removeObservers(Observer c);
    public Tabuleiro getTabuleiro();
    public void reiniciarTabuleiro();
    public int getJogadorDaVez();
    public void setJogadorDaVez(int i);
    public int getCartasAbertas();
    public void notificarCarta(int i, int j);
    public boolean verifyIf2CartasIsEquals(Jogador jogador);
    public List<Posicao> getPosicoesCartasFechadas();
}
