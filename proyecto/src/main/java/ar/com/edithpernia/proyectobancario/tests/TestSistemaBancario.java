package ar.com.edithpernia.proyectobancario.tests;
 
import ar.com.edithpernia.proyectobancario.entidades.clientes.ClienteEmpresa;
import ar.com.edithpernia.proyectobancario.entidades.clientes.ClienteIndividual;
import ar.com.edithpernia.proyectobancario.entidades.cuentas.CajaAhorro;
import ar.com.edithpernia.proyectobancario.entidades.cuentas.Cheque;
import ar.com.edithpernia.proyectobancario.entidades.cuentas.Cuenta;
import ar.com.edithpernia.proyectobancario.entidades.cuentas.CuentaCorriente;
 
public class TestSistemaBancario {
    public static void main(String[] args) {
 
        // ---------- CLIENTES ----------
        ClienteIndividual Edith = new ClienteIndividual(1, "Edith", "Pernia", "30111222");
        System.out.println(Edith);
 
        ClienteEmpresa empresa = new ClienteEmpresa(2, "Globos Almagro", "30-12345678-9");
        System.out.println(empresa);
 
         System.out.println("----- CAJA DE AHORRO -----");
 
        CajaAhorro caja = new CajaAhorro(987654321, Edith, 0.05);
 
        caja.depositarEfectivo(2000);
        System.out.println("Saldo caja despues de depositar: " + caja.getSaldo());   
 
        caja.extraerEfectivo(200);
        System.out.println("Saldo caja despues de extraer: " + caja.getSaldo());     
 
        caja.extraerEfectivo(5000);  // caso invalido: debe imprimir el mensaje de error
 
        caja.cobrarInteres();
        System.out.println("Saldo caja despues del interes: " + caja.getSaldo());    
 
        System.out.println("----- CUENTA CORRIENTE -----");
 
        CuentaCorriente corriente = new CuentaCorriente(123456789, Edith, 500);
 
        corriente.depositarEfectivo(300);
        System.out.println("Saldo cuenta corriente: " + corriente.getSaldo());        
 
        corriente.extraerEfectivo(700);  // usa el descubierto (700 <= 300 + 500)
        System.out.println("Saldo cuenta corriente tras usar descubierto: " + corriente.getSaldo()); 
 
        Cheque cheque = new Cheque(1000, "Banco Nacion", "15/10/2026");
        corriente.depositarCheque(cheque);
        System.out.println("Saldo cuenta corriente tras depositar cheque: " + corriente.getSaldo()); 
 
        System.out.println("----- POLIMORFISMO -----");
 
        Cuenta cuentaDeAhorro = new CajaAhorro(111111111, empresa, 0.02);
        Cuenta cuentaConGiro = new CuentaCorriente(222222222, empresa, 400);
 
        cuentaDeAhorro.depositarEfectivo(1000);
        cuentaConGiro.depositarEfectivo(600);
 
        cuentaDeAhorro.extraerEfectivo(1500);   // CajaAhorro: no hay descubierto, debe imprimir error
        cuentaConGiro.extraerEfectivo(900);     // CuentaCorriente: 900 <= 600 + 400, se permite
 
        System.out.println("Saldo cuentaDeAhorro: " + cuentaDeAhorro.getSaldo());   
        System.out.println("Saldo cuentaConGiro: " + cuentaConGiro.getSaldo());     
    }
}
 