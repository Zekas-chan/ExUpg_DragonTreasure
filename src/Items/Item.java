package Items;

// Abstract innebär att klassen måste ärvas innan objekt kan skapas.
// dvs new Item() går inte att köra, men en ny klass Potion som har "extends Item" kan göra det.

public abstract class Item {
    private String itemName;
    private String description; //can be used to describe the appearance of the item
   

    public Item(String name, String description) {
        this.itemName = name;
        this.description = description;
        
    } //constructor items

    public String getName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public abstract void use(); //implemented in subclasses

    @Override
    public String toString() {
        return "Item: " + itemName + "\nDescription: " + description;
    } //enables information about the items to be viewed
}
