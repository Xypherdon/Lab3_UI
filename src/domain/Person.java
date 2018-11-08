package domain;

import java.util.Objects;

public class Person{
    private long ID;
    private String firstName;
    private String lastName;
    private String address;

    public Person(long ID, String firstName,String lastName,String address){
        this.ID=ID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    /**
     *
     * @return A string version of an object and all it's attributes.
     */
    @Override
    public String toString() {
        String string;
        return "idText: " + getID() + "\n First Name: " + this.getFirstName() + "\n Last Name: "
                + this.getLastName() + "\n Address: " + this.getAddress() + "\n";
    }

    /**
     *
     * @param object = A random object
     * @return True if objects are the same;
     *         False if objects are different.
     */
    @Override
    public boolean equals(Object object){
        return (this == object);
    }

    /**
     *
     * @return A hashcode of the object.
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.getID());
    }
}
