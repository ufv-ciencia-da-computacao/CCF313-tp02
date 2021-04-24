package model;

import java.awt.image.BufferedImage;

public class Carta {
    private int id;
    private StatusCarta status;
    private BufferedImage imagemFrente;
    private BufferedImage imagemTras;

    public Carta(int id, StatusCarta status, BufferedImage imagemFrente, BufferedImage imagemTras) {
        this.id = id;
        this.status = status;
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
}
