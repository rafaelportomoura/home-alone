import java.util.*;

class Player {

    private int vida;
    private Ambiente ambiente;
    private Set<Item> inventario; // Set garante que não tem Item duplicado
    private Configuration configuration;
    private List<Ambiente> ambientesJaBuscados;

    public Player(Ambiente ambiente) {
        this.vida = 100;
        this.ambiente = ambiente;
        inventario = new HashSet<>();
        ambientesJaBuscados = new ArrayList<>();
    }

    public void mover(Ambiente ambiente) {
        this.ambiente = ambiente;
        this.perderVida(Configuration.getConfiguration().getPercaDeVida());
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public String buscar() {
        if (!this.ambientesJaBuscados.contains(this.ambiente)) {
            Item buscado = ambiente.buscar();
            this.perderVida(Configuration.getConfiguration().getPercaDeVida() * 5);
            if (Objects.nonNull(buscado)) {
                // encontrou a dica ou a chave
                inventario.add(buscado);
                if (buscado instanceof Dica) {
                    return "dica";
                } else {
                    return "chave";
                }
            }

            this.ambientesJaBuscados.add(this.ambiente);

            return "";
        }

        return "nao buscou";
    }

    public int getVida() {
        return vida;
    }

    public void perderVida(int dano) {
        this.vida = vida - dano;
        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean getTemChave() {
        boolean temChave = false;

        for (Item i : inventario) {
            if (i instanceof Chave) {
                temChave = true;
            }
        }

        return temChave;
    }

    public boolean getTemDica() {
        boolean temDica = false;

        for (Item i : inventario) {
            if (i instanceof Dica) {
                temDica = true;
            }
        }

        return temDica;
    }
}
