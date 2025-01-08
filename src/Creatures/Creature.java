package Creatures;

public class Creature {
    private int health;
    private int damage;
    private int maxHealth;

    public Creature (int health, int damage)
    {
        this.health = health; this.damage = damage; this.maxHealth = health;
    }//Constructor

    //Getter and adder for damage
    public int getDamage(){return damage;}
    public void addDamage(int damage){this.damage += damage;}
    //vi kan också använda modifiers men this will do for now, modifier class, sword extends, potion extends

    public int getHealth(){
        return health;
    }

    //heals entity with the incoming health and adds to current health
    public void Heal (int health)
    {
        if (this.health + health > maxHealth) {
            this.health = maxHealth;
        }
        else this.health += health;
    }

    public void takeDamage(int damage) {this.health -= damage;} //Health minus damage done to the entity

    public boolean isAlive (){return health > 0;} //If health is greater than 0 it returns true, if not it returns false

}
