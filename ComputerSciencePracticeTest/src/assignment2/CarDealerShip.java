/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author pgn
 */
public class CarDealerShip
{

    private List<Car> carsForSale = new ArrayList<>();

    /**
     * Adds another car for sale but only if the value is not null, and only if
     * the car has a price greater than zero.
     *
     * @param car The car to add to list of cars for sale.
     */
    public void addCarForSale(Car car) {
        if (car != null && car.getPrice() > 0) {
            carsForSale.add(car);
        }
    }

    /**
     * Gets the total value of all cars for sale.
     * @return the total value of all cars for sale.
     */
    public double getTotalCarValues() {
        double totalValue = 0;
        for (Car car : carsForSale) {
            totalValue = totalValue + car.getPrice();
        }
        return totalValue;
    }
    
    /**
     * Gets the cheapest car from the list ov cars for sale.
     * @return the cheapest car from the list ov cars for sale.
     */
    public Car getCheapestCar() {
        Car cheapestCar = carsForSale.get(0);  // Sets index 0 as the cheapest car.
        for (Car car : carsForSale) {       // runs through the list.
            if (car.getPrice() < cheapestCar.getPrice()) { // if current car price is less than the current cheapestcar
            cheapestCar = car;   // then we set Cheapestcar to this new car object.
            }
        }
        return cheapestCar;         // returns the car with the lowest getprice value.
    }
    
    /**
     * Sells the car in the parameter.
     * @param car The car to be sold.
     */
    public void sellCar(Car car)
    {
        carsForSale.remove(car);
    }

    /**
     * Nothing to do here
     * @return 
     */
    public List<Car> getCarsForSale()
    {
        return carsForSale;
    }

    /**
    * Sort the list of cars for sale in order from cheapest to most expensive
    */
    public void sortCarsByPrice()
    {
        Comparator comp = Comparator.comparingDouble(Car::getPrice);  // parses all the cars, and extracts all the prices
        carsForSale.sort(comp); // sorts these prices.
    }
    
    /**
    * Sort the list of cars for sale in order from slowest to fastest.
    */    
    public void sortCarsByMaxSpeed()
    {
        carsForSale.sort(Comparator.comparingInt(Car::getMaxKilometersPerHour));
    }
    
    /**
     * Get a random car for sale.
     * @return a randomly selected car for sale.
     */
    public Car presentRandomCarToCustomer()
    {
        Random random = new Random();  // initializes the Random generator as random.
        int randomNumber = random.nextInt(carsForSale.size());  // Gets a psuedo random number with the bounds being the
                                                                // size of the ArrayList, so that we get a random car.
        return  carsForSale.get(randomNumber);  // returns this random car object.

    }


}