/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectofinal;

/**
 *
 * @author Aaron
 */

import java.awt.Image;
import java.awt.Toolkit;
public class Boton extends Base{
     
    public Boton (int posX, int posY) {
        super(posX, posY);    
    }
    
    public Animacion getAnima(){
        return anima;
    }
}
