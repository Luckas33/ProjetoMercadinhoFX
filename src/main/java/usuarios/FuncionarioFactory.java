package usuarios;

import estoques.IEstoque;
import registros.IRegistro;

public final class FuncionarioFactory {
    private FuncionarioFactory() {
    }

    public static Funcionario criarFuncionario(String tipoFuncionario, IRegistro registro, IEstoque estoque, String nome, String login, String email, String senha) {
        if ("Gerente".equals(tipoFuncionario)) {
            return new Gerente(registro, estoque, nome, login, email, senha);
        }
        if ("Vendedor".equals(tipoFuncionario)) {
            return new Vendedor(registro, estoque, nome, login, email, senha);
        }
        throw new IllegalArgumentException("Tipo de funcionario invalido: " + tipoFuncionario);
    }
}
