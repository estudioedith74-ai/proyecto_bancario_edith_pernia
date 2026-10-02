package ar.com.edithpernia.proyectobancario.entidades.cuentas;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Cheque {

    private double monto;
    private String bancoEmisor;
    private String fechaPago;

}