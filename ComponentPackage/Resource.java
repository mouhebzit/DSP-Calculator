package ComponentPackage;
import Src.Component;

public class Resource extends Component {

  private String minedby;

  public Resource(String id, String name, String minedby){
    super(id, name);
    this.minedby = minedby;
  }

  @Override
  public String getMinedby(){
    return minedby;
  }

  public String toString(){
      return ""+"Resource : \n" + "  id = " + super.id + "\n  name = " + super.name + "\n  minedby = " + minedby;
  }


}
