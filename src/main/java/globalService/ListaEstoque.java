package globalService;

import estoques.IEstoque;

import java.util.Vector;

public class ListaEstoque {
    public static Vector<IEstoque> estoqueVector;

    public ListaEstoque(){
        ListaEstoque.estoqueVector = new Vector<>();
    }

}
