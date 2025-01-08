package Items;

public class Potion extends Item {
    private int healingAmount;

    public Potion(String name, String description, int healingAmount) {
        super(itemName, description);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void use() {
        // Implement the effect of using the potion, like healing the player.
        System.out.println("You use the " + getName() + " and heal " + healingAmount + " health points.");
    }

    @Override
    public String toString() {
        return super.toString() + "\nHealing Amount: " + healingAmount;
    }
}

