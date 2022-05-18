package ComponentPackage;
import Src.Component;

public class SimpleComponent extends Component {

  public SimpleComponent(String id, String name){
    super(id, name);
  }


 public String toString(){
   return "SimpleComponent \n" + super.toString();
 }

}
