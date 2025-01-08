package Creatures;

import Items.*;

import java.util.ArrayList;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Player extends Creature {
    private final String name;
    private Key key;
    private Weapon weapon;
    private Potion potion;
    private Treasure treasure;


    /**
     * Konstruktor
     * @param name spelarens namn
     */
    public Player(String name) {
        super(10,1);//Calls fighting and sets initial health and damage
        this.name = name;
    }//Constructor

    public void pickUpItem(Item item)
    {
        if(item instanceof Key)
        {
            this.key = (Key) item;
        }else

        if(item instanceof Weapon)
        {
            this.weapon = (Weapon) item;
            weapon.use();
        }else if(item instanceof Potion)
        {
            this.potion = (Potion) item;
        }else if(item instanceof Treasure)
        {
            this.treasure = (Treasure) item;
        }
    }

    public boolean hasTreasure()
    {
        return treasure != null;
    }

    public boolean hasKey()
    {
        return key != null;
    }

    @Override
    public int getDamage()
    {
        return super.getDamage() + weapon.getDamage();
    }

    public void usePotion()
    {
        if(this.potion != null)
        {
            potion.use();
            super.Heal(potion.getHealingAmount());
            this.potion = null;
        }else
        {
            System.out.println("You don't have a potion.");
        }
    }

    public boolean hasPotion()
    {
        return this.potion != null;
    }

    //getters
    /**
     * @return spelarens namn
     */
    public String getName()
    {
        return name;
    }//Getter
}//class