package excecao;

public class VNException extends Exception{
    
    private double valor;

    public VNException(double valor){
        super("\nValor Negativo");
        this.valor = valor;
      }
     
      public double getvalor(){
        return valor;
      }

}
