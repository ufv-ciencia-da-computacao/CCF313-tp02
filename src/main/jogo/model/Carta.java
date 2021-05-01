package main.jogo.model;

import main.jogo.util.StatusCarta;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Carta implements Cloneable {
    private int id;
    private StatusCarta status;
    private BufferedImage imagemFrente;
    private BufferedImage imagemTras;

    public Carta(int id, BufferedImage imagemFrente, BufferedImage imagemTras) {
        this.id = id;
        this.status = StatusCarta.FECHADA;
        this.imagemFrente = imagemFrente;
        this.imagemTras = imagemTras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusCarta getStatus() {
        return status;
    }

    public void setStatus(StatusCarta status) {
        this.status = status;
    }

    public BufferedImage getImagemFrente() {
        return imagemFrente;
    }

    public void setImagemFrente(BufferedImage imagemFrente) {
        this.imagemFrente = imagemFrente;
    }

    public BufferedImage getImagemTras() {
        return imagemTras;
    }

    public void setImagemTras(BufferedImage imagemTras) {
        this.imagemTras = imagemTras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return id == carta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Object clone() {
        try {
            return (Carta) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
