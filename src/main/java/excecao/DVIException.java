package excecao;

import java.time.LocalDate;



public class DVIException extends Exception{
    
    
    public DVIException(String id, LocalDate data) {
        super("\nData de validade já ultrapassada Id/Saldo:"+id+"/"+data);
        
      }
    
}
