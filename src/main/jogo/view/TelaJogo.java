/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.jogo.view;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import main.jogo.model.Carta;
import main.jogo.model.Jogador;
import main.jogo.model.Tabuleiro;
import main.jogo.service.IJogoController;
import main.jogo.util.Posicao;
import main.jogo.util.StatusCarta;

/**
 *
 * @author dener
 */
public class TelaJogo extends javax.swing.JFrame {
    
    private final int N = 2;
    private final int M = 4;
    JButton tabuleiro[][] = new JButton[N][M];
    private Jogador jogador;
    private Computador com;
    private IJogoController jogoController;
    private int jogadorVez;
    
    /**
     * Creates new form TelaJogo
     */
    public TelaJogo(Jogador jogador, IJogoController controller) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.jogador = jogador;
        this.com = new Computador();
        this.jogoController = controller;

        this.jogoController.addObservers(com);

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        jTextField1.setText("");

        tabuleiro[0][0] = btn_0_0;
        tabuleiro[0][1] = btn_0_1;
        tabuleiro[0][2] = btn_0_2;
        tabuleiro[0][3] = btn_0_3;
        tabuleiro[1][0] = btn_1_0;
        tabuleiro[1][1] = btn_1_1;
        tabuleiro[1][2] = btn_1_2;
        tabuleiro[1][3] = btn_1_3;
        
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jogoController.reiniciarTabuleiro();
                inicializarJogo();
            }
        });

        inicializarJogo();
        setListenerBotao();

        jogadorVez = 1;
        nomeJogador1.setText(jogador.getNome());
        nomeJogador2.setText(com.getNome());
        pontosJogador1.setText(String.valueOf(jogador.getPontuacao()));
        pontosJogador2.setText(String.valueOf(com.getPontuacao()));
    }

    void inicializarJogo() {
        notificarTabuleiro(jogoController.getTabuleiro());

        jogadorVez = 1;
        nomeJogador1.setText(jogador.getNome());
        nomeJogador2.setText(com.getNome());

        jogador.setPontuacao(0);
        com.setPontuacao(0);

        pontosJogador1.setText(String.valueOf(jogador.getPontuacao()));
        pontosJogador2.setText(String.valueOf(com.getPontuacao()));
    }
    
    public void notificarTabuleiro(Tabuleiro tabuleiro) {
        for(int i=0; i<N; i++) for(int j=0; j<M; j++) {
            Carta c = tabuleiro.getCartaMatriz(i, j);
            if(c.getStatus() == StatusCarta.ABERTA) {
                this.tabuleiro[i][j].setIcon(new ImageIcon(c.getImagemFrente().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            } else {
                this.tabuleiro[i][j].setIcon(new ImageIcon(c.getImagemTras().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            }
            this.tabuleiro[i][j].setContentAreaFilled(false);
            this.tabuleiro[i][j].setMargin(new Insets(0, 0, 0, 0));
            this.tabuleiro[i][j].setText("");
        }
    }

    private void setListenerBotao() {
        for(int i=0; i<N; i++) for(int j=0; j<M; j++) {
            int finalJ = j;
            int finalI = i;
            tabuleiro[i][j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (jogoController.getJogadorDaVez() == 1) {
                        jogoController.notificarCarta(finalI, finalJ);
                        notificarTabuleiro(jogoController.getTabuleiro());

                        if (jogoController.getCartasAbertas() == 2) {
                            if (jogoController.verifyIf2CartasIsEquals(jogador)) {
                                pontosJogador1.setText(String.valueOf(jogador.getPontuacao()));
                            } else {
                                try {
                                    Thread.sleep(450);
                                } catch(Exception ignored) { }
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        notificarTabuleiro(jogoController.getTabuleiro());
                                        if (jogoController.getJogadorDaVez() == 2)
                                            for (Posicao p : com.joga(jogoController.getPosicoesCartasFechadas())) {
                                                jogoController.notificarCarta(p.getI(), p.getJ());
                                                notificarTabuleiro(jogoController.getTabuleiro());
                                            }
                                        if (jogoController.verifyIf2CartasIsEquals(com)) {
                                            pontosJogador2.setText(String.valueOf(com.getPontuacao()));
                                            jogoController.setJogadorDaVez(1);
                                        } else {
                                            try {
                                                Thread.sleep(2000);
                                            } catch (Exception ignored) {
                                            }

                                            SwingUtilities.invokeLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    notificarTabuleiro(jogoController.getTabuleiro());
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    }

                    if (jogador.getPontuacao() + com.getPontuacao() == 4) {
                        if (jogador.getPontuacao() > com.getPontuacao())
                            jTextField1.setText("Parabéns " + jogador.getNome() + "!");
                        else if (jogador.getPontuacao() == com.getPontuacao())
                            jTextField1.setText("Empate!");
                        else
                            jTextField1.setText("Você Perdeu!");
                    }
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_0_0 = new javax.swing.JButton();
        btn_0_1 = new javax.swing.JButton();
        btn_0_2 = new javax.swing.JButton();
        btn_0_3 = new javax.swing.JButton();
        btn_1_0 = new javax.swing.JButton();
        btn_1_1 = new javax.swing.JButton();
        btn_1_2 = new javax.swing.JButton();
        btn_1_3 = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pontosJogador1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pontosJogador2 = new javax.swing.JLabel();
        nomeJogador1 = new javax.swing.JLabel();
        nomeJogador2 = new javax.swing.JLabel();
        iniciarButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 400));
        setMinimumSize(new java.awt.Dimension(700, 400));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jogo da Memória");

        jPanel3.setLayout(new java.awt.GridLayout(2, 8, 5, 5));

        btn_0_0.setText("jButton1");
        jPanel3.add(btn_0_0);

        btn_0_1.setText("jButton3");
        jPanel3.add(btn_0_1);

        btn_0_2.setText("jButton5");
        jPanel3.add(btn_0_2);

        btn_0_3.setText("jButton7");
        jPanel3.add(btn_0_3);

        btn_1_0.setText("jButton2");
        jPanel3.add(btn_1_0);

        btn_1_1.setText("jButton4");
        jPanel3.add(btn_1_1);

        btn_1_2.setText("jButton6");
        jPanel3.add(btn_1_2);

        btn_1_3.setText("jButton8");
        jPanel3.add(btn_1_3);

        sair.setText("Sair");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setMaximumSize(new java.awt.Dimension(54, 33));
        jPanel1.setMinimumSize(new java.awt.Dimension(54, 33));

        pontosJogador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pontosJogador1.setText("10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pontosJogador1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pontosJogador1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMaximumSize(new java.awt.Dimension(54, 33));
        jPanel2.setMinimumSize(new java.awt.Dimension(54, 33));

        pontosJogador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pontosJogador2.setText("10");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pontosJogador2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pontosJogador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        nomeJogador1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeJogador1.setText("Jogador 1");

        nomeJogador2.setText("Jogador 2");

        iniciarButton.setText("Iniciar Novo Jogo");

        jTextField1.setText("Vencedor é Nome do Jogador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nomeJogador1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomeJogador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iniciarButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sair)
                    .addComponent(jLabel1)
                    .addComponent(iniciarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomeJogador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomeJogador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_0_0;
    private javax.swing.JButton btn_0_1;
    private javax.swing.JButton btn_0_2;
    private javax.swing.JButton btn_0_3;
    private javax.swing.JButton btn_1_0;
    private javax.swing.JButton btn_1_1;
    private javax.swing.JButton btn_1_2;
    private javax.swing.JButton btn_1_3;
    private javax.swing.JButton iniciarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JLabel nomeJogador1;
    private javax.swing.JLabel nomeJogador2;
    private javax.swing.JLabel pontosJogador1;
    private javax.swing.JLabel pontosJogador2;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
