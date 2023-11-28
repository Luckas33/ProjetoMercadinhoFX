package registros;

import java.util.Vector;

import bancoDados.FileSave;
import produtos.ProdutoHistorico;

public class Registro implements IRegistro{
    private Vector<ProdutoHistorico> registro;
    
    // registra um produto adquirido para o estoque

    public Registro(){
        this.registro = new Vector<>();
    }

    @Override
    public void registrarAquisicao(String idProduto, double precoCompra, int quantidade){
        desserializar();
        ProdutoHistorico registroProduto = new ProdutoHistorico(idProduto, precoCompra, quantidade);
        registroProduto.setForma("Compra");
        registro.add(registroProduto);
        serializar();
    }

    // registra um produto vendido do estoque
    @Override
    public void registrarVenda(String idProduto, double precoVenda, int quantidade){
        desserializar();
        ProdutoHistorico registroProduto = new ProdutoHistorico(idProduto,  precoVenda, quantidade);
        registroProduto.setForma("Venda");
        registro.add(registroProduto);
        serializar();
    }

    public Vector<ProdutoHistorico> retornaRegistro(){
        desserializar();
        return registro;
    }

    private void serializar() {
        String caminho = "src/main/java/arquivos/registro.txt";
        FileSave.gravarObjetos(registro, caminho);
    }

    private void desserializar(){
        String caminho = "src/main/java/arquivos/registro.txt";
        try {
            Vector<ProdutoHistorico> registrosTemp = (Vector<ProdutoHistorico>) FileSave.recuperarObjetos(caminho);        // Tem a possibilidade do arquivo não existir
            if (registrosTemp != null) {                 // Tem a possibilidade de ser null
                this.registro.clear();                   // Limpa o que já existe, supondo que há a mesma coisa no arquivo
                for(ProdutoHistorico registroProduto : registrosTemp) {
                    this.registro.add(registroProduto);          // Adicionando os produtos recuperados do arquivo
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
