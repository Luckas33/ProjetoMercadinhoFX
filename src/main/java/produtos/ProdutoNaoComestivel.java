
package produtos;

public class ProdutoNaoComestivel extends Produto {
    
    public ProdutoNaoComestivel(String nome, String id, String marca, double preco_compra, String tipo) {
        super(nome, id, marca, preco_compra, tipo);
        this.quantidade = 0;
        this.taxaLucro = 0.0;
        this.preco_venda = 0.0;
        this.quantidadeVendida = 0;
    }
    
}
