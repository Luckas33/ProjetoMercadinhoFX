package globalService;

import produtos.Produto;
import produtos.ProdutoComestivel;
import produtos.ProdutoNaoComestivel;

import java.util.Vector;

public class ListaProduto {

    public static Vector<Produto> produtosVector;

    public ListaProduto() {
        produtosVector = new Vector<>();
    }

    public static Produto checarProduto(String id){
        for(Produto produto : ListaProduto.produtosVector) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }
}
