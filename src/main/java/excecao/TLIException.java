package excecao;

public class TLIException extends Exception {
    
  private double taxa;

  public TLIException(double taxa){

    super("\nTaxa de lucro inválida");
    this.taxa = taxa;
  }

  public double getTaxa(){
    return taxa;
  }
}
