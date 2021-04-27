package persistence;

import model.Carta;
import model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class LocalDatabaseSinleton {
    private static final LocalDatabaseSinleton INSTANCE = new LocalDatabaseSinleton();
    public List<Carta> cartas;
    public Tabuleiro tabuleiro;

    public LocalDatabaseSinleton() {
        cartas = new ArrayList<Carta>();
        tabuleiro = new Tabuleiro(2, 4);
    }

    public static LocalDatabaseSinleton getInstance() {
        return INSTANCE;
    }
}
