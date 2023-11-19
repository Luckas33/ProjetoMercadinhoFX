package produtos;

import java.time.LocalDate;

public class ProdutoHistorico {
    //Atributos
    private String idVenda;
    private double preco;
    private int quantidadeVendida;
    private String forma;
    private LocalDate data;

    //Construtor
    public ProdutoHistorico(String idVenda, double preco, int quantidadeVendida) {
        this.idVenda = idVenda;
        this.preco = preco;
        this.quantidadeVendida = quantidadeVendida;
        this.data = LocalDate.now();
    }
    
    //Métodos
    public String getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(String idVenda) {
        this.idVenda = idVenda;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }


    @Override
    public String toString() {
        return "Produto: [ID=" + idVenda + ", preço=" + preco + ", quantidade=" + quantidadeVendida
                + ", forma=" + forma + ", data=" + data + "]";
    }

    public LocalDate getData() {
        return data;
    }

    

    
}
