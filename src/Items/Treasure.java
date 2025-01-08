package Items;

public class Treasure extends Item {
    private int goldValue; //the value of the treasure

    public Treasure(String name, String description, int value) {
        super(name, description);
        this.goldValue = value;
    } //Constructor for creating treasure

    public int getgoldValue() {
        return goldValue;
    }

    @Override
    public void use() {
     
        System.out.println("You collect the treasure worth " + goldValue + " gold coins.");
    } //method that defines the effect of "using" (picking up) treasure items 

    @Override
    public String toString() {
        return super.toString() + "\nValue: " + goldValue;
    } //enables the value of the treasure to be viewed
}
