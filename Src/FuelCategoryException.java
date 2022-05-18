package Src;


public class FuelCategoryException extends Exception {
  private String category;
  private String id;
  private String type;

  public FuelCategoryException(String id, String category, String type) {
     this.category=category;
     this.id=id;
     this.type=type;
  }





 public String toString () {

  String res ="The "+type+" which has the ID "+id+" has an invalid category which is "+category+".\n";
  res+="Unable to create this object as it will generates other exceptions.";
  res+="\n\n";
  return res;
 }

}
