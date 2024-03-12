package visualizealgorithms.be;

import java.util.Comparator;

/**
 *
 * @author Søren Spangsberg
 */
public class User implements Comparable<User> {

    private String fname;
    private String lname;
    private int age;

    public User(String fname, String lname, int age) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return Comparator.comparing(User::getFname)
                .thenComparing(User::getLname)
                .thenComparingInt(User::getAge)
                .compare(this, o);
    }
}
