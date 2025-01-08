package Items;

public class Key extends Item {


    public Key(String name, String description) {
        super(name, description);
       
    } //constructor for creating a key


    @Override
    public void use()
    {
        System.out.println("You use the " + getName() + " to unlock the door.");
    } //defines the effect of using a key

  
}
