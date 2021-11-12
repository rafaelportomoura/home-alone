
public class Historico implements Comparable<Historico> {
  private int vidaRestante;
  private double tempoGasto;
  private boolean morreu;
  private boolean encontrouAChave;

  public Historico(int vidaRestante, int tempoGasto, boolean encontrouAChave, boolean morreu) {
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

  public int getVidaRestante() {
    return vidaRestante;
  }

  public double getTempoGasto() {
    return tempoGasto;
  }

  public boolean isMorreu() {
    return morreu;
  }

  public boolean isEncontrouAChave() {
    return encontrouAChave;
  }

  public String rankingTemplate() {
    String linhaVidaRestante = "Vida restante: " + vidaRestante + "\n";
    String linhaTempoGasto = "Tempo Gasto: " + tempoGasto + " (Segundos) \n";
    String linhaMorreu = morreu ? "Jogador morreu" : "Jogador não morreu" + "\n";
    String linhaEncontrouAChave = encontrouAChave ? "Jogador encontrou a chave"
        : "Jogador não encontrou a chave" + "\n";
    return linhaVidaRestante + linhaTempoGasto + linhaMorreu + linhaEncontrouAChave;
  }

}
