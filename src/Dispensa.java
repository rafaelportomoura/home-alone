import java.util.List;

public class Dispensa extends Ambiente {

    public Dispensa(String nome, List<Integer> saidas) {
        super(nome, saidas);
    }

    public Dispensa(String nome) {
        super(nome);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Item buscar() {
        return new Dica();
    }

}
