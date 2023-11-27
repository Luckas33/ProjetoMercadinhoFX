package excecao;

public class PCIException extends Exception{
    
    private double preco_compra;


    public PCIException(double preco_compra){

        super("\nPreço de compra inválido: ");
        this.preco_compra = preco_compra;
      }
    
      public double getPrecoCompra(){
        return preco_compra;
      }
}
