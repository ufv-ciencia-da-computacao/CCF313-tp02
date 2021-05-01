/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.interfaces;

import model.Carta;
import util.Posicao;

import java.util.List;

/**
 *
 * @author dener
 */
public interface Observer {
    public void notificarCartaAberta(Carta carta, Posicao posicao);
    public void notificarCartaEncontrada(Posicao posicao1, Posicao posicao2);
    public List<Posicao> joga(List<Posicao> disponiveis);
}
