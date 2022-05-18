package ComponentPackage;
import Src.Component;

public abstract class Fuel extends Component {
  protected int value;
  protected String minedby;
  protected String category;


  public Fuel(String id, String name, int value, String category){
    super(id, name);
    this.value = value;
    this.category = category;
  }

  public Fuel(String id, String name, int value, String minedby, String category){
    super(id, name);
    this.value = value;
    this.minedby = minedby;
    this.category = category;
  }

  @Override
  public String getMinedby(){
    return minedby;
  }

  public String getCategory(){
    return category;
  }

  public String toString(){
    String ref=null;
    String m="";
    if(this.minedby==null)
      ref = "SimpleComponent";
    else{
      m =  "\n  minedby = " + minedby;
      ref = "Resource";

    }

    return ref + "\n  id = " + super.id + "\n  name = " + super.name  + "\n  value = " + value +m;
  }

}
