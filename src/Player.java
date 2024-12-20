import Items.Item;

import java.util.ArrayList;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Player {
    private final String name;
    private int healthPoints;
    private int damage;
    private ArrayList<Item> inventory; //spelaren bör rimligtvis ha en inventory också

    /**
     * Konstruktor
     * @param name spelarens namn
     */
    public Player(String name)
    {
        this.name = name;
    }//Constructor

    //getters
    /**
     * @return spelarens namn
     */
    public String getName()
    {
        return name;
    }//Getter
}//class