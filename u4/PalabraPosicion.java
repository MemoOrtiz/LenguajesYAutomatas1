package u4;

public class PalabraPosicion {
    private String palabra;
    private int posicion;

    public PalabraPosicion(String palabra, int posicion) {
        this.palabra = palabra;
        this.posicion = posicion;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return
                "palabra= " + palabra  +
                " posicion= " + posicion ;
    }

}
