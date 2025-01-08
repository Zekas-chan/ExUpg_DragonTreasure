package Items;

public class Weapon extends Item {
    private int increaseDamage; //The amount of damage the player gains from weilding the weapon
    private String weaponType; //Type of weapon ex. sword, bow, mace etc.

    public Weapon(String name, String description, int damage, String weaponType) {
        super(name, description);
        this.increaseDamage = damage;
        this.weaponType = weaponType;
    } //constructor for creating weapons

    public int getDamage() {
        return increaseDamage;
    }

    public String getWeaponType() {
        return weaponType;
    }

    @Override
    public void use() {
        System.out.println("You wield the " + getName() + " and can now deal " + increaseDamage + " more damage.");
    }  //Method that defines the effect of using the weapon

    @Override
    public String toString() {
        return super.toString() + "\nDamage: " + increaseDamage + "\nWeapon Type: " + weaponType;
    } //to enable more information about the weapon to be viewed
}
