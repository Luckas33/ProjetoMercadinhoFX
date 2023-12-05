
package usuarios;

import com.programa.projetomercadofx.controllerUtil.Alerts;
import estoques.IEstoque;
import javafx.scene.control.Alert;
import registros.IRegistro;
import excecao.*;
import produtos.Produto;
import produtos.ProdutoComestivel;
import produtos.ProdutoHistorico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import bancoDados.FileSave;


public class Gerente extends Funcionario {
    
    //construtor
    public Gerente(IRegistro registro,IEstoque estoque, String nome, String login, String email, String senha) {
        super(registro, estoque, nome, login, email, senha);
    }


    // *****teste*****//
    //metodo para cadastrar um produto no estoque, checa se o produto ja existe no estoque, se não existir
    //ele cadastra
    public void cadastrar(Produto produto, int quantidade, double taxaLucro) throws PEException, SIException, QINException, DVIException {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
        if(quantidade>0){
            if(produto != null){ 
                if(!this.estoque.existe(produto.getId())){//calcula o valor total da compra
                    double valorTotal = (quantidade * produto.getPreco_compra());

                    if(this.estoque.verSaldo() >= valorTotal) {//checa se o saldo do mercado é maior que o preço da compra

                        if (produto instanceof ProdutoComestivel) {
                            LocalDate data = LocalDate.parse(((ProdutoComestivel) produto).getValidade(), formatador);
                            if (date.isBefore(data)) {
                                cadastroIntermediario(produto, taxaLucro, valorTotal, quantidade);    
                            }
                            else
                                throw new DVIException(produto.getId(), data);
                        }else{
                            cadastroIntermediario(produto, taxaLucro, valorTotal, quantidade);
                        }
                    }else{
                        throw new SIException(this.estoque.verSaldo()); //restrição de saldo insuficiente para adquirir novos produtos
                    }
                }else{
                    throw new PEException(produto.getId()); //restricao se o produto ja existe
                }
            }
        }else{ 
            throw new QINException(quantidade);//restriçao se a quantidade for inválida
        }
    }

    private void cadastroIntermediario(Produto produto, double taxaLucro, double valorTotal, int quantidade){
        try {
            produto.setTaxaLucro(taxaLucro);  //seta a taxa de lucro que esse produto vai ter
        } catch (TLIException e) { // testa se a taxa lucro é inválida
            System.out.print(e.getMessage());
            System.out.print(" Taxa: ");
            System.out.print(taxaLucro); // **n seria taxaLucro??**
        }
        double precoFinal = (produto.getPreco_compra() * (produto.getTaxaLucro() / 100)) + produto.getPreco_compra();
        produto.setPrecoVenda(precoFinal); //aqui ele seta o preço de venda, sendo a multiplicação do preço de compra pela taxa de lucro
        this.estoque.inserir(produto, quantidade); //ele chama o metodo inserir da interface de estoque, onde ele insere o produto e a quantidade no estoque
        try {
            this.estoque.definirSaldo(this.estoque.verSaldo() - valorTotal); //atualiza o saldo
        } catch (SIException e) {
            e.printStackTrace();
        }
        this.registro.registrarAquisicao(produto.getId(), valorTotal, quantidade);
    }


    //metodo para adicionar mais produtos no estoque, caso ele ja esteja cadastrado
    //se nao tiver cadastrado, nao vai adicionar
    //esqueci de salvar esse método sem o método adquirirEstoque em vez de definirSaldo
      public void adicionar(String id, int quantidade) throws SIException, PIException, QINException, DVIException {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
        Produto produto = this.estoque.procurar(id);
        if (quantidade>0){
        if(produto != null){
            double valorTotal = (quantidade * produto.getPreco_compra());  //valor da compra
            if(this.estoque.existe(produto.getId())) {

                if (this.estoque.verSaldo() >= valorTotal) {//checa o saldo

                    if (produto instanceof ProdutoComestivel){
                        LocalDate data = LocalDate.parse(((ProdutoComestivel) produto).getValidade(), formatador);
                    if (date.isBefore(data)){

                        this.estoque.inserir(produto, quantidade);  //insere no estoque
                    try {
                        this.estoque.definirSaldo(this.estoque.verSaldo() - valorTotal); //atualiza o saldo
                    } catch (SIException e) {
                        e.printStackTrace();
                    }
                    this.registro.registrarAquisicao(produto.getId(), valorTotal, quantidade);
                }
                else
                    throw new DVIException(id, data);
            }
                    else{
                        this.estoque.inserir(produto, quantidade);  //insere no estoque
                        try {
                            this.estoque.definirSaldo(this.estoque.verSaldo() - valorTotal); //atualiza o saldo
                        } catch (SIException e) {
                            e.printStackTrace();
                        }
                        this.registro.registrarAquisicao(produto.getId(), valorTotal, quantidade);
                    }
            }
               else{
                   throw new SIException(this.estoque.verSaldo()); // restrição se o cara nao tem saldo o suficiente para bancar o a compra de novos produtos
               }
            }
            else{
                throw new PIException(produto.getId()); // restricao se o produto nao foi cadastrado ainda
            }
        }
        }else
            throw new QINException(quantidade);
    }

