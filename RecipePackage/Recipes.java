package RecipePackage;
import Src.*;
import ComponentPackage.Resource;

import java.util.ArrayList;
import ComponentPackage.Fuel;

public class Recipes extends Items {
  protected	ArrayList<String> listComp;
  protected	ArrayList<Float> listQuantity;
  protected	ArrayList<String> listOfResource;
  protected	ArrayList<Float> listOfResourceQuantity;
  protected	ArrayList<String> listOfNonResource;
  protected	ArrayList<Float> listOfNonResourceQuantity;


  protected float time;
  protected String producers;

  public Recipes (String id, String name, float time, String producers){
    super(id,name);
    this.producers = producers;
    this.time=time;
    listComp= new ArrayList<>();
    listQuantity= new ArrayList<>();
    listOfResource = new ArrayList<>();
    listOfNonResource = new ArrayList<>();
    listOfResourceQuantity = new ArrayList<>();
    listOfNonResourceQuantity = new ArrayList<>();

  }

  public void addIngredient(String IngrName, float quantity){
    listComp.add(IngrName);
    listQuantity.add(quantity);
  }

  public String getRecipeComponent(int i){
    return listComp.get(i);
  }

  public ArrayList<String> getListComp(){
    return listComp;
  }

  public void FindResourceFromComponent(ArrayList<Component> listComponent){
    int i=0;
    while(i<listComp.size()){
      for (Component component : listComponent) {
        if(component.getId().equals(listComp.get(i))){
          if((component instanceof Fuel || component instanceof Resource) && component.getMinedby()!=null ){
            addToListOfResource(listComp.get(i), listQuantity.get(i));
          }
          else{
            addToListOfNonResource(listComp.get(i), listQuantity.get(i));
          }
        }
      }
      i++;
    }
  }

 public void addToListOfResource(String s, float f){
  listOfResource.add(s);
  listOfResourceQuantity.add(f);

 }



 public void addToListOfNonResource(String s, float f){
  listOfNonResource.add(s);
  listOfNonResourceQuantity.add(f);
 }

 public ArrayList<String> getListResource(){
   return listOfResource;
 }

 public ArrayList<String> getListNonResource(){
   return listOfNonResource;
 }

 public ArrayList<Float> getListNonResourceQuantity(){
   return listOfNonResourceQuantity;
 }

 public ArrayList<Float> getListResourceQuantity(){
   return listOfResourceQuantity;
 }




 public void addResourcesNeededToList(ArrayList<String> array, ArrayList<Float> arrayQuantity, float coeff){
   for (String s : this.listOfResource) {
     array.add(s);
   }
   for (float f : this.listOfResourceQuantity) {
     arrayQuantity.add(f*coeff);
   }

 }


  public boolean recipeIsResource(){
    if(listOfNonResource.size()==0)
     return true;
    return false;
  }

  public String getProducers(){
    return producers;
  }


  public String toString(){
    String ref = super.toString();
    ref += "\n time = " + time;
    ref += "\n producers = " + producers;
    ref+="\n \n in: \n";
    for(int i=0; i<listComp.size(); i++){
      String n = listComp.get(i);
      n+=" :" +listQuantity.get(i) +"\n";
      ref+=n;
    }

    return ref;
  }

}
