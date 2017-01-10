package edu.tudelft.games.f1manager.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.tudelft.games.f1manager.core.Car;
import edu.tudelft.games.f1manager.core.CarList;
import edu.tudelft.games.f1manager.core.Engine;
import edu.tudelft.games.f1manager.core.Tyres;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


import java.util.Scanner;


public class AddCarToJson {

  /**
   * Application to easily add a Car to Json db.
   *
   * @param args args
   */
  public static void main(String[] args) throws IOException {


    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/Tests/cars.json");
    Reader reader = new InputStreamReader(is);
    Gson gson = new GsonBuilder().create();
    CarList carlist = gson.fromJson(reader, CarList.class);

    while (true) {

      Scanner sc = new Scanner(System.in);

      System.out.println("What do you want to do?");
      System.out.println("1. Add a new Car to Json Database...");
      System.out.println("2. Show Json Car Database");
      System.out.println("3. Close the application...");

      int decision = sc.nextInt();

      if (decision == 1) {

        carlist.addCar(createCar());
        System.out.println();
        System.out.println("You just added a new car to the list");
        System.out.println();

        String json = gson.toJson(carlist);

        FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/Tests/cars.json");
        outputStream.write(json.getBytes());
        outputStream.close();


      } else if (decision == 2) {

        String json = gson.toJson(carlist);
        System.out.println(json);

      } else if (decision == 3) {

        System.out.println();
        System.out.println("Application closed...");
        break;
      }
    }
  }

  /**
   * Returns Car object that gets created by user input.
   *
   * @return a car
   */
  public static Car createCar() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Brand of engine? :");
    String brand = sc.nextLine();

    System.out.println("Power of engine?: ");
    double power = Double.parseDouble(sc.next());

    System.out.println("Drivability of engine?: ");
    double drivability = Double.parseDouble(sc.next());

    System.out.println("FuelEfficiency of engine?: ");
    double fuelEfficiency = Double.parseDouble(sc.next());

    System.out.println("Price of engine?: ");
    double price = Double.parseDouble(sc.next());

    System.out.println("Body of car?: ");
    double body = Double.parseDouble(sc.next());

    System.out.println("Hardness of tyres?: ");
    int hardness = Integer.parseInt(sc.next());

    Engine engine = new Engine(brand, power, drivability, fuelEfficiency);
    Tyres tyres = new Tyres(hardness);
    return new Car(engine, body, tyres);
  }
}

