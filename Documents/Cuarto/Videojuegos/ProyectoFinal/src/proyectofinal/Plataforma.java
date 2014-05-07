package proyectofinal;

/**
 * Clase Plataforma
 *
 * @author Luis Reyna
 * @version 1.00 2014/9/4
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Plataforma extends Base {

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto plataforma.
     * @param posY es el <code>posiscion en y</code> del objeto plataforma.
     * @param anima es la <code>Animacion</code> del objeto plataforma.
     */
    public Plataforma(int posX, int posY) {
        super(posX, posY);
        //Se cargan las imágenes(cuadros) para la animación
        Image plataforma = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/p800.png"));

        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(plataforma, 100);
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
