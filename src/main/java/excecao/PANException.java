package excecao;

public class PANException extends Exception{
    
    private double parcelas;

    public PANException(double parcelas){
        super("\nNúmero de parcelas negativa");
        this.parcelas = parcelas;
      }
     
      public double getParcelas(){
        return parcelas;
      }
}
