package Rooms;

/**
 * Representerar en dörr
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Door {
    private boolean locked;
    private final char orientation; //north, south, east, west

    /**
     * Konstruktor för dörr
     * @param orientation Riktningen för dörren
     * @param locked Om den är låst
     */
    public Door(char orientation, boolean locked)
    {
        this.orientation = Character.toLowerCase(orientation); //ser till att det alltid är liten bokstav
        this.locked = locked;
    }

    /**
     * Get-metod för locked
     * @return Huruvida dörren är låst eller inte
     */
    public boolean isLocked()
    {
        return locked;
    }

    /**
     * Låser upp dörren
     */
    public void unlockDoor()
    {
        this.locked = false;
    }

    /**
     * Get-metod för orientation
     * @return Dörrens "position" som en char
     */
    public char getOrientation()
    {
        return orientation;
    }

    /**
     * Dörrens riktning som ord istället för char med tangenthint.
     * @return Dörrens riktning utskriven
     */
    public String getMovementHint()
    {
        String showLock = locked ? " [locked]" : " ["+orientation+"]";
        switch(orientation)
        {
            case 'n': return "north" + showLock;
            case 'e': return "east" + showLock;
            case 's': return "south" + showLock;
            case 'w': return "west" + showLock;
            default:
                return "Door.java - This should never display"; //:prayinghands:
        }
    }
}