/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbad;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Plataforma extends Base implements Commons {

    String paddle = "images/Turtle.gif";

    int dx;

    public Plataforma() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle));
        image = ii.getImage();

        width = image.getWidth(null);
        heigth = image.getHeight(null);

        resetState();

    }

    public void move() {
        x += dx;
        if (x <= 2) 
          x = 2;
        if (x >= Commons.PADDLE_RIGHT)
          x = Commons.PADDLE_RIGHT;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;

        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public void resetState() {
        x = Commons.WIDTH/2;
        y = Commons.HEIGTH-100;
    }
}