package globalService;

import estoques.IEstoque;
import registros.IRegistro;

import java.util.Vector;

public class ListaEstoque {
    private static ListaEstoque instance;

    private Vector<IEstoque> estoqueVector;
    private Vector<IRegistro> registroVector;

    private ListaEstoque() {
        this.estoqueVector = new Vector<>();
        this.registroVector = new Vector<>();
    }

    public static ListaEstoque getInstance() {
        if (instance == null) {
            instance = new ListaEstoque();
        }
        return instance;
    }

    public Vector<IEstoque> getEstoqueVector() {
        return estoqueVector;
    }

    public Vector<IRegistro> getRegistroVector() {
        return registroVector;
    }
}