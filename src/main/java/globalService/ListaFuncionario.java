package globalService;

import usuarios.Gerente;
import usuarios.Vendedor;

import java.util.Vector;

public class ListaFuncionario {
    public static Vector<Gerente> gerentesVector;
    public static Vector<Vendedor> vendedoresVector;

    public ListaFuncionario(){
        ListaFuncionario.gerentesVector = new Vector<>();
        ListaFuncionario.vendedoresVector = new Vector<>();
    }

    public static boolean verificarCredenciaisGerente(String login, String senha) {
        for (Gerente gerente : gerentesVector) {
            if (gerente.getLogin().equals(login) && gerente.getSenha().equals(senha)) {
                return true; // Encontrou uma correspondência
            }
        }
        return false; // Nenhuma correspondência encontrada
    }

    public static boolean verificarCredenciaisVendedor(String login, String senha) {
        for (Vendedor vendedor : vendedoresVector) {
            if (vendedor.getLogin().equals(login) && vendedor.getSenha().equals(senha)) {
                return true; // Encontrou uma correspondência
            }
        }
        return false;
    }
}
