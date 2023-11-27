
package estoques;


import excecao.PIException;
import excecao.SNException;
import produtos.Produto;
import java.util.Vector;

public interface IEstoque {
    
    
    public void inserir(Produto produto, int quantidade);
    
    public Produto procurar(String id);
    
    public boolean existe(String id);
    
    public void reduzir(String id, int quantidade) throws PIException;
    
    public void mostrarEstoqueTipo(String tipo);

   public Produto mostrarEstoqueTotal();

    public Vector<Produto> retornaEstoque();
    
    public double verSaldo();
    
    public void definirSaldo(double valor) throws SNException;

    

}
