public class Item {
private String name;
private String description;
private double weight;

    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public String getLongItemDescription()
    {
       // return "- Name:" + name + " (" + description + ") weight: " + weight + "kg";
        return "> Name: " + name +" <\n"
                + "> Description: " + description + " <\n"
                + "> Weight: " + weight + "kg <";

    }

}
