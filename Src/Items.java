package Src;


public abstract class Items {
  protected final String id;
  protected final String name;

  public Items (String id, String name){
    this.id = id;
    this.name = name;
  }


  public boolean findItemByname(String name){
     if(name.equals(this.name))
       return true;

     return false;
  }

  public String toString(){
    return "" + "  id = " + id + "\n  name = " + name;
  }

  public String getName(){
    return name;
  }

  public String getId(){
    return id;
  }


}
