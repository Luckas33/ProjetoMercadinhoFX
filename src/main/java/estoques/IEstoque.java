
package estoques;


import excecao.PIException;
import excecao.SIException;
import excecao.SNException;
import excecao.VNException;
import produtos.Produto;

public interface IEstoque {
    
    
    public void inserir(String id, int quantidade);
    
    public Produto procurar(String id);
    
    public boolean existe(String id);
    
    public void reduzir(String id, int quantidade) throws PIException;
    
    public void mostrarEstoqueTipo(String tipo);

    public void mostrarEstoqueTotal();
    
    public double verSaldo();
    
    public void definirSaldo(double valor) throws SNException;
    
    
    // **teste**
    public void venderEstoque(double valor) throws VNException;

    public void adquirirEstoque(String Id, double valor) throws SIException;

    public void venderCartaoEstoque(double valor);
}
