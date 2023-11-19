package excecao;

public class TLNException extends Exception {
    
  private double taxa;

  public TLNException(double taxa){

    super("\nTaxa de lucro negativa");
    this.taxa = taxa;
  }

  public double getTaxa(){
    return taxa;
  }
}
