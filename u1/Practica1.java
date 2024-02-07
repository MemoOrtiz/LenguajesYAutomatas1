package u1;

import javax.swing.*;
import java.io.*;

public class Practica1 {
    //Se tienen objetos estaticos para que puedan ser llamadas en todos los metodos, ya que se usan en todos.

    static FileWriter fWriter = null;
    static BufferedWriter bw = null;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


  /*  Se crea un archivo desde mi directorio u1(esta en mi proyecto, recordemos el concepto de ruta relativa
    y se usa un FileWritter y se activa el append para la permanencia de datos
    el cual se usa en un BufferedWritter y este se usa para llamar
    el metodo escribir archivo()
    */

    public static void main(String[] args) throws IOException {
        try {
            File archive = new File("./u1/Practica1.txt");
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

    /*
    El metodo escribirArchivo recibe un objeto BufferedWriter. Se inicia con una variable booleana
    para indicar que queremos agregar otro registro, se inicia en verdaderos para iniciar el ciclo while
    Y dentro del ciclo se manda a llamar el metodo pedirInformacionPersona() y se escribe en el archivo
    por medio del BufferedWriter y por medio del metodo toString() del objeto persona.
    Se pregunta si se quiere agregar otro registro y dependiendo de la respuesta, si equivale si o no
    se modifica el estado booleano de la variable agregarOtro parasalir del ciclo
     */
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

    /*
    Este método llamado pedirInformacionPersona es de tipo Persona, ya que en este metodo se
    piden los datos por medio de la consola usando el objeto BufferedReader y se retorna un
    objeto Persona
     */
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