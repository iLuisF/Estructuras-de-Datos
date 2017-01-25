
import java.util.Scanner;

/**
 * Programa de prueba en donde se realiza un menú para que el usuario interactúe
 * con el programa. Escogiendo la implementación que desea y la operación a
 * realizar.
 *
 * @author Flores González Luis Brandon
 * @version 1.0
 */
public class Main {

    /**
     * Metodo para probar el programa, el cual usa dos implementaciones la
     * primera es de arreglo de arreglos y la segunda de polinomio de
     * direccionamiento. Cuenta con las operaciones suma, producto, producto
     * escalar y trasponer.
     *
     * @param args no se ocupa.
     */
    public static void main(String[] args) {
        String archivo;
        do {
            Matriz matriz1 = new MatrizArreglo();
            Matriz matriz2 = new MatrizArreglo();
            Matriz matriz3 = new MatrizPolinomio();
            Matriz matriz4 = new MatrizPolinomio();
            LectorMatrizXML lectormat;
            Scanner lector = new Scanner(System.in);
            System.out.println("Escoge una implementación (Teclea solo el número)"
                    + "\n1. Arreglo de arreglos."
                    + "\n2. Polinomio de direccionamiento.");
            int opcion = lector.nextInt();
            lector.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("¿Qué operación quieres realizar? (Teclea solo el numero)"
                            + "\n1. Suma (Suma dos matrices) \n2. Producto(Multiplica dos matrices)"
                            + "\n3. Producto escalar(Multiplica una matriz por un escalar)"
                            + "\n4. Trasponer(Traspone una matriz)");
                    opcion = lector.nextInt();
                    lector.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("Escogiste la suma.");
                            System.out.print("Ingresa el primer archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nPrimer Matriz:\n");
                            matriz1.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz1.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz1.despliega();
                            System.out.print("Ingresa el segundo archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nSegunda Matriz:\n");
                            matriz2.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz2.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz2.despliega();
                            System.out.println("Resultado de la suma de ambas matrices:\n");
                            matriz1.suma(matriz2).despliega();
                            break;
                        case 2:
                            System.out.println("Escogiste el producto.");
                            System.out.print("Ingresa el primer archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nPrimer Matriz:\n");
                            matriz1.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz1.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz1.despliega();
                            System.out.print("Ingresa el segundo archivo xml: \t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nSeguna Matriz:\n");
                            matriz2.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz2.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz2.despliega();
                            System.out.println("Resultado del producto de ambas matrices:\n");
                            matriz1.producto(matriz2).despliega();
                            break;
                        case 3:
                            System.out.println("Escogiste el producto escalar.");
                            System.out.print("Ingresa el archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nMatriz:\n");
                            matriz1.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz1.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz1.despliega();
                            System.out.print("Ingresa el escalar:\t");
                            double escalar = lector.nextDouble();
                            System.out.println("Resultado del producto escalar:\n");
                            matriz1.productoEscalar(escalar).despliega();
                            break;
                        case 4:
                            System.out.println("Escogiste trasponer.");
                            System.out.print("Ingresa el archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nMatriz:\n");
                            matriz1.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz1.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz1.despliega();
                            System.out.println("Resultado de la operación:\n");
                            matriz1.trasponer().despliega();
                            break;
                        default:
                            System.out.println("Escoge una opción.");
                    }
                    break;
                case 2:
                    System.out.println("¿Qué operación quieres realizar? (Teclea solo el número)"
                            + "\n1. Suma (Suma dos matrices) \n2. Producto(Multiplica dos matrices)"
                            + "\n3. Producto escalar(Multiplica una matriz por un escalar)"
                            + "\n4. Trasponer(Traspone una matriz)");
                    opcion = lector.nextInt();
                    lector.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("Escogiste la suma.");
                            System.out.print("Ingresa el primer archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nPrimer Matriz:\n");
                            matriz3.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz3.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());

                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz3.despliega();
                            System.out.print("Ingresa el segundo archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nSeguna Matriz:\n");
                            matriz4.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz4.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz4.despliega();
                            System.out.println("Resultado de la suma de ambas matrices:\n");
                            matriz3.suma(matriz4).despliega();
                            break;
                        case 2:
                            System.out.println("Escogiste el producto.");
                            System.out.print("Ingresa el primer archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nPrimer Matriz:\n");
                            matriz3.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz3.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz3.despliega();
                            System.out.print("Ingresa el segundo archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nSeguna Matriz:\n");
                            matriz4.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz4.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());

                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz4.despliega();
                            System.out.println("Resultado del producto de ambas matrices:\n");
                            matriz3.producto(matriz4).despliega();
                            break;
                        case 3:
                            System.out.println("Escogiste el producto escalar.");
                            System.out.print("Ingresa el archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nMatriz:\n");
                            matriz3.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz3.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());

                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz3.despliega();
                            System.out.print("Ingresa el escalar:\t");
                            double escalar = lector.nextDouble();
                            System.out.println("Resultado del producto escalar:\n");
                            matriz3.productoEscalar(escalar).despliega();
                            break;
                        case 4:
                            System.out.println("Escogiste trasponer.");
                            System.out.print("Ingresa el archivo xml:\t");
                            lectormat = new LectorMatrizXML(lector.nextLine());
                            System.out.println("\nMatriz:\n");
                            matriz3.creaMatriz(lectormat.getRenglones(), lectormat.getColumnas(), lectormat.getDefault(), lectormat.getTipo());
                            for (int i = 0; i < lectormat.getEntradas(); i++) {
                                matriz3.setEntrada(lectormat.getRenglon(),
                                        lectormat.getColumna(), lectormat.getEntrada());
                                if (lectormat.haySiguiente()) {
                                    lectormat.siguienteEntrada();
                                }
                            }
                            matriz3.despliega();
                            System.out.println("Resultado de la operación:\n");
                            matriz3.trasponer().despliega();
                            break;
                        default:
                            System.out.println("Escoge una opción.");
                    }
                    break;
                default:
                    System.out.println("Escoge una opción.");
            }
            System.out.println("¿Quieres otra operación? (S/N)");
            archivo = lector.nextLine();
        } while ("S".equals(archivo) || "s".equals(archivo));
    }
}
