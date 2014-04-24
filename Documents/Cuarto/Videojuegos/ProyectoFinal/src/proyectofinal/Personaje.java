package proyectofinal;
/**
 * Clase Personaje
 *
 * @author Luis Reyna 
 * @version 1.00 2014/9/4
 */
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Personaje extends Base {

    private final static String PAUSE = "PAUSADO";
 
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
     * @param posX es la <code>posiscion en x</code> del objeto personaje. 
     * @param posY es el <code>posiscion en y</code> del objeto personaje
     */
    public Personaje(int posX, int posY) {
        super(posX, posY);
        //Se cargan las imágenes(cuadros) para la animación
        Image corre1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/corre1.png"));
        Image corre2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/corre2.png"));
        Image corre3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/corre4.png"));
        Image corre4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/corre5.png"));
        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(corre1, 200);
        anima.sumaCuadro(corre2, 200);
        anima.sumaCuadro(corre3, 200);
        anima.sumaCuadro(corre4, 200);
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
    /**
     * Metodo de acceso que regresa un nuevo rectangulo
     *
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro
     * del rectangulo
     */
    public Rectangle getPerimetroJhon() {
        
        return new Rectangle((int) getPosX(), (int) getPosY()+120, getAncho(), getAlto()-120);
    }
    

    /**
     * Metodo intersecta
     * 
     * Checa si el objeto <code>Animal</code> intersecta a otro
     * <code>Animal</code>
     *
     * @return un valor boleano <code>true</code> si lo intersecta
     * <code>false</code> en caso contrario
     */
    public boolean intersectaJhon(Base obj) {
        return getPerimetroJhon().intersects(obj.getPerimetro());
    }
}