public class Comodo extends Ambiente {

    private Boolean temChave;

    public Comodo(String nome, int[] saidas) {
        super(nome, saidas);
        this.temChave = false;
    }

    @Override
    public Item buscar() {
        return temChave ? new Chave() : null;
    }

    public void setChave() {
        this.temChave = true;
    }

}
