package excecao;

public class PCNException extends Exception{
    
    private double preco_compra;


    public PCNException(double preco_compra){

        super("\nPreço de compra negativo: ");
        this.preco_compra = preco_compra;
      }
    
      public double getPrecoCompra(){
        return preco_compra;
      }
}
