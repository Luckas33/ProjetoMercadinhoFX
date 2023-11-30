package globalService;

import usuarios.Funcionario;
import usuarios.Gerente;
import usuarios.Vendedor;

import java.util.Vector;

public class ListaFuncionario {
    public static Vector<Funcionario> funcionariosVector;

    public ListaFuncionario(){
        ListaFuncionario.funcionariosVector = new Vector<>();
    }

    public static boolean verificarCredenciaisGerente(String login, String senha) {
        for (Funcionario funcionario : funcionariosVector) {
            if(funcionario instanceof Gerente) {
                if (funcionario.getLogin().equals(login) && funcionario.getSenha().equals(senha)) {
                    return true; // Encontrou uma correspondência
                }
            }
        }
        return false; // Nenhuma correspondência encontrada
    }

    public static boolean verificarCredenciaisVendedor(String login, String senha) {
        for (Funcionario funcionario : funcionariosVector) {
            if(funcionario instanceof Vendedor) {
                if (funcionario.getLogin().equals(login) && funcionario.getSenha().equals(senha)) {
                    return true; // Encontrou uma correspondência
                }
            }
        }
        return false;
    }

    public static boolean verificarGerenteExistente(String login){
        if(login == null || login.isEmpty()){
            return false;
        }
        for (Funcionario funcionario : funcionariosVector) {
            if(funcionario instanceof Gerente) {
                if (funcionario.getLogin().equals(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verificarVendedorExistente(String login){
        if(login == null || login.isEmpty()){
            return false;
        }
        for (Funcionario funcionario : funcionariosVector) {
            if(funcionario instanceof Vendedor) {
                if (funcionario.getLogin().equals(login)) {
                    return true; // Encontrou uma correspondência
                }
            }
        }
        return false;
    }
}
