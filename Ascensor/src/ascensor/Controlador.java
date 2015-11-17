package ascensor;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Marcos Lorrio
 * @version 1 31/10/2015
 */
public class Controlador {

    // Declaramos la clase Vista
    Vista objVista = null;

    /**
     * Declaramos el constructor
     *
     * @param vi Nuevo objeto Vista
     */
    public Controlador() {
        objVista = new Vista("Ascensor");
    }

    // Variables de modelo de datos
    // Numero maximo de personas que permite el ascensor
    public static int capacidad = 6;
    // Numero de plantas
    public static int plantas = 4;
    // Planta actual en la que se encuentra el ascensor
    public static int plantaActual = 0;
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

    /**
     * Metodo para crear una nueva persona
     */
    public static void nuevaPersona() {
        int pInicio = Modelo.plantaAleatoria(); // Planta donde cogera el ascensor
        int pFin = Modelo.plantaAleatoria(); // Planta donde se bajara

        // Si las plantas son las mismas, se cambia la de fin
        while (pInicio == pFin) {
            pFin = Modelo.plantaAleatoria();
        }

        // Se crea la persona y se le anaden las plantas de inicio y fin, y su numero
        Persona p = new Persona(pInicio, pFin, countPersonas);

        // Se anade la persona al array
        personasEsperando.add(p);

        // Lanzamos a la persona
        p.start();

        // Aumentamos el contador
        countPersonas++;
    }

    /**
     * Metodo para cambiar de piso el ascensor
     */
    public static void cambiarPiso() {
        if (subiendo) {
            // Se incrementa la planta
            plantaActual++;
            // Si llega al último piso, cambiamos la direccion para que baje
            if (plantaActual == plantas) {
                subiendo = false;
            }
        } else if (!subiendo) {
            // Se decrementa la planta
            plantaActual--;
            // Si llega al primer piso, cambiamos la dirección para que suba
            if (plantaActual == 0) {
                subiendo = true;
            }
        }

        System.out.println("El ascensor ha llegado a la planta " + plantaActual);
    }

    /**
     * Metodo para controlar el acceso al ascensor
     *
     * @param p Persona que entra al ascensor
     */
    public static void entrarAscensor(Persona p) {

        // Mientras la capacidad del ascensor este ocupada, esperamos
        while (personasAscensor.size() < capacidad) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // Si se ha llegado a la capacidad maxima
        if (personasAscensor.size() < capacidad) {
            try {
                // Cerramos el semaforo del ascensor
                semaforoAscensor.acquire();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        // Quitamos a la persona de esperar
        personasEsperando.remove(p);
        // La anadimos al ascensor
        personasAscensor.add(p);
    }

    public static void salirAscensor(Persona p) {
        
        
        // Quitamos a la persona del ascensor
        personasAscensor.remove(p);

        // Abrimos el semaforo
        semaforoPlanta[plantaActual].release();
    }

    public void Run() {
        // Cambiamos el piso del ascensor
        cambiarPiso();

        // Abrimos semaforo del piso actual
        semaforoPlanta[plantaActual].release();

        // Generamos un numero aleatorio de personas nuevas
        int personasNuevas = Modelo.nuevasPersonasAleatorio();
        // Las creamos
        for (int i = 0; i < personasNuevas; i++) {
            nuevaPersona();
        }

        // Cerramos el semaforo del piso actual
        try {
            semaforoPlanta[plantaActual].acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

//        // Comprobamos si alguna de las personas del ascensor va a salir en la planta actual
//        // Si se encuentra alguna, se espera a que termine su proceso
//        for (int j = 0; j < personasAscensor.size(); j++) {
//            if (personasAscensor.get(j).getPlantaFin() == plantaActual) {
//                // TODO waitFor()
//            }
//        }
    }
}
