package u2;

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

/*
La aplicación o programa puede recibir como entrada:
•	leer un archivo de texto
Y mostrar la clasificación de cada cadena o palabra de acuerdo al lenguaje definido
(haciendo uso del manejo de la expresión regular o simulacion con programacion).
Se deberá probar al menos 3 ejemplos de cada expresión regular, considere manejo errores
y diseño lógico del programa acorde al paradigma asociado al lenguaje de programación
 */
public class Practica2 {
    static String nombreArchivo = "./u2/practica2.txt";
    static List<String> palabrasDeArchivo = new ArrayList<>();
    static List<String> palabrasValidadas = new ArrayList<>();
    static List<String> palabrasNoValidas = new ArrayList<>();

    //Este metodo se usa para verificar que el archivo tenga contenido
    private static boolean archivoTieneContenido() {
        try {
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            boolean tieneContenido = br.readLine() != null; // Verificar si hay al menos una línea para leer
            br.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Conflicto para leer el archivo");
            return false;
        }
    }
    //Aqui se verifica que el archivo tenga contenido, si es asi se lee y se almacena en arraylists
    private static boolean leerArchivo(){
        if (!archivoTieneContenido()) {
            JOptionPane.showMessageDialog(null, "El archivo está vacío");
            return false;
        }
        try{
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while((linea = br.readLine())!=null) {  //leer el archivo por lineas
                String[] palabrasLinea = linea.split("[,\\s]+");    //Dividir la línea en palabras separadas por comas o espacios
                palabrasDeArchivo.addAll(Arrays.asList(palabrasLinea)); //Agregar las palabras al ArrayList
            }

            br.close();//cerrar lectura de archivo
            JOptionPane.showMessageDialog(null, "Archivo leido con exito");
            return true;

        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo, porque no existe o no se encuentra");
        } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Conflicto para leer el archivo");
        }
        return false;
    }
    //Este metodo ejecuta la logica del programa a traves de un menu de opciones y verificaciones
    private static void realizarMenu(){
        String menu = """
                1. Comparar Archivo con Lenguaje 1
                2. Comparar Archivo con Lenguaje 2
                3. Comparar Archivo con Lenguaje 3
                4. Terminar
                """;
        int opcion = 0;
        while(opcion != 4){
            try{
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,menu,"MENU",JOptionPane.PLAIN_MESSAGE));
                if (opcion < 1 || opcion > 4) {
                    JOptionPane.showMessageDialog(null, "Ingrese una opción válida","ERROR",JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                switch (opcion){

                    case 1:
                        // Limpiar las listas antes de comenzar la comparación
                        palabrasValidadas.clear();
                        palabrasNoValidas.clear();

                        String titulo1 = "COINCIDENCIA EXITOSA";
                        String titulo2 = "COINCIDENCIA NO VALIDA";
                        String expresionRegular = "(hola)+(0|1)*";
                        Pattern pattern = Pattern.compile(expresionRegular);

                        // se usa un foreach para evaluar el arraylist en cada elemento, el cual contiene elementos palabras
                        for (String palabra:palabrasDeArchivo) {
                            boolean coincidencia = pattern.matcher(palabra).matches();
                            if(coincidencia){
                                palabrasValidadas.add(palabra);
                            }else{
                                palabrasNoValidas.add(palabra);
                            }
                            //System.out.println("Palabra: " + palabra + " - Coincide con : " + coincidencia);
                        }

                        String palabrasValidadasString = String.join("\n",palabrasValidadas);
                        String palabrasNoValidasString = String.join("\n",palabrasNoValidas);

                        SalidaFormateada.imprimeConScroll("Las palabras que SI coinciden con la Expresion Regular "+expresionRegular+"\n"+"\n"+palabrasValidadasString,titulo1);
                        SalidaFormateada.imprimeConScroll("Las palabras que NO coinciden con la Expresion Regular "+expresionRegular+"\n"+"\n"+palabrasNoValidasString,titulo2);
                        break;

                    case 2:
                        // Limpiar las listas antes de comenzar la comparación
                        palabrasValidadas.clear();
                        palabrasNoValidas.clear();

                        String titulo1Caso3 = "COINCIDENCIA EXITOSA - Lenguaje 2";
                        String titulo2Caso3 = "COINCIDENCIA NO VALIDA - Lenguaje 2";
                        String expresionRegular2 = "(aa|bb)#+(123)*";

                        // Iterar sobre las palabras del archivo
                        for (String palabra : palabrasDeArchivo) {
                            int index = 0;
                            int length = palabra.length();

                            // Verificar si la cadena cumple con el patrón
                            if (length < 2 || (palabra.charAt(0) != 'a' && palabra.charAt(0) != 'b') || palabra.charAt(0) != palabra.charAt(1)) {
                                palabrasNoValidas.add(palabra);
                                continue; // Saltar a la siguiente palabra
                            }

                            index += 2; // Avanzar el índice si la cadena comienza con "aa" o "bb"

                            // Verificar la presencia de "#" después de "aa" o "bb"
                            while (index < length && palabra.charAt(index) == '#') {
                                index++; // Avanzar el índice si encuentra "#"
                            }

                            // Verificar la presencia de "123" después de los "#"
                            while (index + 3 <= length && palabra.charAt(index) == '1' && palabra.charAt(index + 1) == '2' && palabra.charAt(index + 2) == '3') {
                                index += 3; // Avanzar el índice si encuentra "123"
                            }

                            // Si el índice alcanza la longitud de la palabra, es válida
                            if (index == length) {
                                palabrasValidadas.add(palabra);
                            } else {
                                palabrasNoValidas.add(palabra);
                            }
                        }

                        // Convertir las listas a cadenas
                        String palabrasValidadasStringCaso3 = String.join("\n", palabrasValidadas);
                        String palabrasNoValidasStringCaso3 = String.join("\n", palabrasNoValidas);

                        // Mostrar los resultados
                        SalidaFormateada.imprimeConScroll("Cadenas que SI coinciden con la ER "+expresionRegular2+"\n"+"\n"+palabrasValidadasStringCaso3,titulo1Caso3);
                        SalidaFormateada.imprimeConScroll("Cadenas que NO coinciden con la ER"+expresionRegular2+"\n"+"\n"+palabrasNoValidasStringCaso3, titulo2Caso3);

                        break;

                    case 3:

                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Hasta luego");
                        break;
                        
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido del menu.");
            }
        }
    }
    public static void main (String[]args){
        boolean archivoLeido = leerArchivo(); //se llama el segundo metodo
        if (!archivoLeido) {
            JOptionPane.showMessageDialog(null, "El archivo no pudo ser leído. Se cerrará la ventana.","Error",JOptionPane.ERROR_MESSAGE);
            return; // Salir del programa
        }
        realizarMenu();
    }
}
