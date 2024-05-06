package u5;

import u4.DatosPalabra;
import u4.SinglyLinkedList;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Practica5 {
    static String nombreArchivo = "./u4/Tabla de Tokens.txt";
    //static SinglyLinkedList<DatosPalabra> listaTokens = new SinglyLinkedList<>();
    static LinkedList<DatosPalabra> listaDeTokens = new LinkedList<>();

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
    private static void logicaLectura(String linea, int numLinea) {
        // Verificar si la línea es una coma
        if (linea.startsWith(",,")) {
            String[] partes = linea.split(",", -1);

            // Asignar cada parte a su respectiva variable
            String lexema = ",";
            String token = partes[2];
            String id = partes[3];
            String last = partes[4]; // Guardar el último número

            listaDeTokens.add(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last),numLinea));
            //listaTokens.addLast(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last)));
            /*// Imprimir los resultados
            System.out.println("Lexema: " + lexema);
            System.out.println("Token: " + token);
            System.out.println("ID: " + id);
            System.out.println("Last: " + last);
            System.out.println("Número de línea tabla tokens: " + numLinea);
        */
        } else {
            // Dividir la línea en partes usando la coma como delimitador
            String[] partes = linea.split(",", -1);

            // Asignar cada parte a su respectiva variable
            String lexema = partes[0];
            String token = partes[1];
            String id = partes[2];
            String last = partes[3];

            listaDeTokens.add(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last),numLinea));
            //listaTokens.addLast(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last)));
            // Imprimir los resultados
            /*System.out.println("Lexema: " + lexema);
            System.out.println("Token: " + token);
            System.out.println("ID: " + id);
            System.out.println("Last: " + last);
            System.out.println("Número de línea tabla tokens: " + numLinea);*/
        }
    }

    public static int validarInicio(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){
        while(numLinea < listaDeTokens.size()){
            if(listaDeTokens.get(numLinea).getValorToken() == -1) {
                numLinea++;
            }
            if (listaDeTokens.get(numLinea).getValorToken() == -55) {
                numLinea++;
            }
            if (listaDeTokens.get(numLinea).getValorToken() == -75) {
                numLinea++;
                continue;
            }
            numLinea = validarSentencia(listaDeTokens,numLinea);
        }
        return numLinea;
    }

    public static int validarIdentificadores(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){
        if(listaDeTokens.get(numLinea).getValorToken() == -51) {
            numLinea++;
            return numLinea;
        }else if(listaDeTokens.get(numLinea).getValorToken() == -52){
            numLinea++;
            return numLinea;
        }else if(listaDeTokens.get(numLinea).getValorToken() == -53){
            numLinea++;
            return numLinea;

        }else if(listaDeTokens.get(numLinea).getValorToken() == -54){
            numLinea++;
            return numLinea;
        } else if(listaDeTokens.get(numLinea).getValorToken() == -55){
            numLinea++;
            return numLinea;
        }
        return -1;
    }


    public static int validarTipoDato(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){
        if(listaDeTokens.get(numLinea).getValorToken() == -11){
            numLinea++;
            return numLinea;
        } else if (listaDeTokens.get(numLinea).getValorToken() == -12) {
            numLinea++;
            return numLinea;
        } else if (listaDeTokens.get(numLinea).getValorToken() == -13) {
            numLinea++;
            return numLinea;
        } else if (listaDeTokens.get(numLinea).getValorToken() == -14) {
            numLinea++;
            return numLinea;
        }
        return -1;
    }
    public static int validarSentencia(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){
        numLinea = validarSentenciaDeclaracion(listaDeTokens,numLinea);
        return numLinea;
    }
    public static int validarSentenciaDeclaracion(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){
       if (listaDeTokens.get(numLinea).getValorToken() == -15){
           numLinea++;

       }
        numLinea = validarDeclaracion(listaDeTokens,numLinea);
        return numLinea;
    }
    public static int validarDeclaracion(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){
        //llamar validarTipoDato
        numLinea = validarTipoDato(listaDeTokens,numLinea);
        if (listaDeTokens.get(numLinea).getValorToken() == -77){
            numLinea++;
        }
        while(listaDeTokens.get(numLinea).getValorToken() == -76
        || listaDeTokens.get(numLinea).getValorToken() == -51
        || listaDeTokens.get(numLinea).getValorToken() == -52
        || listaDeTokens.get(numLinea).getValorToken() == -53
        || listaDeTokens.get(numLinea).getValorToken() == -54){
            //llamar validarIdentificadores
            numLinea = validarIdentificadores(listaDeTokens,numLinea);
            if (listaDeTokens.get(numLinea).getValorToken() == -76){
                numLinea++;
            }
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -75){
            numLinea++;
            return numLinea;
        }
        return -1;
    }
    



    public static int validarOperadoresAritmeticos(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){
        if(listaDeTokens.get(numLinea).getValorToken() == -24){
            numLinea++;
        }
        return -1;
    }

    /*public static int validarOperadoresRelacionales(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){

    }
    public static int validarConstantes(LinkedList<DatosPalabra> listaDeTokens ,int numLinea){

    }*/

    public static void main(String[] args) {
        if(leerArchivo()){
            int numLinea = 0;
            numLinea = validarInicio(listaDeTokens,numLinea);
            int numeroFinal = listaDeTokens.getLast().getLineaSintactico();
            System.out.println("Numero final: " + numeroFinal);
            System.out.println("Numero de linea: " + numLinea);
            if(numLinea == -1){
                JOptionPane.showMessageDialog(null, "Error en la linea: " + numLinea);
            }else{
                JOptionPane.showMessageDialog(null, "El archivo se ha validado correctamente");
            }
        }
    }
}
