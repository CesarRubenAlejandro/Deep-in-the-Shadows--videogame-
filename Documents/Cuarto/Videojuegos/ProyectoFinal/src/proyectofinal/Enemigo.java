
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
        //Se cargan las imágenes(cuadros) para la animación
//        Image momia = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia.png"));
        Image momia1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia1.png"));
        Image momia2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia2.png"));
        Image momia3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia3.png"));
        Image momia4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia4.png"));
        Image momia5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia5.png"));
        //Se crea la animación
        anima = new Animacion();
//        anima.sumaCuadro(momia, 100);
        anima.sumaCuadro(momia1, 100);
        anima.sumaCuadro(momia2, 200);
        anima.sumaCuadro(momia3, 300);
        anima.sumaCuadro(momia4, 400);
        anima.sumaCuadro(momia5, 500);
 
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
