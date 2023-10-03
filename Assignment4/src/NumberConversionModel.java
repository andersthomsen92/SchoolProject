import javafx.fxml.FXML;

public class NumberConversionModel {
    public String getGreetingsMessage(String name)
    {
        return "Hello, " + name + ". Greetings from the top Java developer class in town";
    }

    public double getMilesFromKilometers(double km){
        return km / 1.609344;
    }

    public double getKilometersFromMiles(double miles){
        return miles * 1.609344;
    }

}

