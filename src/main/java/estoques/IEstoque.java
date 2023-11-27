
package estoques;

import excecao.*;
import produtos.Produto;

public interface IEstoque {
    
    
    public void inserir(Produto produto, int quantidade);
    
    public Produto procurar(String id);
    
    public boolean existe(String id);
    
    public void reduzir(String id, int quantidade) throws PIException, QINException;
    
    public void mostrarEstoqueTipo(String tipo);

    public void mostrarEstoqueTotal();
    
    public double verSaldo();
    
    public void definirSaldo(double valor) throws SNException;
    

}
