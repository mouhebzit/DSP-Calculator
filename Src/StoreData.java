package Src;
import BuildingPackage.*;
import ComponentPackage.*;
import RecipePackage.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;



public class StoreData{

  private static final String FILENAME = "data.xml";

  public static void StoreDataFromXml(ArrayList<Component> listComponent, ArrayList<Building> listBuilding, ArrayList<Recipes> listRecipes){
    StoreComponents(listComponent, listBuilding);
    StoreRecipies(listRecipes);
    Building b;
    Recipes r;


    for (int i=0;i<listBuilding.size() ; i++) {
      b =listBuilding.get(i);
      b.LinkResource(listComponent);
      if(b instanceof FuelPlant){
        for (Component component : listComponent) {
          if(component instanceof Fuel){
            ((FuelPlant)b).LinkFuels((Fuel)component);
          }
        }
      }


      for (int j=0;j<listRecipes.size() ; j++) {
        r = listRecipes.get(j);
        b.LinkRecipies(r);


      }
    }

    for (int j=0;j<listRecipes.size() ; j++) {
      r = listRecipes.get(j);
      r.FindResourceFromComponent(listComponent);

    }

    for (Component component : listComponent) {
      component.decomposeComponent(listRecipes, component.getId(), 1);
    }

  }






  public static void StoreComponents(ArrayList<Component> listComponent, ArrayList<Building> listBuilding)
  {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try
    {
      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(new File(FILENAME));


      //On parcourt tous les composants du fichier
      NodeList list = doc.getElementsByTagName("items");

      //Pour chaque composant...
      for (int temp = 0; temp < list.getLength(); temp++)
      {
        Node node = list.item(temp);

        if(node.getNodeType() == Node.ELEMENT_NODE)
        {
          //On regarde le nom et la categorie du composant, et on les affiche
          Element element = (Element) node;
          //Quand le tag lu est unique pour l'objet, on peut faire ainsi
          String category = element.getElementsByTagName("category").item(0).getTextContent();
          String name = element.getElementsByTagName("name").item(0).getTextContent();
          String id = element.getElementsByTagName("id").item(0).getTextContent();


          Element fuel = (Element) element.getElementsByTagName("fuel").item(0);


          if(!category.equals("buildings")) {
            if(category.equals("resource") && fuel==null){
              String minedby = element.getElementsByTagName("minedby").item(0).getTextContent();
              listComponent.add(new Resource (id, name, minedby));
            }
            else if (category.equals("resource") && fuel!= null) {
              String minedby = element.getElementsByTagName("minedby").item(0).getTextContent();

              String category2 = fuel.getElementsByTagName("category").item(0).getTextContent();
              int value = Integer.parseInt(fuel.getElementsByTagName("value").item(0).getTextContent());
              if(category2.equals("chemical"))
              listComponent.add(new Chemical (id, name, value, minedby, category2));
              else if (category2.equals("antimatter")) {
                listComponent.add(new Antimatter (id, name, value, minedby, category2));
              }
              else
              listComponent.add(new Nuclear (id, name, value, minedby,category2));

            }
            else if (category.equals("components")  && fuel!= null) {

              String category2 = fuel.getElementsByTagName("category").item(0).getTextContent();
              int value = Integer.parseInt(fuel.getElementsByTagName("value").item(0).getTextContent());


              try{
                if(!category2.equals("nuclear") && !category2.equals("chemical") && !category2.equals("antimatter"))
                 throw new FuelCategoryException(id, category2,"Fuel");

                 if(category2.equals("chemical"))
                 listComponent.add(new Chemical (id, name, value, category2));
                 else if (category2.equals("antimatter")) {
                   listComponent.add(new Antimatter (id, name, value, category2));
                 }
                 else
                 listComponent.add(new Nuclear (id, name, value, category2));
              }
              catch(FuelCategoryException e){
                 System.err.println(e);
              }


            }

            else if (category.equals("components")) {
              listComponent.add(new SimpleComponent(id, name));
            }

          }
          else if(category.equals("buildings")){
            Element factory = (Element) element.getElementsByTagName("factory").item(0);
            Element mining = (Element) element.getElementsByTagName("mining").item(0);


            if(factory==null){
              if(mining!=null){
                float miningSpeed=Float.parseFloat(mining.getElementsByTagName("speed").item(0).getTextContent());
                listBuilding.add(new ExtractorNoElec(id, name, miningSpeed));
              }
              else
              listBuilding.add(new Building(id, name));
            }
            else {


              String type= factory.getElementsByTagName("type").item(0).getTextContent();


              if (mining!=null) {
                int usage=Integer.parseInt(factory.getElementsByTagName("usage").item(0).getTextContent());
                float drain=Integer.parseInt(factory.getElementsByTagName("drain").item(0).getTextContent());
                float miningSpeed=Float.parseFloat(mining.getElementsByTagName("speed").item(0).getTextContent());

                try{
                  if(drain>usage)
                   throw new ElectricityConsumptionException(drain, usage,id);
                  listBuilding.add(new Extractor(id, name, type, usage, drain, miningSpeed));
                }
                catch(ElectricityConsumptionException e){
                   System.err.println(e);
                }


              }
              else if (mining==null) {
                if(!type.equals("burner") && !type.equals("electric-production" )){

                  float prodSpeed;
                  int usage;
                  float drain;
                  if(factory.getElementsByTagName("drain").item(0)!=null)
                  drain=Float.parseFloat(factory.getElementsByTagName("drain").item(0).getTextContent());
                  else
                  drain = 0;

                  if(factory.getElementsByTagName("speed").item(0)!=null)
                  prodSpeed=Float.parseFloat(factory.getElementsByTagName("speed").item(0).getTextContent());
                  else
                  prodSpeed=1;
                  if(factory.getElementsByTagName("usage").item(0)!=null)
                  usage=Integer.parseInt(factory.getElementsByTagName("usage").item(0).getTextContent());
                  else
                  usage =(int) drain;

                  try{
                    if(drain>usage)
                     throw new ElectricityConsumptionException(drain, usage,id);
                    listBuilding.add(new SimpleFactory(id, name, type, usage, drain, prodSpeed));
                  }
                  catch(ElectricityConsumptionException e){
                     System.err.println(e);
                  }

                }
                else{
                  String category3;
                  if(factory.getElementsByTagName("category").item(0)!=null)
                  category3= factory.getElementsByTagName("category").item(0).getTextContent();
                  else
                  category3=null;
                  float value=Float.parseFloat(factory.getElementsByTagName("value").item(0).getTextContent());
                  if(category3==null)
                  listBuilding.add(new RenewableEnergyPlant(id, name, type, value));
                  else {
                    float prodSpeed;
                    if(factory.getElementsByTagName("speed").item(0)!=null)
                    prodSpeed=Float.parseFloat(factory.getElementsByTagName("speed").item(0).getTextContent());
                    else
                    prodSpeed=1;

                    try{
                      if(!category3.equals("nuclear") && !category3.equals("chemical") && !category3.equals("antimatter"))
                       throw new FuelCategoryException(id, category3,"Fuel Plant");
                      listBuilding.add(new FuelPlant(id, name, type, value, prodSpeed,category3 ));
                    }
                    catch(FuelCategoryException e){
                       System.err.println(e);
                    }




                  }

                }

              }
            }
          }
        }
      }
      //Sort listComponent
      Collections.sort(listComponent, new Comparator<Component>() {
        @Override
        public int compare(Component c1 , Component c2) {
          String s1 = c1.getName();
          String s2 = c2.getName();
          return s1.compareToIgnoreCase(s2);
        }
      });

      Collections.sort(listBuilding, new Comparator<Building>() {
        @Override
        public int compare(Building c1 , Building c2) {
          String s1 = c1.getName();
          String s2 = c2.getName();
          return s1.compareToIgnoreCase(s2);
        }
      });



      //Display the ArrayList



    }
    catch (ParserConfigurationException | SAXException | IOException e)
    {
      e.printStackTrace();
    }

  }


