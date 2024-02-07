package u1;

import javax.swing.*;
import java.io.*;

public class Practica1 {

    static FileWriter fWriter = null;
    static BufferedWriter bw = null;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        try {
            File archive = new File("./Practica1.txt");
            fWriter = new FileWriter(archive,true);
            
            bw = new BufferedWriter(fWriter);

            escribirArchivo(bw);
            bw.close();
            fWriter.close();
            br.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());

        }
    }

    public static void escribirArchivo(BufferedWriter bw) throws IOException {
        boolean agregarOtro = true;
        while(agregarOtro){
            Persona persona = pedirInformacionPersona();
            bw.write(persona.toString());
            bw.newLine();

            System.out.println("¿Deseas agregar a otra persona? (SI/NO):");

            String respuesta = br.readLine();

            while (!respuesta.equalsIgnoreCase("SI") && !respuesta.equalsIgnoreCase("NO")) {
                System.out.println("Respuesta inválida. Por favor, responda con 'SI' o 'NO':");
                respuesta = br.readLine();
            }

            agregarOtro = respuesta.equalsIgnoreCase("SI");
        }
    }
    public static Persona pedirInformacionPersona()throws IOException{
        System.out.println("Escriba el nombre: ");
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nombre = br.readLine();


        System.out.println("Escriba el apellido Paterno: ");
        String apellidoPaterno = br.readLine();

        System.out.println("Escriba el apellido Materno: ");
        String apellidoMaterno = br.readLine();

        System.out.println("Escriba la edad: ");
        int edad = Integer.parseInt(br.readLine());
        return new Persona(nombre, apellidoPaterno, apellidoMaterno, edad);
    }
}


    /*public static void escribirArchivo() {
        try {
            File archive = new File("./Practica1.txt");
            fWriter = new FileWriter(archive);
            bw = new BufferedWriter(fWriter);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Escriba el nombre: ");
            String nombre = br.readLine();


            System.out.println("Escriba el apellido Paterno: ");
            String apellidoPaterno = br.readLine();

            System.out.println("Escriba el apellido Materno: ");
            String apellidoMaterno = br.readLine();

            System.out.println("Escriba la edad: ");
            int edad = br.read();


            bw.write(nombre+ " | " +apellidoPaterno+ " | " +apellidoMaterno+ " | " +edad);


            // cerrarlo
            pWriter.close();
            fWriter.close();
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "no se ha podido cerrar el archivo");
        }
    }*/