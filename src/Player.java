
class Player {

    private int vida;
    private Ambiente ambiente;

    public Player(int vida, Ambiente ambiente) {
        this.vida = vida;
        this.ambiente = ambiente;
    }

    public void mover(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void buscar() {
        if (ambiente.getTemChave()) {
            // receber chave
        } else if (ambiente instanceof Dispensa) {
            // habilitar dica
        } else {
            // achou nada
        }
    }

    public int getVida() {
        return vida;
    }

    public void perderVida(int dano) {
        this.vida = vida - dano;
    }
}