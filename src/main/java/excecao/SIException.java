package excecao;

public class SIException extends Exception{
    
    private String Id;
    private double saldo;
    private double valor;

    public SIException(String Id, double saldo, double valor){
        super("\nSaldo Insuficiente");
        this.Id = Id;
        this.saldo = saldo;
        this.valor = valor;
      }
      public String getId(){
        return Id;
      }
    
      public double getSaldo(){
        return saldo;
      }

      public double getValor(){
        return valor;
      }
}
