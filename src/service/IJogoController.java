package service;

import model.Jogador;
import model.Tabuleiro;
import util.Posicao;
import view.Computador;
import view.interfaces.Observer;

import java.util.List;

public interface IJogoController {
    public void addObservers(Observer c);
    public void removeObservers(Observer c);
    public Tabuleiro getTabuleiro();
    public int getJogadorDaVez();
    public void setJogadorDaVez(int i);
    public int getCartasAbertas();
    public void notificarCarta(int i, int j);
    public boolean verifyIf2CartasIsEquals(Jogador jogador);
    public List<Posicao> getPosicoesCartasFechadas();
}
