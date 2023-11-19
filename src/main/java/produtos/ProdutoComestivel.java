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
    }

    //to string mas dessa vez mostrando a data de validade
    public String CustomtoString() {
        return "Produto{" + "nome=" + nome + ", id=" + id + ", marca=" + marca +
                ", preco=" + preco_compra + ", tipo=" + tipo +
                ", quantidade=" + quantidade + ", validade=" + validade + '}';
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    
}
