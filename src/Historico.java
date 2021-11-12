
public class Historico implements Comparable<Historico> {
  private int vidaRestante;
  private double tempoGasto;
  private boolean morreu;
  private boolean encontrouAChave;

  public Historico(int vidaRestante, double tempoGasto, boolean encontrouAChave, boolean morreu) {
    this.vidaRestante = vidaRestante;
    this.tempoGasto = tempoGasto;
    this.encontrouAChave = encontrouAChave;
    this.morreu = morreu;
  }

  @Override
  public String toString() {
    return vidaRestante + ";" + tempoGasto + ";" + encontrouAChave + ";" + morreu;
  }

  @Override
  public int compareTo(Historico a) {
    if (this.tempoGasto < a.tempoGasto) {
      return -1;
    } else if (this.tempoGasto > a.tempoGasto) {
      return 1;
    }
    return 0;
  }

}
