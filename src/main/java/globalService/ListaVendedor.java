package globalService;


import usuarios.Vendedor;
import java.util.Vector;

public class ListaVendedor {
    public static Vector<Vendedor> vededoresVector;

    public ListaVendedor() {
        ListaVendedor.vededoresVector = new Vector<>();
    }

    public static void mostrarLista(){
            System.out.println(ListaVendedor.vededoresVector);
    }

    public static boolean verificarCredenciais(String login, String senha) {
        for (Vendedor vendedor : vededoresVector) {
            if (vendedor.getLogin().equals(login) && vendedor.getSenha().equals(senha)) {
                return true; // Encontrou uma correspondência
            }
        }
        return false;
    }

}
