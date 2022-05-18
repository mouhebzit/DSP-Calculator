package Src;
import RecipePackage.Recipes;
import ComponentPackage.Resource;
import ComponentPackage.Fuel;

import java.util.ArrayList;

public class Building extends Items {
  protected ArrayList<Recipes> listRecipes;
  protected ArrayList<Component> listComponents;

  public Building (String id, String name){
    super(id,name);
    listRecipes = new ArrayList<>();
    listComponents = new ArrayList<>();
  }

  public void LinkRecipies(Recipes R){
   if (R.getProducers().equals(super.id))
      listRecipes.add(R);
   }

  public void LinkResource(ArrayList<Component> listComponent){
    String minby;
    for (Component component : listComponent) {

      if(component instanceof Resource || component instanceof Fuel) {

        if(component instanceof Resource){
          Resource r = (Resource)component;
          minby = r.getMinedby();

        }

        else{
           Fuel f =  (Fuel)component;
           minby = f.getMinedby();
        }

        if (super.id.equals(minby)){
           this.listComponents.add(component);
        }

       }

      }
  }


   public ArrayList<Recipes> ShowRelatedRecipies(){
     return listRecipes;
   }

   public ArrayList<Component> ShowRelatedComponents(){
     return listComponents;
   }


  public String toString(){
    return "" + "id = " + super.id + "\n name = " + super.name +"\n";
  }

}
