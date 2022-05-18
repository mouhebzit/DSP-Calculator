package BuildingPackage;
import RecipePackage.Recipes;


import java.util.ArrayList;
import java.util.List;


public class SimpleFactory extends Factory {
  private ArrayList<Recipes> listRecipes;


  public SimpleFactory(String id, String name, String type, int usage, float drain, float prodSpeed){
    super(id, name, type, usage, drain, prodSpeed);
    listRecipes = new ArrayList<Recipes>();
  }



}
