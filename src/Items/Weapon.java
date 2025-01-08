package Items;

public class Weapon extends Item {
    private int increaseDamage;
    private String weaponType;

    public Weapon(String name, String description, int damage, String weaponType) {
        super(name, description);
        this.increaseDamage = damage;
        this.weaponType = weaponType;
    }

    public int getDamage() {
        return increaseDamage;
    }

    public String getWeaponType() {
        return weaponType;
    }

    @Override
    public void use() {
       
        System.out.println("You wield the " + getName() + " and can now deal " + increaseDamage + " damage.");
    }

    @Override
    public String toString() {
        return super.toString() + "\nDamage: " + increaeDamage + "\nWeapon Type: " + weaponType;
    }
}
