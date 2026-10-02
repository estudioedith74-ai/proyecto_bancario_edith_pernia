package ar.com.edithpernia.proyectobancario.entidades.cuentas;
 
import ar.com.edithpernia.proyectobancario.entidades.clientes.Cliente;
 
import lombok.Getter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;
 

@Getter
@ToString
@RequiredArgsConstructor//incluye todos los atributos marcados como final o aquellos marcados como @NonNull
public abstract class Cuenta {
 
    private final int nro;
    private final Cliente clienteAsociado;   // final: se recibe una sola vez, en el constructor, no cambia despues
    private double saldo;
 
    // Se queda CONCRETO: ninguna hija cambia esta regla, todas depositan igual.
    public void depositarEfectivo(double efectivo) {
        if (efectivo > 0) this.saldo += efectivo;
        else System.out.println("No se pueden depositar montos negativos o iguales a cero.");
    }
 
    // ABSTRACTO: cada hija define su propia regla para extraer.
    public abstract void extraerEfectivo(double efectivo);
 
    // protected, NO publico: solo esta clase y sus hijas pueden usarlo para modificar el saldo.
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }
 
}
 