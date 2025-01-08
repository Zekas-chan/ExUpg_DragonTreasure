package Items;

public class Key extends Item {


    public Key(String name, String description) {
        super(name, description);
       
    }


    @Override
    public void use()
    {
        System.out.println("You use the " + getName() + " to unlock the door.");
    }

  
}
