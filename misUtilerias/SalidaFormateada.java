package misUtilerias;

import javax.swing.*;

public class SalidaFormateada {
    public static void imprimeConScroll(String cadena) {

        JTextArea area = new JTextArea(cadena, 35, 30); //los 20 20 son las dimensiones de la ventwnw
        JScrollPane panel = new JScrollPane(area,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JOptionPane.showMessageDialog(null, panel);
    }
}
