package ComponentPackage;


public class Nuclear extends Fuel {

  public Nuclear(String id, String name, int value, String minedby, String category){
    super(id, name, value, minedby, category);
  }

  public Nuclear(String id, String name, int value, String category){
    super(id, name, value, category);
  }


  public String toString(){
    String ref = "Nuclear fuel : ";
    return ref + super.toString();
  }

}
