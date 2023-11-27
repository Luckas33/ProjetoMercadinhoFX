package registros;

import java.util.Vector;

import produtos.Produto;
import produtos.ProdutoHistorico;

public class Registro implements IRegistro{
    private Vector<ProdutoHistorico> registro;
    
    // registra um produto adquirido para o estoque
    @Override
    public void registrarAquisicao(Produto produto, int quantidade){
        ProdutoHistorico registroProduto = new ProdutoHistorico(produto.getId(),  (quantidade * produto.getPreco_compra()), quantidade);
        registroProduto.setForma("Compra");
        registro.add(registroProduto);
    }

    // registra um produto vendido do estoque
    @Override
    public void registrarVenda(Produto produto, int quantidade){
        ProdutoHistorico registroProduto = new ProdutoHistorico(produto.getId(),  (quantidade * produto.getPrecoVenda()), quantidade);
        registroProduto.setForma("Venda");
        registro.add(registroProduto);
    }

    public Vector<ProdutoHistorico> getRegistro(){
        return registro;
    }
}
