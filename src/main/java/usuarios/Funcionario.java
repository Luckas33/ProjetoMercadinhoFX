
package usuarios;

import estoques.Estoque;
import estoques.IEstoque;
import registros.IRegistro;
import registros.Registro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Funcionario implements Serializable {
  //atributos  
    protected String login;
    protected String senha;
    protected String nome;
    protected String email;
    protected transient IEstoque estoque;
    protected transient IRegistro registro;
    private static final long serialVersionUID = 1L;
   
    //construtor
    public Funcionario(IRegistro registro, IEstoque estoque, String nome, String login, String email, String senha){
        this.estoque = estoque;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.registro = registro;
       
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        // Inicialize o campo transient após a desserialização
        this.registro = new Registro();  // Substitua com a inicialização apropriada
        this.estoque = new Estoque();
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
    
   
    

