package u5;

import u4.DatosPalabra;
import u4.SinglyLinkedList;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Practica5 {
    static String nombreArchivo = "./u4/Tabla de Tokens.txt";
    static SinglyLinkedList<DatosPalabra> listaTokens = new SinglyLinkedList<>();

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

            listaTokens.addLast(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last)));
            // Imprimir los resultados
            System.out.println("Lexema: " + lexema);
            System.out.println("Token: " + token);
            System.out.println("ID: " + id);
            System.out.println("Last: " + last);
            System.out.println("Número de línea tabla tokens: " + numLinea);
        } else {
            // Dividir la línea en partes usando la coma como delimitador
            String[] partes = linea.split(",", -1);

            // Asignar cada parte a su respectiva variable
            String lexema = partes[0];
            String token = partes[1];
            String id = partes[2];
            String last = partes[3];

            listaTokens.addLast(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last)));
            // Imprimir los resultados
            System.out.println("Lexema: " + lexema);
            System.out.println("Token: " + token);
            System.out.println("ID: " + id);
            System.out.println("Last: " + last);
            System.out.println("Número de línea tabla tokens: " + numLinea);
        }
    }


    public static void main(String[] args) {
        if(leerArchivo()){

        }
    }
}
