package ascensor;

import static ascensor.Controlador.plantaActual;
import static ascensor.Controlador.plantas;
import static ascensor.Controlador.subiendo;

/**
 *
 * @author Daniel Marcos Lorrio
 */
public class Ascensor {

    // Planta actual en la que se encuentra el ascensor
    public static int plantaActual = 0;
    // Boolean para controlar la direccion del ascensor
    public static boolean subiendo = true;

    public void Run() {

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
}
