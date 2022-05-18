package BuildingPackage;


public class FuelAndPlantCategoryException extends Exception {
  private String id;
  private String categoryFuel;
  private String categoryFuelPlant;

  public FuelAndPlantCategoryException(String id, String categoryFuelPlant, String categoryFuel) {
     this.id=id;
     this.categoryFuel=categoryFuel;
     this.categoryFuelPlant=categoryFuelPlant;
  }





 public String toString () {

  String res ="It's not possible to associate the Fuel Plant which has the id "+id+" and the category "+categoryFuelPlant+ " with the Fuel which is "+categoryFuel+".\n";
  res+="Unable to associate the fuel plant with the fuel as it will generates other exceptions.";
  res+="\n\n";
  return res;
 }

}
