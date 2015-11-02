package ascensor;

import static ascensor.Modelo.plantaAleatoria;

/**
 *
 * @author Daniel Marcos Lorrio
 * @version 1 31/10/2015
 */
public class Principal {

    // Boolean para parar/reanudar el programa
    public static boolean funcionando = true;

    
    public static void main(String args[]) {
        
        while (funcionando) {
            System.out.println(Modelo.sleepAleatorio());
        }
    }

}
