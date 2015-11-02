package ascensor;

import java.util.Random;

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
}
