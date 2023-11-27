package excecao;

public class QINException extends Exception{
    

    private double quantidade_requerida;

    public QINException(double quantidade_requerida){
        super("\nQuantidade requerida inválida");
        this.quantidade_requerida = quantidade_requerida;
      }

      public double getQuantidadeRequerida(){
        return quantidade_requerida;
      }
}
