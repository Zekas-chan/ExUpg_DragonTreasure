package Items;

public class Potion extends Item {
    private int healingAmount; //the hp a player gains by using the potion

    public Potion(String name, String description, int healingAmount) {
        super(name, description);
        this.healingAmount = healingAmount;
    } //Constructor for creating a potion 

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void use() {
        System.out.println("You use the " + getName() + " and heal " + healingAmount + " health points.");
    } //Defines the effect of using a potion 

    @Override
    public String toString() {
        return super.toString() + "\nHealing Amount: " + healingAmount;
    } //enables viewing of the healing amount of the potion
}

