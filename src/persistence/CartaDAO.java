package persistence;

import model.Carta;
import util.StatusCarta;

public class CartaDAO {
    public void flipCarta(Carta carta, StatusCarta statusCarta) {
        carta.setStatus(statusCarta);
    }
}
