/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author Maribel
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Antorcha extends Base {

    /**
     * Metodo constructor de la clase
     *
     * @param posX es la posicion en x
     * @param posY es la posision en y
     */
    public Antorcha(int posX, int posY) {
        super(posX, posY);
        //Se cargan las imágenes(cuadros) para la animación
        Image diamante2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/antorcha.png"));
        Image diamante3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/antorcha2.png"));
        Image diamante4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/antorcha3.png"));

        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(diamante2, 100);
        anima.sumaCuadro(diamante3, 100);
        anima.sumaCuadro(diamante4, 100);

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
