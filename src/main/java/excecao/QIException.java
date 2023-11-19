package excecao;

public class QIException extends Exception{
    
    private String Id;
    
    private double quantidade_estoque;
    private double quantidade_requerida;

    public QIException(String Id, double quantidade_estoque, double quantidade_requerida){
        super("\nQuantidade insuficiente em estoque");
        this.Id = Id;
        this.quantidade_estoque = quantidade_estoque;
        this.quantidade_requerida = quantidade_requerida;
      }
      public String getId(){
        return Id;
      }
    
      public double getQuantidadeEstoque(){
        return quantidade_estoque;
      }

      public double getQuantidadeRequerida(){
        return quantidade_requerida;
      }
}
