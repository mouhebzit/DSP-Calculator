package BuildingPackage;


public class RenewableEnergyPlant extends PowerPlant {

  public RenewableEnergyPlant(String id, String name, String type, float value){
    super(id, name, type, value);
  }


  public String toString(){
    String ref = "Renewable Energy Plant :";
    ref += super.toString();
    return ref;
  }

}
