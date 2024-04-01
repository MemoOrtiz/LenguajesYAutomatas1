package u4;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Practica4 {
    static String nombreArchivo = "./u4/practica4.txt";
    static List<String> palabrasDeArchivo = new ArrayList<>();
    private static boolean archivoLecturaTieneContenido() {
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

    private static boolean leerArchivo() {
        if (!archivoLecturaTieneContenido()) {
            JOptionPane.showMessageDialog(null, "El archivo está vacío");
            return false;
        }
        try {
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;


            String[] palabrasLinea = new String[0];
            while ((linea = br.readLine()) != null) {  //leer el archivo por lineas
                // Primero, busca los comentarios y los trata como una sola palabra
                Pattern p = Pattern.compile("//[^\\n]*//");
                Matcher m = p.matcher(linea);
                while (m.find()) {
                    palabrasDeArchivo.add(m.group());
                }
                // Luego, elimina los comentarios de la línea
                linea = m.replaceAll("");
                palabrasLinea = linea.split("(?<=,)|(?=,)|\\s+");
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

    public static void main(String[] args) {
        if (leerArchivo()) {
            //JOptionPane.showMessageDialog(null, "Palabras en el archivo: " + palabrasDeArchivo);
            for (String palabra: palabrasDeArchivo) {
                System.out.println(palabra);
            }
            System.out.println("\n" + palabrasDeArchivo);

        }
    }
}
