/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Player {
    private final String name; //Defines the property name as a string

    /**
     * Konstruktor
     * @param name spelarens namn
     */
    public Player(String name)
    {
        this.name = name;
    }//Constructor

    /**
     * @return spelarens namn
     */
    public String getName()
    {
        return name;
    }//Getter
}//class