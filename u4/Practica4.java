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
   static SinglyLinkedList <PalabraPosicion> palabrasDelArchivo = new SinglyLinkedList<>();

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
            //contador de lineas
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {  //leer el archivo por lineas
                numLinea++;
               logicaLectura(linea,numLinea);
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
    // Método que procesa la línea leída del archivo
    private static void logicaLectura(String linea,int numLinea){
        // Divide la línea en segmentos separados por comas
        String[] segmentos = linea.split(",");
        // Patrón para constantes de tipo String
        Pattern pString = Pattern.compile("\"(.*?)\"");
        for (String segmento : segmentos) {
            // Verifica si el segmento es un comentario
            Pattern pComentario = Pattern.compile("//(.*?)//");
            Matcher mComentario = pComentario.matcher(segmento);
            // Verifica si el segmento es una constante de tipo String
            Matcher mString = pString.matcher(segmento);
            if (mComentario.find()) {
                // Si el segmento es un comentario, trata todo el segmento como un comentario
                palabrasDelArchivo.addLast(new PalabraPosicion(mComentario.group(),numLinea));
            } else if (mString.find()) {
                // Si el segmento es una constante de tipo String, trata todo el segmento como una constante de tipo String
                palabrasDelArchivo.addLast(new PalabraPosicion(mString.group(),numLinea));
            } else {
                // Si el segmento no tiene un comentario ni una constante String, divide el segmento en palabras
                String[] palabras = segmento.split("\\s+");
                for (String palabra : palabras) {
                    if (!palabra.trim().isEmpty()) { // Verifica si la cadena no está vacia después de eliminar los espacios en blanco
                        palabrasDelArchivo.addLast(new PalabraPosicion(palabra.trim(),numLinea)); //Agrega las palabras a la lista despues de eliminar los espacios en blanco
                    }
                }
            }
            palabrasDelArchivo.addLast(new PalabraPosicion(",",numLinea)); // Agrega la coma después de procesar cada segmento
        }
        // Elimina la última coma que se agregó al final de la línea
        if (!palabrasDelArchivo.isEmpty() && palabrasDelArchivo.last().getPalabra().equals(",")) {
            palabrasDelArchivo.removeLast();
        }
    }

    private static void categoriaIdentificadores(){
        for (PalabraPosicion palabraPosicion: palabrasDelArchivo) {
            String palabra = palabraPosicion.getPalabra();
            if (palabra.matches("[a-zA-Z]+[a-zA-Z0-9_]*[#$%&?]$")) {
                char ultimoChar = palabra.charAt(palabra.length() - 1);
                if(ultimoChar == '#'){
                    System.out.println("identificadores tipo cadena de texto  " + palabra);
                } else if (ultimoChar == '%'){
                    System.out.println("identificadores de valor real   " + palabra);
                } else if (ultimoChar == '&'){
                    System.out.println("identificadores de valor entero  " + palabra);
                } else if (ultimoChar == '$'){
                    System.out.println("identificadores de valor logico   " + palabra);
                } else if (ultimoChar == '?'){
                    System.out.println("identificadores tipo programa   " + palabra);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (leerArchivo()) {
            System.out.println("Palabras en el archivo:\n " + palabrasDelArchivo);
            categoriaIdentificadores();
        }
    }
}
