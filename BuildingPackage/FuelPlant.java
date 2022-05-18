package BuildingPackage;
import ComponentPackage.Fuel;

import java.util.ArrayList;

public class FuelPlant extends PowerPlant {
  private String category;
  private ArrayList<Fuel> listFuels;


  public FuelPlant(String id, String name, String type,float value, float prodSpeed, String category ){
    super(id, name,type, value, prodSpeed);
    this.category = category;
    listFuels = new ArrayList<>();
  }

   public void LinkFuels(Fuel F){

     try{
       if(!this.category.equals(F.getCategory()))
        throw new FuelAndPlantCategoryException(id, category, F.getCategory());
      listFuels.add(F);
     }
     catch(FuelAndPlantCategoryException e){
        System.err.println(e);
     }


   }

   public ArrayList<Fuel> getEntryFuels(){
     return listFuels;
   }

  public String toString(){
    String ref = "Fuel plant";
    ref += super.toString();
    ref += "\n category = " + category;
    return ref;
  }
}
