package u1;

import javax.swing.*;
import java.io.*;

public class Practica1 {

    static FileWriter fWriter = null;
    static PrintWriter pWriter = null;
    static BufferedWriter bw = null;

    public static void main(String[] args) throws IOException {
            boolean agregarOtro = true;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    }


    public static void escribirArchivo() {
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
    }

    public static Persona informacionPersona() throws IOException {
        System.out.println("Escriba el nombre: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nombre = br.readLine();


        System.out.println("Escriba el apellido Paterno: ");
        String apellidoPaterno = br.readLine();

        System.out.println("Escriba el apellido Materno: ");
        String apellidoMaterno = br.readLine();

        System.out.println("Escriba la edad: ");
        int edad = br.read();
        return new Persona(nombre, apellidoPaterno, apellidoMaterno, edad);
    }
}
