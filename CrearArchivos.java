import javax.swing.*;
import java.io.*;

public class CrearArchivos {

    public static void main(String[] args) {
        File archivo = new File("./Actividad1.txt");
        FileWriter fWriter = null;
        PrintWriter pWriter = null;
        try {
            fWriter = new FileWriter(archivo);
            pWriter = new PrintWriter(fWriter);
            System.out.println("Escriba el nombre: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String nombre = br.readLine();

            pWriter.print(nombre);
            // cerrarlo
            pWriter.close();
            fWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "no se ha podido cerrar el archivo");

            // BufferedReader br = new BufferedReader(new FileReader("./ArchivoCreado.txt"));

        }
    }
}
