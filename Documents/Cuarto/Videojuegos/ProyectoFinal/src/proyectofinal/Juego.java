/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author Cesar Rodriguez, Angela Romo, Luis Reyna, Maribel Pastrana
 */
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import java.awt.Color;
import java.util.LinkedList;

public class Juego extends JFrame implements Runnable, KeyListener, MouseListener {

    // Declarar todas las variable
    private Image dbImage;    // Imagen a proyectar	 
    private Graphics dbg;	// Objeto grafico

    private int nivel; // Nivel actual
    private int direccion;
    private boolean menu; // Bandera para indicar la pantalla de menu
    private boolean instrucciones; // Bandera para indicar la pantalla de instrucciones
    private boolean ajustes; // Bandera para indicar la pantalla de ajustes
    private boolean creditos; // Bandera para indicar la pantalla de creditos
    private boolean puntajes; // Bandera para indicar la pantalla de puntajes
    private boolean gameOver; // Bandera para indicar la pantalla de Game Over
    private long tiempoActual;
    private int veloc;

    //Actores
    private Personaje jhon; 
    private Enemigo momia;
    private Enemigo cobra;
    private Diamante diamante;
    private LinkedList<Plataforma> plataformaLst;
    private Plataforma plataforma; // not used
    private Piedra piedra;
    private Picos picos;
    
    //BOTONES
    private Boton botonCreditos; // Boton para ir a la pantalla de Creditos
    private Boton botonIniciar; // Boton para ir al nivel 1 del juego
    private Boton botonInstrucciones; // Boton para ir a la pantalla de Instrucciones
    private Boton botonAjustes; // Boton para ir a la pantalla de Ajustes
    private Boton botonMejPuntajes; // Boton para ir a la pantalla de Mejores Puntajes
    private Boton botonRegresa; // Boton para ir a la pantalla de Menu

    //Imagenes botones
    private Image imagenBotonCreditos; 
    private Image imagenBotonInstrucciones;
    private Image imagenBotonIniciar;
    private Image imagenBotonAjustes;
    private Image imagenBotonMejPuntajes;
    private Image imagenBotonRegresa;

    // fondos
    private Image imFondoMenu;
    private Image imFondoAjustes;
    private Image imFondoCreditos;
    private Image imFondoPuntajes;
    private Image imFondoInstrucciones;
    private Image imFondoGameOver;
    private Image imFondoNivel1;
    private Image imFondoNivel2;
    private Image imFondoNivel3;

    //Constructor
    public Juego() {
        setTitle("Deep in the shadows");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600); //tamaño del jframe
        addKeyListener(this);
        addMouseListener(this);

        imagenBotonCreditos = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonCreditos.png"));
        imagenBotonInstrucciones = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonInstruc.png"));
        imagenBotonIniciar = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonIniciar.png"));
        imagenBotonAjustes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonAjustes.png"));
        imagenBotonMejPuntajes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonMPuntaje.png"));
        imagenBotonRegresa = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonRegresa.png"));

        //Los botones se crean y se posicionan al centro del JFrame uno debajo de otro
        botonCreditos = new Boton(0, 0);
        botonCreditos.getAnima().sumaCuadro(imagenBotonCreditos, 300);
        botonCreditos.setPosX(getWidth() / 2 - botonCreditos.getAncho() / 2);
        botonCreditos.setPosY(getHeight() / 2 + 200);

        botonInstrucciones = new Boton(0, 0);
        botonInstrucciones.getAnima().sumaCuadro(imagenBotonInstrucciones, 300);
        botonInstrucciones.setPosX(getWidth() / 2 - botonInstrucciones.getAncho() / 2);
        botonInstrucciones.setPosY(getHeight() / 2 + 50);

        botonIniciar = new Boton(0, 0);
        botonIniciar.getAnima().sumaCuadro(imagenBotonIniciar, 300);
        botonIniciar.setPosX(getWidth() / 2 - botonIniciar.getAncho() / 2);
        botonIniciar.setPosY(getHeight() / 2);

        botonAjustes = new Boton(0, 0);
        botonAjustes.getAnima().sumaCuadro(imagenBotonAjustes, 300);
        botonAjustes.setPosX(getWidth() / 2 - botonAjustes.getAncho() / 2);
        botonAjustes.setPosY(getHeight() / 2 + 100);

        botonMejPuntajes = new Boton(0, 0);
        botonMejPuntajes.getAnima().sumaCuadro(imagenBotonMejPuntajes, 300);
        botonMejPuntajes.setPosX(getWidth() / 2 - botonMejPuntajes.getAncho() / 2);
        botonMejPuntajes.setPosY(getHeight() / 2 + 150);

