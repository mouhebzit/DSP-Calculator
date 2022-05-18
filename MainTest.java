import java.util.ArrayList;
import Src.*;
import RecipePackage.*;
import ComponentPackage.*;
import BuildingPackage.*;

public class MainTest {



	public static void main(String[] args)
	{

		ArrayList<Component> listComponent = new ArrayList<>();
		ArrayList<Building> listBuilding = new ArrayList<>();
    ArrayList<Recipes> listRecipes = new ArrayList<>();

    StoreData.StoreDataFromXml(listComponent, listBuilding,listRecipes);

	  ConsoleMenu menu = new ConsoleMenu(listComponent, listBuilding,listRecipes);
		//System.out.print("\033\143");

		menu.Run();

	}

}
