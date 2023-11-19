package excecao;

public class SNException extends Exception{


    private double saldo;

    public SNException(double saldo){
        super("\nSaldo Negativo");
        this.saldo = saldo;
      }
     
      public double getSaldo(){
        return saldo;
      }
}
