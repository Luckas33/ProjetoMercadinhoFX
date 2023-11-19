package excecao;

public class QNException extends Exception{
    

    private double quantidade_requerida;

    public QNException(double quantidade_requerida){
        super("\nQuantidade requerida negativa");
        this.quantidade_requerida = quantidade_requerida;
      }

      public double getQuantidadeRequerida(){
        return quantidade_requerida;
      }
}
