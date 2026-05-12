package ProyectoFinalArcade;

public class jugador {

    private String nombre;
    private int saldo;

    public jugador(String nombre, int saldo) {

        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}