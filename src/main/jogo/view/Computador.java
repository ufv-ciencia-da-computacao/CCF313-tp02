package main.jogo.view;

import main.jogo.model.Carta;
import main.jogo.model.Jogador;
import main.jogo.util.Posicao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import main.jogo.view.interfaces.Observer;

public class Computador extends Jogador implements Observer {

    private List<CartaConhecida> cartasConhecidas;
    private Posicao ultimaEscolha;

    public Computador() {
        super("Computador");
        cartasConhecidas = new ArrayList<>();
        ultimaEscolha = null;
    }

    public void notificarCartaAberta(Carta carta, Posicao posicao) {
        for(CartaConhecida c : this.cartasConhecidas) {
            if(c.isAt(posicao)) return;
        }
        cartasConhecidas.add(new CartaConhecida(carta, posicao));
    }

    public void notificarCartaEncontrada(Posicao posicao1, Posicao posicao2) {
        for (int k = 0; k < this.cartasConhecidas.size(); k++) {
            CartaConhecida c = this.cartasConhecidas.get(k);
            if (c.isAt(posicao1) ||  c.isAt(posicao2)) {
                c = null;
                this.cartasConhecidas.remove(k);
            }
        }
    }

    @Override
    public List<Posicao> joga(List<Posicao> disponiveis) {
        Posicao p1 = escolherPrimeiraPosicaoParaJogar(disponiveis);
        disponiveis.remove(p1);
        Posicao p2 = escolherSegundaPosicaoParaJogar(disponiveis);
        return new ArrayList<>(Arrays.asList(p1, p2));
    }

    public Posicao escolherPrimeiraPosicaoParaJogar(List<Posicao> disponiveis) {
        Carta algumaCarta = null;
        for(Posicao p : disponiveis) {
            boolean alguem = false;
            for(CartaConhecida c : this.cartasConhecidas) {
                if(c.isAt(p)) {
                    algumaCarta = c.getCarta();
                    alguem = true;
                    break;
                }
            }

            if(!alguem) continue;
            for(CartaConhecida c : this.cartasConhecidas) {
                if(c.getCarta().equals(algumaCarta) && !c.isAt(p)) {
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
        ultimaEscolha = escolha.get(0);
        return ultimaEscolha;
    }

    public Posicao escolherSegundaPosicaoParaJogar(List<Posicao> disponiveis) {
        Carta escolhida = null;
        for(CartaConhecida c : cartasConhecidas) {
            if(c.isAt(ultimaEscolha)) {
                escolhida = c.getCarta();
                break;
            }
        }

        if (escolhida != null) {
            for (CartaConhecida c : cartasConhecidas) {
                if (!c.isAt(ultimaEscolha)) {
                    if (escolhida.getId() == c.getCarta().getId()) {
                        return c.getPosicao();
                    }
                }
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
            return (this.posicao.getI() == posicao.getI() && this.posicao.getJ() == posicao.getJ());
        }

        Carta getCarta() {
            return this.carta;
        }

        public Posicao getPosicao() {
            return posicao;
        }
    }
}
