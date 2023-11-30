package globalService;

import usuarios.Funcionario;
import usuarios.Gerente;
import usuarios.Vendedor;

import java.util.Vector;

import bancoDados.FileSave;
import excecao.FEException;
import excecao.FIException;

public class ListaFuncionario {
    public static Vector<Funcionario> funcionariosVector;

    public ListaFuncionario(){
        ListaFuncionario.funcionariosVector = new Vector<>();
    }

    public void cadastraFuncionario(Funcionario funcionario) throws FEException{
        if(!existeFuncionario(funcionario)){
            desserializar();
            funcionariosVector.add(funcionario);
            serializar();
        } else {
            throw new FEException(funcionario.getNome());
        }
    }

    public void removeFuncionario(Funcionario funcionario) throws FIException{
        if(!existeFuncionario(funcionario)){
            throw new FIException(funcionario.getNome());
        } else {
            desserializar();
            funcionariosVector.remove(funcionario);
            serializar();
        }
    }

    private boolean existeFuncionario(Funcionario funcionario){
        desserializar();
        for(Funcionario funcionarioTemp: funcionariosVector){
            if(funcionarioTemp.getLogin().equals(funcionario.getLogin())){
                return true;
            }
        }
        return false;
    }

    // removi private static, por enquanto
    public static boolean verificarCredenciaisGerente(String login, String senha) {
        desserializar();
        for (Funcionario funcionario : funcionariosVector) {
            if(funcionario instanceof Gerente) {
                if (funcionario.getLogin().equals(login) && funcionario.getSenha().equals(senha)) {
                    return true; // Encontrou uma correspondência
                }
            }
        }
        return false; // Nenhuma correspondência encontrada
    }

    // removi private static, por enquanto
    public static boolean verificarCredenciaisVendedor(String login, String senha) {
        desserializar();
        for (Funcionario funcionario : funcionariosVector) {
            if(funcionario instanceof Vendedor) {
                if (funcionario.getLogin().equals(login) && funcionario.getSenha().equals(senha)) {
                    return true; // Encontrou uma correspondência
                }
            }
        }
        return false;
    }

    private static void serializar() {
        String caminho = "src/main/java/arquivos/funcionarios.txt";
        FileSave.gravarObjetos(funcionariosVector, caminho);
    }

    private static void desserializar(){
        String caminho = "src/main/java/arquivos/funcionarios.txt";
        try {
            Vector<Funcionario> funcionariosTemp = (Vector<Funcionario>) FileSave.recuperarObjetos(caminho);        // Tem a possibilidade do arquivo não existir
            if (funcionariosTemp != null) {                 // Tem a possibilidade de ser null
                funcionariosVector.clear();                   // Limpa o que já existe, supondo que há a mesma coisa no arquivo
                for(Funcionario funcionario : funcionariosTemp) {
                    funcionariosVector.add(funcionario);          // Adicionando os produtos recuperados do arquivo
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
