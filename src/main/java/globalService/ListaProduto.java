package globalService;

import produtos.Produto;

import java.util.Vector;

public class ListaProduto {

    public static Vector<Produto> produtosVector;

    public ListaProduto() {
        produtosVector = new Vector<>();
    }

    public static void mostrarLista(){
        System.out.println(produtosVector);
    }
    //metodo para checar se o produto existe, usado para quando for ser colocado no estoque
    public static Produto checarProduto(String id){
        for(Produto produto : ListaProduto.produtosVector) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }
}
