package Src;


public class ElectricityConsumptionException extends Exception {

  private float drain;
  private float usage;
  private String id;

  public ElectricityConsumptionException(float drain, float usage, String id) {
     this.drain=drain;
     this.usage=usage;
     this.id=id;
  }



 public String toString () {
  String res ="The factory which has the ID "+id+" consume more energy when it's not in use than during production.\n" ;
  res+="The drain value which is "+drain+"kW/s is superior to the value of usage which is "+usage+"kW/s.\n";
  res+="Unable to create this object as it will generates other exceptions.";
  res+="\n\n";
  return res;
 }

}
