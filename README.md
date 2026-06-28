# ProjetoMercadoFX

Sistema de mercadinho desenvolvido em JavaFX para exercicios de Programacao Orientada a Objetos e Padroes de Software.

## Objetivo do relatorio

Este relatorio descreve a aplicacao do padrao **Factory** na criacao de funcionarios do sistema, especialmente nas classes `Gerente` e `Vendedor`.

Antes da alteracao, o `CadastrarController` conhecia diretamente as classes concretas e criava os objetos com `new Gerente(...)` e `new Vendedor(...)`. Depois da alteracao, essa responsabilidade passou para `FuncionarioFactory`.

## Estrutura antiga

Na estrutura anterior, a tela de cadastro estava acoplada diretamente as subclasses de `Funcionario`.

```mermaid
classDiagram
    class CadastrarController
    class Main

    class Funcionario {
        #String login
        #String senha
        #String nome
        #String email
        #IEstoque estoque
        #IRegistro registro
        +Funcionario(...)
        +getLogin() String
        +getSenha() String
        +getNome() String
        +getEmail() String
    }

    class Vendedor {
        -double taxaCredito
        +Vendedor(...)
        +venderDinheiro(id, quantidade) void
        +venderDebito(id, quantidade) void
        +venderCredito(id, quantidade, parcelas) void
        +retornaProduto(id) Produto
    }

    class Gerente {
        +Gerente(...)
        +cadastrar(produto, quantidade, taxaLucro) void
        +adicionar(id, quantidade) void
        +removerProduto(id) void
        +inserirSaldo(valor) void
        +removerSaldo(valor) void
        +verBalancoData(data) void
        +verBalancoTotal() void
        +limparTudo() void
    }

    class Produto

    class IEstoque {
        <<interface>>
    }

    class IRegistro {
        <<interface>>
    }

    Vendedor --|> Funcionario
    Gerente --|> Funcionario

    CadastrarController ..> Vendedor : cria diretamente
    CadastrarController ..> Gerente : cria diretamente
    Main ..> Gerente : criava usuario de teste

    Funcionario --> IEstoque : usa
    Funcionario --> IRegistro : usa

    Gerente ..> Produto : manipula
    Vendedor ..> Produto : consulta/vende
```

### Problema identificado

O principal problema era o alto acoplamento entre o controller e as classes concretas:

```java
new Gerente(registro, estoque, nome, login, email, senha);
new Vendedor(registro, estoque, nome, login, email, senha);
```

Com isso, qualquer novo tipo de funcionario exigiria alteracoes diretas no controller. A tela tambem precisava conhecer detalhes de instanciacao das subclasses.

## Estrutura nova com Factory

A nova estrutura adiciona a classe `FuncionarioFactory`, responsavel por decidir qual subtipo de `Funcionario` deve ser criado.

```mermaid
classDiagram
    class CadastrarController
    class Main

    class FuncionarioFactory {
        +criarFuncionario(tipoFuncionario, registro, estoque, nome, login, email, senha) Funcionario
    }

    class Funcionario {
        #String login
        #String senha
        #String nome
        #String email
        #IEstoque estoque
        #IRegistro registro
        +Funcionario(...)
        +getLogin() String
        +getSenha() String
        +getNome() String
        +getEmail() String
    }

    class Vendedor {
        -double taxaCredito
        +Vendedor(...)
        +venderDinheiro(id, quantidade) void
        +venderDebito(id, quantidade) void
        +venderCredito(id, quantidade, parcelas) void
        +retornaProduto(id) Produto
    }

    class Gerente {
        +Gerente(...)
        +cadastrar(produto, quantidade, taxaLucro) void
        +adicionar(id, quantidade) void
        +removerProduto(id) void
        +inserirSaldo(valor) void
        +removerSaldo(valor) void
        +verBalancoData(data) void
        +verBalancoTotal() void
        +limparTudo() void
    }

    class Produto

    class IEstoque {
        <<interface>>
    }

    class IRegistro {
        <<interface>>
    }

    Vendedor --|> Funcionario
    Gerente --|> Funcionario

    CadastrarController ..> FuncionarioFactory : usa
    FuncionarioFactory ..> Gerente : cria
    FuncionarioFactory ..> Vendedor : cria
    FuncionarioFactory ..> Funcionario : retorna

    Main --> IEstoque : inicializa
    Main --> IRegistro : inicializa

    Funcionario --> IEstoque : usa
    Funcionario --> IRegistro : usa

    Gerente ..> Produto : manipula
    Vendedor ..> Produto : consulta/vende
```

## Implementacao aplicada

A classe criada foi:

```java
public final class FuncionarioFactory {
    private FuncionarioFactory() {
    }

    public static Funcionario criarFuncionario(
        String tipoFuncionario,
        IRegistro registro,
        IEstoque estoque,
        String nome,
        String login,
        String email,
        String senha
    ) {
        if ("Gerente".equals(tipoFuncionario)) {
            return new Gerente(registro, estoque, nome, login, email, senha);
        }
        if ("Vendedor".equals(tipoFuncionario)) {
            return new Vendedor(registro, estoque, nome, login, email, senha);
        }
        throw new IllegalArgumentException("Tipo de funcionario invalido: " + tipoFuncionario);
    }
}
```

O `CadastrarController` passou a solicitar um `Funcionario` para a fabrica:

```java
Funcionario funcionarioObj = FuncionarioFactory.criarFuncionario(
    tipoFuncionario,
    registro,
    estoque,
    nome,
    login,
    email,
    senha
);
```

## Resultado

A criacao de `Gerente` e `Vendedor` foi centralizada em uma unica classe. Com isso, o controller deixa de instanciar diretamente as subclasses e passa a depender de uma abstracao de criacao.

Ganhos obtidos:

- Reducao do acoplamento entre `CadastrarController`, `Gerente` e `Vendedor`.
- Centralizacao da regra de criacao de funcionarios.
- Retorno polimorfico por meio da superclasse `Funcionario`.
- Maior facilidade para adicionar novos tipos de funcionario no futuro.

## Observacao sobre o Main

A criacao do usuario de teste que existia no `Main` foi removida. Atualmente o `Main` fica responsavel apenas por inicializar os servicos principais, registrar `Estoque` e `Registro` nas listas globais e iniciar a aplicacao JavaFX.

## Tecnologias utilizadas

- Java
- JavaFX
- Maven
- SceneBuilder
