import java.util.List;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, barracks,throneRoom,treasury,garden,tower,arcaneStudy,towerRoof,cellar,armory,guardRoom,dungeon,storage,panicRoom;
      
        // create the rooms

        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a theater");
        pub = new Room("in the pub, smells of mold");
        lab = new Room("in a laboratory, you hear something shuffle");
        barracks = new Room("in the office");
        treasury = new Room("In the treasury, glittering gold, trinkets, and baubles... Paid for in blood!");
        throneRoom = new Room("The throne room, a single ornate chair, is placed in the middle.");
        garden = new Room("Well kept garden, something is rustling in the greenery");
        tower = new Room("In the groundfloor of the mages Tower");
        arcaneStudy = new Room("books and magical objects are scattered around this arcane study.");
        towerRoof = new Room("At the top of the tower, remind yourself that overconfidence is a slow and insidious killer.");
        cellar = new Room("in this dank cellar, you see crates and barrels standing around.");
        armory = new Room("in an armory, filled with weapons and armor");
        guardRoom = new Room("Stools and tables, left empty with discarded dice and mugs, in this guardroom");
        dungeon = new Room("A disused dungeon, with empty cells. Yet, you hear rattling");
        storage = new Room("Organized rows and rows of crates and paintings");
        panicRoom = new Room("in a panic room for the royalty");

        // create the items

        Item oldBook = new Item("Old book","Decrepit book",0.5);
        Item labReagent = new Item("Reagent Bottle", "A bottle full of aqua regia",0.2);

        // initialise room exits
        // Outside
        outside.setExits("west",pub);
        outside.setExits("south",lab);
        outside.setExits("east",theater);
        outside.addItem(oldBook);

        // Pub
        pub.setExits("east",outside);
        pub.setExits("south",barracks);
        // theater
        theater.setExits("west",outside);
        // Barracks
        barracks.setExits("north",pub);
        barracks.setExits("east",lab);
        barracks.setExits("downstairs",cellar);
        // Laboratory
        lab.setExits("north",outside);
        lab.setExits("west",barracks);
        lab.setExits("south",garden);
        lab.addItem(labReagent);
        // Garden
        garden.setExits("north",lab);
        garden.setExits("south",throneRoom);
        // Throne Room
        throneRoom.setExits("north",garden);
        throneRoom.setExits("east",treasury);
        // Treasury
        treasury.setExits("west",throneRoom);
        // Cellar
        cellar.setExits("upstairs",barracks);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());

        List<Item> items = currentRoom.getItems();
        if (!items.isEmpty()) {
            System.out.println("Items in the room:");
            for (Item item : items) {
                System.out.println(item.getLongItemDescription());
            }
        }

        /*System.out.println("Exits: ");
        if (currentRoom.getExit("north") != null)
            {System.out.println("north ");}
        if (currentRoom.getExit("east") != null)
            {System.out.println("east ");}
        if (currentRoom.getExit("south") != null);
            {System.out.println("south ");}
        if (currentRoom.getExit("west") != null)
            {System.out.println("west ");}
        if (currentRoom.getExit("upstairs") != null)
            System.out.println("upstairs ");
        if (currentRoom.getExit("downstairs") != null)
            System.out.println("downstairs "); */

        System.out.println();
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            System.out.println(currentRoom.getLongDescription());
        } else if (commandWord.equals("eat"))
        {
            System.out.println("You ate a ration, you are sated.");
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
