package ar.com.edithpernia.proyectobancario.entidades.clientes;
 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter  
@Getter
@ToString(callSuper = true)//callSuper=true incluye los datos del padre (nroCliente) al imprimir el ToString
public class ClienteIndividual extends Cliente {
 
    private String nombre;
    private String apellido;
    private String dni;
 
    public ClienteIndividual(int nroCliente, String nombre, String apellido, String dni) {
        super(nroCliente);// LLAMADA OBLIGATORIA: Envía el nroCliente al constructor de la clase padre (Cliente)
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
 
}
 