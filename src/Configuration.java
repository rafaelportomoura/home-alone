import java.util.HashMap;

public class Configuration {
  private static Configuration config;

  // Quantidade de vida que o usuário perde a cada segundo
  private int PERCA_DE_VIDA;

  // Tempo para o usuário mover de um ambiente ao outro sem buscar
  private int TEMPO_POR_AMBIENTE;

  // Tempo para o usuário mover de um ambiente ao outro buscando pela chave
  private int TEMPO_DE_BUSCA;

  private String DIFICULDADE;

  private HashMap<Integer, Ambiente> AMBIENTES;

  private Configuration(String dificuldade) {
    criarAmbientes();
    determinarDificuldade(dificuldade);
    TEMPO_POR_AMBIENTE = 1;
    TEMPO_DE_BUSCA = 5;
  }

  public static Configuration getConfiguration(String dificuldade) {
    if (config == null) {
      config = new Configuration(dificuldade);
    }
    return config;
  }

  private void determinarDificuldade(String dificuldade) {
    switch (dificuldade) {
    case "dificil":
      this.PERCA_DE_VIDA = 3;
      break;
    default:
      this.PERCA_DE_VIDA = 2;
    }
  }

  private void criarAmbientes() {
    AMBIENTES = new HashMap<Integer, Ambiente>();

    AMBIENTES.put(1, new Comodo("Cozinha", new int[] { 2, 4 }));
    AMBIENTES.put(2, new Comodo("Lavanderia", new int[] { 1 }));
    AMBIENTES.put(3, new Comodo("Banheiro", new int[] { 5 }));
    AMBIENTES.put(4, new Comodo("Sala de Jantar", new int[] { 5 }));
    AMBIENTES.put(5, new Comodo("Sala de estar", new int[] { 3, 4, 7 }));
    AMBIENTES.put(6, new Comodo("Sala de TV", new int[] { 7 }));
    AMBIENTES.put(7, new Comodo("Corredor 1", new int[] { 5, 6, 7 }));
    AMBIENTES.put(8, new Comodo("Hall de Entrada", new int[] { 7, 10 }));
    AMBIENTES.put(9, new Comodo("Quarto", new int[] { 10, 12 }));
    AMBIENTES.put(10, new Comodo("Corredor 2", new int[] { 8, 9, 11 }));
    AMBIENTES.put(11, new Comodo("Escritório", new int[] { 10, 13 }));
    AMBIENTES.put(12, new Comodo("Banheiro", new int[] { 9 }));
    int ambienteQueTeraAChave = determinaAmbienteComChave();
    AMBIENTES.put(13, new Dispensa("Dispensa", ambienteQueTeraAChave,new int[] { 11 }));

  }

  private int determinaAmbienteComChave() {
    int ambienteQueTeraAChave = (int) ((Math.random() * (2 - 12)) + 2);

    Ambiente ambiente = AMBIENTES.get(ambienteQueTeraAChave);

    Comodo comChave = (Comodo) ambiente;

    comChave.setChave();

    return ambienteQueTeraAChave;
  }

  public Ambiente getAmbiente(int ambienteId) {
    if (AMBIENTES.containsKey(ambienteId)) {
      return AMBIENTES.get(ambienteId);
    }

    return null;
  }

  public int getPercaDeVida() {
    return PERCA_DE_VIDA;
  }

  public int getTempoPorAmbiente() {
    return TEMPO_POR_AMBIENTE;
  }

  public int getTempoDeBusca() {
    return TEMPO_DE_BUSCA;
  }

  public String getDificuldade() {
    return DIFICULDADE;
  }

}
