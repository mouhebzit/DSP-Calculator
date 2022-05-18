package BuildingPackage;
import Src.Building;

public class ExtractorNoElec extends Building {

  private float miningSpeed;

  public ExtractorNoElec(String id, String name, float miningSpeed){
    super(id, name);
    this.miningSpeed = miningSpeed;
  }

  public String toString(){
    String ref = super.toString();
    ref += "\n miningSpeed = " + miningSpeed;
    return ref;
  }

}
