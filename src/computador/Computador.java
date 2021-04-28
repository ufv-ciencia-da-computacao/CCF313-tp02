package computador;

import model.Carta;
import model.Jogador;
import util.Posicao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Computador extends Jogador {

    private List<CartaConhecida> cartasConhecidas;
    private Posicao ultimaEscolha;

    public Computador(String name) {
        super(name);
        cartasConhecidas = new ArrayList<>();
        ultimaEscolha = null;
    }

    void notificarCartaAberta(Carta carta, Posicao posicao) {
        for(CartaConhecida c : this.cartasConhecidas) {
            if(c.isAt(posicao)) return;
        }
        cartasConhecidas.add(new CartaConhecida(carta, posicao));
    }

    void notificarCartaEncontrada(Posicao posicao) {
        for (int k = 0; k < this.cartasConhecidas.size(); k++) {
            CartaConhecida c = this.cartasConhecidas.get(k);
            if (c.isAt(posicao)) {
                c = null;
                this.cartasConhecidas.remove(k);
                break;
            }
        }
    }

    Posicao escolherPrimeiraPosicaoParaJogar(List<Posicao> disponiveis) {
        Carta algumaCarta = null;
        for(Posicao p : disponiveis) {
            boolean alguem = false;
            for(CartaConhecida c : this.cartasConhecidas) {
                if(c.isAt(p)) {
                    alguem = true;
                    break;
                }
            }
            if(!alguem) continue;
            for(CartaConhecida c : this.cartasConhecidas) {
                if(c.getCarta().equals(c) && !c.isAt(p)) {
                    ultimaEscolha = p;
                    return p;
                }
            }
        }

        List<Posicao> escolha = new ArrayList<>();
        for(Posicao p : disponiveis) {
            boolean conheco = false;
            for(CartaConhecida c : cartasConhecidas) {
                if(c.isAt(p)) {
                    conheco = true;
                    break;
                }
            }
            if(!conheco) {
                escolha.add(p);
            }
        }

        Collections.shuffle(escolha);

        return escolha.get(0);
    }

    Posicao escolherSegundaPosicaoParaJogar(List<Posicao> disponiveis) {
        Carta escolhida = null;
        for(CartaConhecida c : cartasConhecidas) {
            if(c.isAt(ultimaEscolha)) {
                escolhida = c.getCarta();
            }
        }

        for(CartaConhecida c : cartasConhecidas) {
            if(escolhida.equals(c.getCarta()) && !c.isAt(ultimaEscolha)) {
                return c.getPosicao();
            }
        }

        List<Posicao> escolha = new ArrayList<>(disponiveis);
        Collections.shuffle(escolha);

        return escolha.get(0);
    }

    private class CartaConhecida {
        Carta carta;
        Posicao posicao;

        CartaConhecida(Carta carta, Posicao posicao) {
            this.carta = carta;
            this.posicao = posicao;
        }

        boolean isAt(Posicao posicao) {
            return this.posicao.equals(posicao);
        }

        Carta getCarta() {
            return this.carta;
        }

        public Posicao getPosicao() {
            return posicao;
        }
    }
}
