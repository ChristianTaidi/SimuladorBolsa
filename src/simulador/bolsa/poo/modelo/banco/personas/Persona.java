package simulador.bolsa.poo.modelo.banco.personas;

public abstract class Persona {
    private String nombre;
    private String dni;

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