        botonRegresa = new Boton(0, 0);
        botonRegresa.getAnima().sumaCuadro(imagenBotonRegresa, 300);
        botonRegresa.setPosX(getWidth() / 2 - botonRegresa.getAncho() / 2);
        botonRegresa.setPosY(getHeight() - 55);

        //Se inicializan las imagenes de Fondo

        imFondoMenu = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/Menu.jpg")); // imagen de fondo del menu
        imFondoAjustes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/ajustes.jpg"));
        imFondoPuntajes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/puntajes.jpg"));
        imFondoCreditos = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/creditos.jpg"));
        imFondoNivel1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/nivel1.jpg"));
        imFondoNivel2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/nivel2.jpg"));
        imFondoNivel3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/nivel3.jpg"));
        imFondoInstrucciones =  Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/instrucciones.jpg"));

        imFondoInstrucciones = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/instrucciones.jpg"));
        imFondoGameOver = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/gameOver.jpg"));

        //se inicializan actores
        jhon = new Personaje(200,200);
        plataformaLst = new LinkedList();
        int ran = 3 + (int)(Math.random() * ((8 - 3) + 1));
        for(int x=0; x<ran; x++){
            int ran2= 200 + (int)(Math.random() * ((600 - 200) + 1));
            plataformaLst.add(new Plataforma(-(800-ran2),200*x));
            plataformaLst.add(new Plataforma(ran2+100,200*x));
        }
        
        diamante= new Diamante(0,plataformaLst.get(ran/2).getPosY()-60);
        //ya no se utiliza
        //plataforma= new Plataforma(0,200+jhon.getAlto()); 
        piedra = new Piedra(this.getWidth()-110,this.getHeight()-110);
        picos= new Picos(0,25);
        
        cobra = new Enemigo (500,getHeight()-60);
        Image cob =  Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpiente.png"));
        Image cob2 =  Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpiente2.png"));
        cobra.getAnima().sumaCuadro(cob, 100);
        cobra.getAnima().sumaCuadro(cob2, 100);
        // CAMBIO
        momia = new Enemigo (0,90);
        Image mom = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia.png"));
        Image mom2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia2.png"));
        momia.getAnima().sumaCuadro(mom, 100);
        momia.getAnima().sumaCuadro(mom2, 100);
        
        nivel = 0;// Nivel 0 indica que todavia no inicia
        menu = true; // comenzamos en el menu
        // Las demas pantallas estan apagadas
        instrucciones = false;
        ajustes = false;
        creditos = false;
        puntajes = false;
        gameOver = false;
        veloc=2;

