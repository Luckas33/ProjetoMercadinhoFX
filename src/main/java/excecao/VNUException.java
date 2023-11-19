package excecao;

public class VNUException extends Exception{
    
    private double valor;

    public VNUException(double valor){
        super("\nValor Nulo");
        this.valor = valor;
      }
     
      public double getvalor(){
        return valor;
      }

}
