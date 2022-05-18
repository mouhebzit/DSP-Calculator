package ComponentPackage;


public class Chemical extends Fuel {

  public Chemical(String id, String name, int value, String minedby, String category){
    super(id, name, value, minedby, category);
  }

  public Chemical(String id, String name, int value,  String category){
    super(id, name, value, category);
  }

  public String toString(){
    String ref = "Chemical fuel : ";
    return ref + super.toString();
  }

}
