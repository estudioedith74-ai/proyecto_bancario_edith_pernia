package ar.com.edithpernia.proyectobancario.tests;

import ar.com.edithpernia.proyectobancario.entidades.clientes.ClienteEmpresa;
import ar.com.edithpernia.proyectobancario.entidades.cuentas.Cheque;
import ar.com.edithpernia.proyectobancario.entidades.cuentas.CuentaConvertibilidad;

public class TestCuentaConvertibilidad {
    public static void main(String[] args) {

        ClienteEmpresa empresa = new ClienteEmpresa(2, "Globos Almagro", "30-12345678-9");
        System.out.println(empresa);

        CuentaConvertibilidad cuenta = new CuentaConvertibilidad(333333333, empresa, 1000);

        System.out.println("--- 1) Heredado de Cuenta: depositarEfectivo ---");
        cuenta.depositarEfectivo(5000);
        System.out.println("Saldo pesos: " + cuenta.getSaldo());                 // esperado: 5000.0

        System.out.println("--- 2) Dolares ---");
        cuenta.depositarDolares(100);
        System.out.println("Saldo dolares: " + cuenta.getSaldoDolar());          // esperado: 100.0

        cuenta.extraerDolares(500);
        System.out.println("Saldo dolares tras intento invalido: " + cuenta.getSaldoDolar()); // esperado: 100.0

        cuenta.extraerDolares(30);
        System.out.println("Saldo dolares tras extraer 30: " + cuenta.getSaldoDolar());       // esperado: 70.0

        System.out.println("--- 3) Conversion pesos -> dolares (tasa 1000) ---");
        cuenta.convertirPesosADolares(2000, 1000);
        System.out.println("Saldo pesos: " + cuenta.getSaldo());                 // esperado: 3000.0
        System.out.println("Saldo dolares: " + cuenta.getSaldoDolar());          // esperado: 72.0

        System.out.println("--- 4) Conversion dolares -> pesos (tasa 1000) ---");
        cuenta.convertirDolaresAPesos(1, 1000);
        System.out.println("Saldo pesos: " + cuenta.getSaldo());                 // esperado: 4000.0
        System.out.println("Saldo dolares: " + cuenta.getSaldoDolar());          // esperado: 71.0

        System.out.println("--- 5) Heredado de CuentaCorriente: descubierto en pesos ---");
        cuenta.extraerEfectivo(4500);
        System.out.println("Saldo pesos tras usar descubierto: " + cuenta.getSaldo());  // esperado: -500.0

        System.out.println("--- 6) Heredado de CuentaCorriente: depositarCheque ---");
        Cheque cheque = new Cheque(1000, "Banco Nacion", "15/10/2026");
        cuenta.depositarCheque(cheque);
        System.out.println("Saldo pesos tras cheque: " + cuenta.getSaldo());     // esperado: 500.0

        System.out.println(cuenta);
    }
}