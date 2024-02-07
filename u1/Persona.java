package u1;

public class Persona {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int edad) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno + " " + edad;
    }
}
