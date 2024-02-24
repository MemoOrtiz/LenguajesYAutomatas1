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
            try{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,menu));
                if (opcion < 1 || opcion > 5) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida.");
                    continue;
                }
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
                        palabrasDeArchivo.addAll(Arrays.asList(palabrasLinea));
                    }
                    //cerrar lectura de archivo
                    br.close();
                    JOptionPane.showMessageDialog(null, "Archivo leido con exito");

                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Conflicto para leer el archivo");

                }
                    break;
                case 2:
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
                case 3:
                    break;
                case 4:
                    break;

            }
        }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido del menu.");
            }
        }
    }
}
