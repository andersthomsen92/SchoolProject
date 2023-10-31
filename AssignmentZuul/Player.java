import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<Item> inventory;

    public Player()
    {
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room room){
        currentRoom = room;
    }
    // TODO Need to implement the drop item and the total weight of the items
    // see SDE assignment with Interface Ishapes.

  /*  public void dropItem(){}

    public boolean lootItem(Item item)
    {
        if (currentRoom.getItems().contains(item))
        {
            double totalWeight = calculateTotalWeight();
        }
        if (totalWeight + item.getWeight() <= 100)
        {
            currentRoom.removeItem(item);
            inventory.add(item);
            return true;
        }
        return false;
    }*/

    public void listInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println(item.getDescription());
            }
        }
    }

    private double calculateTotalWeight()
    {
        double totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }
}
