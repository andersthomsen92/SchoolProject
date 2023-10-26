package be;

public abstract class Person {
    private int id;
    private String name;
    private String email;

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + "           " + name + "           " + email;
    }

    @Override  // Overrides the equals method from the Object Class(top dog)
    public boolean equals(Object obj) {     // The reason for overriding is to specify what constitutes equal. in this case ID
        Person otherPerson = (Person) obj;  // "obj" is still just a random object. So it's typecast into a Person object, to get access to the getID method from Person.class.
        if (otherPerson.getId() == this.getId())    // This typecast can cause a ClassCastException error.
            return true;
        else return false;
    }
}

