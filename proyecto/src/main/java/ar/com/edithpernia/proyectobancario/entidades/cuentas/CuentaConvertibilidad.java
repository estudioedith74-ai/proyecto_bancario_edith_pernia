package ar.com.edithpernia.proyectobancario.entidades.cuentas;
 
import ar.com.edithpernia.proyectobancario.entidades.clientes.ClienteEmpresa;
 
import lombok.Getter;
import lombok.ToString;
 

@Getter
@ToString(callSuper = true)
public class CuentaConvertibilidad extends CuentaCorriente {
 
    private double saldoDolar;
 
     public CuentaConvertibilidad(int nro, ClienteEmpresa clienteAsociado, double montoAutorizadoDescubierto) {
        super(nro, clienteAsociado, montoAutorizadoDescubierto);
    }
 
    public void depositarDolares(double dolares) {
        if (dolares > 0) this.saldoDolar += dolares;
        else System.out.println("No se pueden depositar dolares negativos o iguales a cero.");
    }
 
    // Sin descubierto en dolares (regla de la consigna): compara solo contra saldoDolar.
    public void extraerDolares(double dolares) {
        if (dolares > 0 && dolares <= saldoDolar) this.saldoDolar -= dolares;
        else System.out.println("El monto en dolares es invalido o supera el saldo en dolares.");
    }
 
    // Pesos -> dolares: se DIVIDE por la tasa. La tasa es un parametro, nunca un valor fijo.
    public void convertirPesosADolares(double montoPesos, double tasaConversion) {
        if (montoPesos > 0 && montoPesos <= getSaldo() && tasaConversion > 0) {
            double dolaresConvertidos = montoPesos / tasaConversion;
            setSaldo(getSaldo() - montoPesos);
            this.saldoDolar += dolaresConvertidos;
        } else {
            System.out.println("Monto o tasa invalidos, o saldo en pesos insuficiente para convertir.");
        }
    }
 
    // Dolares -> pesos: se MULTIPLICA por la tasa.
    public void convertirDolaresAPesos(double montoDolares, double tasaConversion) {
        if (montoDolares > 0 && montoDolares <= saldoDolar && tasaConversion > 0) {
            double pesosConvertidos = montoDolares * tasaConversion;
            this.saldoDolar -= montoDolares;
            setSaldo(getSaldo() + pesosConvertidos);
        } else {
            System.out.println("Monto o tasa invalidos, o saldo en dolares insuficiente para convertir.");
        }
    }
 
}
 