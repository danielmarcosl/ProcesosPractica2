package ascensor;

import java.awt.TextArea;
import javax.swing.JFrame;

/**
 * TODO
 *
 * @author Daniel Marcos Lorrio
 * @version 2 20/11/2015
 */
public class Vista {

    private JFrame ventana = new JFrame();
    private TextArea texto_ventana = new TextArea();

    /**
     * Creacion de ventana de Windows
     *
     * @param nombre Nombre de la ventana
     */
    Vista(String nombre) {
        ventana.setName(nombre);
        ventana.setTitle(nombre);
        ventana.setSize(400, 400);
        ventana.setVisible(true);
        ventana.add(texto_ventana);
    }
}
