package Rooms;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Door {
    private boolean locked;
    private char orientation; //north, south, east, west

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
     * @return Dörrens riktning utskriven
     */
    public String getCardinalDirection()
    {
        switch(orientation)
        {
            case 'n':
                return "north";
            case 'e':
                return "east";
            case 's':
                return "south";
            case 'w':
                return "west";
            default:
                return "This should never display";
        }
    }
}