        //HILO
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }

    /**
     * Reset - limpia y prepara todo para el inicio del nuevo juego.
     */
    public void reset() {

    }

    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>JFrame</code> y luego manda a dormir el hilo.
     *
     */
    public void run() {

        while (true) {
            actualiza();
            checaColision();

            repaint(); // Se actualiza el <code>JFrame</code> repintando el contenido.
            try {
                // El thread se duerme.
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    /**
     * Metodo <I>actualiza</I>
     * Es usado para actualizar la posicion de los personajes y los valores de
     * las variables.
     */
    public void actualiza() {
         
        switch (direccion) {
                case 1: {
//                        if (!ladoIzq && balonMove) {
                            jhon.setPosY(jhon.getPosY() - 4);
                            break;    //se mueve hacia izquierda
//                        }

                    }
                case 2: {
//                    if (!ladoDer && balonMove) {
                        jhon.setPosY(jhon.getPosY() + 4);
                        break;    //se mueve hacia derecha
//                    }
                }
                case 3: {
//                    if (!ladoIzq && balonMove) {
                        jhon.setPosX(jhon.getPosX() - 4);
                        break;    //se mueve hacia izquierda
//                    }

                }
                case 4: {
//                    if (!ladoDer && balonMove) {
                        jhon.setPosX(jhon.getPosX() + 4);
                        break;    //se mueve hacia derecha
//                    }
                }
            }
         long tiempoTranscurrido =System.currentTimeMillis() - getTiempoActual();
        setTiempoActual(getTiempoActual() + tiempoTranscurrido);
        getJhon().actualiza(tiempoTranscurrido);
        diamante.actualiza(tiempoTranscurrido);
        diamante.setPosY(diamante.getPosY()-veloc);
        for(Plataforma p: plataformaLst){
            p.setPosY(p.getPosY()-veloc);
        }
    }

    /**
     * Metodo <I>checaColision</I>
     * Metodo usado para checar las colisiones de los objetos barquito y rayito
     * entre sí y con las orillas del <code>JFrame</code>.
     */
    public void checaColision() {
        //revisa si la barra intenta salir del JFrame  
        if(jhon.getPosX()>this.getWidth()-jhon.getAncho() || jhon.getPosX()<0 || jhon.getPosY()>this.getHeight()-jhon.getAlto() || jhon.getPosY()<0) direccion= 0;
    }

    /**
     * Metodo <I>paint</I>
     * En este metodo lo que hace es actualizar el contenedor (Update)
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Metodo <I>paint1</I>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia. (Paint)
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {

       
        // Si esta prendida la variable menu, pintar el fondo correspondiente y los botones necesarios
        if (menu) {
            g.drawImage(imFondoMenu, 0, 0, this);
            g.drawImage(botonIniciar.getImagenI(), botonIniciar.getPosX(), botonIniciar.getPosY(), this);
            g.drawImage(botonInstrucciones.getImagenI(), botonInstrucciones.getPosX(), botonInstrucciones.getPosY(), this);
            g.drawImage(botonCreditos.getImagenI(), botonCreditos.getPosX(), botonCreditos.getPosY(), this);
            g.drawImage(botonAjustes.getImagenI(), botonAjustes.getPosX(), botonAjustes.getPosY(), this);
            g.drawImage(botonMejPuntajes.getImagenI(), botonMejPuntajes.getPosX(), botonMejPuntajes.getPosY(), this);
        }
        //Si esta prendida la variable ajustes, pintar el fondo correspondiente y el boton de regresar
        if (ajustes) {
            g.drawImage(imFondoAjustes, 0, 0, this);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);
        }
         //Si esta prendida la variable creditos, pintar el fondo correspondiente y el boton de regresar
        if (creditos) {
            g.drawImage(imFondoCreditos, 0, 0, this);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);
        }
         //Si esta prendida la variable puntajes, pintar el fondo correspondiente y el boton de regresar
        if (puntajes) {
            g.drawImage(imFondoPuntajes, 0, 0, this);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);
        }
         //Si esta prendida la variable instrucciones, pintar el fondo correspondiente y el boton de regresar
        if (instrucciones) {
            g.drawImage(imFondoInstrucciones, 0, 0, this);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);
        }
        //Si se termino el juego, pintar el fondo de GameOver y el boton de regresar
        if (gameOver){
            g.drawImage(imFondoGameOver, 0, 0, this);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);

        }
        
        if( !menu && nivel == 1){
            g.drawImage(imFondoNivel1,0,0,this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(getJhon().getImagenI(), getJhon().getPosX(), getJhon().getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(diamante.getImagenI(), diamante.getPosX(), diamante.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            for(Plataforma p: plataformaLst){
                g.drawImage(p.getImagenI(), p.getPosX(), p.getPosY(), this);
            }
            //g.drawImage(plataforma.getImagenI(), plataforma.getPosX(), plataforma.getPosY(), this);
           
        }
        
        if (!menu && nivel == 2){
            g.drawImage (imFondoNivel2,0,0,this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(getJhon().getImagenI(), getJhon().getPosX(), getJhon().getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(diamante.getImagenI(), diamante.getPosX(), diamante.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            //g.drawImage(plataforma.getImagenI(), plataforma.getPosX(), plataforma.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(picos.getImagenI(), picos.getPosX(), picos.getPosY(), this);
            
            g.drawImage(momia.getImagenI(), momia.getPosX(), momia.getPosY(), this);
            
            g.drawImage (cobra.getImagenI(), cobra.getPosX(), cobra.getPosY(), this);
        }
        if(!menu && nivel == 3){
            g.drawImage (imFondoNivel3,0,0,this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(getJhon().getImagenI(), getJhon().getPosX(), getJhon().getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(diamante.getImagenI(), diamante.getPosX(), diamante.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            //g.drawImage(plataforma.getImagenI(), plataforma.getPosX(), plataforma.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(picos.getImagenI(), picos.getPosX(), picos.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(piedra.getImagenI(), piedra.getPosX(), piedra.getPosY(), this);
            
            g.drawImage(momia.getImagenI(), momia.getPosX(), momia.getPosY(), this);
            
            g.drawImage (cobra.getImagenI(), cobra.getPosX(), cobra.getPosY(), this);
        }
    }
   

    /**
     * Metodo <I>keyTyped</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una tecla que
     * no es de accion.
     *
     * @param e es el <code>evento</code> que se genera en al presionar las
     * teclas.
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metodo <I>keyPressed</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar cualquier la
     * tecla.
     *
     * @param e es el <code>evento</code> generado al presionar las teclas.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {    //Presiono flecha izquierda
            direccion = 3;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {    //Presiono flecha derecha
            direccion = 4;
        }else if (e.getKeyCode() == KeyEvent.VK_UP) {    //Presiono flecha derecha
            direccion = 1;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {    //Presiono flecha derecha
            direccion = 2;
        }
    }

    /**
     * Metodo <I>keyReleased</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar la tecla
     * presionada.
     *
     * @param e es el <code>evento</code> que se genera en al soltar las teclas.
     */
    public void keyReleased(KeyEvent e) {

        //Para la entrega Alpha, una vez que se haya inicado el juego, se presiona la tecla 2 para ir al nivel 2, la tecla 3 para ir al nivel 3
        // y la tecla G para terminar el juego
        if (nivel > 0) {
            if (e.getKeyCode() == KeyEvent.VK_G) {
                gameOver = true;
                nivel = 0;
            }

            if (e.getKeyCode() == KeyEvent.VK_2) {
                nivel = 2;
            }

            if (e.getKeyCode() == KeyEvent.VK_3) {
                nivel = 3;
            }
        }

    }

    /**
     * Metodo mouseClicked sobrescrito de la interface MouseListener. En este
     * metodo maneja el evento que se genera al hacer click con el mouse sobre
     * algun componente. e es el evento generado al hacer click con el mouse.
     */
    public void mouseClicked(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();
        //Si estas en la pantalla de menu, checar si se da click en los botones correspondientes
        if (menu) {
            if (botonIniciar.clickEnPersonaje(clickX, clickY)) {
                nivel = 1;
                menu = false;

            } else if (botonAjustes.clickEnPersonaje(clickX, clickY)) {
                ajustes = true;
                menu = false;

            } else if (botonCreditos.clickEnPersonaje(clickX, clickY)) {
                creditos = true;
                menu = false;

            } else if (botonMejPuntajes.clickEnPersonaje(clickX, clickY)) {
                puntajes = true;
                menu = false;

            } else if (botonInstrucciones.clickEnPersonaje(clickX, clickY)) {
                instrucciones = true;
                menu = false;
            }
        }
        //Si no estas en la pantalla de menu, checar si se da click en el boton de Regresar
        else if (ajustes || creditos || puntajes || instrucciones|| gameOver) {
            if (botonRegresa.clickEnPersonaje(clickX, clickY)) {
                menu = true;
                instrucciones = false;
                puntajes = false;
                creditos = false;
                ajustes = false;
                gameOver = false;
            }
        }

       

    }


    /**
     * Metodo mousePressed sobrescrito de la interface MouseListener. En este
     * metodo maneja el evento que se genera al presionar un botón del mouse
     * sobre algun componente. e es el evento generado al presionar un botón del
     * mouse sobre algun componente.
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Metodo mouseReleased sobrescrito de la interface MouseListener. En este
     * metodo maneja el evento que se genera al soltar un botón del mouse sobre
     * algun componente. e es el evento generado al soltar un botón del mouse
     * sobre algun componente.
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Metodo mouseEntered sobrescrito de la interface MouseListener. En este
     * metodo maneja el evento que se genera cuando el mouse entra en algun
     * componente. e es el evento generado cuando el mouse entra en algun
     * componente.
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Metodo mouseExited sobrescrito de la interface MouseListener. En este
     * metodo maneja el evento que se genera cuando el mouse sale de algun
     * componente. e es el evento generado cuando el mouse sale de algun
     * componente.
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * @return the tiempoActual
     */
    public long getTiempoActual() {
        return tiempoActual;
    }

    /**
     * @param tiempoActual the tiempoActual to set
     */
    public void setTiempoActual(long tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    /**
     * @return the jhon
     */
    public Personaje getJhon() {
        return jhon;
    }

    /**
     * @param jhon the jhon to set
     */
    public void setJhon(Personaje jhon) {
        this.jhon = jhon;
    }

    /**
     * @return the enemigo
     */
    ///public Enemigo getEnemigo() {
      //  return enemigo;
   // }

    /**
     * @param enemigo the enemigo to set
     */
  //  public void setEnemigo(Enemigo enemigo) {
   //     this.enemigo = enemigo;
   // }

}
