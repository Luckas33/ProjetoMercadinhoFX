package registros;

import java.util.Vector;

import produtos.Produto;
import produtos.ProdutoHistorico;

public interface IRegistro {
    public void registrarAquisicao(String idProduto, double precoCompra, int quantidade);

    public void registrarVenda(String idProduto, double precoVenda, int quantidade);

    public Vector<ProdutoHistorico> retornaRegistro();
}
