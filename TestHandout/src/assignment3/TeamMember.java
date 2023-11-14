package assignment3;
/**
 * 
 * @author easv
 * 
 * Do not modify. Nothing to do.
 * 
 */
public class TeamMember {
    private int id;
    private String name, role;

    public TeamMember(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

}
