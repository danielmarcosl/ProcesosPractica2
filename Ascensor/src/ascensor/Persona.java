package ascensor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Persona con metodos get y set
 *
 * @author Daniel Marcos Lorrio
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
        System.out.println("Persona " + numero + " va del piso " + plantaInicio + " al " + plantaFin);

        Modelo.entrarAscensor(this);
        Modelo.salirAscensor(this);
    }

    public void setPlantaInicio(int pi) {
        plantaInicio = pi;
    }

    public void setPlantaFin(int pf) {
        plantaFin = pf;
    }

    public void setNombre(int nu) {
        numero = nu;
    }

    public int getPlantaInicio() {
        return plantaInicio;
    }

    public int getPlantaFin() {
        return plantaFin;
    }

    public int getNumero() {
        return numero;
    }

//    public void Run() {
//
//        System.out.println("Persona " + numero + " va del piso " + plantaInicio + " al " + plantaFin);
//        
//        Controlador.entrarAscensor(this);
//        Controlador.salirAscensor(this);
//    }
}
