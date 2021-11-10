import java.util.List;

public abstract class Ambiente {

    private String nome;
    List<Integer> saidas;

    public Ambiente(String nome) {
        this.nome = nome;
    }

    public Ambiente(String nome, List<Integer> saidas) {
        this.nome = nome;
        this.saidas = saidas;
    }

    public abstract Item buscar();

    public void ajustarSaidas(List<Integer> saidas){
        this.saidas = saidas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getSaidas() {
        return saidas;
    }

    public void setSaidas(List<Integer> saidas) {
        this.saidas = saidas;
    }

}
