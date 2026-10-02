package ar.com.edithpernia.proyectobancario.entidades.cuentas;
 
import ar.com.edithpernia.proyectobancario.entidades.clientes.Cliente;
 
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString(callSuper = true)
public class CajaAhorro extends Cuenta {
 
    private double tasaInteres;
 
    public CajaAhorro(int nro, Cliente clienteAsociado, double tasaInteres) {
        super(nro, clienteAsociado);
        this.tasaInteres = tasaInteres;
    }
 
   
    @Override
    public void extraerEfectivo(double efectivo) {
        if (efectivo > 0 && efectivo <= getSaldo()) {
            setSaldo(getSaldo() - efectivo);
        } else {
            System.out.println("El monto es inválido o supera el saldo disponible.");
        }
    }
 
    public void cobrarInteres() {
        double interes = getSaldo() * tasaInteres;
        setSaldo(getSaldo() + interes);
    }
 
}
 