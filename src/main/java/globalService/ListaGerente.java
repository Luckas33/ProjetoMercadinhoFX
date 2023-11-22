package globalService;

import usuarios.Gerente;

import java.util.Vector;

public class ListaGerente {
    public static Vector<Gerente> gerentesVector;

    public ListaGerente() {
        ListaGerente.gerentesVector = new Vector<>();
    }

    public static void mostrarLista(){
        System.out.println(ListaGerente.gerentesVector);
    }
    public static boolean verificarCredenciais(String login, String senha) {
        for (Gerente gerente : gerentesVector) {
            if (gerente.getLogin().equals(login) && gerente.getSenha().equals(senha)) {
                return true; // Encontrou uma correspondência
            }
        }
        return false; // Nenhuma correspondência encontrada
    }

}