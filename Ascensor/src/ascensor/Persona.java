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
    String nombre;

    /**
     * Crea un hilo hijo con el numero de hilo dado por el padre
     *
     * @param nHilo Numero de hilo dado por el padre
     */
    Persona(int pi, int pf) {
        this.plantaInicio = pi;
        this.plantaFin = pf;
        //this.nombre = n;
    }

    public void setPlantaInicio(int pi) {
        plantaInicio = pi;
    }

    public void setPlantaFin(int pf) {
        plantaFin = pf;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public int getPlantaInicio() {
        return plantaInicio;
    }

    public int getPlantaFin() {
        return plantaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void run() {


    }
}
