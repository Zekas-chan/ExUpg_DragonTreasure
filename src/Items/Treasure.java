public class Treasure extends Item {
    private int goldValue;

    public Treasure(String name, String description, int value) {
        super(name, description);
        this.goldValue = goldValue;
    }

    public int getgoldValue() {
        return goldValue;
    }

    @Override
    public void use() {
     
        System.out.println("You collect the treasure worth " + goldValue + " gold coins.");
    }

    @Override
    public String toString() {
        return super.toString() + "\nValue: " + goldValue;
    }
}
