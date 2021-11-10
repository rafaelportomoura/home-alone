import java.util.List;

public class Dispensa extends Ambiente {

    private Dica dica;

    public Dispensa(String nome, int comodoComAChave, List<Integer> saidas) {
        super(nome, saidas);
        criarDica(comodoComAChave);
    }

    public Dispensa(String nome, int comodoComAChave) {
        super(nome);
        criarDica(comodoComAChave);
    }

    private void criarDica(int comodoComAChave) {
        dica = new Dica(comodoComAChave);
    }

    @Override
    public Item buscar() {
        return dica;
    }

}
