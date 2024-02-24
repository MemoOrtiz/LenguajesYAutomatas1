package u2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    static List<String> palabras = new ArrayList<>();
    public static void main (String[]args){
        String menu = """
                1. Leer Archivo
                2. Comparar Archivo con Lenguaje 1
                3. Comparar Archivo con Lenguaje 2
                4. Comparar Archivo con Lenguaje 3
                5. Terminar
                """;
        int opcion = 0;
        while(opcion != 5){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,menu));
            switch (opcion){
                case 1:
                try{
                    FileReader fr = new FileReader(nombreArchivo);
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    //leer el archivo por lineas
                    while((linea = br.readLine())!=null) {
                        //Dividir la línea en palabras separadas por comas o espacios
                        String[] palabrasLinea = linea.split("[,\\s]+");
                        //Agregar las palabras al ArrayList
                        palabras.addAll(Arrays.asList(palabrasLinea));
                    }
                    br.close();

                    System.out.println("Palabras en el archivo:");
                    for (String palabra : palabras) {
                        System.out.println(palabra);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("No se pudo abrir el archivo");
                } catch (IOException e) {
                    System.out.println("Conflicto para leer el archivo");
                }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;

            }
        }
    }
}
