
package estoques;


import excecao.PIException;
import excecao.QINException;
import excecao.SIException;
import excecao.SNException;
import produtos.Produto;
import observador.Observer;

import java.util.Vector;

public interface IEstoque extends Observer{
    
    
    public void inserir(Produto produto, int quantidade);
    
    public Produto procurar(String id);

    public void remove(Produto produto);
    
    public boolean existe(String id);
    
    public void reduzir(String id, int quantidade) throws PIException, QINException;


    public Vector<Produto> retornaEstoque();
    
    public double verSaldo();
    
    public void definirSaldo(double valor) throws SNException;
    

}
