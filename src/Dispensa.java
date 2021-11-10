public class Dispensa extends Ambiente {
    private Dica dica;

    public Dispensa(String nome, int comodoComAChave, int[] saidas) {
        super(nome, saidas);
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
