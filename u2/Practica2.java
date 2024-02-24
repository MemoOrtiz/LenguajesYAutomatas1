package u2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
La aplicación o programa puede recibir como entrada:
•	leer un archivo de texto
Y mostrar la clasificación de cada cadena o palabra de acuerdo al lenguaje definido
(haciendo uso del manejo de la expresión regular o simulacion con programacion).
Se deberá probar al menos 3 ejemplos de cada expresión regular, considere manejo errores
y diseño lógico del programa acorde al paradigma asociado al lenguaje de programación
 */
public class Practica2 {
    static String nombreArchivo = "practica2.txt";
    public static void main (String[]args){
        String menu = """
                1. Leer Archivo
                2. Comparar Archivo con Lenguaje 1
                3. Comparar Archivo con Lenguaje 2
                4. Comparar Archivo con Lenguaje 3
                """;
        int opcion = 0;
        while(opcion != 4){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,menu));
            switch (opcion){
                case 1:
                try{
                    FileReader fr = new FileReader(nombreArchivo);
                    BufferedReader br = new BufferedReader(fr);
                    

                } catch (FileNotFoundException e) {
                    System.out.println("No se pudo abrir el archivo");
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
        System.out.println(menu);
    }
}
