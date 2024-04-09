package u4;

import javax.swing.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Practica4 {
    static String nombreArchivo = "./u4/practica4.txt";
   static SinglyLinkedList <DatosPalabra> palabrasDelArchivo = new SinglyLinkedList<>();

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
                palabrasDelArchivo.addLast(new DatosPalabra(mComentario.group(),numLinea));
            } else if (mString.find()) {
                // Si el segmento es una constante de tipo String, trata todo el segmento como una constante de tipo String
                palabrasDelArchivo.addLast(new DatosPalabra(mString.group(),numLinea));
            } else {
                // Si el segmento no tiene un comentario ni una constante String, divide el segmento en palabras
                String[] palabras = segmento.split("\\s+");
                for (String palabra : palabras) {
                    if (!palabra.trim().isEmpty()) { // Verifica si la cadena no está vacia después de eliminar los espacios en blanco
                        palabrasDelArchivo.addLast(new DatosPalabra(palabra.trim(),numLinea)); //Agrega las palabras a la lista despues de eliminar los espacios en blanco
                    }
                }
            }
            palabrasDelArchivo.addLast(new DatosPalabra(",",numLinea)); // Agrega la coma después de procesar cada segmento
        }
        // Elimina la última coma que se agregó al final de la línea
        if (!palabrasDelArchivo.isEmpty() && palabrasDelArchivo.last().getPalabra().equals(",")) {
            palabrasDelArchivo.removeLast();
        }
    }

    private static void categoriaIdentificadores(DatosPalabra datosPalabra){
            String palabra = datosPalabra.getPalabra();
            if (palabra.matches("[a-zA-Z]+[a-zA-Z0-9_]*[#$%&?]$")) {
                datosPalabra.setEsIdentificador(-2);
                char ultimoChar = palabra.charAt(palabra.length() - 1);
                if(ultimoChar == '#'){ //"identificadores tipo cadena de texto
                    datosPalabra.setValorToken(-53);
                    System.out.println(datosPalabra);
                } else if (ultimoChar == '%'){ //identificadores de valor real
                    datosPalabra.setValorToken(-52);
                    System.out.println(datosPalabra);
                } else if (ultimoChar == '&'){//identificadores de valor entero
                    datosPalabra.setValorToken(-51);
                    System.out.println(datosPalabra);
                } else if (ultimoChar == '$'){//identificadores de valor logico
                    datosPalabra.setValorToken(-54);
                    System.out.println(datosPalabra);
                } else if (ultimoChar == '?'){//identificadores tipo programa
                    datosPalabra.setValorToken(-55);
                    System.out.println(datosPalabra);
                }
            }//no es identificador
    }
    private static void categoriaPalabrasReservadas(DatosPalabra datosPalabra){
        String palabra = datosPalabra.getPalabra();
        switch (palabra) {
            case "program":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-1);
                System.out.println(datosPalabra);
                break;
            case "begin":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-2);
                System.out.println(datosPalabra);
                break;
            case "end":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-3);
                System.out.println(datosPalabra);
                break;
            case "read":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-4);
                System.out.println(datosPalabra);
                break;
            case "write":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-5);
                System.out.println(datosPalabra);
                break;
            case "if":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-6);
                System.out.println(datosPalabra);
                break;
            case "else":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-7);
                System.out.println(datosPalabra);
                break;
            case "while":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-8);
                System.out.println(datosPalabra);
                break;
            case "repeat":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-9);
                System.out.println(datosPalabra);
                break;
            case "until":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-10);
                System.out.println(datosPalabra);
                break;
            case "int":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-11);
                System.out.println(datosPalabra);
                break;
            case "real":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-12);
                System.out.println(datosPalabra);
                break;
            case "string":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-13);
                System.out.println(datosPalabra);
                break;
            case "bool":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-14);
                System.out.println(datosPalabra);
                break;
            case "var":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-15);
                System.out.println(datosPalabra);
                break;
            case "then":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-16);
                System.out.println(datosPalabra);
                break;
            case "do":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-17);
                System.out.println(datosPalabra);
                break;
            default:
                // No es una palabra reservada
                break;
        }
    }

    private static void categoriaCaracteresEspeciales(DatosPalabra datosPalabra){
        String palabra = datosPalabra.getPalabra();
        if(palabra.matches("[(),;:]")){
            datosPalabra.setEsIdentificador(-1);
            switch (palabra){
                case "(":
                    datosPalabra.setValorToken(-73);
                    System.out.println(datosPalabra);
                    break;
                case ")":
                    datosPalabra.setValorToken(-74);
                    System.out.println(datosPalabra);
                    break;
                case ",":
                    datosPalabra.setValorToken(-76);
                    System.out.println(datosPalabra);
                    break;
                case ";":
                    datosPalabra.setValorToken(-75);
                    System.out.println(datosPalabra);
                    break;
                case ":":
                    datosPalabra.setValorToken(-77); //Se añadio el -77 para el caracter especial : no estaba en la tabla de tokens
                    System.out.println(datosPalabra);
                    break;
                default:
                    break;
            }
        }
    }
    private static void categoriaNumerosEnteros(DatosPalabra datosPalabra){
        String palabra = datosPalabra.getPalabra();
        if (palabra.matches("\\d+")) {
            datosPalabra.setEsIdentificador(-1);
            datosPalabra.setValorToken(-61);
            System.out.println(datosPalabra);
        }//no es numero entero
    }

    private static void categoriaNumerosDecimales(DatosPalabra datosPalabra){
        String palabra = datosPalabra.getPalabra();
        if (palabra.matches("(-)?\\d+(\\.)\\d+")) {
            datosPalabra.setEsIdentificador(-1);
            datosPalabra.setValorToken(-62);
            System.out.println(datosPalabra);
        }//no es numero decimal
    }

    private static void categoriaConstanteString(DatosPalabra datosPalabra){
        String palabra = datosPalabra.getPalabra();
        if (palabra.matches("\"(.+?)\"")) {
            datosPalabra.setEsIdentificador(-1);
            datosPalabra.setValorToken(-63);
            System.out.println(datosPalabra);
        }//no es constante string
    }
    private static void categoriaValorLogico(DatosPalabra datosPalabra){
        String palabra = datosPalabra.getPalabra();
        switch (palabra) {
            case "true":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-64);
                System.out.println(datosPalabra);
                break;
            case "false":
                datosPalabra.setEsIdentificador(-1);
                datosPalabra.setValorToken(-65);
                System.out.println(datosPalabra);
                break;
            default:
                // No es un valor logico
                break;
        }
    }

    private static void analisisLexico(){
        for (DatosPalabra datosPalabra : palabrasDelArchivo){
            categoriaIdentificadores(datosPalabra);
            categoriaPalabrasReservadas(datosPalabra);
            categoriaCaracteresEspeciales(datosPalabra);
            categoriaNumerosEnteros(datosPalabra);
            categoriaNumerosDecimales(datosPalabra);
            categoriaConstanteString(datosPalabra);
            categoriaValorLogico(datosPalabra);
        }
    }

    public static void escribirArchivo() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("u4/resultadoAnalisisLexico.txt"));
            for (DatosPalabra datosPalabra : palabrasDelArchivo) {
                writer.write(datosPalabra.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Conflicto para escribir el archivo");
        }
    }

    public static void main(String[] args) {
        if (leerArchivo()) {
            for (DatosPalabra datosPalabra : palabrasDelArchivo) {
                System.out.println(datosPalabra.getPalabra());
            }
            analisisLexico();
            escribirArchivo();
        }
    }
}
