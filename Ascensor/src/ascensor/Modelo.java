package ascensor;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Daniel Marcos Lorrio
 * @version 2 20/11/2015
 */
public class Modelo {

    /**
     * Metodo para crear una nueva persona
     */
    public static void nuevaPersona() {
        // Planta donde cogera el ascensor
        int pInicio = Modelo.plantaAleatoria();
        // Planta donde se bajara
        int pFin = Modelo.plantaAleatoria();

        // Si las plantas son las mismas, se cambia la de fin hasta que sea distinta
        while (pInicio == pFin) {
            pFin = Modelo.plantaAleatoria();
        }

        // Se crea la persona y se le anaden las plantas de inicio y fin, y su numero
        Persona p = new Persona(pInicio, pFin, Controlador.countPersonas);

        // Se anade la persona al array de personas esperando
        Controlador.personasEsperando.add(p);

        // Lanzamos a la persona
        Controlador.per[Controlador.countPersonas] = p;
        Controlador.per[Controlador.countPersonas].start();

        // Aumentamos el contador
        Controlador.countPersonas++;
    }

    /**
     * Metodo que inicializa los semaforos
     */
    public static void inicializarSemaforos() {
        Controlador.semaforoAscensor = new Semaphore(5);

        for (int i = 0; i < Controlador.plantas; i++) {
            Controlador.semaforoPlanta[i] = new Semaphore(1);
            try {
                Controlador.semaforoPlanta[i].acquire();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metodo para cambiar de piso el ascensor
     */
    public static void cambiarPiso() {
        if (Controlador.subiendo) {
            // Se incrementa la planta
            Controlador.plantaActual++;
            // Si llega al último piso, cambiamos la direccion para que baje
            if (Controlador.plantaActual == (Controlador.plantas - 1)) {
                Controlador.subiendo = false;
            }
        } else if (!Controlador.subiendo) {
            // Se decrementa la planta
            Controlador.plantaActual--;
            // Si llega al primer piso, cambiamos la dirección para que suba
            if (Controlador.plantaActual == 0) {
                Controlador.subiendo = true;
            }
        }

        System.out.println("\nEl ascensor ha llegado a la planta " + Controlador.plantaActual);
    }

    /**
     * Metodo para controlar la region critica de la entrada del ascensor
     *
     * @param p Persona que entra el ascensor
     */
    public static void entrarAscensor(Persona p) {

        // Cierra el semaforo de la planta
        // Debe estar abierto previamente, sino el hilo esperara a que se abra
        try {
            Controlador.semaforoPlanta[p.plantaInicio].acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Abre el ascensor de la planta
        Controlador.semaforoPlanta[p.plantaInicio].release();

        // Cerramos el semaforo del ascensor
        try {
            Controlador.semaforoAscensor.acquire();
            System.out.println("\nPersona " + p.numero + " entra al ascensor");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Quitamos a la persona del array de personas esperando
        Controlador.personasEsperando.remove(p);
        // Anadimos a la persona al array de personas dentro del ascensor
        Controlador.personasAscensor.add(p);
    }

    /**
     * Metodo para controlar la region critica de la salida del ascensor
     *
     * @param p Persona que sale del ascensor
     */
    public static void salirAscensor(Persona p) {

        // Cierra el semaforo de la planta
        // Debe estar abierto previamente, sino el hilo esperara a que se abra
        try {
            Controlador.semaforoPlanta[p.plantaFin].acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Abre el semaforo de la planta
        Controlador.semaforoPlanta[p.plantaFin].release();

        // Quitamos a la persona del array de personas dentro del ascensor
        Controlador.personasAscensor.remove(p);

        // Abrimos el semaforo del ascensor
        Controlador.semaforoAscensor.release();

        System.out.println("\nPersona " + p.numero + " sale del ascensor");
    }

    /**
     * Metodo para generar un numero aleatorio para la planta
     *
     * @return Numero aleatorio generado entre 0 y 4
     */
    public static int plantaAleatoria() {
        Random rand = new Random();

        int planta = rand.nextInt(4);;

        return planta;
    }

    /**
     * Metodo para generar un numero aleatorio para el Sleep() de las personas
     *
     * @return Numero aleatorio generado entre 1000 y 5000
     */
    public static int sleepAleatorio() {
        Random rand = new Random();

        int sl = rand.nextInt(5000) + 1000;

        return sl;
    }

    /**
     * Metodo para generar un numero aleatorio para las personas que llegan en
     * cada oleada
     *
     * @return Numero aleatorio generado entre 1 y 4
     */
    public static int nuevasPersonasAleatorio() {
        Random rand = new Random();

        int np = rand.nextInt(4) + 1;

        return np;
    }

    /**
     * Metodo que comprueba si se ha alcanzado el numero total de personas
     * creadas
     */
    public static void comprobarFinPrograma() {
        if (Controlador.countPersonas == Controlador.totalPersonas) {
            Controlador.funcionando = false;
        }
    }
}
