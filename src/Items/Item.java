package Items;

// Abstract innebär att klassen måste ärvas innan objekt kan skapas.
// dvs new Item() går inte att köra, men en ny klass Potion som har "extends Item" kan göra det.
public abstract class Item {
	private String name;
	private String itemDesc;
}
