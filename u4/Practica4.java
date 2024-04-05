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

    private static void categoriaIdentificadores(PalabraPosicion palabraPosicion){
            String palabra = palabraPosicion.getPalabra();
            if (palabra.matches("[a-zA-Z]+[a-zA-Z0-9_]*[#$%&?]$")) {
                palabraPosicion.setEsIdentificador(-2);
                char ultimoChar = palabra.charAt(palabra.length() - 1);
                if(ultimoChar == '#'){ //"identificadores tipo cadena de texto
                    palabraPosicion.setValorToken(-53);
                    System.out.println(palabraPosicion);
                } else if (ultimoChar == '%'){ //identificadores de valor real
                    palabraPosicion.setValorToken(-52);
                    System.out.println(palabraPosicion);
                } else if (ultimoChar == '&'){//identificadores de valor entero
                    palabraPosicion.setValorToken(-51);
                    System.out.println(palabraPosicion);
                } else if (ultimoChar == '$'){//identificadores de valor logico
                    palabraPosicion.setValorToken(-54);
                    System.out.println(palabraPosicion);
                } else if (ultimoChar == '?'){//identificadores tipo programa
                    palabraPosicion.setValorToken(-55);
                    System.out.println(palabraPosicion);
                }
            }
    }
    private static void categoriaPalabrasReservadas(PalabraPosicion palabraPosicion){
        String palabra = palabraPosicion.getPalabra();
        switch (palabra) {
            case "program":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-1);
                System.out.println(palabraPosicion);
                break;
            case "begin":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-2);
                System.out.println(palabraPosicion);
                break;
            case "end":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-3);
                System.out.println(palabraPosicion);
                break;
            case "read":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-4);
                System.out.println(palabraPosicion);
                break;
            case "write":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-5);
                System.out.println(palabraPosicion);
                break;
            case "if":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-6);
                System.out.println(palabraPosicion);
                break;
            case "else":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-7);
                System.out.println(palabraPosicion);
                break;
            case "while":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-8);
                System.out.println(palabraPosicion);
                break;
            case "repeat":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-9);
                System.out.println(palabraPosicion);
                break;
            case "until":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-10);
                System.out.println(palabraPosicion);
                break;
            case "int":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-11);
                System.out.println(palabraPosicion);
                break;
            case "real":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-12);
                System.out.println(palabraPosicion);
                break;
            case "string":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-13);
                System.out.println(palabraPosicion);
                break;
            case "bool":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-14);
                System.out.println(palabraPosicion);
                break;
            case "var":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-15);
                System.out.println(palabraPosicion);
                break;
            case "then":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-16);
                System.out.println(palabraPosicion);
                break;
            case "do":
                palabraPosicion.setEsIdentificador(-1);
                palabraPosicion.setValorToken(-17);
                System.out.println(palabraPosicion);
                break;
            default:
                // No es una palabra reservada
                break;
        }
    }

    private static void analisisLexico(){
        for (PalabraPosicion palabraPosicion: palabrasDelArchivo){
            categoriaIdentificadores(palabraPosicion);
            categoriaPalabrasReservadas(palabraPosicion);
        }
    }

    public static void main(String[] args) {
        if (leerArchivo()) {
            for (PalabraPosicion palabraPosicion: palabrasDelArchivo) {
                System.out.println(palabraPosicion.getPalabra());
            }
            analisisLexico();
        }
    }
}
