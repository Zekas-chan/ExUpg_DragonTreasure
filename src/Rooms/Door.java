package Rooms;

/**
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
     * Get-metod för orientation
     * @return Dörrens "position" som en char
     */
    public char getOrientation()
    {
        return orientation;
    }

    /**
     * Dörrens riktning som ord i stället för char
     * TODO behöver ändras när nycklar är implementerade med all sannolikhet
     * @return Dörrens riktning utskriven
     */
    public String getMovementHint()
    {
        String showLock = locked ? "[locked]" : "";
        switch(orientation)
        {
            case 'n': return "north [n] " + showLock;
            case 'e': return "east [e] " + showLock;
            case 's': return "south [s] " + showLock;
            case 'w': return "west [w] " + showLock;
            default:
                return "This should never display";
        }
    }
}