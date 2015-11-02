package ascensor;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Daniel Marcos Lorrio
 * @version 1 31/10/2015
 */
public class Controlador {

    // Declaramos un objeto para cada clase
    Modelo objModelo = null;
    Vista objVista = null;

    /**
     * Declaramos el constructor
     *
     * @param vi Nuevo objeto Vista
     * @param mo Nuevo objeto Modelo
     */
    public Controlador(Vista vi, Modelo mo) {
        this.objVista = vi;
        this.objModelo = mo;
    }

    // Variables de modelo de datos
    // Numero maximo de personas que permite el ascensor
    public static int capacidad = 6;
    // Numero de plantas
    public static int plantas = 4;
    // Boolean para controlar la direccion del ascensor
    public static boolean subiendo = true;
    // Semaforo para cada planta
    public static Semaphore semaforoPlanta[];
    // Planta actual en la que se encuentra el ascensor
    public static int plantaActual = 0;

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

        // Se crea la persona y se le anaden las plantas de inicio y fin
        Persona p = new Persona();
        // TODO nombre
        p.setPlantaInicio(pInicio);
        p.setPlantaFin(pFin);

        // Se anade la persona al array
        personasEsperando.add(p);

        // TODO Lanzar persona
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
    }

    public void Run() {
        // Cambiamos el piso del ascensor
        cambiarPiso();

        // Generamos un numero aleatorio de personas nuevas
        int personasNuevas = Modelo.nuevasPersonasAleatorio();
        // Las creamos
        for (int i = 0; i < personasNuevas; i++) {
            nuevaPersona();
        }

        // Comprobamos si alguna de las personas del ascensor va a salir en la planta actual
        // Si se encuentra alguna, se espera a que termine su proceso
        for (int j = 0; j < personasAscensor.size(); j++) {
            if (personasAscensor.get(j).getPlantaFin() == plantaActual) {
                // TODO waitFor()
            }
        }
    }
}
