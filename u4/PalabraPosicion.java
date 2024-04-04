package u4;

public class PalabraPosicion {
    private String palabra;
    private int valorToken;
    private int esIdentificador;
    private int posicion;

    public PalabraPosicion(String palabra, int posicion) {
        this.palabra = palabra;
        this.posicion = posicion;
    }
    public String getPalabra() {
        return palabra;
    }
    public int getValorToken() { return valorToken; }
    public int getEsIdentificador() { return esIdentificador; }
    public int getPosicion() {
        return posicion;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    public void setValorToken(int valorToken) { this.valorToken = valorToken; }
    public void setEsIdentificador(int esIdentificador) { this.esIdentificador = esIdentificador; }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return(palabra != null && !palabra.isEmpty() ? "palabra= " + palabra : "") +
                (posicion != 0 ? " posicion= " + posicion : "") +
                (valorToken != 0 ? " valorToken= " + valorToken : "") +
                (esIdentificador != 0 ? " esIdentificador= " + esIdentificador : "");
    }

}
