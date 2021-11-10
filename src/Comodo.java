import java.util.List;

public class Comodo extends Ambiente {

    private Boolean temChave;

    public Comodo(String nome) {
        super(nome);
        this.temChave = false;
    }

    public Comodo(String nome, Boolean temChave) {
        super(nome);
        this.temChave = temChave;
    }

    @Override
    public Item buscar() {
        return temChave ? new Chave() : null;
    }

    public void setChave() {
        this.temChave = true;
    }

}
