import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
  private String arquivoDeHistorico;
  List<Historico> historicos;

  public Arquivo() {
    Configuration conf = Configuration.getConfiguration();
    arquivoDeHistorico = conf.getArquivoDeHistorico();
    historicos = new ArrayList<Historico>();
  }

  public void salvarChave(String comodo) {
    try (FileWriter arquivo = new FileWriter("comodoComChave.txt")) {
      arquivo.write(comodo);
    } catch (FileNotFoundException e) {
      System.out.println("Problema na abertura do arquivo de chave");
    } catch (IOException e) {
      System.out.println("Falha ao salvar histórico de chave" + arquivoDeHistorico);
    }

  }

  public void salvarHistorico(Historico historico) {
    lerHistorico();
    try (FileWriter arquivo = new FileWriter(arquivoDeHistorico)) {
      for (Historico historicoDalista : historicos) {
        arquivo.write(historicoDalista.toString());
      }
      arquivo.write(historico.toString());
    } catch (FileNotFoundException e) {
      System.out.println("Problema na abertura do arquivo de historico");
    } catch (IOException e) {
      System.out.println("Falha ao salvar histórico do jogo" + arquivoDeHistorico);
    }
  }

  public void lerHistorico() {
    try (BufferedReader arquivo = new BufferedReader(new FileReader(arquivoDeHistorico))) {
      String linha = arquivo.readLine();
      while (linha != null) {
        String[] campos = linha.split(";");
        int vidaRestante = Integer.parseInt(campos[0]);
        int tempoGasto = Integer.parseInt(campos[1]);
        boolean encontrouAChave = Boolean.parseBoolean(campos[2]);
        boolean morreu = Boolean.parseBoolean(campos[3]);
        Historico historicoLido = new Historico(vidaRestante, tempoGasto, encontrouAChave, morreu);
        historicos.add(historicoLido);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Problema na abertura do arquivo de historico");
    } catch (IOException e) {
      System.out.println("Problema na leitura do arquivo de historico");
    }
  }

  public List<Historico> retornaRanking() {
    lerHistorico();
    List<Historico> ranking = new ArrayList<Historico>();
    Collections.sort(historicos);
    ranking.add(historicos.get(0));
    ranking.add(historicos.get(1));
    ranking.add(historicos.get(2));
    return ranking;
  }
}
