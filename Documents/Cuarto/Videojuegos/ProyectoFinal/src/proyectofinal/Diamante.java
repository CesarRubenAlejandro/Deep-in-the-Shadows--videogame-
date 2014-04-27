
package proyectofinal;
/**
 * Clase Diamante
 *
 * @author Luis Reyna 
 * @version 1.00 2014/9/4
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Diamante extends Base {

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto diamante.
     * @param posY es el <code>posiscion en y</code> del objeto diamante.
     */
    public Diamante(int posX, int posY) {
        super(posX, posY);
        //Se cargan las imágenes(cuadros) para la animación
        Image diamante1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/diamanteP.png"));
        Image diamante2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/diamante2P.png"));
        Image diamante3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/diamante3P.png"));

        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(diamante1, 100);
        anima.sumaCuadro(diamante2, 100);
        anima.sumaCuadro(diamante3, 100);
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
}
