package BuildingPackage;


public abstract class PowerPlant extends Factory {
  protected float value;

  public PowerPlant(String id, String name, String type, float value, float prodSpeed){
    super(id, name, type, prodSpeed);
    this.value = value;
  }

  public PowerPlant(String id, String name, String type, float value){
    super(id, name, type);
    this.value = value;
  }


  public String toString(){
    String ref = super.toString();
    ref += "\n type = " + type + "\n  value = " + value;
    return ref;
  };


}
