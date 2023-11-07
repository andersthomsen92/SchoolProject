/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author pgn
 */
public class Car
{

    private double price = 55000;
    private int maxKilometersPerHour = 100;
    private String manufactor = "Renault";

    /**
     *
     * @return The value of the maxKilometersPerHour instance variable
     */
    public int getMaxKilometersPerHour()
    {
        //TODO Implement this method
        return maxKilometersPerHour;
        //throw new UnsupportedOperationException();
    }

    /**
     * Sets the maxKilometersPerHour instance variable, but only the parameter
     * is a positive number greater than zero and not greater than 300 km/h!
     *
     * @param maxKilometersPerHour
     */
    public void setMaxKilometersPerHour(int maxKilometersPerHour)
    {
        //TODO Implement this method
        if (maxKilometersPerHour > 0 && maxKilometersPerHour < 300)
        this.maxKilometersPerHour = maxKilometersPerHour;
        //throw new UnsupportedOperationException();
    }

    /**
     *
     * @return the value of the manufactor instance variable
     */
    public String getManufactor()
    {
        //TODO Implement this method
        return manufactor;
        //throw new UnsupportedOperationException();
    }

    /**
     * Sets the manufactor instance variable but only if the parameter is not
     * null or empty.
     *
     * @param manufactor
     */
    public void setManufactor(String manufactor)
    {
        //TODO Implement this method
        if (manufactor != null && !manufactor.isEmpty()){
        this.manufactor = manufactor;}
        //throw new UnsupportedOperationException();
    }

    /**
     *
     * @return the value of the price instance variable.
     */
    public double getPrice()
    {
        //TODO Implement this method
        return price;
        //throw new UnsupportedOperationException();
    }

    /**
     * Sets the value of the price instance variable but only if it is positive
     * and greater than zero
     *
     * @param price
     */
    public void setPrice(double price)
    {
        //TODO Implement this method
        if (price > 0)
        this.price = price;
        //throw new UnsupportedOperationException();
    }

}
