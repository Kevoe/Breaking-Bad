/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbad;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Plataforma extends Base implements Dimensiones {

    String plat = "images/Turtle.gif";    //path de la imagen de la plataforma

    int dx; // cambio en x

    public Plataforma() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(plat));
        image = ii.getImage();

        width = image.getWidth(null); //ancho
        heigth = image.getHeight(null); //largo
            
        posicionOriginal();     //posicion original

    }
    
    //funcion para mover la plataforma
    public void move() {
        x += dx;    //sumale a la posicion x el cambio en x
        if (x <= 2)     //que no pase la pared de la izquierda
          x = 2;
        if (x >= Dimensiones.PLATAFORMA_RIGHT)  //que no pase la pared de la derecha
          x = Dimensiones.PLATAFORMA_RIGHT;
    }
    
    //detecta las teclas que presionas, cambiara el cambio en x dependiendo a la flechita que presiones
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {  //presiona flecha izq para moverlo a la izq
            dx = -2;

        }

        if (key == KeyEvent.VK_RIGHT) { //presiona flecha der para moverlo a la der
            dx = 2;
        }
    }
    
    // detecta cuando sueltas la tecla, dejara de moverse cuando sueltes la tecla
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
    
    //Mueve la plataforma a su posicion original
    public void posicionOriginal() {
        x = Dimensiones.WIDTH/2;
        y = Dimensiones.HEIGTH-100;
    }
}