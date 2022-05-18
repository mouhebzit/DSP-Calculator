package Src;
import RecipePackage.*;

import java.util.ArrayList;


public abstract class Component extends Items {
  private ArrayList<String> listCombinationResources = new ArrayList<>();
  private ArrayList<Float> listCombinationResourcesQuantity = new ArrayList<>();


  public Component (String id, String name){
    super(id,name);
    listCombinationResources = new ArrayList<>();
    listCombinationResourcesQuantity = new ArrayList<>();
  }



  public void decomposeComponent(ArrayList<Recipes> listRecipes, String s, float qty){
    RecipesForManyComponents rm=null;
    int a=1;
    for (Recipes rec : listRecipes) {
      if(rec instanceof RecipesForManyComponents ){
        rm = (RecipesForManyComponents)rec;
        if(rm.isEqualToRecipeOut(s))
        a=1;
      }
      else
        a=0;

      if(rec.getId().equals(s) || a==1){

        if(rec.recipeIsResource()){
          rec.addResourcesNeededToList(listCombinationResources, listCombinationResourcesQuantity,qty);
        }

        else{
          rec.addResourcesNeededToList(listCombinationResources,listCombinationResourcesQuantity,qty );
          int i=0;
          for (String nonResource : rec.getListNonResource() ) {
            decomposeComponent(listRecipes, nonResource, rec.getListNonResourceQuantity().get(i));
            i++;
          }
        }
        break;
      }

    }
  }


  public ArrayList<String> getlistCombinationResources(){
    return listCombinationResources;
  }

  public ArrayList<Float> getlistCombinationResourcesQuantity(){
    return listCombinationResourcesQuantity;
  }


  public String getMinedby(){
    return null;
  }


  public String toString(){
    return super.toString();
  }


}
