package excecao;

public class PANUException extends Exception{
    
    private double parcelas;

    public PANUException(double parcelas){
        super("\nNúmero de parcelas nulo");
        this.parcelas = parcelas;
      }
     
      public double getParcelas(){
        return parcelas;
      }
}
