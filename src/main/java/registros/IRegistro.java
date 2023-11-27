package registros;

import java.util.Vector;

import produtos.Produto;
import produtos.ProdutoHistorico;

public interface IRegistro {
    public void registrarAquisicao(Produto produto, int quantidade);

    public void registrarVenda(Produto produto, int quantidade);

    public Vector<ProdutoHistorico> getRegistro();
}
