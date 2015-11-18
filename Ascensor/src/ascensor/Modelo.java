package ascensor;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Marcos Lorrio
 * @version 1 31/10/2015
 */
public class Modelo {

    /**
     * Metodo para generar un numero aleatorio para la planta
     *
     * @return Numero de planta
     */
    public static int plantaAleatoria() {
        Random rand = new Random();

        int planta = rand.nextInt((4 - 0) + 1) + 0;

        return planta;
    }

    /**
     * Metodo para generar un numero aleatorio para el Sleep() de las personas
     *
     * @return Numero de Sleep()
     */
    public static int sleepAleatorio() {
        Random rand = new Random();

        int sl = rand.nextInt((5000 - 1000) + 1) + 1000;

        return sl;
    }

    public static int nuevasPersonasAleatorio() {
        Random rand = new Random();

        int np = rand.nextInt((3 - 1) + 1) + 1;

        return np;
    }

    public static void entrarAscensor(Persona p) {

        // Cierra el semaforo
        try {
            Controlador.semaforoPlanta[p.plantaInicio].acquire();
            System.out.println("Persona " + p.numero + " cierra semaforo " + p.plantaInicio);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        Controlador.semaforoPlanta[p.plantaInicio].release();
        System.out.println("Persona " + p.numero + " abre semaforo " + p.plantaInicio);

        Controlador.esperando = false;
        
        // Cierra el ascensor
        try {
            // Cerramos el semaforo del ascensor
            Controlador.semaforoAscensor.acquire();
            System.out.println("Persona " + p.numero + " entra al ascensor");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Quitamos a la persona de esperar
        Controlador.personasEsperando.remove(p);
        // La anadimos al ascensor
        Controlador.personasAscensor.add(p);
    }

    /**
     * Metodo para controlar el acceso al ascensor
     *
     * @param p Persona que entra al ascensor
     */
    public static void salirAscensor(Persona p) {

        try {
            Controlador.semaforoPlanta[p.plantaFin].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Persona " + p.numero + " cierra semaforo " + p.plantaFin);
        
        Controlador.semaforoPlanta[p.plantaFin].release();
        System.out.println("Persona " + p.numero + " abre semaforo " + p.plantaFin);
        // Quitamos a la persona del ascensor
        Controlador.personasAscensor.remove(p);

        // Abrimos el semaforo
        Controlador.semaforoAscensor.release();
        System.out.println("Persona " + p.numero + " sale del ascensor");
    }
}
