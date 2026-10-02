package ar.com.edithpernia.proyectobancario.entidades.clientes;
 
import lombok.Getter;
import lombok.ToString;
 

@Getter
@ToString(callSuper = true)
public class ClienteEmpresa extends Cliente {
 
    private String nombreFantasia;
    private String cuit;
 
    public ClienteEmpresa(int nroCliente, String nombreFantasia, String cuit) {
        super(nroCliente);
        this.nombreFantasia = nombreFantasia;
        this.cuit = cuit;
    }
 
}
 