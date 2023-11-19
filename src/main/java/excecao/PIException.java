package excecao;

public class PIException extends Exception{
    
    private String Id;

    public PIException(String Id){

        super("\nProduto não existente ");
        this.Id = Id;
      }
    
      public String getId(){
        return Id;
      }
}
