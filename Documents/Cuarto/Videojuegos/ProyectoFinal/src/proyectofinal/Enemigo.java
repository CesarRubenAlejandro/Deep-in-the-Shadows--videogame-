
package proyectofinal;
/**
 * Clase Personaje
 *
 * @author Luis Reyna 
 * @version 1.00 2014/9/4
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Enemigo extends Base {

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto enemigo.
     * @param posY es el <code>posiscion en y</code> del objeto enemigo.
     */
    public Enemigo(int posX, int posY) {
        super(posX, posY);
        anima = new Animacion();
 
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
