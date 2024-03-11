package u3;

import misUtilerias.SalidaFormateada;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Practica3 {
    public static void main(String[] args) {
        boolean archivoLeido = leerArchivo(); //se llama el segundo metodo
        if (!archivoLeido) {
            JOptionPane.showMessageDialog(null, "El archivo no pudo ser leído. Se cerrará la ventana.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del programa
        }
        realizarMenu();
    }
    //Aqui se almacenan las variables estaticas durante la ejecucion de la clase
    //Decidi usar List para tener flexibilidad en algun momento, de ser necesario
    //un cambio de estructura de datos.
    static String nombreArchivo = "./u3/practica3.txt";
    static List<String> palabrasDeArchivo = new ArrayList<>();
    static List<String> palabrasValidadas = new ArrayList<>();
    static List<String> palabrasNoValidas = new ArrayList<>();
    static String nombreArchivoNuevo = "./u3/resultadoPractica3";

    //Este metodo se usa para verificar que el archivo tenga contenido, por lo que
    //es de tipo boolean
    private static boolean archivoTieneContenido() {
        try {
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            boolean tieneContenido = br.readLine() != null; // Verificar si hay al menos una línea para leer
            br.close();
            return tieneContenido; //retorno "true"
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Conflicto para leer el archivo");
            return false;
        }
    }

    //Aqui se verifica que el archivo tenga contenido, si es asi se lee y se almacena en arraylists
    //y si no se lanza una excepcion
    private static boolean leerArchivo() {
        if (!archivoTieneContenido()) {
            JOptionPane.showMessageDialog(null, "El archivo está vacío");
            return false;
        }
        try {
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;


            String[] palabrasLinea = new String[0];
            while ((linea = br.readLine()) != null) {  //leer el archivo por lineas
                    palabrasLinea = linea.split("[,\n]+");
                    palabrasDeArchivo.addAll(Arrays.asList(palabrasLinea)); //Agregar las palabras al ArrayList
                }


            br.close();//cerrar lectura de archivo
            JOptionPane.showMessageDialog(null, "Archivo leido con exito");
            return true;

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo, porque no existe o no se encuentra");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Conflicto para leer el archivo");
        }
        return false;
    }

    //Este metodo ejecuta la logica del programa a traves de un menu de opciones y verificaciones
    private static void realizarMenu() {
        //formato del string del menu
        String menu = """
                1. Comparar Archivo con Identificadores en Java
                2. Comparar Archivo con Números enteros y reales en lenguaje C
                3. Comparar Archivo con Operadores aritméticos, lógicos y relacionales en PYTHON
                4. Terminar
                """;
        int opcion = 0;
        while (true) {//ciclo while para iterar un switch case para hacer multiples opciones
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "MENU", JOptionPane.PLAIN_MESSAGE));
                if (opcion < 1 || opcion > 4) { //verificacion de rango de opciones
                    JOptionPane.showMessageDialog(null, "Ingrese una opción válida", "ERROR", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                switch (opcion) {

                    case 1:
                        // Limpiar las listas antes de comenzar la comparación
                        palabrasValidadas.clear();
                        palabrasNoValidas.clear();


                        String titulo1 = "COINCIDENCIA EXITOSA";
                        String titulo2 = "COINCIDENCIA NO VALIDA";
                        String expresionRegular1 = "$*_*[a-zA-Z]+[a-zA-Z0-9_$]*";

                        int [][] tabla = {
                                {2, 3, 4, 1},
                                {1, 1, 1, 1},
                                {8, 6, 4, 5},
                                {7, 3, 4, 5},
                                {7, 6, 4, 5},
                                {7, 6, 4, 5},
                                {7, 6, 4, 5},
                                {7, 6, 4, 5},
                                {8, 6, 4, 5}
                        };
                        int[] estados_finales = {3,4,5,6,7,8};

                        for (String palabra : palabrasDeArchivo) {
                        int estadoActual = 0;

                        for (char c : palabra.toCharArray()) {

                            int column;

                            if(c == '_'){
                                column = 0;// _
                            } else if (c == '$') {
                                column = 1;// $
                            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                                column = 2; // [a-z][A-Z]
                            } else if (c >= '0' && c <= '9') {
                                column = 3; // [0-9]
                            } else {
                                break;
                            }
                                estadoActual = tabla[estadoActual][column];
                        }

                        boolean encontrado = false;
                        for (int estadoFinal : estados_finales) {
                            if (estadoActual == estadoFinal) {
                                encontrado = true;
                                break;
                            }
                        }

                        if (encontrado) {
                            palabrasValidadas.add(palabra);
                        } else {
                            palabrasNoValidas.add(palabra);
                        }
                    }
                        String palabrasValidadasString = String.join("\n", palabrasValidadas);
                        String palabrasNoValidasString = String.join("\n", palabrasNoValidas);

                        SalidaFormateada.imprimeConScroll("Las palabras que SI coinciden con la Expresion Regular " + expresionRegular1 + "\n" + "\n" + palabrasValidadasString, titulo1);
                        SalidaFormateada.imprimeConScroll("Las palabras que NO coinciden con la Expresion Regular " + expresionRegular1 + "\n" + "\n" + palabrasNoValidasString, titulo2);


                        String identificador = "identificador";

                        // Abrir el archivo para escribir
                        try (FileWriter fw = new FileWriter(nombreArchivoNuevo + ".txt")) {
                            // Escribir cada palabra validada junto con el identificador en el archivo
                            for (String palabraValidada : palabrasValidadas) {
                                fw.write(palabraValidada + ", " + identificador + "\n");
                            }
                            // Mostrar mensaje de éxito
                            JOptionPane.showMessageDialog(null, "Contenido de palabras validadas guardado en el nuevo archivo.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Hubo un error al escribir en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 2:
                        // Limpiar las listas antes de comenzar la comparación
                        palabrasValidadas.clear();
                        palabrasNoValidas.clear();

                        String titulo1Case2 = "COINCIDENCIA EXITOSA";
                        String titulo2Case2 = "COINCIDENCIA NO VALIDA";
                        String expresionRegularCase2 = "(-)?[0-9]+(\\.[0-9]+)?(L|f|d)?";

                        int[][] tablaTransicionCase2 = {
                                {2,1, 8, 8, 8, 8},
                                {8,1, 3, 8, 8, 8},
                                {8,1, 8, 8, 8, 8},
                                {8,4, 8, 8, 8, 8},
                                {8,4, 8, 5, 6, 7},
                                {8,8, 8, 8, 8, 8},
                                {8,8, 8, 8, 8, 8},
                                {8,8, 8, 8, 8, 8},
                                {8,8, 8, 8, 8, 8}
                        };

                        int[] estadosFinalesCase2 = {1, 4, 5, 6, 7};

                        for (String palabra : palabrasDeArchivo) {
                            int estadoActual = 0;
                            for (char caracter : palabra.toCharArray()) {
                                int columna;
                                if (caracter == '-') {
                                    columna = 0;
                                } else if (Character.isDigit(caracter)) {
                                    columna = 1;
                                } else if (caracter == '.') {
                                    columna = 2;
                                } else if (caracter == 'L') {
                                    columna = 3;
                                } else if (caracter == 'f') {
                                    columna = 4;
                                } else if (caracter == 'd') {
                                    columna = 5;
                                } else {
                                    break;
                                }
                                estadoActual = tablaTransicionCase2[estadoActual][columna];
                            }
                            boolean encontrado = false;
                            for (int estadoFinal : estadosFinalesCase2) {
                                if (estadoActual == estadoFinal) {
                                    encontrado = true;
                                    break;
                                }
                            }
                            if (encontrado) {
                                palabrasValidadas.add(palabra);
                            } else {
                                palabrasNoValidas.add(palabra);
                            }
                        }

                        String palabrasValidadasStringCase2 = String.join("\n", palabrasValidadas);
                        String palabrasNoValidasStringCase2 = String.join("\n", palabrasNoValidas);

                        SalidaFormateada.imprimeConScroll("Palabras que SI coinciden con la ER " + expresionRegularCase2 + "\n" +
                                "\n" + palabrasValidadasStringCase2, titulo1Case2);
                        SalidaFormateada.imprimeConScroll("Palabras que NO coinciden con la ER " + expresionRegularCase2 + "\n" +
                                "\n" + palabrasNoValidasStringCase2, titulo2Case2);

                        break;

                    case 3:

                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Hasta luego");
                        return;

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido del menu.");
            }
        }
    }
}
