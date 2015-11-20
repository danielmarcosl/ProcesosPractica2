package ascensor;

/**
 * Clase Persona con metodos get y set y su ejecucion
 *
 * @author Daniel Marcos Lorrio
 * @version 2 20/11/2015
 */
public class Persona extends Thread {

    int plantaInicio;
    int plantaFin;
    int numero;

    /**
     * Crea un hilo hijo con el numero de hilo dado por el padre
     *
     * @param nHilo Numero de hilo dado por el padre
     */
    Persona(int pi, int pf, int nu) {
        this.plantaInicio = pi;
        this.plantaFin = pf;
        this.numero = nu;
    }

    public void run() {
        System.out.println("\nPersona " + numero + " va del piso " + plantaInicio + " al " + plantaFin);

        Modelo.entrarAscensor(this);
        Modelo.salirAscensor(this);
    }
}
