public class Dica implements Item {
  private String dica;

  public Dica(int comodoComAChave) {
    setDica(comodoComAChave);
  }

  private void setDica(int comodoComAChave) {
    switch (comodoComAChave) {
    case 1:
      break;
    case 2:
      break;
    case 3:
      break;
    case 4:
      break;
    case 5:
      break;
    case 6:
      break;
    case 7:
      break;
    case 8:
      break;
    case 9:
      break;
    case 10:
      break;
    case 11:
      break;
    case 12:
      break;
    default:
      dica = "Não possui dica";
      break;
    }
  }

  public String getDica() {
    return dica;
  }
}
