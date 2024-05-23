package u5;

import u4.DatosPalabra;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                logicaLectura(linea, numLinea);
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

            listaDeTokens.add(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last), numLinea));
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

            listaDeTokens.add(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last), numLinea));
            //listaTokens.addLast(new DatosPalabra(lexema, Integer.parseInt(token), Integer.parseInt(id), Integer.parseInt(last)));
            // Imprimir los resultados
            /*System.out.println("Lexema: " + lexema);
            System.out.println("Token: " + token);
            System.out.println("ID: " + id);
            System.out.println("Last: " + last);
            System.out.println("Número de línea tabla tokens: " + numLinea);*/
        }
    }

    public static int general(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        numLinea = validarInicio(listaDeTokens, numLinea);
        numLinea = validarSentenciaDeclaracionVar(listaDeTokens, numLinea);
        numLinea = validarSentenciaCuerpo(listaDeTokens, numLinea);
        return numLinea;
    }
    public static int validarInicio(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -1) {
            numLinea++;
            if (listaDeTokens.get(numLinea).getValorToken() == -55) {
                numLinea++;
                if (listaDeTokens.get(numLinea).getValorToken() == -75) {
                    numLinea++;
                    return numLinea;
                } throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ;");
            } throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un identificador");
        } throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra program");
    }

    public static int validarIdentificadores(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -51) {
            numLinea++;
            return numLinea;
        } else if (listaDeTokens.get(numLinea).getValorToken() == -52) {
            numLinea++;
            return numLinea;
        } else if (listaDeTokens.get(numLinea).getValorToken() == -53) {
            numLinea++;
            return numLinea;

        } else if (listaDeTokens.get(numLinea).getValorToken() == -54) {
            numLinea++;
            return numLinea;
        } else if (listaDeTokens.get(numLinea).getValorToken() == -55) {
            numLinea++;
            return numLinea;
        }
        throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un identificador");
        //return numLinea;
    }


    public static int validarTipoDato(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -11) {
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
        throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un tipo de dato");
    }


    public static int validarSentenciaDeclaracionVar(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -15) {
            numLinea++;
            if(numLinea < listaDeTokens.size() && listaDeTokens.get(numLinea).getValorToken() == -11 || listaDeTokens.get(numLinea).getValorToken() == -12 || listaDeTokens.get(numLinea).getValorToken() == -13 || listaDeTokens.get(numLinea).getValorToken() == -14) {
                numLinea = validarSentenciaDeclaracion(listaDeTokens, numLinea);
            }
             return numLinea;
        }
        throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra var");

    }

    public static int validarSentenciaDeclaracion(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        numLinea = validarDeclaracion(listaDeTokens, numLinea);
        if (numLinea < listaDeTokens.size() && (listaDeTokens.get(numLinea).getValorToken() == -11 || listaDeTokens.get(numLinea).getValorToken() == -12 || listaDeTokens.get(numLinea).getValorToken() == -13 || listaDeTokens.get(numLinea).getValorToken() == -14)) {
            numLinea = validarSentenciaDeclaracion(listaDeTokens, numLinea);
            return numLinea;
        }
        return numLinea;
    }

    public static int validarDeclaracion(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        //llamar validarTipoDato
        numLinea = validarTipoDato(listaDeTokens, numLinea);
        if (listaDeTokens.get(numLinea).getValorToken() == -77) {
            numLinea++;
            numLinea = validarIdentificadoresConComa(listaDeTokens, numLinea);
            if (listaDeTokens.get(numLinea).getValorToken() == -75) {
                numLinea++;
                return numLinea;
            } else {
                throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un punto y coma \"; \"");
            }
        }
        throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba dos puntos \":\"");
    }

    public static int validarIdentificadoresConComa(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        // Primero validar un identificador
        numLinea = validarIdentificadores(listaDeTokens, numLinea);

        // Verificar si el siguiente token es -76 (una coma)
        if (numLinea < listaDeTokens.size() && listaDeTokens.get(numLinea).getValorToken() == -76) {
            numLinea++;

            // Si es así, llamar a este método recursivamente para continuar validando más identificadores separados por comas
            numLinea = validarIdentificadoresConComa(listaDeTokens, numLinea);
        }

        return numLinea;
    }

    public static int validarSentenciaCuerpo(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -2) {
            numLinea++;
            numLinea = contenidoCuerpo2(listaDeTokens, numLinea);
            if(listaDeTokens.get(numLinea).getValorToken() == -3){
                numLinea++;
                return numLinea;
            }
                throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra end");
        }
           throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra begin");
        //return numLinea;
    }

    public static int contenidoCuerpo2(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        numLinea = estructuraSentencias(listaDeTokens, numLinea);
        if(listaDeTokens.get(numLinea).getValorToken() != -3){
            throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra end");
        }
        return numLinea;
    }
    public static int estructuraSentencias(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {

        numLinea = comprobarSentencias(listaDeTokens, numLinea);
        if(listaDeTokens.get(numLinea).getValorToken() == -3){

            return numLinea;
        } else {
            return estructuraSentencias(listaDeTokens, numLinea);
        }
    }
    public static int comprobarSentencias(LinkedList<DatosPalabra> listaDeTokens, int numLinea) throws ValidacionException {
        switch (listaDeTokens.get(numLinea).getValorToken()){
            case -4 -> numLinea = validarRead(listaDeTokens, numLinea);
            case -5 -> numLinea = validarWrite(listaDeTokens, numLinea);
            case -6 -> numLinea = validarIf(listaDeTokens, numLinea);
            case - 8 -> numLinea = validarWhile(listaDeTokens, numLinea);
            case -9 -> numLinea = validarRepeat(listaDeTokens, numLinea);
            case -51,-52,-53,-54 -> numLinea = validarAsignacion2(listaDeTokens, numLinea);
        }
        return numLinea;
    }

    public static int validarAsignacion2(LinkedList<DatosPalabra> listaDeTokens, int numLinea)throws ValidacionException{
        numLinea = validarIdentificadores(listaDeTokens, numLinea);
        if (listaDeTokens.get(numLinea).getValorToken() == -26) {
            numLinea++;
            numLinea = validarExpresion(listaDeTokens, numLinea);
            if (listaDeTokens.get(numLinea).getValorToken() == -75) {
                numLinea++;
                return numLinea;
            } else {
                throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ;");
            }
        }return numLinea;
    }
    public static int validarExpresion(LinkedList<DatosPalabra>listaDeTokens, int numLinea)throws ValidacionException{
        numLinea = validarExpresionSimple(listaDeTokens, numLinea);
        if(listaDeTokens.get(numLinea).getValorToken() == -31 ||listaDeTokens.get(numLinea).getValorToken() == -32 ||listaDeTokens.get(numLinea).getValorToken() == -33 ||listaDeTokens.get(numLinea).getValorToken() == -34|| listaDeTokens.get(numLinea).getValorToken() == -35 || listaDeTokens.get(numLinea).getValorToken() == -36){
            numLinea = validarOperadoresRelacionales(listaDeTokens, numLinea);
            numLinea = validarExpresionSimple(listaDeTokens, numLinea);
        }
        return numLinea;
    }
    public static int validarExpresionSimple(LinkedList<DatosPalabra> listaDeTokens, int numLinea)throws  ValidacionException{
        numLinea = validarTermino(listaDeTokens, numLinea);
        if(listaDeTokens.get(numLinea).getValorToken() == -21 ||listaDeTokens.get(numLinea).getValorToken() == -22 ||listaDeTokens.get(numLinea).getValorToken() == -23 ||listaDeTokens.get(numLinea).getValorToken() == -24 ||listaDeTokens.get(numLinea).getValorToken() == -25){
            numLinea = validarOperadoresYTermino(listaDeTokens, numLinea);
        }
        return numLinea;
    }
    public static int validarOperadoresYTermino(LinkedList<DatosPalabra> listaDeTokens, int numLinea)throws  ValidacionException{

        numLinea = validarOperadoresAritmeticos(listaDeTokens, numLinea);
        numLinea = validarTermino(listaDeTokens, numLinea);
        //operadores aritmeticos
        if(listaDeTokens.get(numLinea).getValorToken() == -21 ||listaDeTokens.get(numLinea).getValorToken() == -22 ||listaDeTokens.get(numLinea).getValorToken() == -23 ||listaDeTokens.get(numLinea).getValorToken() == -24||listaDeTokens.get(numLinea).getValorToken() == -25) {
            //numLinea++;
            numLinea = validarOperadoresYTermino(listaDeTokens, numLinea);
        }
        return numLinea;
    }
    public static int validarTermino(LinkedList<DatosPalabra> listaDeTokens, int numLinea)throws  ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -73){
            numLinea++;
            numLinea = validarExpresion(listaDeTokens,numLinea);
            if (listaDeTokens.get(numLinea).getValorToken() == -74){
                numLinea++;
                return numLinea;
            }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ) ");
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -51 || listaDeTokens.get(numLinea).getValorToken() == -52 ||
                listaDeTokens.get(numLinea).getValorToken() == -53 || listaDeTokens.get(numLinea).getValorToken() == -54 ){
            numLinea = validarIdentificadores(listaDeTokens,numLinea);
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -61 || listaDeTokens.get(numLinea).getValorToken() == -62 || listaDeTokens.get(numLinea).getValorToken() == -63
                || listaDeTokens.get(numLinea).getValorToken() == -64 || listaDeTokens.get(numLinea).getValorToken() == -65) {
            numLinea = validarConstantes(listaDeTokens, numLinea);
            return numLinea;
        }
                //throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ( ");
        return numLinea;

    }

    public static int validarSentenciaLogica(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -73){
            numLinea++;
            numLinea = validarLogica(listaDeTokens,numLinea);
            if(listaDeTokens.get(numLinea).getValorToken() == -74){
                numLinea++;
            }
        }else {
            numLinea = validarLogica(listaDeTokens,numLinea);
        }
        return numLinea;
    }

