package persistence;

import model.Carta;
import model.StatusCarta;

public class CartaDAO {
    public void flipCarta(Carta carta, StatusCarta statusCarta) {
        carta.setStatus(statusCarta);
    }
}
