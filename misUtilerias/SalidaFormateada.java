package misUtilerias;

import javax.swing.*;
import java.awt.*;

public class SalidaFormateada {
    public static void imprimeConScroll(String cadena,String titulo) {

        JTextArea area = new JTextArea(cadena, 35, 30); //los 20 20 son las dimensiones de la ventana
        Font font = new Font("Arial", Font.PLAIN, 14); // Por ejemplo, Arial 14pt
        area.setFont(font);
        JScrollPane panel = new JScrollPane(area,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JOptionPane.showMessageDialog(null, panel,titulo,JOptionPane.INFORMATION_MESSAGE);
    }
}
