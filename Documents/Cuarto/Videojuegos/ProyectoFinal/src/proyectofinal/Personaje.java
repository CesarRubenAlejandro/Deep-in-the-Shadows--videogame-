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

    private int balas;
    private boolean dispara;
    private int num;
    private Image imagenDispara;
    private Image imagenSalta;

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
        
        Image corre1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/jhon1.png"));
        Image corre2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/jhon2.png"));
        Image corre3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/jhon3.png"));
        Image corre4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/jhon4.png"));
        
        imagenDispara= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/disparo.png"));
        imagenSalta = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/saltaaa.png"));
        
        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(corre1, 200);
        anima.sumaCuadro(corre2, 200);
        anima.sumaCuadro(corre3, 200);
        anima.sumaCuadro(corre4, 200);
        balas = 5;
        dispara = false;
    }
    /**
     * Metodo que hace llamada al metodo de anima para actualizar la imagen segun el tiempo
     * <code>Animacion</code>.
     *
     * @param tiempo es el tiempo <code>Int</code> del objeto Animacion.
     */
    public void actualiza(long tiempo) {
        if(isDispara()){
          //  dispAnim.actualiza(tiempo);
            num--;
            if(num==0)
                setDispara(false);
        }else{
            anima.actualiza(tiempo);
        }
    }
    /**
     * Metodo de acceso que regresa un nuevo rectangulo
     *
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro
     * del rectangulo
     */
    public Rectangle getPerimetroJhon() {
        
        return new Rectangle((int) getPosX(), (int) getPosY()+80, getAncho(), getAlto()-80);
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
    
    /**
     * Metodo para regresar el numero de balas restantes
     * @return balas son las balas restantes
     */
    public int getBalas(){
        return balas;
        
    }
    /**
     * Metodo para modificar la cantidad de balas restantes
     * @param b es la nueva cantidad de balas
     */
    public void setBalas(int b){
        balas = b;
    }

    /**
     * @return dispara
     * regresa un booleano que indica si el personaje esta disparando
     */
    public boolean isDispara() {
        return dispara;
    }

    /**
     * @param dispara the dispara to set
     */
    public void setDispara(boolean dispara) {
        this.dispara = dispara;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }
    /**
     * Metodo que regresa la imagen del personaje saltando
     * @return imagen del personaje saltando
     */
    public Image getSalta(){
        return imagenSalta;
    }
    /**
     * Metodo que regresa la imagen del personaje disparando
     * @return imagen del personaje disparando
     */
    public Image getDispara(){
        return imagenDispara;
    }
}