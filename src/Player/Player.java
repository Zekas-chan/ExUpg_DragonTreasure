package Player;

import Fighting.Fighting;
import Items.Item;

import java.util.ArrayList;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Player extends Fighting {
    private final String name;
    private ArrayList<Item> inventory; //spelaren bör rimligtvis ha en inventory också

    /**
     * Konstruktor
     * @param name spelarens namn
     */
    public Player(String name) {
        super(10,1);//Calls fighting and sets initial health and damage
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