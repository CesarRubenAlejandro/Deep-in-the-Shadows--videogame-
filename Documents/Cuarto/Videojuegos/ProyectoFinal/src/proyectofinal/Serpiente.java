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

    private int direccion;

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto serpiente.
     * @param posY es el <code>posiscion en y</code> del objeto serpiente.
     * @param anima es la <code>Animacion</code> del objeto serpiente.
     */
    public Serpiente(int posX, int posY) {
        super(posX, posY);
        //Se cargan las imágenes(cuadros) para la animación
        Image serpiente = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpientee.png"));
        Image serpiente1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpientee2.png"));
        direccion = 3;

        //Se crea la animación
        anima = new Animacion();
        anima.sumaCuadro(serpiente, 100);
        anima.sumaCuadro(serpiente1, 100);
    }

    /**
     * Metodo para regresar el parametro de animacion
     *
     * @return anima es la animacion de la serpiente
     */
    public Animacion getAnima() {
        return anima;
    }

    /**
     * Metodo para cambiar la direccion de la serpiente
     *
     * @param x es la nueva direccion
     */
    public void setDireccion(int x) {
        direccion = x;
    }

    /**
     * Metodo para regresar la direccion de la serpiente
     *
     * @return direccion es la direccion de la serpiente
     */
    public int getDireccion() {
        return direccion;
    }
}
