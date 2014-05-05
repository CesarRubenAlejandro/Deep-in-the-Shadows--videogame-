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
import java.awt.Font;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class Juego extends JFrame implements Runnable, KeyListener, MouseListener {

    // Declarar todas las variable
    private Image dbImage;    // Imagen a proyectar	 
    private Graphics dbg;	// Objeto grafico
    private Font fontPuntajes; // tipografia para los puntajes
    private Image gifIntro;

    private int nivel; // Nivel actual
    private int direccion;
    private boolean menu; // Bandera para indicar la pantalla de menu
    private boolean instrucciones; // Bandera para indicar la pantalla de instrucciones
    private boolean ajustes; // Bandera para indicar la pantalla de ajustes
    private boolean creditos; // Bandera para indicar la pantalla de creditos
    private boolean puntajes; // Bandera para indicar la pantalla de puntajes
    private boolean saltaIntroduccion; //Bandera para indicar si se salta o no la intro
    private boolean gameOver; // Bandera para indicar la pantalla de Game Over
    private boolean mueveJohn; //sirve para que cuando el usuario no le este presionando a las flechas,John no se anime!
    private long tiempoActual;
    private int veloc;
    private boolean gravedadB;
    private double gravedad;
    private double tP;
    private double t;
    private double brinco;
    private int cont; //Variable auxiliar para el tiempo de la intro (dura 330)
    private int puntos; //Variable para guardar los puntos acumulados del usuario
    private int ran; //Variable  para el numero de barras que hay en el primer nivel
    private int iniciaMusica;//Variable auxiliar para comenzar la musica y no se ejecute cada vez que entra a actualiza
    private int iniciaMusicaIntro; //Variable auxiliar para comenzar la musica y no se ejecute cada vez que entra a actualiza
    private int iniciaMusicaNivel1; //Variable auxiliar para comenzar la musica y no se ejecute cada vez que entra a actualiza
    private boolean sonidosFlag; //Bandera para saber si debe o no haber sonido
    private boolean musicaFlag; //Bandera para saber si debe o no haber musica
    private boolean pausa; //Variable para indicar si el usuario teclea P 
    //Musica
    private SoundClip musicaMenu; //Musica del menu
    private SoundClip musicaIntro; //Musica de la intro
    private SoundClip musicaNivel1; //Musica del nivel 1

    //Sonidos
    private SoundClip sonidoSalta; //Sonido cuando John salta
    private SoundClip sonidoDiamante; //Sonido cuando John toma un diamante

    //Actores
    private Personaje jhon; //Personaje principal
    private Enemigo momia;
    private Enemigo cobra;
    private LinkedList<Plataforma> plataformaLst; //Lista de plataformas para el primer nivel
    private Plataforma piso; // not used
    private Piedra piedra;
    private Picos picos;

    //BOTONES
    private Boton botonCreditos; // Boton para ir a la pantalla de Creditos
    private Boton botonIniciar; // Boton para ir al nivel 1 del juego
    private Boton botonInstrucciones; // Boton para ir a la pantalla de Instrucciones
    private Boton botonAjustes; // Boton para ir a la pantalla de Ajustes
    private Boton botonMejPuntajes; // Boton para ir a la pantalla de Mejores Puntajes
    private Boton botonRegresa; // Boton para ir a la pantalla de Menu
    private Boton botonSaltaIntro; //Imagen del boton que salta intro
    private Boton sonidoOn;
    private Boton sonidoOff;
    private Boton musicaOn;
    private Boton musicaOff;

    //Imagenes botones
    private Image imagenBotonCreditos; //Imagen del boton de creditos
    private Image imagenBotonInstrucciones; //Imagen del boton de instrucciones
    private Image imagenBotonIniciar; //Imagen del boton iniciar
    private Image imagenBotonAjustes;//Imagen del boton ajustes del menu
    private Image imagenBotonMejPuntajes;//Imagen del boton mejores puntajes del menu
    private Image imagenBotonRegresa;//Imagen del boton regresa a menu
    private Image imagenBotonSaltaIntro;//Imagen del boton salta introduccion
    private Image imagenPausado;//Imagen del pausado
    private Image imagenMusicaOn;
    private Image imagenMusicaOff;
    private Image imagenSonidoOn;
    private Image imagenSonidoOff;

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
    private Image imagenIntro; //Imagen del gif de intro

    //Diamantes
    private Diamante diamante;
    private Diamante diamante2;
    private Diamante diamante3;

    //Antorchas
    private Antorcha antorcha1;
    private Antorcha antorcha2;

    //Puertas
    private Puerta puerta1;

    //Nombre del jugador
    private String playerName;
    private boolean auxLeerNombre;

    //Puntajes y nombres
    private String nom1;
    private int punt1;

    private String nom2;
    private int punt2;

    private String nom3;
    private int punt3;

    private String nom4;
    private int punt4;

    private String nom5;
    private int punt5;

    //Constructor
    public Juego() {
        setTitle("Deep in the shadows");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600); //tamaño del jframe
        addKeyListener(this);
        addMouseListener(this);

        fontPuntajes = new Font("Cambria", 0, 34);

        cont = 0;//contador de tiempo del video al inicio
        auxLeerNombre = true;// permite leer una vez el nombre del jugador
        //Valores por default a los nombres
        nom1 = "-";
        nom2 = "-";
        nom3 = "-";
        nom4 = "-";
        nom5 = "-";
        //Valores por default a los mejores puntajes
        punt1 = 0;
        punt2 = 0;
        punt3 = 0;
        punt4 = 0;
        punt5 = 0;

        try { //Se llama la funcion de Puntajes para establecer los puntajes mayores antes de empezar cualquier juego
            // Si es la primera vez que se juega, se llena con los valores default
            Puntajes();
        } catch (IOException e) {
            System.out.println("Error en " + e.toString());
        }

        imagenBotonCreditos = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonCreditos.png"));
        imagenBotonInstrucciones = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonInstruc.png"));
        imagenBotonIniciar = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonIniciar.png"));
        imagenBotonAjustes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonAjustes.png"));
        imagenBotonMejPuntajes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonMPuntaje.png"));
        imagenBotonRegresa = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/botonRegresa.png"));
        imagenBotonSaltaIntro = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Botones/saltarIntro.png"));
        imagenPausado = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Pausado.png"));

        imagenSonidoOff = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/apagado.png"));
        imagenSonidoOn = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/encendido.png"));
        imagenMusicaOff = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/apagado.png"));
        imagenMusicaOn = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/encendido.png"));

        //Los botones se crean y se posicionan al centro del JFrame uno debajo de otro
        botonCreditos = new Boton(0, 0);
        botonCreditos.getAnima().sumaCuadro(imagenBotonCreditos, 300);
        botonCreditos.setPosX(getWidth() / 2 - botonCreditos.getAncho() / 2);
        botonCreditos.setPosY(getHeight() / 2 + 200);

        //BOTONES DE LAS BOCINAS
        musicaOn = new Boton(0, 0);
        musicaOn.getAnima().sumaCuadro(imagenMusicaOn, 300);
        musicaOn.setPosX(50);
        musicaOn.setPosY(380);

        musicaOff = new Boton(0, 0);
        musicaOff.getAnima().sumaCuadro(imagenMusicaOff, 300);
        musicaOff.setPosX(120);
        musicaOff.setPosY(380);

        sonidoOn = new Boton(0, 0);
        sonidoOn.getAnima().sumaCuadro(imagenSonidoOn, 300);
        sonidoOn.setPosX(50);
        sonidoOn.setPosY(280);

        sonidoOff = new Boton(0, 0);
        sonidoOff.getAnima().sumaCuadro(imagenSonidoOff, 300);
        sonidoOff.setPosX(120);
        sonidoOff.setPosY(280);

        botonSaltaIntro = new Boton(0, 0);
        botonSaltaIntro.getAnima().sumaCuadro(imagenBotonSaltaIntro, 300);
        botonSaltaIntro.setPosX(getWidth() - 160);
        botonSaltaIntro.setPosY(getHeight() - 55);

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

        //Antorchas
        antorcha1 = new Antorcha(getWidth() / 2, 0);
        antorcha2 = new Antorcha(3 * getWidth() / 4, 0);
        antorcha1.setPosY(getHeight() + antorcha1.getAlto());
        antorcha2.setPosY(getHeight() + antorcha2.getAlto());

        iniciaMusicaIntro = 0;//Auxiliar para reproducir la musica de introduccion
        iniciaMusicaNivel1 = 0;//Auxiliar para reproducir la musica del nivel 1

        //Puerta
        puerta1 = new Puerta(10, 0);
        puerta1.setPosY(getHeight() + puerta1.getAlto());
        //puerta1.setPosX(puerta1.getAncho());

        //Se inicializan las imagenes de Fondo
        imFondoMenu = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/menu.jpg")); // imagen de fondo del menu
        imFondoAjustes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/ajustes_1.JPG"));
        imFondoPuntajes = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/PantallaMPuntajes.jpg"));
        imFondoCreditos = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/creditos.JPG"));
        imFondoNivel1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/fondo1.jpg"));
        imFondoNivel2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/nivel2.jpg"));
        imFondoNivel3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/nivel3.jpg"));
        imFondoInstrucciones = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/instrucciones.JPG"));
        imagenIntro = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/IntroGIF.gif"));

        //Musica
        musicaMenu = new SoundClip("sonidos/menu.wav");
        musicaIntro = new SoundClip("sonidos/intro.wav");
        musicaNivel1 = new SoundClip("sonidos/nivel1.mid");

        //Sonidos
        sonidoDiamante = new SoundClip("sonidos/clic.wav");
        sonidoSalta = new SoundClip("sonidos/Cae.wav");

        // imFondoInstrucciones = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/instrucciones.jpg"));
        imFondoGameOver = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Fondos/gameOver.JPG"));
        piso = new Plataforma(0, getHeight() + 10);
        //se inicializan actores
        jhon = new Personaje(0, 200 - 100); //menos 100 para que este sobre la primera barra
        jhon.setPosX(jhon.getAncho());

        plataformaLst = new LinkedList();
        ran = 6;
        //Llenar lista de plataformas
        for (int x = 1; x <= ran; x++) {

            if (x == ran) { // si estas en la ultima plataforma, insertar la plataforma extendida que funcionara como piso del nivel
                piso.setPosY(200 * x);
                plataformaLst.add(piso);
            } else { // Si no, insertar pares de plataformas 
                int ran2 = 200 + (int) (Math.random() * ((600 - 200) + 1));
                plataformaLst.add(new Plataforma(-(800 - ran2), 200 * x));
                plataformaLst.add(new Plataforma(ran2 + 120, 200 * x));
            }

        }
        //Se inicializan los diamantes
        diamante = new Diamante(5, plataformaLst.get(ran).getPosY() - 60);
        diamante2 = new Diamante(getWidth() - 100 - diamante.getAncho(), plataformaLst.getFirst().getPosY() - 60);
        diamante3 = new Diamante(getWidth() / 2, plataformaLst.get(ran / 2).getPosY() - 60);
        //Se inicializan los enemigos y obstaculos
        piedra = new Piedra(this.getWidth() - 110, this.getHeight() - 110);
        picos = new Picos(0, 25);
        cobra = new Enemigo(500, getHeight() - 60);
        Image cob = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpiente.png"));
        Image cob2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/serpiente2.png"));
        cobra.getAnima().sumaCuadro(cob, 100);
        cobra.getAnima().sumaCuadro(cob2, 100);
        momia = new Enemigo(0, 90);
        Image mom = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia.png"));
        Image mom2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/momia2.png"));
        momia.getAnima().sumaCuadro(mom, 100);
        momia.getAnima().sumaCuadro(mom2, 100);

        nivel = 1;// Nivel 0 indica que todavia no inicia
        menu = false; // comenzamos en el menu

        // Las demas pantallas estan apagadas
        instrucciones = false;
        ajustes = false;
        creditos = false;
        mueveJohn = false;
        puntajes = false;
        pausa = false;
        gameOver = false;
        sonidosFlag = true;
        musicaFlag = true;
        saltaIntroduccion = false;
        veloc = 2;
        gravedadB = false;
        gravedad = 9.8;
        tP = .1;
        t = .15;
        puntos = 0;
        iniciaMusica = 0;
        playerName = "";
        //HILO
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }

    /**
     * Reset - limpia y prepara todo para el inicio del nuevo juego.
     *
     */
    public void reset() {

        //cont = 0;//contador de tiempo del video al inicio
        auxLeerNombre = true;// permite leer una vez el nombre del jugador

        //Antorchas
        antorcha1.setPosY(getHeight() + antorcha1.getAlto());
        antorcha2.setPosY(getHeight() + antorcha2.getAlto());

        puerta1.setPosY(getHeight() + puerta1.getAlto());
        iniciaMusicaIntro = 0;//Auxiliar para reproducir la musica de introduccion
        iniciaMusicaNivel1 = 0;//Auxiliar para reproducir la musica del nivel 1

        nivel = 0;// Nivel 0 indica que todavia no inicia
        menu = true; // comenzamos en el menu
        brinco = 0;
        direccion = 0;

        // Las demas pantallas estan apagadas
        instrucciones = false;
        ajustes = false;
        pausa = false;
        sonidosFlag = true;
        musicaFlag = true;
        creditos = false;
        puntajes = false;
        gameOver = false;
        saltaIntroduccion = false;
        // Valores default
        veloc = 2;
        gravedadB = false;
        gravedad = 9.8;
        tP = .1;
        t = .15;
        musicaNivel1.stop();
        musicaMenu.play();
        musicaMenu.setLooping(true);
        //Vaciamos la lista de plataformas y volvemos a llenarla
        plataformaLst.clear();

        ran = 6;

        for (int x = 1; x <= ran; x++) {

            if (x == ran) { // si estas en la ultima plataforma, insertar la plataforma extendida que funcionara como piso del nivel
                piso.setPosY(200 * x);
                plataformaLst.add(piso);
            } else { // Si no, insertar pares de plataformas 
                int ran2 = 200 + (int) (Math.random() * ((600 - 200) + 1));
                plataformaLst.add(new Plataforma(-(800 - ran2), 200 * x));
                plataformaLst.add(new Plataforma(ran2 + 120, 200 * x));
            }

        }
        diamante.setPosX(5);
        diamante.setPosY(plataformaLst.get(ran).getPosY() - 60);
        diamante2.setPosX(getWidth() - 100 - diamante.getAncho());
        diamante2.setPosY(plataformaLst.getFirst().getPosY() - 60);
        diamante3.setPosX(getWidth() / 2);
        diamante3.setPosY(plataformaLst.get(ran / 2).getPosY() - 60);
        //Se reposiciona a Jhon para que inicie desde arriba 
        jhon.setPosX(getWidth() / 2);
        jhon.setPosY(200-100); // Valor aproximado para que empiece arriba de todas las plataformas

        //Se reposiciona el diamante sobre la ultima plataforma nueva
        Plataforma aux2 = (Plataforma) plataformaLst.getLast();
        diamante.setPosX(20);
        diamante.setPosY(aux2.getPosY() - aux2.getAlto() - diamante.getAlto() - 10);

    }

    /**
     * Reset - limpia y prepara todo para el inicio del nuevo juego.
     *
     */
    public void resetNivel() {

        //cont = 0;//contador de tiempo del video al inicio
        auxLeerNombre = true;// permite leer una vez el nombre del jugador

        //Antorchas
        antorcha1.setPosY(getHeight() + antorcha1.getAlto());
        antorcha2.setPosY(getHeight() + antorcha2.getAlto());

        puerta1.setPosY(getHeight() + puerta1.getAlto());
        iniciaMusicaIntro = 0;//Auxiliar para reproducir la musica de introduccion
        iniciaMusicaNivel1 = 0;//Auxiliar para reproducir la musica del nivel 1

        nivel = 2;// Nivel 0 indica que todavia no inicia
        menu = false; // comenzamos en el menu
        brinco = 0;
        direccion = 0;

        // Las demas pantallas estan apagadas
        instrucciones = false;
        ajustes = false;
        pausa = false;
        sonidosFlag = true;
        musicaFlag = true;
        creditos = false;
        puntajes = false;
        gameOver = false;
        saltaIntroduccion = false;
        // Valores default
        veloc = 2;
        gravedadB = false;
        gravedad = 9.8;
        tP = .1;
        t = .15;
        //AGregar musica nivel
//        musicaNivel1.stop();
//        musicaMenu.play();
        // musicaMenu.setLooping(true);
        //Vaciamos la lista de plataformas y volvemos a llenarla
        plataformaLst.clear();

       // ran = 6;

        for (int x = 1; x <= ran; x++) {

            if (x == ran) { // si estas en la ultima plataforma, insertar la plataforma extendida que funcionara como piso del nivel
                piso.setPosY(200 * x);
                plataformaLst.add(piso);
            } else if(x==1){//el primer barra ponerla mas abajo
                int ran2 = 200 + (int) (Math.random() * ((600 - 200) + 1));
                plataformaLst.add(new Plataforma(-(800 - ran2), 400));
                plataformaLst.add(new Plataforma(ran2 + 120, 400));
            }else { // Si no, insertar pares de plataformas 
                int ran2 = 200 + (int) (Math.random() * ((600 - 200) + 1));
                plataformaLst.add(new Plataforma(-(800 - ran2), 200 * (x+1)));
                plataformaLst.add(new Plataforma(ran2 + 120, 200 * (x+1)));
            }

        }
        diamante.setPosX(5);
        diamante.setPosY(plataformaLst.get(ran).getPosY() - 60);
        diamante2.setPosX(getWidth() - 100 - diamante.getAncho());
        diamante2.setPosY(plataformaLst.getFirst().getPosY() - 60);
        diamante3.setPosX(getWidth() / 2);
        diamante3.setPosY(plataformaLst.get(ran / 2).getPosY() - 60);
        //Se reposiciona a Jhon para que inicie desde arriba 
        jhon.setPosX(getWidth() / 2);
        Plataforma aux = (Plataforma) plataformaLst.get(0);
        jhon.setPosY(200-100); // Valor aproximado para que empiece arriba de todas las plataformas

        //Se reposiciona el diamante sobre la ultima plataforma nueva
        Plataforma aux2 = (Plataforma) plataformaLst.getLast();
        diamante.setPosX(20);
        diamante.setPosY(aux2.getPosY() - aux2.getAlto() - diamante.getAlto() - 10);

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
            // cambio de colision y actualiza

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
     * Metodo Puntajes Este metodo abre el archivo de texto donde estan los
     * mejores puntajes guardados Lee los mejores puntajes y nombres y compara
     * con el punaje actual Sobreescrive los puntajes y los reacomoda
     *
     * @throws IOException
     */
    public void Puntajes() throws IOException {
        try {
            String nomArchivo = "Datos.txt"; // Nombre del archivo de texto
            BufferedReader fileIn = new BufferedReader(new FileReader(nomArchivo));
            int apunta = -1; // Apuntador auxiliar para cambiar el nombre
            int[] arreglo; // Arreglo auxiliar donde guardar los puntajes del .txt
            arreglo = new int[5]; // De tamanio 5

            String[] nombres; // Arreglo auxiliar donde guardar los nombres
            nombres = new String[5];

            //Leer los nombres y puntajes
            //nom1 = fileIn.readLine();
            nombres[0] = fileIn.readLine();
            arreglo[0] = Integer.parseInt(fileIn.readLine());

            //nom2 = fileIn.readLine();
            nombres[1] = fileIn.readLine();
            arreglo[1] = Integer.parseInt(fileIn.readLine());

            // nom3 = fileIn.readLine();
            nombres[2] = fileIn.readLine();
            arreglo[2] = Integer.parseInt(fileIn.readLine());

            //nom4 = fileIn.readLine();
            nombres[3] = fileIn.readLine();
            arreglo[3] = Integer.parseInt(fileIn.readLine());

            //nom5 = fileIn.readLine();
            nombres[4] = fileIn.readLine();
            arreglo[4] = Integer.parseInt(fileIn.readLine());

            fileIn.close();

            //Auxiliares de reacomodo
            int aux;
            int aux2;
            String ayuda;
            String ayuda2;
            for (int i = 0; i < 5; i++) { // Recorre el arreglo completamente
                if (puntos > arreglo[i]) { // Si el puntaje actual es mayor al de la posicion actual del arreglo
                    //Reemplaza el valor y reacomoda todo el arreglo
                    aux = arreglo[i];
                    ayuda = nombres[i];

                    arreglo[i] = puntos;
                    nombres[i] = playerName;

                    apunta = i;
                    for (int j = i + 1; j < 5; j++) {
                        aux2 = arreglo[j];
                        ayuda2 = nombres[j];

                        arreglo[j] = aux;
                        nombres[j] = ayuda;

                        aux = aux2;
                        ayuda = ayuda2;
                    }
                    i = 6; // Sale del ciclo
                }
            }
            //Reasignar los valores de mejores puntajes con sus respectivos nombres
            punt1 = arreglo[0];
            nom1 = nombres[0];

            punt2 = arreglo[1];
            nom2 = nombres[1];

            punt3 = arreglo[2];
            nom3 = nombres[2];

            punt4 = arreglo[3];
            nom4 = nombres[3];

            punt5 = arreglo[4];
            nom5 = nombres[4];

            switch (apunta) { // Sirve para sustituir el nombre correspondiente y su puntaje
                case 0:
                    nom1 = playerName;
                    break;
                case 1:
                    nom2 = playerName;
                    break;
                case 2:
                    nom3 = playerName;
                    break;
                case 3:
                    nom4 = playerName;
                    break;
                case 4:
                    nom5 = playerName;
                    break;
            }
            //Reescribir en el archivo de texto los mejores puntajes
            PrintWriter fileOut = new PrintWriter(new FileWriter(nomArchivo));
            fileOut.println(nom1);
            fileOut.println(punt1);

            fileOut.println(nom2);
            fileOut.println(punt2);

            fileOut.println(nom3);
            fileOut.println(punt3);

            fileOut.println(nom4);
            fileOut.println(punt4);

            fileOut.println(nom5);
            fileOut.println(punt5);

            fileOut.close();
        } catch (IOException e) { // Si no existe el archivo, crea uno y escribe los valores default
            String nomArchivo = "Datos.txt";
            PrintWriter fileOut = new PrintWriter(new FileWriter(nomArchivo));
            fileOut.println(nom1);
            fileOut.println(punt1);

            fileOut.println(nom2);
            fileOut.println(punt2);

            fileOut.println(nom3);
            fileOut.println(punt3);

            fileOut.println(nom4);
            fileOut.println(punt4);

            fileOut.println(nom5);
            fileOut.println(punt5);

            fileOut.close();
        }
    }

    /*
     Este metodo sirve para mover los objetos antorchas y puertas junto a la ultima barra
     Los objetos estan posicionados encima de la ultima barra, de esta forma simulan moverse junto a ella
     */
    public void mueveObjetos() {

        antorcha1.setPosY(plataformaLst.get(ran + 4).getPosY() - antorcha1.getAlto());
        antorcha2.setPosY(plataformaLst.get(ran + 4).getPosY() - antorcha1.getAlto());
        puerta1.setPosY(plataformaLst.get(ran + 4).getPosY() - puerta1.getAlto());

    }

    /**
     * Metodo <I>actualiza</I>
     * Es usado para actualizar la posicion de los personajes y los valores de
     * las variables.
     */
    public void actualiza() {

        if (gameOver && auxLeerNombre) { // Si se acaba el juego y esta prendida la bandera
            //Leer el nombre del jugador por medio de un OptionPane
            playerName = JOptionPane.showInputDialog(null, "Nombre:", "Puntaje", JOptionPane.QUESTION_MESSAGE);
            if (playerName == null) {
                System.exit(0);
            }
            //Checar y acomodar mejores puntajes
            try {
                Puntajes();
            } catch (IOException e) {
                System.out.println("Error en " + e.toString());
            }
            auxLeerNombre = false; // Apagar bandera para realizar el proceso solo 1 vez cada vez que termine el juego
        }
        if ((menu) && (iniciaMusica == 0)) { // Se utiliza un bool para todas las musicas de fondo para que se comience a reproducir solo una vez 
            musicaMenu.setLooping(true);
            if (musicaFlag) {
                musicaMenu.play();
            }

            iniciaMusica++; // Una vez que se actualice, no entrara otra vez y habra continuidad natural de la musica
        }
        if (!menu && nivel < 1) {

            /* Lo que dura el gif de intro  es hasta 330, y se inicializo en 0*/
            if (cont < 330) {
                if (iniciaMusicaIntro == 0) { // Se utiliza un bool para todas las musicas de fondo para que se comience a reproducir solo una vez 
                    if (musicaFlag) {
                        musicaIntro.play();
                    }

                    iniciaMusicaIntro++; // Una vez que se actualice, no entrara otra vez y habra continuidad natural de la musica
                }

                cont++; // Contador de tiempo de longitud de la introduccion del juego
            }

            /* si ya es 330 prendo menu */
            if (cont == 330) {
                if (musicaFlag) {
                    musicaIntro.stop();
                }

                menu = true;
                cont = 400; // De esta forma, el contador de la intro ya no afectara con el juego
            }
        }
        if ((nivel > 0) && (!pausa)) {

            musicaMenu.stop();
            if ((nivel == 1) && (iniciaMusicaNivel1 == 0)) {
                musicaNivel1.setLooping(true);
                if (musicaFlag) {
                    musicaNivel1.play();
                }

                iniciaMusicaNivel1++;

            }

//            if (mueveJohn) { // Movimiento del personaje
                switch (direccion) {
                    case 3: {
                        jhon.setPosX(jhon.getPosX() - 10);
                        mueveJohn = false;
                        break;    //se mueve hacia izquierda                
                    }
                    case 4: {
                        jhon.setPosX(jhon.getPosX() + 10);
                        mueveJohn = false;
                        break;    //se mueve hacia derecha                  
                    }
                }
//            }

            long tiempoTranscurrido = System.currentTimeMillis() - getTiempoActual();
            setTiempoActual(getTiempoActual() + tiempoTranscurrido);

            jhon.actualiza(tiempoTranscurrido);

            diamante.actualiza(tiempoTranscurrido);
            diamante2.actualiza(tiempoTranscurrido);
            diamante3.actualiza(tiempoTranscurrido);
            antorcha1.actualiza(tiempoTranscurrido);
            antorcha2.actualiza(tiempoTranscurrido);
            if(momia != null){
                momia.actualiza(tiempoTranscurrido);
            }

            //entra cuando no esta tocando las barras y la gravedad actua
            if (gravedadB) {
                int y = (int) ((brinco * 0.8939966636005579 * t) - (.5 * gravedad * 2 * t * t));
                jhon.setPosY(-y + jhon.getPosY());
                t = t + tP;
                //checa si el nuevo y de jhon esta justo al borde de la barra y si no la pone justo al borde
                for (Plataforma p : plataformaLst) {
                    if (jhon.intersectaJhon(p)) {
                        jhon.setPosY(p.getPosY() - jhon.getAlto());
                        brinco = 0;
                        t = .15; // parche para que al final no se vaya hasta el final, sucede algo en el manejo de las colisiones que no checa que esta tocando la barra
                        break;
                    }
                }
            } else {
                if (brinco != 0) {
                    jhon.setPosY((-(int) (brinco * 0.8939966636005579 * t) + jhon.getPosY()) - veloc);

                    t = .25;
                }
                t = .15;
            }

            //mueve plataforma hacia arriba hasta que sea la ultima barra
            if (plataformaLst.get(ran + 4).getPosY() > getHeight() - 40) {
                diamante.setPosY(diamante.getPosY() - veloc);
                diamante2.setPosY(diamante2.getPosY() - veloc);
                diamante3.setPosY(diamante3.getPosY() - veloc);

                for (Plataforma p : plataformaLst) {
                    p.setPosY(p.getPosY() - veloc);

                }
                if (!gravedadB) {
                    jhon.setPosY(jhon.getPosY() - veloc);
                }
            }
            //else{
            mueveObjetos();
            //}

        }
    }

    /**
     * Metodo <I>checaColision</I>
     * Metodo usado para checar las colisiones de los objetos barquito y rayito
     * entre sí y con las orillas del <code>JFrame</code>.
     */
    public void checaColision() {

        boolean b = false;
        for (Plataforma p : plataformaLst) {
            if (jhon.intersectaJhon(p)) {
                b = true;
            }
        }
        if (b) {
            gravedadB = false;

        } else {
            gravedadB = true;

        }
        //revisa si la barra intenta salir del JFrame  
        if (jhon.getPosX() > this.getWidth() - jhon.getAncho() || jhon.getPosX() < 0) {
            direccion = 0;
        }

        //Revisa si John intersecta con un diamante
        if (jhon.intersecta(diamante)) {
            diamante.setPosX(-100);
            puntos += 10;
            if (sonidosFlag) {
                sonidoDiamante.play();
            }

        }

        //Revisa si John intersecta con un diamante
        if (jhon.intersecta(diamante2)) {
            diamante2.setPosX(-100);
            puntos += 10;
            if (sonidosFlag) {
                sonidoDiamante.play();
            }

        }

        //Revisa si John intersecta con un diamante
        if (jhon.intersecta(diamante3)) {
            diamante3.setPosX(-100);
            puntos += 10;
            if (sonidosFlag) {
                sonidoDiamante.play();
            }

        }

        if (jhon.getPosY() + jhon.getAlto() - 15 < 0 || (jhon.getPosY() + jhon.getAlto() / 2 > getHeight())) { // si el personaje se pasa por arriba del jframe, pierde el juego (nivel 1)
            gameOver = true;//pierde el juego                                                     // o si el personaje cae cuando aun no aparecen las plataformas
            nivel = 0;
        }

        if ((nivel > 1) && (jhon.intersecta(picos))) {
            gameOver = true;
            nivel = 0;
        }
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
        g.setColor(Color.WHITE);
        g.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
        //Si no se ha dado click en el boton de saltarIntroduccion y no ha pasado el tiempo completo del gif se pintan
        if ((cont < 330) && (!saltaIntroduccion)) {
            g.drawImage(imagenIntro, 0, 0, this);
            if (cont >= 78) {
                g.drawImage(botonSaltaIntro.getImagenI(), botonSaltaIntro.getPosX(), botonSaltaIntro.getPosY(), this);
            }

        }

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
            if (musicaFlag) {
                g.drawImage(musicaOff.getImagenI(), musicaOff.getPosX(), musicaOff.getPosY(), this);
            } else if (!musicaFlag) {
                g.drawImage(musicaOn.getImagenI(), musicaOn.getPosX(), musicaOn.getPosY(), this);

            }
            if (sonidosFlag) {
                g.drawImage(sonidoOff.getImagenI(), sonidoOff.getPosX(), sonidoOff.getPosY(), this);
            } else if (!sonidosFlag) {
                g.drawImage(sonidoOn.getImagenI(), sonidoOn.getPosX(), sonidoOn.getPosY(), this);

            }
        }
        //Si esta prendida la variable creditos, pintar el fondo correspondiente y el boton de regresar
        if (creditos) {
            g.drawImage(imFondoCreditos, 0, 0, this);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);
        }
        //Si esta prendida la variable puntajes, pintar el fondo correspondiente y el boton de regresar
        if (puntajes) {
            g.drawImage(imFondoPuntajes, 0, 0, this);
            //Lugares de mejores puntajes
            g.setColor(Color.yellow);
            g.setFont(fontPuntajes);
            g.drawString("1.", 40, 170);
            g.drawString("2.", 40, 220);
            g.drawString("3.", 40, 270);
            g.drawString("4.", 40, 320);
            g.drawString("5.", 40, 370);

            // Mejores jugadores y sus puntos
            g.setColor(Color.WHITE);

            g.drawString(nom1, 70, 170);
            g.drawString("" + punt1, 270, 170);

            g.drawString(nom2, 70, 220);
            g.drawString("" + punt2, 270, 220);

            g.drawString(nom3, 70, 270);
            g.drawString("" + punt3, 270, 270);

            g.drawString(nom4, 70, 320);
            g.drawString("" + punt4, 270, 320);

            g.drawString(nom5, 70, 370);
            g.drawString("" + punt5, 270, 370);

            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);
        }
        //Si esta prendida la variable instrucciones, pintar el fondo correspondiente y el boton de regresar
        if (instrucciones) {
            g.drawImage(imFondoInstrucciones, 0, 0, this);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);
        }
        //Si se termino el juego, pintar el fondo de GameOver y el boton de regresar
        if (gameOver) {
            g.drawImage(imFondoGameOver, 0, 0, this);
            g.drawString("Puntajes: " + puntos, 330, 495);
            g.drawImage(botonRegresa.getImagenI(), botonRegresa.getPosX(), botonRegresa.getPosY(), this);

        }
        if (!menu && nivel == 1) {

            g.drawImage(imFondoNivel1, 0, 0, this);
            g.drawImage(puerta1.getImagenI(), puerta1.getPosX(), puerta1.getPosY(), this);
            g.drawImage(antorcha1.getImagenI(), antorcha1.getPosX(), antorcha1.getPosY(), this);
            g.drawImage(antorcha2.getImagenI(), antorcha2.getPosX(), antorcha2.getPosY(), this);

            //Dibuja la imagen en la posicion actualizada
            if (direccion == 3) {
                g.drawImage(getJhon().getImagenI(), getJhon().getPosX() + 50, getJhon().getPosY(), -jhon.getAncho(), jhon.getAlto(), this);
            } else {
                g.drawImage(getJhon().getImagenI(), getJhon().getPosX(), getJhon().getPosY(), jhon.getAncho(), jhon.getAlto(), this);
            }
            if ((diamante != null) && (diamante2 != null) && (diamante3 != null)) {
                g.drawImage(diamante.getImagenI(), diamante.getPosX(), diamante.getPosY(), this);
                g.drawImage(diamante2.getImagenI(), diamante2.getPosX(), diamante2.getPosY(), this);
                g.drawImage(diamante3.getImagenI(), diamante3.getPosX(), diamante3.getPosY(), this);
            }

            //Dibuja la imagen en la posicion actualizada
            for (Plataforma p : plataformaLst) {
                g.drawImage(p.getImagenI(), p.getPosX(), p.getPosY(), this);
            }

            //Si le tecleo P entonces esta en Pausa
            if (pausa) {
                g.drawImage(imagenPausado, getWidth() / 2 - 130, getHeight() / 2 - 50, this);
            }

            g.drawString("Puntos: " + puntos, getWidth() - 150, 60);
            g.drawString("Balas: " + jhon.getBalas(), getWidth() - 250, 60);
        }

        if (!menu && nivel == 2) {
            g.drawImage(imFondoNivel2, 0, 0, this);
//            //Dibuja la imagen en la posicion actualizada
//            g.drawImage(getJhon().getImagenI(), getJhon().getPosX(), getJhon().getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(diamante.getImagenI(), diamante.getPosX(), diamante.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            //g.drawImage(plataforma.getImagenI(), plataforma.getPosX(), plataforma.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(picos.getImagenI(), picos.getPosX(), picos.getPosY(), this);

            g.drawImage(momia.getImagenI(), momia.getPosX(), momia.getPosY(), this);
            //Dibuja la imagen en la posicion actualizada
            if (direccion == 3) {
                g.drawImage(getJhon().getImagenI(), getJhon().getPosX() + 50, getJhon().getPosY(), -jhon.getAncho(), jhon.getAlto(), this);
            } else {
                g.drawImage(getJhon().getImagenI(), getJhon().getPosX(), getJhon().getPosY(), jhon.getAncho(), jhon.getAlto(), this);
            }
            //Dibuja la imagen en la posicion actualizada
            for (Plataforma p : plataformaLst) {
                g.drawImage(p.getImagenI(), p.getPosX(), p.getPosY(), this);
            }
            g.drawImage(cobra.getImagenI(), cobra.getPosX(), cobra.getPosY(), this);
        }
        if (!menu && nivel == 3) {
            g.drawImage(imFondoNivel3, 0, 0, this);
            g.drawImage(diamante.getImagenI(), diamante.getPosX(), diamante.getPosY(), this);
            g.drawImage(picos.getImagenI(), picos.getPosX(), picos.getPosY(), this);
            g.drawImage(piedra.getImagenI(), piedra.getPosX(), piedra.getPosY(), this);
            g.drawImage(momia.getImagenI(), momia.getPosX(), momia.getPosY(), this);
            g.drawImage(cobra.getImagenI(), cobra.getPosX(), cobra.getPosY(), this);
        }
        //Si le tecleo P entonces esta en Pausa
        if (pausa) {
            g.drawImage(imagenPausado, getWidth() / 2 - 130, getHeight() / 2 - 50, this);
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
            mueveJohn = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {    //Presiono flecha derecha
            direccion = 4;
            mueveJohn = true;
        } else if ((e.getKeyCode() == KeyEvent.VK_UP) && (!gravedadB)) {    //Presiono flecha derecha
            brinco = 20;
            if (sonidosFlag) {
                sonidoSalta.play();
            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {    //Presiono flecha derecha
            direccion = 2;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_B) {
            jhon.setNum(5);
            jhon.setDispara(true);
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

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (jhon.intersecta(puerta1)) {
                    nivel++;
                    resetNivel();// SE inicializan todos los valores para el siguiente nivel
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                pausa = !pausa;
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

        if (musicaOn.clickEnPersonaje(clickX, clickY)) {
            musicaFlag = true;
            musicaMenu.play();

        }
        if (musicaOff.clickEnPersonaje(clickX, clickY)) {
            musicaFlag = false;
            musicaMenu.stop();
        }
        if (sonidoOn.clickEnPersonaje(clickX, clickY)) {
            sonidosFlag = true;
        }
        if (sonidoOff.clickEnPersonaje(clickX, clickY)) {
            sonidosFlag = false;
        }
        if (botonSaltaIntro.clickEnPersonaje(clickX, clickY)) {
            saltaIntroduccion = true;
            musicaIntro.stop();
            menu = true;
        }
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
        } //Si no estas en la pantalla de menu, checar si se da click en el boton de Regresar
        else if (ajustes || creditos || puntajes || instrucciones /*|| gameOver*/) {
            if (botonRegresa.clickEnPersonaje(clickX, clickY)) {
                menu = true;
                instrucciones = false;
                puntajes = false;
                creditos = false;
                ajustes = false;
                gameOver = false;
            }
        } else if (gameOver) {
            if (botonRegresa.clickEnPersonaje(clickX, clickY)) {
                reset(); // Reiniciar los valores del juego
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
