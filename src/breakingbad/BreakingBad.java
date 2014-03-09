/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbad;
import javax.swing.JFrame;


/**
 *
 * @author Kevin
 */
public class BreakingBad extends JFrame{

    public BreakingBad()
    {
        add(new Juego());
        setTitle("Breaking Bad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Dimensiones.WIDTH, Dimensiones.HEIGTH);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BreakingBad();
    }
}
