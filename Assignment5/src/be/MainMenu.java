package be;

public class MainMenu extends Menu {

    /**
     * Creates an instance of the class with the given header text and
     * menu options.
     *
     * @param header    The header text of the menu.
     * @param menuItems The list of menu items texts.
     */
    public MainMenu() {
        super("My Menu", "First Option", "Second Option");

    }

    @Override
    protected void doAction(int option) {
        if(option == 1)
            System.out.println("Option 1 chosen");
        else if (option == 2) {
            System.out.println("Option 2 chosen");
        }
    }
}
