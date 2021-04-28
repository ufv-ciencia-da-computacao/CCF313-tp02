package service;

import model.Carta;
import util.StatusCarta;
import persistence.CartaDAO;
import persistence.TabuleiroDAO;

public class JogoApplication {
    CartaDAO cartaDAO;
    TabuleiroDAO tabuleiroDAO;

    public JogoApplication(CartaDAO cartaDAO, TabuleiroDAO tabuleiroDAO) {
        this.cartaDAO = cartaDAO;
        this.tabuleiroDAO = tabuleiroDAO;
    }

    void flipCarta(int id, StatusCarta statusCarta) {
        Carta carta = tabuleiroDAO.getCartaFromTabuleiro(id);

        if (carta != null) cartaDAO.flipCarta(carta, statusCarta);
    }

    boolean verifyIf2CartasIsEquals(int id1, int id2) {
        Carta carta1 = tabuleiroDAO.getCartaFromTabuleiro(id1);
        Carta carta2 = tabuleiroDAO.getCartaFromTabuleiro(id2);

        if (carta1 != null && carta2 != null) return carta1.equals(carta2);
        return false;
    }
}
