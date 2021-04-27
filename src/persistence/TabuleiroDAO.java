package persistence;

import model.Carta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TabuleiroDAO {
    LocalDatabaseSinleton db;

    public TabuleiroDAO() {
        this.db = LocalDatabaseSinleton.getInstance();
    }

    public void iniciarTabuleiro() {
        List<Carta> shuffledListCartas = new ArrayList<Carta>(this.db.cartas);
        Collections.shuffle(shuffledListCartas);

        int maxLinhas = this.db.tabuleiro.getLinhaMax();
        int maxColunas = this.db.tabuleiro.getColunaMax();
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                this.db.tabuleiro.setCartaMatriz(shuffledListCartas.get(i+j), i, j);
            }
        }
    }

    public Carta getCartaFromTabuleiro(int id) {
        int maxLinhas = this.db.tabuleiro.getLinhaMax();
        int maxColunas = this.db.tabuleiro.getColunaMax();
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                Carta carta = this.db.tabuleiro.getCartaMatriz(i, j);
                if (carta.getId() == id) return carta;
            }
        }
        return null;
    }
}
