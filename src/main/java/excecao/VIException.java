package excecao;

public class VIException extends Exception{
    
    private double valor;

    public VIException(double valorTotal,double valor){
        super("\nValor Insificiente");
        this.valor = valor;
      }
     
      public double getvalor(){
        return valor;
      }

}
