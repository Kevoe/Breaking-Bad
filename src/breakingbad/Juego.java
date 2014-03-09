/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbad;

/**
 *
 * @author Kevin
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Juego extends JPanel implements Dimensiones {

    Image ii;
    Timer timer;
    String message = "Game Over";
    Bola bola;
    Plataforma plataforma;
    Bloque bloques[];

    boolean ingame = true;
    int timerId;


    public Juego() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);

        bloques = new Bloque[30];
        setDoubleBuffered(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
    }

        public void addNotify() {
            super.addNotify();
            gameInit();
        }

    public void gameInit() {

        bola = new Bola();
        plataforma = new Plataforma();


        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bloques[k] = new Bloque(j * Dimensiones.WIDTH/6 + 15 , i * Dimensiones.HEIGTH/20);
                k++;
            }
        }
    }


    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {
            g.drawImage(bola.getImage(), bola.getX(), bola.getY(),
                        bola.getWidth(), bola.getHeight(), this);
            g.drawImage(plataforma.getImage(), plataforma.getX(), plataforma.getY(),
                        plataforma.getWidth(), plataforma.getHeight(), this);

            for (int i = 0; i < 30; i++) {
                if (!bloques[i].estaDestruido())
                    g.drawImage(bloques[i].getImage(), bloques[i].getX(),
                                bloques[i].getY(), bloques[i].getWidth(),
                                bloques[i].getHeight(), this);
            }
        } else {

            Font font = new Font("Verdana", Font.BOLD, 18);
            FontMetrics metr = this.getFontMetrics(font);

            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(message,
                         (Dimensiones.WIDTH - metr.stringWidth(message)) / 2,
                         Dimensiones.WIDTH / 2);
        }


        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            plataforma.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            plataforma.keyPressed(e);
        }
    }


    class ScheduleTask extends TimerTask {

        public void run() {

            bola.move();
            plataforma.move();
            checkCollision();
            repaint();

        }
    }

    public void stopGame() {
        ingame = false;
        timer.cancel();
    }


    public void checkCollision() {

        if (bola.getRect().getMaxY() > Dimensiones.BOTTOM) {
            stopGame();
        }

        for (int i = 0, j = 0; i < 30; i++) {
            if (bloques[i].estaDestruido()) {
                j++;
            }
            if (j == 30) {
                message = "Victory";
                stopGame();
            }
        }

        if ((bola.getRect()).intersects(plataforma.getRect())) {

            int plataformaLPos = (int)plataforma.getRect().getMinX();
            int bolaLPos = (int)bola.getRect().getMinX();

            int first = plataformaLPos + 8;
            int second = plataformaLPos + 16;
            int third = plataformaLPos + 24;
            int fourth = plataformaLPos + 32;

            if (bolaLPos < first) {
                bola.setXDir(-1);
                bola.setYDir(-1);
            }

            if (bolaLPos >= first && bolaLPos < second) {
                bola.setXDir(-1);
                bola.setYDir(-1 * bola.getYDir());
            }

            if (bolaLPos >= second && bolaLPos < third) {
                bola.setXDir(0);
                bola.setYDir(-1);
            }

            if (bolaLPos >= third && bolaLPos < fourth) {
                bola.setXDir(1);
                bola.setYDir(-1 * bola.getYDir());
            }

            if (bolaLPos > fourth) {
                bola.setXDir(1);
                bola.setYDir(-1);
            }


        }


        for (int i = 0; i < 30; i++) {
            if ((bola.getRect()).intersects(bloques[i].getRect())) {

                int bolaLeft = (int)bola.getRect().getMinX();
                int bolaHeight = (int)bola.getRect().getHeight();
                int bolaWidth = (int)bola.getRect().getWidth();
                int bolaTop = (int)bola.getRect().getMinY();

                Point pointRight =
                    new Point(bolaLeft + bolaWidth + 1, bolaTop);
                Point pointLeft = new Point(bolaLeft - 1, bolaTop);
                Point pointTop = new Point(bolaLeft, bolaTop - 1);
                Point pointBottom =
                    new Point(bolaLeft, bolaTop + bolaHeight + 1);

                if (!bloques[i].estaDestruido()) {
                    if (bloques[i].getRect().contains(pointRight)) {
                        bola.setXDir(-1);
                    }

                    else if (bloques[i].getRect().contains(pointLeft)) {
                        bola.setXDir(1);
                    }

                    if (bloques[i].getRect().contains(pointTop)) {
                        bola.setYDir(1);
                    }

                    else if (bloques[i].getRect().contains(pointBottom)) {
                        bola.setYDir(-1);
                    }

                    bloques[i].setDestruido(true);
                }
            }
        }
    }
}