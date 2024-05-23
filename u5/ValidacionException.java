package u5;

public class ValidacionException extends Exception {
    private int numLinea;

    public ValidacionException(String message) {
        super(message);
        this.numLinea = numLinea;
    }

    public int getNumLinea() {
        return numLinea;
    }
}