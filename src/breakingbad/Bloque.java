/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbad;
import javax.swing.ImageIcon;


public class Bloque extends Base {

    String brick = "images/crystal.png";  //path de la imagen del bloque

    boolean destruido;  // status del bloque (Destruida o no destruido)


    public Bloque(int x, int y) {
      this.x = x;
      this.y = y;

      ImageIcon ii = new ImageIcon(this.getClass().getResource(brick));
      image = ii.getImage();

      width = image.getWidth(null);     //ancho
      heigth = image.getHeight(null);   //largo

      destruido = false;                //cuando creas el bloque aun no esta destruido
    }
    
    //funcion que checa si un bloque esta destruido
    public boolean estaDestruido()
    {
      return destruido;
    }
    
    //funcion para cambiar el status del bloque(destruido o no destruido)
    public void setDestruido(boolean destruido)
    {
      this.destruido = destruido;
    }

}