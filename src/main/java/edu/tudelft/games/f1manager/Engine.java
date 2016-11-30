package edu.tudelft.games.f1manager;

public class Engine {

  private String brand;
  private double power;
  private double drivability;
  private double fuelefficiency;
  private int priceengine;



  public Engine(String brand, double power, double drivability, double fuelefficiency){
    this.brand = brand;
    this.power = power;
    this.drivability = drivability;
    this.fuelefficiency = fuelefficiency;
  }

  public String getBrand(){
    return brand;
  }

  public void setBrand(String newbrand){
    brand = newbrand;
  }

  public int getPrice(){
    return priceengine;
  }

  public double getPower(){
    return power;
  }

  public void setPower(double newpower){
    power = newpower;
  }

  public double getDrivability(){
    return drivability;
  }

  public void setDrivability(double newdrivability){
    drivability = newdrivability;
  }

  public double getFuelefficiency(){
    return fuelefficiency;
  }

}


