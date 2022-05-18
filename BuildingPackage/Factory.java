package BuildingPackage;
import Src.Building;

public abstract class Factory extends Building {
  protected int usage;
  protected float drain;
  protected float prodSpeed;
  protected String type;

  public Factory(String id, String name, String type, int usage, float drain, float prodSpeed){
    super(id, name);
    this.type = type;
    this.usage = usage;
    this.drain = drain;
    this.prodSpeed = prodSpeed;
  }

  public Factory(String id, String name, String type, int usage, float drain){
    super(id, name);
    this.type = type;
    this.usage = usage;
    this.drain = drain;
  }

  public Factory(String id, String name, String type, float prodSpeed){
    super(id, name);
    this.type = type;
    this.prodSpeed = prodSpeed;
  }

  public Factory(String id, String name, String type){
    super(id, name);
    this.type = type;
  }


  public String toString(){
      String ref = "Factory :";
      ref += super.toString();
      ref += "\n" + "type = " + type+ "\n usage = " + usage + "\n drain = " + drain + "\n prodSpeed = " + prodSpeed;
      return ref;
  }
}
