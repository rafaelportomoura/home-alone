import java.util.*;


class Player {

    private int vida;
    private Ambiente ambiente;
    private Set<Item> inventario; // Set garante que não tem Item duplicado
    private Configuration configuration;

    public Player(Ambiente ambiente) {
        this.vida = 100;
        this.ambiente = ambiente;
        inventario = new HashSet<>();
    }

    public void mover(Ambiente ambiente) {
        this.ambiente = ambiente;
        this.perderVida(configuration.getConfiguration().getPercaDeVida());
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public String buscar() {
        Item buscado = ambiente.buscar();
        this.perderVida(configuration.getConfiguration().getPercaDeVida() * 5);
        if (Objects.nonNull(buscado)) {
            // encontrou a dica ou a chave
            inventario.add(buscado);
            if (buscado instanceof Dica) {
                return "dica";
            } else {
                return "chave";
            }
        }

        return null;
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
