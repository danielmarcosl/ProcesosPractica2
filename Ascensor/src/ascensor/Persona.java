package ascensor;

/**
 * Objeto Persona con metodos get y set
 *
 * @author Daniel Marcos Lorrio
 */
public class Persona {

    int plantaInicio;
    int plantaFin;
    String nombre;

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
}
