package RecipePackage;


import java.util.ArrayList;

public class RecipesForManyComponents extends Recipes {
  protected	ArrayList<String> listOutput;
  protected	ArrayList<Float> listOutQuantity;

  public RecipesForManyComponents(String id, String name, float time, String producers){
    super(id, name, time,  producers);
    listOutput= new ArrayList<>();
    listOutQuantity  = new ArrayList<>();
  }

  public void addOutput(String Outname, float qty){
    listOutput.add(Outname);
    listOutQuantity.add(qty);
  }


  public boolean isEqualToRecipeOut(String idComp){
    boolean bl = false;
    for (String s : this.listOutput) {
         if(idComp.equals(s))
           bl=true;
    }
    return bl;
  }

  public String toString(){
    String ref = super.toString();
    ref+="\n out: \n";
    for(int i=0; i<listOutput.size(); i++){
      String n = listOutput.get(i);
      n+=" :" +listOutQuantity.get(i) +"\n";
      ref+=n;
    }

    return ref;
  }

}
