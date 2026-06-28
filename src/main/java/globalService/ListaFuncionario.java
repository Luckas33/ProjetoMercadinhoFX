package globalService;

import usuarios.Funcionario;
import usuarios.Gerente;
import usuarios.Vendedor;
import java.util.Vector;
import bancoDados.FileSave;
import excecao.FEException;
import excecao.FIException;

public class ListaFuncionario {
    
    private static ListaFuncionario instance;

    private Vector<Funcionario> funcionariosVector;

    private ListaFuncionario() {
        this.funcionariosVector = new Vector<>();
    }

    public static ListaFuncionario getInstance() {
        if (instance == null) {
            instance = new ListaFuncionario();
        }
        return instance;
    }

    public void cadastraFuncionario(Funcionario funcionario) throws FEException {
        if(!existeFuncionario(funcionario)){
            desserializar();
            this.funcionariosVector.add(funcionario);
            serializar();
        } else {
            throw new FEException(funcionario.getNome());
        }
    }

    public void removeFuncionario(Funcionario funcionario) throws FIException {
        if(!existeFuncionario(funcionario)){
            throw new FIException(funcionario.getNome());
        } else {
            desserializar();
            this.funcionariosVector.remove(funcionario);
            serializar();
        }
    }

    private boolean existeFuncionario(Funcionario funcionario) {
        desserializar();
        for(Funcionario funcionarioTemp: this.funcionariosVector){
            if(funcionarioTemp.getLogin().equals(funcionario.getLogin())){
                return true;
            }
        }
        return false;
    }

    public boolean verificarCredenciaisGerente(String login, String senha) {
        desserializar();
        for (Funcionario funcionario : this.funcionariosVector) {
            if(funcionario instanceof Gerente) {
                if (funcionario.getLogin().equals(login) && funcionario.getSenha().equals(senha)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarCredenciaisVendedor(String login, String senha) {
        desserializar();
        for (Funcionario funcionario : this.funcionariosVector) {
            if(funcionario instanceof Vendedor) {
                if (funcionario.getLogin().equals(login) && funcionario.getSenha().equals(senha)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarGerenteExistente(String login) {
        desserializar();
        if(login == null || login.isEmpty()){
            return false;
        }
        for (Funcionario funcionario : this.funcionariosVector) {
            if(funcionario instanceof Gerente) {
                if (funcionario.getLogin().equals(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarVendedorExistente(String login) {
        desserializar();
        if(login == null || login.isEmpty()){
            return false;
        }
        for (Funcionario funcionario : this.funcionariosVector) {
            if(funcionario instanceof Vendedor) {
                if (funcionario.getLogin().equals(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Vector<Funcionario> getFuncionariosVector() {
        return this.funcionariosVector;
    }

    private void serializar() {
        String caminho = "src/main/java/arquivos/funcionarios.txt";
        FileSave.gravarObjetos(this.funcionariosVector, caminho);
    }

    @SuppressWarnings("unchecked")
    private void desserializar() {
        String caminho = "src/main/java/arquivos/funcionarios.txt";
        try {
            Vector<Funcionario> funcionariosTemp = (Vector<Funcionario>) FileSave.recuperarObjetos(caminho);
            if (funcionariosTemp != null) {
                this.funcionariosVector.clear();
                for(Funcionario funcionario : funcionariosTemp) {
                    this.funcionariosVector.add(funcionario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
