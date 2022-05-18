package ComponentPackage;


public class Antimatter extends Fuel {


  public Antimatter(String id, String name, int value, String minedby, String category){
    super(id, name, value, minedby, category);
  }

  public Antimatter(String id, String name, int value, String category){
    super(id, name, value, category);
  }

  public String toString(){
    String ref = "Antimatter fuel : ";
    return ref + super.toString();
  }

}
