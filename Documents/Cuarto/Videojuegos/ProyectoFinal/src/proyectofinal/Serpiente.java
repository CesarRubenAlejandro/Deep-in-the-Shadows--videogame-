/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectofinal;
/**
 * Clase Piedra
 *
 * @author Luis Reyna 
 * @version 1.00 2014/9/4
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Serpiente extends Base {

    private final static String PAUSE = "PAUSADO";
    private final static String DISP = "DESAPARECE";

    /**
     * @return the DISP
     */
    public static String getDISP() {
        return DISP;
    }

    /**
     * @return the PAUSE
     */
    public static String getPAUSE() {
        return PAUSE;
    }

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto elefante.
     * @param posY es el <code>posiscion en y</code> del objeto elefante.
     * @param elefN es la <code>imagen</code> del los objetos elefs.
     * @param anima es la <code>Animacion</code> del objeto elefante.
     * @param num es la cantidad de elefes <code>Int</code> del objeto elefante.
     */
    public Serpiente(int posX, int posY) {
        super(posX, posY);
        //Se cargan las imágenes(cuadros) para la animación
        Image serpiente = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpientee.png"));
         Image serpiente1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpientee2.png"));

        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(serpiente, 100);
        anima.sumaCuadro(serpiente1, 100);
    }
    /**
     * Metodo que hace llamada al metodo de anima para actualizar la imagen segun el tiempo
     * <code>Animacion</code>.
     *
     * @param tiempo es el tiempo <code>Int</code> del objeto Animacion.
     */
    public void actualiza(long tiempo) {
        anima.actualiza(tiempo);
    }
    
    public Animacion getAnima(){
        return anima;
    }
}
