package excecao;

public class TLNUException extends Exception{
    

  private double taxa;

  public TLNUException(double taxa){

    super("\nTaxa de lucro nula");
    this.taxa = taxa;
  }

  
  public double getTaxa(){
    return taxa;
  }
}