  //Test un peu plus avance, on parcourt les composants et on affiche, pour ceux qui sont de type ressource, le ou les extracteur permettant de les recuperer

  //Test sur la 6eme recette afin d'afficher la liste de ses ingredients en entree et leur quantite
  public static void StoreRecipies(ArrayList<Recipes> listRecipes)
  {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try
    {
      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(new File(FILENAME));


      //On parcourt tous les composants du fichier
      NodeList list = doc.getElementsByTagName("recipes");
      for (int i=0; i<list.getLength(); i++) {
        Node node = list.item(i);

        if(node.getNodeType() == Node.ELEMENT_NODE)
        {
          //On regarde le nom et la categorie du composant
          Element element = (Element) node;

          String id = element.getElementsByTagName("id").item(0).getTextContent();
          String name = element.getElementsByTagName("name").item(0).getTextContent();
          float time = Float.parseFloat(element.getElementsByTagName("time").item(0).getTextContent());
          String producers = element.getElementsByTagName("producers").item(0).getTextContent();

          //On recupere les ingredients in de la recette
          Element input = (Element) element.getElementsByTagName("in").item(0);
          Element output = (Element) element.getElementsByTagName("out").item(0);

          if(output==null){
            listRecipes.add(new Recipes(id, name, time, producers));
          }
          else
          listRecipes.add(new RecipesForManyComponents(id, name, time, producers));

          NodeList liste_ingredient = input.getElementsByTagName("*");
          for(int j=0; j<liste_ingredient.getLength(); j++)
          {
            //On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
            Element e = (Element)liste_ingredient.item(j);
            String id_ingred = e.getNodeName();
            float qte = Float.parseFloat(input.getElementsByTagName(id_ingred).item(0).getTextContent());
            listRecipes.get(i).addIngredient(id_ingred, qte);
          }

          if(output!=null){
            NodeList liste_output = output.getElementsByTagName("*");
            for(int j=0; j<liste_output.getLength(); j++)
            {
              //On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
              Element e = (Element)liste_output.item(j);
              String id_output = e.getNodeName();
              float qte = Float.parseFloat(output.getElementsByTagName(id_output).item(0).getTextContent());
              RecipesForManyComponents r= (RecipesForManyComponents)listRecipes.get(i);
              r.addOutput(id_output, qte);
            }

          }




        }
      }
      //TRI
      Collections.sort(listRecipes, new Comparator<Recipes>() {
        @Override
        public int compare(Recipes c1 , Recipes c2) {
          String s1 = c1.getName();
          String s2 = c2.getName();
          return s1.compareToIgnoreCase(s2);
        }
      });




    }
    catch (ParserConfigurationException | SAXException | IOException e)
    {
      e.printStackTrace();
    }

  }



}
