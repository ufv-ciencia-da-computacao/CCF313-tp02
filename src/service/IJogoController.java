package service;

import model.Jogador;
import model.Tabuleiro;

public interface IJogoController {

    public void notificarCarta(int i, int j) throws InterruptedException;
    public Tabuleiro getTabuleiro();
    public void aceitarJogada(Jogador jogador, int i, int j);
}
