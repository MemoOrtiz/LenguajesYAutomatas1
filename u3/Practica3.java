package u3;

import misUtilerias.SalidaFormateada;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

                        int[][] tabla_transicion = {
                                {1, 0, 4},
                                {1, 2, 3},
                                {1, 2, 3},
                                {1, 2, 3},
                                {4, 4, 4}
                        };

                        int[] estados_finales = {1, 2, 3};


                        for (String palabra : palabrasDeArchivo) {
                        int estadoActual = 0;
                        //boolean esValido = false;
                        for (char c : palabra.toCharArray()) {
                            int column;
                            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                                column = 0; // [a-z][A-Z]
                            } else if (c == '$' || c == '_') {
                                column = 1; // $,_
                            } else if (c >= '0' && c <= '9') {
                                column = 2; // [0-9]
                            } else {
                                break; // Caracter no válido
                            }
                            estadoActual = tabla_transicion[estadoActual][column];
                        }

                        boolean encontrado = false;
                        for (int estadoFinal : estados_finales) {
                            if (estadoActual == estadoFinal) {
                                //esValido = true;
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
                        

                       /* // La expresión regular para identificadores de Java
                        String regex ="\\$*_*[a-zA-Z]+[a-zA-Z0-9_$]*";

                        // Compilar la expresión regular en un patrón
                        Pattern pattern = Pattern.compile(regex);

                        // Verificar si la cadena coincide con el patrón
                        for(String palabraLeida: palabrasDeArchivo){
                            boolean coincidencia = pattern.matcher(palabraLeida).matches();
                            if (coincidencia) {
                                palabrasValidadas.add(palabraLeida);
                            } else {
                                palabrasNoValidas.add(palabraLeida);
                            }
                        }

                        String palabrasValidadasString = String.join("\n", palabrasValidadas);
                        String palabrasNoValidasString = String.join("\n", palabrasNoValidas);

                        SalidaFormateada.imprimeConScroll("Las palabras que SI coinciden con la Expresion Regular " + expresionRegular1 + "\n" + "\n" + palabrasValidadasString, titulo1);
                        SalidaFormateada.imprimeConScroll("Las palabras que NO coinciden con la Expresion Regular " + expresionRegular1 + "\n" + "\n" + palabrasNoValidasString, titulo2);
*/
                        break;

                    case 2:
                        // Limpiar las listas antes de comenzar la comparación
                        palabrasValidadas.clear();
                        palabrasNoValidas.clear();
                        
                        String titulo1Caso2 = "COINCIDENCIA EXITOSA - Lenguaje 2";
                        String titulo2Caso2 = "COINCIDENCIA NO VALIDA - Lenguaje 2";
                        String expresionRegular2 ;

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
