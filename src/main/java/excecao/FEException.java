package excecao;

public class FEException extends Exception{
    
    private String nome;


    public FEException(String nome){

        super("\nFuncionario já cadastrado. [Nome: "+nome+"]");
        this.nome = nome;
      }
    
      public String getId(){
        return nome;
      }
}
