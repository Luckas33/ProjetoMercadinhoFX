package produtos;

public class ProdutoComestivel extends Produto {

    //atributos
      private String validade;

    //construtor
    public ProdutoComestivel(String nome, String id, String marca, double preco_compra, String tipo, String validade) {
        super(nome, id, marca, preco_compra, tipo);
        this.quantidade = 0;
        this.validade = validade;
        this.taxaLucro = 0.0;
        this.preco_venda = 0.0;
        this.quantidadeVendida = 0;
    }

    //to string mas dessa vez mostrando a data de validade
    @Override
    public String toString() {
        return "Produto:" +  "\n" + "Nome: " + nome + "\n" + "ID: " + id + "\n" + "Marca: " + marca + "\n" +
        "Preço: " + preco_compra + "\n" + "Tipo: " + tipo + "\n" +
        "Quantidade: " + quantidade + "\n" + "Validade: " + validade + "\n";
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    
}
