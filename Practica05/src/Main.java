
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;
import mx.unam.fciencias.EstDat.ListaDoblementeLigada;
import mx.unam.fciencias.EstDat.ListaLigada;
import mx.unam.fciencias.EstDat.ListaArreglo;

/**
 * Clase para probar las implementaciones de ListaLigada, ListaDoblementeLigada
 * y ListaArreglo. Recibe dos argumentos argumentos, el primero sera una letra:
 * A, L o D. Esta letra indicara la representacion de Listas a usar. En el
 * caso de la A se usara una lista implementada con arreglos, en el caso de la
 * L una lista ligada y en el caso de la D una lista doblemente ligada. Ademas
 * como segundo argumento recibira el nombre de un archivo de texto.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Main {

    ListaLigada<Integer> lista1 = new ListaLigada<>();
    ListaDoblementeLigada<Integer> lista2 = new ListaDoblementeLigada<>();
    ListaArreglo<Integer> lista3 = new ListaArreglo<>();
    String tipo;

    /**
     * Constructor de los tipos de lista, el cual tomara como elementos cada 
     * linea de un archivo.
     * @param tipo Tipo de lista que se implementara, A si es una lista
     * implementada con arreglos, D si es una lista doblemente ligada y L si es
     * una lista ligada.
     * @param archivo Archivo en el cual se leera linea por linea los elementos 
     * que se insertaran en cada celda de la lista.
     */
    public Main(String tipo, String archivo) {
        this.tipo = tipo;
        FileReader fr = null;
        try {
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                if (tipo.equals("L")) {
                    lista1.inserta(i, Integer.parseInt(linea));
                    i++;
                }
                if (tipo.equals("D")) {
                    lista2.inserta(i, Integer.parseInt(linea));
                    i++;
                }
                if (tipo.equals("A")) {
                    lista3.inserta(i, Integer.parseInt(linea));
                    i++;
                }
            }
        } catch (IOException | ExcepcionAccesoIncorrecto e) {
            System.out.println(e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
    }
    
    /**
     * Metodo para imprimir en la entrada estandar la lista especificada.
     */
    public void imprimeLista() {
        try {
            if (tipo.equals("L")) {
                for (int i = 0; i < lista1.longitud(); i++) {
                    System.out.println(lista1.elemento(i));
                }
            }
            if (tipo.equals("D")) {
                for (int i = 0; i < lista2.longitud(); i++) {
                    System.out.println(lista2.elemento(i));
                }
            }
            if (tipo.equals("A")) {
                for (int i = 0; i < lista3.longitud(); i++) {
                    System.out.println(lista3.elemento(i));
                }
            }
        } catch (ExcepcionAccesoIncorrecto ex) {
            System.out.println("Indice invalido.");
        }
    }

    /**
     * Metodo que invierte el orden de la lista.
     */
    public void invierteLista() {
        try {
            ListaLigada<Integer> temporal = new ListaLigada<>();
            if (tipo.equals("L")) {
                int j = lista1.longitud() - 1;
                for (int i = 0; i < lista1.longitud(); i++) {
                    temporal.inserta(i, lista1.elemento(j));
                    j--;
                }
                lista1.limpia();
                for (int i = 0; i < temporal.longitud(); i++) {
                    lista1.inserta(i, temporal.elemento(i));
                }
            }
            if (tipo.equals("D")) {
                int j = lista2.longitud() - 1;
                for (int i = 0; i < lista2.longitud(); i++) {
                    temporal.inserta(i, lista2.elemento(j));
                    j--;
                }
                lista2.limpia();
                for (int i = 0; i < temporal.longitud(); i++) {
                    lista2.inserta(i, temporal.elemento(i));
                }
            }
            if (tipo.equals("A")) {
                int j = lista3.longitud() - 1;
                for (int i = 0; i < lista3.longitud(); i++) {
                    temporal.inserta(i, lista3.elemento(j));
                    j--;
                }
                lista3.limpia();
                for (int i = 0; i < temporal.longitud(); i++) {
                    lista3.inserta(i, temporal.elemento(i));
                }
            }
        } catch (ExcepcionAccesoIncorrecto ex) {
            System.out.println("Indice invalido.");
        }
    }

    /**
     * Suma todos los elementos de la lista y regresa el resultado de la suma
     * total.
     * @return Suma total de todos los elementos de la lista. 
     */
    public int sumaLista() {
        int suma = 0;
        try {
            if (tipo.equals("L")) {
                for (int i = 0; i < lista1.longitud(); i++) {
                    suma += lista1.elemento(i);
                }
            }
            if (tipo.equals("D")) {
                for (int i = 0; i < lista2.longitud(); i++) {
                    suma += lista2.elemento(i);
                }
            }
            if (tipo.equals("A")) {
                for (int i = 0; i < lista3.longitud(); i++) {
                    suma += lista3.elemento(i);
                }
            }
        } catch (ExcepcionAccesoIncorrecto ex) {
            System.out.println("Indice invalido." + ex);
        }
        return suma;
    }

    /**
     * Metodo que ordena los elementos de la lista en forma decreciente.
     */
    public void ordenarLista() {
        try {
            if (tipo.equals("L")) {
                for (int i = 0; i < lista1.longitud() - 1; i++) {
                    for (int j = 0; j < lista1.longitud() - 1; j++) {
                        if (lista1.elemento(j) < lista1.elemento(j + 1)) {
                            int tmp = lista1.elemento(j + 1);
                            lista1.reemplaza(j + 1, lista1.elemento(j));
                            lista1.reemplaza(j, tmp);
                        }
                    }
                }
            }
            if (tipo.equals("D")) {
                for (int i = 0; i < lista2.longitud() - 1; i++) {
                    for (int j = 0; j < lista2.longitud() - 1; j++) {
                        if (lista2.elemento(j) < lista2.elemento(j + 1)) {
                            int tmp = lista2.elemento(j + 1);
                            lista2.reemplaza(j + 1, lista2.elemento(j));
                            lista2.reemplaza(j, tmp);
                        }
                    }
                }
            }
            if (tipo.equals("A")) {
                for (int i = 0; i < lista3.longitud() - 1; i++) {
                    for (int j = 0; j < lista3.longitud() - 1; j++) {
                        if (lista3.elemento(j) < lista3.elemento(j + 1)) {
                            int tmp = lista3.elemento(j + 1);
                            lista3.reemplaza(j + 1, lista3.elemento(j));
                            lista3.reemplaza(j, tmp);
                        }
                    }
                }
            }
        } catch (ExcepcionAccesoIncorrecto ex) {
            System.out.println("Indice invalido." + ex);
        }
    }

    /**
     * Metodo principal.
     * @param args Como argumento uno es el tipo de lista que se implementara, 
     * A si es una lista implementada con arreglos, D si es una lista doblemente
     * ligada y L si es una lista ligada. Como segundo argumento recibira el
     * nombre de un archivo de texto.
     */
    public static void main(String[] args) {
        Main lista = new Main(args[0], args[1]);
        System.out.println("Lista original:");
        lista.imprimeLista();
        System.out.println("Lista invertida:");
        lista.invierteLista();
        lista.imprimeLista();
        System.out.println("Lista en orden decreciente:");
        lista.ordenarLista();
        lista.imprimeLista();
        System.out.println("Suma total: " + lista.sumaLista());
    }
}
