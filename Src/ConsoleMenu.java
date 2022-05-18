package Src;
import RecipePackage.*;
import ComponentPackage.*;
import BuildingPackage.*;

import java.util.Scanner;
import static java.lang.System.exit;
import java.util.ArrayList;



public class ConsoleMenu {
  Scanner scanner = new Scanner(System.in);
  int option=1;
  String optionName=null;
  ArrayList<Component> listComponent ;
  ArrayList<Building> listBuilding;
  ArrayList<Recipes> listRecipes;

  public ConsoleMenu(ArrayList<Component> listComponent,ArrayList<Building> listBuilding, ArrayList<Recipes> listRecipes ){
    this.listComponent = listComponent;
    this.listBuilding = listBuilding;
    this.listRecipes= listRecipes;
  }


  public void Run(){
    while(true){
      printMenu();
      optionName = scanner.nextLine();
      try{

        option = Integer.parseInt(optionName);
        switch (option){
          case 1: test1(listComponent,""); break;
          case 2: test2(listBuilding,""); break;
          case 3: test3(listRecipes,""); break;
          case 0: exit(0);
          default:  System.out.print("\033\143");  System.out.print("Unvalid choice : Type valid numbers only\n\n"); Run();
        }
        break;
      }catch(NumberFormatException e){
        System.out.print("\033\143");
        System.out.print("Unvalid choice : Type valid numbers only\n\n");
      }
    }
  }






  public void printMenu(){

    System.out.println("----------------------------Welcome to DSP Calculator----------------------------\n");
    System.out.println("Choose an option");
    System.out.println("1: Show all components ");
    System.out.println("2: Show all buildings");
    System.out.println("3: Show all recipies");
    System.out.println("0: Exit");

  }


  public void ShowComponentDetails(Component comp){

    while(true){
      System.out.println(comp);
      System.out.println("\n");
      System.out.println("Choose an option");
      System.out.println("Press 1 to display "+comp.getName()+ "'s decompositon in resources");
      System.out.println("Press 0 to return to the menu");

      optionName = scanner.nextLine();
      System.out.print("\033\143");
      try{
        option = Integer.parseInt(optionName);
        switch (option){
          case 1: ShowDecomposition(comp); break;
          case 0: Run(); break;
          default: System.out.print("Unvalid choice : Type valid numbers only\n\n");
        }
        break;
      }catch(NumberFormatException e){
        System.out.print("\033\143");
        System.out.print("Unvalid choice : Type valid numbers only\n\n");
      }
    }

    Run();


  }

  public void ShowDecomposition(Component comp){
    int i=0;
    String res="";

    while(i<comp.getlistCombinationResources().size() && i<comp.getlistCombinationResourcesQuantity().size() ){
      String s = comp.getlistCombinationResources().get(i);
      Float f = comp.getlistCombinationResourcesQuantity().get(i);
      res+="\n";
      res+=s +" : x"+ f + "\n";
      i++;
    }
    if (res.equals("")) {
      res+= "It's already a resource\n";
    }
    else
    res+="\n \n";
    System.out.println(res);
  }




  public void test1(ArrayList<Component> listComponent, String resultat){
    String res="";
    for(int i=0; i<listComponent.size(); i++){
      Component component = listComponent.get(i);
      res+= component.getName() +"\n\n\n";
    }
    while(true){
      System.out.print(res);
      System.out.println(resultat);
      System.out.println("Choose an option :");
      System.out.println("Press 1 to enter the component name for details");
      System.out.println("Press 2 to return to the main menu ");

      optionName = scanner.nextLine();
      //System.out.print("\033\143");
      try{
        option = Integer.parseInt(optionName);
        switch (option){
          case 1: typeComponentNameCHECKED(); break;
          case 2: Run(); break;
          default: System.out.print("Unvalid choice : Type valid numbers only\n\n");
        }
        break;
      }catch(NumberFormatException e){
        System.out.print("\033\143");
        System.out.print("Unvalid choice : Type valid numbers only\n\n");
      }
    }

  }


public void typeComponentNameCHECKED(){

  System.out.print("Type the name of the chosen component : ");
  optionName = scanner.nextLine();
  System.out.print("\033\143");
  Component comp=null;
  for (Component component : listComponent) {
    if(component.findItemByname(optionName)){
      comp = component;
      break;
    }
  }
  if(comp==null){
    test1(listComponent, "****** Wrong component name, please try again! ******\n");
  }
  else {
    ShowComponentDetails(comp);

  }
}

public void typeBuildingNameCHECKED(){
  System.out.print("Type the name of the chosen building : ");
  optionName = scanner.nextLine();
  System.out.print("\033\143");
  Building build=null;
  for (Building building : listBuilding) {
    if(building.findItemByname(optionName)){
      build = building;
      break;
    }
  }
  if(build==null){
    test2(listBuilding, "----------Wrong building name, please try again!---------------\n");
  }
  else {
    ShowBuildingDetails(build);

  }
}


public void typeRecipeNameCHECKED(){
  System.out.print("Type the name of the chosen Recipe : ");
  optionName = scanner.nextLine();
  System.out.print("\033\143");
  Recipes recipe=null;
  for (Recipes recip : listRecipes) {
    if(recip.findItemByname(optionName)){
      recipe = recip;
      break;
    }
  }
  if(recipe==null){
    test3(listRecipes, "----------Wrong recipe name, please try again!---------------\n");
  }
  else {
    ShowRecipesDetails(recipe);

  }
}






