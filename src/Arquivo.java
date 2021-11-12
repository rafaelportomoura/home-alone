import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
  private String arquivoDeHistorico;

  public Arquivo() {
    Configuration conf = Configuration.getConfiguration();
    arquivoDeHistorico = conf.getArquivoDeHistorico();
  }

  public void salvarHistorico(Historico historico) {
    try (FileWriter arquivo = new FileWriter(arquivoDeHistorico)) {
      arquivo.write(historico.toString());
    } catch (FileNotFoundException e) {
      System.out.println("Problema na abertura do arquivo de historico");
    } catch (IOException e) {
      System.out.println("Falha ao salvar histórico do jogo" + arquivoDeHistorico);
    }
  }

  public List<Historico> retornaRanking() {

    List<Historico> ranking = new ArrayList<Historico>();
    try (BufferedReader arquivo = new BufferedReader(new FileReader(arquivoDeHistorico))) {
      List<Historico> historicos = new ArrayList<Historico>();
      String linha = arquivo.readLine();
      while (linha != null) {
        String[] campos = linha.split(";");
        int vidaRestante = Integer.parseInt(campos[0]);
        double tempoGasto = Double.parseDouble(campos[1]);
        boolean encontrouAChave = Boolean.parseBoolean(campos[2]);
        boolean morreu = Boolean.parseBoolean(campos[3]);
        Historico historicoLido = new Historico(vidaRestante, tempoGasto, encontrouAChave, morreu);
        historicos.add(historicoLido);
      }
      Collections.sort(historicos);
      ranking.add(historicos.get(0));
      ranking.add(historicos.get(1));
      ranking.add(historicos.get(2));
    } catch (FileNotFoundException e) {
      System.out.println("Problema na abertura do arquivo de historico");
    } catch (IOException e) {
      System.out.println("Problema na leitura do arquivo de historico");
    }
    return ranking;
  }
}
