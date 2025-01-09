package Creatures;

import Items.*;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Player extends Creature {
    private final String name;

    //En quick and dirty inventory, sköts egentligen bättre med ArrayList men får duga för tillfället
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

    /**
     * Plockar upp items och lägger till i spelarens inventory
     * TODO Bör skrivas om helt om spelaren ska kunna ha mer än ett item. Skriver i nuläget över det item som finns, men då det bara finns ett av varje är det inget problem just nu.
     * @param item det item som ska plockas upp
     */
    public void pickUpItem(Item item)
    {
        if(item instanceof Key)
        {
            this.key = (Key) item;
        }

        else if (item instanceof Weapon)
        {
            this.weapon = (Weapon) item;
            weapon.use();
        }

        else if (item instanceof Potion)
        {
            this.potion = (Potion) item;
        }

        else if (item instanceof Treasure)
        {
            this.treasure = (Treasure) item;
        }
    }

    /**
     * Om spelaren har skatten.
     * @return true/false
     */
    public boolean hasTreasure()
    {
        return treasure != null;
    }

    /**
     * Om spelaren har nyckeln
     * @return true/false
     */
    public boolean hasKey()
    {
        return key != null;
    }

    /**
     * Hämtar spelarens skada, modifierat av vapen om spelaren har ett
     * @return Skadan som int
     */
    @Override
    public int getDamage()
    {
        return super.getDamage() + (weapon != null ? weapon.getDamage() : 0);
    }

    /**
     * Använder en potion från spelarens inventory om hen har en.
     */
    public void usePotion()
    {
        if(this.potion != null) //om spelaren har en potion
        {
            potion.use();
            super.Heal(potion.getHealingAmount());
            this.potion = null;
        }else //om spelaren inte har en potion
        {
            System.out.println("You don't have a potion.");
        }
    }

    public boolean isHurt()
    {
        return (super.getHealth() < super.getMaxHealth());
    }

    /**
     * Kontrollerar om spelaren har en potion.
     * @return true/false om objektet finns
     */
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