  public void ShowBuildingDetails(Building build){

    while(true){
      System.out.println(build);
      System.out.println("\n");
      System.out.println("Choose an option");
      System.out.println("Press 1 to display "+build.getName()+ "'s recipes");

      if(build instanceof Extractor || build instanceof ExtractorNoElec)
      System.out.println("Press 2 to display "+build.getName()+ "'s resources that can extract");

      else if(build instanceof FuelPlant)
      System.out.println("Press 3 to display "+build.getName()+ "'s Fuels that can use");

      System.out.println("Press 0 to return to the menu");
      optionName = scanner.nextLine();
      System.out.print("\033\143");
      try{
        option = Integer.parseInt(optionName);
        switch (option){
          case 1: test4 (build); break;
          case 2: test5 (build); break;
          case 3: test6 (build); break;
          case 0: Run(); break;
          default: System.out.print("Unvalid choice : Type valid numbers only\n\n");
        }
        break;
      }catch(NumberFormatException e){
        System.out.print("\033\143");
        System.out.print("Unvalid choice : Type valid numbers only\n\n");
      }
    }
   ShowBuildingDetails(build);

  }



  public void test2(	ArrayList<Building> listBuilding, String resultat){
    String res="";
    for(int i=0; i<listBuilding.size(); i++){
      Building building = listBuilding.get(i);
      res+= building.getClass().getSimpleName()+ " : "+building.getName() +"\n\n";
    }

    while(true){
      System.out.print(res);
      System.out.println(resultat);
      System.out.println("Choose an option :");
      System.out.println("Press 1 to enter the building name for details");
      System.out.println("Press 2 to return to the main menu ");

      optionName = scanner.nextLine();
      //System.out.print("\033\143");
      try{
        option = Integer.parseInt(optionName);
        switch (option){
          case 1: typeBuildingNameCHECKED(); break;
          case 2: Run(); break;
          default: System.out.print("Unvalid choice : Type valid numbers only\n\n");
        }
        break;
      }catch(NumberFormatException e){
        System.out.print("\033\143");
        System.out.print("Unvalid choice : Type valid numbers only\n\n");
      }
    }


  }


  public void ShowRecipesDetails(Recipes recipe){
    System.out.println(recipe);
    System.out.println("\n");
    test7 (recipe);
    System.out.println("\n");
    OptionDecompose(listComponent, recipe);
  }


  public void OptionDecompose(ArrayList<Component> listComponent, Recipes r){
    System.out.println("Choose an option");
    System.out.println("Enter the Component name to get the decompositon");
    System.out.println("Press 1 to return to the main menu ");
    optionName = scanner.nextLine();
    System.out.print("\033\143");
    option=0;
    if(optionName.equals("1"))
    option =1;
    switch (option){
      case 0: howToDecomposeComponent(listComponent, optionName, r); break;
      case 1: Run(); break;
    }
  }


  public void howToDecomposeComponent(ArrayList<Component> listComponent, String componentName, Recipes r){
    for (Component component : listComponent) {
      if(component.getId().equals(componentName)){
        ShowDecomposition(component);
      }
    }

    test7 (r);
    System.out.println("\n");
    OptionDecompose(listComponent, r);
  }



  public void test3( ArrayList<Recipes> listRecipes, String resultat){
    System.out.print("\033\143");
    String res="";
    for(int i=0; i<listRecipes.size(); i++){
      Recipes rec = listRecipes.get(i);
      res+= rec.getName() +"\n\n";
    }

    while(true){
      System.out.print(res);
      System.out.println(resultat);
      System.out.println("Choose an option :");
      System.out.println("Press 1 to enter the recipe name for details");
      System.out.println("Press 2 to return to the main menu ");

      optionName = scanner.nextLine();
      //System.out.print("\033\143");
      try{
        option = Integer.parseInt(optionName);
        switch (option){
          case 1: typeRecipeNameCHECKED(); break;
          case 2: Run(); break;
          default: System.out.print("Unvalid choice : Type valid numbers only\n\n");test3(listRecipes,  resultat);
        }
        break;
      }catch(NumberFormatException e){
        System.out.print("\033\143");
        System.out.print("Unvalid choice : Type valid numbers only\n\n");
      }
    }




  }


  public void test4 (Building b){
    for(int i=0; i< b.ShowRelatedRecipies().size(); i++){
      System.out.println(i + " Recipe :");
      System.out.println(b.ShowRelatedRecipies().get(i));
      System.out.println("\n");
    }
    if(b.ShowRelatedRecipies().size()==0)
    System.out.println("No recipe \n");


    Run();
  }

  public void test5 (Building b){
    for(int i=0; i< b.ShowRelatedComponents().size(); i++){
      System.out.println(i + " Resource :");
      System.out.println(b.ShowRelatedComponents().get(i));
      System.out.println("\n");
    }
    if (b.ShowRelatedComponents().size()==0)
    System.out.println("This building doesn't extract resources\n");

    Run();

  }

  public void test6 (Building b){
    if(b instanceof FuelPlant){
      FuelPlant fp = (FuelPlant)b;

      for(int i=0; i< fp.getEntryFuels().size(); i++){
        System.out.println(i + " Fuel :");
        System.out.println(fp.getEntryFuels().get(i));
        System.out.println("\n");
      }

      if(fp.getEntryFuels().size()==0)
      System.out.println("This fuel plant doesn't use fuels \n");
    }
  else
    System.out.println("This building doesn't use fuels \n");

    Run();
  }


  public void test7 (Recipes r){
    String res = "Resources : \n";
    for(int i=0; i<r.getListResource().size(); i++){
      res+= r.getListResource().get(i);
      res+="\n";
    }

    if(r.getListResource().size()==0)
    res="";
    else
    res+="\n";
    if(r.getListNonResource().size()>0){
      res += "Non resource :\n";

      for(int i=0; i<r.getListNonResource().size(); i++){
        res+= r.getListNonResource().get(i);
        res+="\n";
      }
    }


    System.out.println(res);

  }




}