public static int validarLogica(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -61 || listaDeTokens.get(numLinea).getValorToken() == -62 || listaDeTokens.get(numLinea).getValorToken() == -64 || listaDeTokens.get(numLinea).getValorToken() == -65) {
            numLinea++;
        }else{
            if(listaDeTokens.get(numLinea).getValorToken() == -51 || listaDeTokens.get(numLinea).getValorToken() == -52 || listaDeTokens.get(numLinea).getValorToken() == -53 || listaDeTokens.get(numLinea).getValorToken() == -54 ){
                numLinea = validarIdentificadores(listaDeTokens,numLinea);
            }
        }

        numLinea = validarLogicaRecursiva(listaDeTokens,numLinea);
    return numLinea;
}

public static int validarLogicaRecursiva(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        numLinea = validarOperadoresRelacionales(listaDeTokens,numLinea);
        if (listaDeTokens.get(numLinea).getValorToken() == -61 || listaDeTokens.get(numLinea).getValorToken() == -62 || listaDeTokens.get(numLinea).getValorToken() == -64 || listaDeTokens.get(numLinea).getValorToken() == -65) {
            numLinea++;

        }else{
            if(listaDeTokens.get(numLinea).getValorToken() == -51 || listaDeTokens.get(numLinea).getValorToken() == -52 || listaDeTokens.get(numLinea).getValorToken() == -53 || listaDeTokens.get(numLinea).getValorToken() == -54 ){
                numLinea = validarIdentificadores(listaDeTokens,numLinea);
            }
        }
        if (numLinea < listaDeTokens.size() && validarOperadoresRelacionales(listaDeTokens,numLinea) != numLinea){
            numLinea = validarLogicaRecursiva(listaDeTokens,numLinea);
        }
        return numLinea;
}

    public static int validarWhile(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -8){
            numLinea++;
            if(listaDeTokens.get(numLinea).getValorToken() == -73){
                numLinea++;
                numLinea = validarSentenciaLogica(listaDeTokens,numLinea);
                if (listaDeTokens.get(numLinea).getValorToken() == -74) {
                    numLinea++;
                    numLinea = validarSentenciaDo(listaDeTokens,numLinea);
                }

            }
        }
        return numLinea;
    }

    public static int validarSentenciaDo(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -17){
            numLinea++;
            if (listaDeTokens.get(numLinea).getValorToken() == -2){
                numLinea++;
                numLinea = contenidoCuerpo2(listaDeTokens,numLinea);
                if (listaDeTokens.get(numLinea).getValorToken() == -3) {
                    numLinea++;
                    return numLinea;
                }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra end");
            }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra begin");
        }
        throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra do");
    }

    public static int validarIf(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -6){
            numLinea++;
            if(listaDeTokens.get(numLinea).getValorToken() == -73){
                numLinea++;
                numLinea = validarIfRecursivo(listaDeTokens,numLinea);
                if (listaDeTokens.get(numLinea).getValorToken() == -74){
                    numLinea++;
                    if (listaDeTokens.get(numLinea).getValorToken() == -16){
                        numLinea++;
                        if(listaDeTokens.get(numLinea).getValorToken() == -2){
                            numLinea++;
                            numLinea = contenidoCuerpo2(listaDeTokens,numLinea);
                            if(listaDeTokens.get(numLinea).getValorToken() == -3) {
                                numLinea++;
                                //return numLinea;
                                if (listaDeTokens.get(numLinea).getValorToken()== -7){
                                    numLinea = validarElse(listaDeTokens,numLinea);
                                    return numLinea;
                                }return numLinea;
                            }
                        }
                    }else{
                        throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra then");
                    }
                }else{
                    throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un )");
                }
            }else{
                throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un (");

            }
        }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra if");

    }
    public static int validarIfRecursivo(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -43){
            numLinea++;
        }
        numLinea = validarSentenciaLogica(listaDeTokens,numLinea);
        if(listaDeTokens.get(numLinea).getValorToken() == -41 || listaDeTokens.get(numLinea).getValorToken() == -42){
            numLinea++;
            numLinea = validarIfRecursivo(listaDeTokens,numLinea);
        }
        return numLinea;
    }

    public static int validarElse(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -7){
            numLinea++;
            if (listaDeTokens.get(numLinea).getValorToken() == -2){
                numLinea++;
                numLinea = contenidoCuerpo2(listaDeTokens, numLinea);
                if (listaDeTokens.get(numLinea).getValorToken() == -3) {
                    numLinea++;
                    return numLinea;
                }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra end");
            }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra begin");

        }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra else");

    }
    public static int validarRepeat(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -9){
            numLinea++;
            if (listaDeTokens.get(numLinea).getValorToken() == -2) {
                numLinea++;
                numLinea = contenidoCuerpo2(listaDeTokens, numLinea);
                if (listaDeTokens.get(numLinea).getValorToken() == -3) {
                    numLinea++;
                    if (listaDeTokens.get(numLinea).getValorToken() == -10) {
                        numLinea++;
                        if (listaDeTokens.get(numLinea).getValorToken() == -73) {
                            numLinea++;
                            numLinea = validarSentenciaLogica(listaDeTokens, numLinea);
                            if (listaDeTokens.get(numLinea).getValorToken() == -74) {
                                numLinea++;
                                if (listaDeTokens.get(numLinea).getValorToken() == -75) {
                                    numLinea++;
                                    return numLinea;
                                } else {
                                    throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ;");
                                }
                            } else {
                                throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un )");
                            }
                        } else {
                            throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un (");
                        }
                    }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra until");
                }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra end");
            }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra begin");
        }
        throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba la palabra repeat");
    }
    public static int validarWrite(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        int tempNumLinea;
        if(listaDeTokens.get(numLinea).getValorToken() == -5){
            numLinea++;
            if(listaDeTokens.get(numLinea).getValorToken() == -73){
                numLinea++;
                tempNumLinea = validarIdentificadores(listaDeTokens,numLinea);
                if(tempNumLinea != numLinea) {
                    numLinea = tempNumLinea;
                }else{
                    tempNumLinea = validarConstantes(listaDeTokens,numLinea);
                    if(tempNumLinea != numLinea){
                        numLinea = tempNumLinea;
                    }
                }
                if (listaDeTokens.get(numLinea).getValorToken() == -74){
                    numLinea++;
                    if (listaDeTokens.get(numLinea).getValorToken() == -75){
                        numLinea++;
                        return numLinea;
                    }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ;");
                } throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un )");
            }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un (");
        }
       return numLinea;
    }
    public static int validarRead(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -4){
            numLinea++;
            if (listaDeTokens.get(numLinea).getValorToken() == -73){
                numLinea++;
                numLinea = validarIdentificadores(listaDeTokens,numLinea);
                if (listaDeTokens.get(numLinea).getValorToken() == -74){
                    numLinea++;
                    if (listaDeTokens.get(numLinea).getValorToken() == -75){
                        numLinea++;
                        return numLinea;
                    }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ;");
                }throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un )");
            }
            throw new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba un ;(");
        }
        return numLinea;
    }

    public static int validarOperadoresAritmeticos(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if (listaDeTokens.get(numLinea).getValorToken() == -21){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -22){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -24){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -25){
            numLinea++;
            return numLinea;
        }
        return numLinea;
    }

    public static int validarOperadoresRelacionales(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -31){
            numLinea++;
            return numLinea;
        }
        if(listaDeTokens.get(numLinea).getValorToken() == -32){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -33){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -34){
            numLinea++;
            return numLinea;
        }
        if(listaDeTokens.get(numLinea).getValorToken() == -35){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -36){
            numLinea++;
            return numLinea;
        }
        return numLinea;
    }
    public static int validarConstantes(LinkedList<DatosPalabra> listaDeTokens ,int numLinea) throws ValidacionException {
        if(listaDeTokens.get(numLinea).getValorToken() == -61){
            numLinea++;
            return numLinea;
        }
        if(listaDeTokens.get(numLinea).getValorToken() == -62){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -63){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -64){
            numLinea++;
            return numLinea;
        }
        if (listaDeTokens.get(numLinea).getValorToken() == -65){
            numLinea++;
            return numLinea;
        }
        throw  new ValidacionException("Error en la línea: " + numLinea + ". Se esperaba una constante entera, real, cadena de caracteres, true o false");
    }

    public static void main(String[] args) {
        try{
            if(leerArchivo()){
                int numLinea = 0;
                numLinea = general(listaDeTokens,numLinea);
                int numeroFinal = listaDeTokens.getLast().getLineaSintactico();
                System.out.println("Numero final: " + numeroFinal);
                System.out.println("Numero de linea: " + numLinea);
                JOptionPane.showMessageDialog(null, "El archivo se ha validado correctamente");
            }
        }catch (ValidacionException e){
            System.out.println("Error en la línea " + e.getNumLinea()+  ": " + e.getMessage());
        }
    }
}

