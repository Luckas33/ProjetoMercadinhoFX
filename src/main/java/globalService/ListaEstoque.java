package globalService;

import estoques.IEstoque;
import registros.IRegistro;

import java.util.Vector;

public class ListaEstoque {
    public static Vector<IEstoque> estoqueVector;
    public static Vector<IRegistro> registroVector;


    public ListaEstoque(){
        ListaEstoque.estoqueVector = new Vector<>();
        ListaEstoque.registroVector = new Vector<>();
    }

}
