package BuildingPackage;


public class Extractor extends Factory {
  private float miningSpeed;

  public Extractor(String id, String name, String type, int usage, float drain, float miningSpeed){
      super(id, name, type, usage, drain);
      this.miningSpeed = miningSpeed;
  }

  public String toString(){
    String ref = "Extractor :";
    ref+= super.toString();
    ref += "\n miningSpeed = " + miningSpeed;
    return ref;
  }


}
