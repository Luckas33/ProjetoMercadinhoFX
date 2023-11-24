package globalService;


import usuarios.Vendedor;
import java.util.Vector;

public class ListaVendedor {
    public static Vector<Vendedor> vendedoresVector;

    public ListaVendedor() {
        ListaVendedor.vendedoresVector = new Vector<>();
    }

    public static void mostrarLista(){
            System.out.println(ListaVendedor.ven'dedoresVector);
    }

    public static boolean verificarCredenciais(String login, String senha) {
        for (Vendedor vendedor : vendedoresVector) {
            if (vendedor.getLogin().equals(login) && vendedor.getSenha().equals(senha)) {
                return true; // Encontrou uma correspondência
            }
        }
        return false;
    }
}