   //ver o balanço financeiro pela data, escreve uma data e mostra todos os produtos vendidos nesse dia, quanto entrou, quanto saiu 
    public void verBalancoData(String data){
          double ganho = 0.0; 
          double perda = 0.0;
       
          Vector<ProdutoHistorico> historico = registro.retornaRegistro();
        
         for(ProdutoHistorico produto : historico){ 
            if(data.equals(produto.getData())){  //pega todos os produtos que foram vendidos/comprados na data escrita
             System.out.println(produto);  //printa todos eles
             if(produto.getForma() == "Venda"){
             ganho += produto.getPreco();  //calcula quanto ganhou
             }
             else{
                 perda += produto.getPreco();  //calcula quanto perdeu
             }
            }
         }
         double balanco = ganho - perda;
         System.out.println("Saiu: " + perda + "\n" + "Entrou: " + ganho);  //mpstra os ganhos e perdas
         System.out.printf("Balanço final: $%.2f\n", balanco); //mostra o balanço do dia (sem contar o saldo setado inicialmente, so o quanto foi comprado e vendido)
      }

    public void limparTudo(){
        FileSave.apagarObjetos("src/main/java/arquivos/registro.txt");
        FileSave.apagarObjetos("src/main/java/arquivos/estoque.txt");       
    }

    //mostra todos os produtos vendidos e comprados, mesmo funcionamento do anterior
    public void verBalancoTotal(){
          double ganho = 0.0;
          double perda = 0.0;
          Vector<ProdutoHistorico> historico = registro.retornaRegistro();
        
         for(ProdutoHistorico produto : historico){ 
            System.out.println(produto);
            if(produto.getForma().equals("Venda")){
               ganho += produto.getPreco();
            }
            else{
               perda += produto.getPreco();
            }
            
         }
         double balanco = ganho - perda;
         System.out.println("Saiu: " + perda + "\n" + "Entrou: " + ganho);
         System.out.printf("Balanço final: $%.2f\n", balanco);
         System.out.println("Saldo do mercado: " + this.estoque.verSaldo());
         
      }

    //metodo para setar o saldo inicial do mercado
    public void inserirSaldo(double valor) throws SIException{
        if(valor > 0){
            this.estoque.definirSaldo(valor + this.estoque.verSaldo());
        }else{
            throw new SIException(this.estoque.verSaldo());
        }
    }

    public void removerSaldo(double valor){
        try {
            this.estoque.definirSaldo(this.estoque.verSaldo() - valor);
            Alerts.showAlert("Saque", null, "Saque concluído", Alert.AlertType.INFORMATION);
        }catch (SIException e){
            System.out.print(e.getMessage());
            System.out.print(" Saldo: ");
            System.out.print(valor);
            Alerts.showAlert("Saque Erro", null, "Saque inválido", Alert.AlertType.INFORMATION);
        }

    }

    public void atualizarTaxa(String id, double taxa){
        Produto produto = this.estoque.procurar(id);
        if(produto != null){
            try{
                produto.setTaxaLucro(taxa);
                Double valorNovo = (produto.getPreco_compra() * (taxa/100)) + produto.getPreco_compra();
                produto.setPrecoVenda(valorNovo);
            }catch(TLIException e){
                e.printStackTrace();
            }
        }
    }

    public void atualizaPrecoCompra(String id, double precoNovo){
        Produto produto = this.estoque.procurar(id);
        if(produto != null){
            try{
                produto.setPrecoCompra(precoNovo);
            }catch(PCIException e){
                e.printStackTrace();
            }
        }
    }

    public void conferirSaldo(){
        System.out.println(this.estoque.verSaldo());
    }
    public String retornaSaldo(){
        String saldoAtual = String.valueOf(this.estoque.verSaldo());
        return saldoAtual;
    }

    public void removerProduto(String id) throws PIException {
        Produto produto = this.estoque.procurar(id);
        if(produto != null) {
            this.estoque.remove(produto);
        }else{
            throw new PIException(id);
        }
    }
    @Override
    public String toString() {
        return "Gerente: " + "\n" + "Nome: " + nome + "\n" + "Login: " + login + "\n" + "Email: " + email + "\n" +"Senha: " + senha + "\n";
    }
}
      
