public abstract class Ambiente {

    private String nome;
    int[] saidas;

    public Ambiente(String nome, int[] saidas) {
        this.nome = nome;
        this.saidas = saidas.clone();
    }

    public abstract Item buscar();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int[] getSaidas() {
        return saidas.clone();
    }

}
