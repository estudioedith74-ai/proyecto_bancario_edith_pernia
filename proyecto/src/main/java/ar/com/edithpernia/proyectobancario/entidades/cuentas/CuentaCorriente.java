package ar.com.edithpernia.proyectobancario.entidades.cuentas;
 
import ar.com.edithpernia.proyectobancario.entidades.clientes.Cliente;
 
import lombok.Getter;
import lombok.ToString;
 
// Sin @Setter: nadie necesita cambiar montoAutorizadoDescubierto despues de creada la cuenta.
@Getter
@ToString(callSuper = true)
public class CuentaCorriente extends Cuenta {
 
    private double montoAutorizadoDescubierto;
 
    public CuentaCorriente(int nro, Cliente clienteAsociado, double montoAutorizadoDescubierto) {
        super(nro, clienteAsociado);
        this.montoAutorizadoDescubierto = montoAutorizadoDescubierto;
    }
 
    public void depositarCheque(Cheque cheque) {
        setSaldo(getSaldo() + cheque.getMonto());
    }
 
    // Implementa el metodo abstracto de Cuenta. Regla de CuentaCorriente: se puede
    // extraer hasta el saldo MAS el descubierto autorizado.
    @Override
    public void extraerEfectivo(double efectivo) {
        if (efectivo > 0 && efectivo <= getSaldo() + montoAutorizadoDescubierto) {
            setSaldo(getSaldo() - efectivo);
        } else {
            System.out.println("El monto es inválido o supera el saldo más el descubierto autorizado.");
        }
    }
 
}
 