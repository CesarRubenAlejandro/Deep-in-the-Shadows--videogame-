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
     
    /**
     * Crea un objeto nuevo de tipo Boton
     * @param posX es la posicion en x del boton
     * @param posY es la posicion en y del boton
     */
    public Boton (int posX, int posY) {
        super(posX, posY);  


        anima= new Animacion();

        anima = new Animacion();


        anima = new Animacion();

    }
    
    
    /**
     * Regresa el atributo de animacion del objeto
     * @return atributo animacion del objeto Boton 
     */
    public Animacion getAnima(){
        return anima;
    }
}
