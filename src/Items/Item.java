package Items;

// Abstract innebär att klassen måste ärvas innan objekt kan skapas.
// dvs new Item() går inte att köra, men en ny klass Potion som har "extends Item" kan göra det.

public abstract class Item {
    private String itemName;
    private String description;
   

    public Item(String name, String description) {
        this.itemName = name;
        this.description = description;
        
    }

    public String getName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

  

 
    public abstract void use();

    @Override
    public String toString() {
        return "Item: " + itemName + "\nDescription: " + description;
    }
}
