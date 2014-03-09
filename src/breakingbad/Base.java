/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbad;

import java.awt.Image;
import java.awt.Rectangle;

public class Base {     //Objeto Base. Bloque, bola, y plataforma son hijos de BASE
    
    protected int x;        //posicion x
    protected int y;        //posicion y
    protected int width;    //ancho
    protected int heigth;   //largo
    protected Image image;  //imagen

    //funcion para cambiar posicion x
    public void setX(int x) {
        this.x = x;
    }
    //funcion para obtener posicion x
    public int getX() {
        return x;
    }
    
    //funcion para cambiar posicion y
    public void setY(int y) {
        this.y = y;
    }

    //funcion para obtener posicion y
    public int getY() {
        return y;
    }
    
    //obtener ancho
    public int getWidth() {
        return width;
    }
    
    //obtener largo
    public int getHeight() {
        return heigth;
    }

    //obtener imagen
    Image getImage()
    {
      return image;
    }

    //obtener rectangulo
    Rectangle getRect()
    {
      return new Rectangle(x, y, 
          image.getWidth(null), image.getHeight(null));
    }
}