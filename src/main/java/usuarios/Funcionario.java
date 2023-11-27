
package usuarios;

import estoques.IEstoque;
import registros.IRegistro;

public class Funcionario {
  //atributos  
    protected String login;
    protected String senha;
    protected String nome;
    protected String email;
    protected IEstoque estoque;
    protected IRegistro registro;
   
    //construtor
    public Funcionario(IRegistro registro, IEstoque estoque, String nome, String login, String email, String senha){
        this.estoque = estoque;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.registro = registro;
       
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
    
   
    

