/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Maribel
 */
public class Municion extends Base {

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto diamante.
     * @param posY es el <code>posiscion en y</code> del objeto diamante.
     */
    public Municion(int posX, int posY) {
        super(posX, posY);
        //Se cargan las imágenes(cuadros) para la animación
        Image municiones = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/municiones.png"));

        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(municiones, 100);

    }

    /**
     * Metodo que hace llamada al metodo de anima para actualizar la imagen
     * segun el tiempo <code>Animacion</code>.
     *
     * @param tiempo es el tiempo <code>Int</code> del objeto Animacion.
     */
    public void actualiza(long tiempo) {
        anima.actualiza(tiempo);
    }

}
