
class Player {

    private int vida;
    private Ambiente ambiente;
    private Set<Item> inventario; // Set garante que não tem Item duplicado

    public Player(Ambiente ambiente) {
        this.vida = 100;
        this.ambiente = ambiente;
    }

    public void mover(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void buscar() {
        if (Objects.nonNull(ambiente.buscar()) || ambiente instanceof Dispensa) {
            //encontrou a dica ou a chave
            inventario.add(ambiente.buscar());
        }
    }

    public int getVida() {
        return vida;
    }

    public void perderVida(int dano) {
        this.vida = vida - dano;
    }
    
    public boolean getTemChave(){
        boolean temChave;
        
        for(Item i : inventario){
            if(i instanceof Chave){
                temChave = true;
            }
        }
        
        return temChave;
    }
        
        
    public boolean getTemDica(){
        boolean temDica;
        
        for(Item i : inventario){
            if(i instanceof Dica){
                temDica = true;
            }
        }
        
        return temChave;
    }
}
