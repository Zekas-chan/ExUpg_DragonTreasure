/**
 * @Author Saga Gillback, Ella Ni Chana , Philip/Linnea Larsson
 */
public class Door {
    private boolean locked;

    public char getOrientation()
    {
        return orientation;
    }

    private char orientation; //north, south, east, west
    Room targetRoom;


    /**
     * Konstruktor för dörr.
     *
     * @param orientation Riktningen i vilken dörren pekar
     */
    public Door(char orientation, boolean locked, Room targetRoom)
    {
        this.orientation = Character.toLowerCase(orientation); //ser till att det alltid är liten bokstav
        this.locked = locked;
        this.targetRoom = targetRoom;
    }

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
    public char getPosition()
    {
        return orientation;
    }

    /**
     *
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