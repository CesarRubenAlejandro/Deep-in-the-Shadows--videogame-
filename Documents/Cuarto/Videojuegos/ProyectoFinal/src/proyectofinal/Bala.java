/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectofinal;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 *
 * @author Aaron
 */
public class Bala extends Base{
    private Image imagenBala; // imagen de la bala
    private Boolean enMovimiento; //Bandera que indica si la bala se mueve
    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto personaje. 
     * @param posY es el <code>posiscion en y</code> del objeto personaje
     */
    public Bala(int posX, int posY){
        super(posX, posY);
        imagenBala = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bala.png"));
        enMovimiento = false;
        
        anima = new Animacion();
        anima.sumaCuadro(imagenBala,100);
    }
    /**
     * Metodo que sirve para modificar la bandera a su valor contrario
     * Este metodo servira para detener o comenzar el movimiento de la bala durante el juego
     */
    public void cambiaDispara(){
        enMovimiento = !enMovimiento;
    }
    /**
     * Metodo para regresar la imagen de la bala
     * @return imagenBala es la imagen de la bala
     */
    public Image getImagenBala(){
        return imagenBala;
    }
    /**
     * Metodo que actualiza la posicion de la bala
     * @param direccion es la direccion que la bala tomara
     */
    public void mueveBala(int direccion){
        if (direccion == 3){
            setPosX(getPosX()-50);
        }
        else{
            setPosX(getPosX()+50);
        }
        
    }
    /**
     * Metodo para regresar el booleano de la bala que determina si esta en movimiento
     * @return enMovimiento es el booleano
     */
    public boolean getEnMovimiento(){
        return enMovimiento;
    }
    
}
