package ascensor;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Daniel Marcos Lorrio
 * @version 2 20/11/2015
 */
public class Controlador {
    
    // Variable vista
    Vista objVista = new Vista(Vista.texto_ventana.getText() + "Ascensor");

    // Variables de modelo de datos
    // Numero maximo de personas que permite el ascensor
    public static int capacidad = 6;
    // Numero de plantas
    public static int plantas = 4;
    // Planta actual en la que se encuentra el ascensor
    public static int plantaActual = 0;
    // Numero total de personas que se crearan
    public static int totalPersonas = 50;
    // Contador de personas para asignarles numeros
    public static int countPersonas = 0;
    // Boolean para controlar la direccion del ascensor
    public static boolean subiendo = true;
    // Semaforo para cada planta
    public static Semaphore semaforoPlanta[] = new Semaphore[plantas];
    // Semaforo para el ascensor
    public static Semaphore semaforoAscensor;
    // ArrayList de personas esperando al ascensor
    public static ArrayList<Persona> personasEsperando = new ArrayList<Persona>();
    // ArrayList de personas dentro del ascensor
    public static ArrayList<Persona> personasAscensor = new ArrayList<Persona>();
    // Array de personas con el numero total de personas que se crearan
    public static Persona per[] = new Persona[totalPersonas];
    // Boolean que controlara el bucle del programa
    public static boolean funcionando = true;

    /**
     * Declaramos el constructor
     */
    public Controlador() {
        
        // Inicializamos los semaforos de las plantas y el del ascensor
        Modelo.inicializarSemaforos();

        while (funcionando) {
            // Cambiamos el piso del ascensor
            Modelo.cambiarPiso();

            // Abrimos semaforo del piso actual
            semaforoPlanta[plantaActual].release();

            // Generamos un numero aleatorio de personas nuevas
            int personasNuevas = Modelo.nuevasPersonasAleatorio();
            // Las creamos
            //System.out.println("\nHan llegado " + personasNuevas + " personas");
            Vista.texto_ventana.setText(Vista.texto_ventana.getText() + "\nHan llegado " + personasNuevas + " personas");
            for (int i = 0; i < personasNuevas; i++) {
                Modelo.nuevaPersona();
            }

            // Dormimos al ascensor varios segundos
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            // Cerramos el semaforo del piso actual
            try {
                semaforoPlanta[plantaActual].acquire();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //System.out.println("\nEl ascensor lleva actualmente " + personasAscensor.size() + " personas");
            Vista.texto_ventana.setText(Vista.texto_ventana.getText() + "\nEl ascensor lleva actualmente " + personasAscensor.size() + " personas\n");


            // Dormimos al ascensor varios segundos
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            // Comprobamos si el programa ha finalizado
            Modelo.comprobarFinPrograma();
        }
    }
}
