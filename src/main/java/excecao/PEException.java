package excecao;

public class PEException extends Exception{
    
    private String Id;


    public PEException(String Id){

        super("\nProduto já existente. [Id: "+Id+"]");
        this.Id = Id;
      }
    
      public String getId(){
        return Id;
      }
}
