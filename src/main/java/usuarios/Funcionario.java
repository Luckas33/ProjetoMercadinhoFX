
package usuarios;

import estoques.IEstoque;

public abstract class Funcionario {
  //atributos  
    protected String login;
    protected String senha;
    protected String nome;
    protected IEstoque estoque;
   
    //construtor
    public Funcionario(IEstoque estoque, String nome, String login, String senha){
        this.estoque = estoque;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
       
    }

   
//getters e setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
    
   
    

