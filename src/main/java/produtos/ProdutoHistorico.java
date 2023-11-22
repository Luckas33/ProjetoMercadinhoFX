package produtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoHistorico {
    //Atributos
    private String idVenda;
    private double preco;
    private int quantidadeVendida;
    private String forma;
    private String data;

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //Construtor
    public ProdutoHistorico(String idVenda, double preco, int quantidadeVendida) {
        this.idVenda = idVenda;
        this.preco = preco;
        this.quantidadeVendida = quantidadeVendida;
        this.data = date.format(formatador);
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
        return "Produto:" + "\n" + "ID: " + idVenda + "\n" + "Preço: " + preco + "\n" + "Quantidade: " + quantidadeVendida
                + "\n" + "Forma: " + forma + "\n" + "Data: " + data + "\n";
    }

    public String getData() {
        return data;
    }

    

    
}
