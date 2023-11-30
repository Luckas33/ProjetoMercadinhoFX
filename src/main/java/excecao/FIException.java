package excecao;

public class FIException extends Exception{
    
    private String nome;


    public FIException(String nome){

        super("\nFuncionario Inexistente. [Nome: "+nome+"]");
        this.nome = nome;
      }
    
      public String getId(){
        return nome;
      }
}
