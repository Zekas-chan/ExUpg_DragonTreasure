/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Room {
    private final String roomDescription;
    private boolean isExit;//Defines the properties of type Room
    private final Door[] doors;

    /**
     * Konstruerar ett nytt rum
     * @param description Textbeskrivning av rummet, typ "Det är dammigt och luktar gympaskor"
     * @param doors En array där varje element är en dörr
     * @throws IllegalArgumentException om det är för många eller få dörrar i arrayen
     */
    public Room(String description, Door[] doors) throws IllegalArgumentException
    {
        if (doors.length > 4 || doors.length < 1){
            throw new IllegalArgumentException("Invalid amount of doors!");
        }
        this.roomDescription = description;
        this.doors = doors;
    }

    /**
     * TODO ärv ner som subklass istället
     * Konstruktor #2
     * Konstruerar ett nytt AVSLUTsrum
     * @param description Textbeskrivning av rummet, typ "Det är dammigt och luktar gympaskor"
     * @param doors En array där varje element är en dörr
     * @throws IllegalArgumentException om det är för många eller få dörrar i arrayen
     */
    public Room(String description, Door[] doors, boolean isExit) throws IllegalArgumentException
    {
        if (doors.length > 4 || doors.length < 1){
            throw new IllegalArgumentException("Invalid amount of doors!");
        }
        this.roomDescription = description;
        this.doors = doors;
        this.isExit = isExit;

    }

    //Getter for flag isExit
    public boolean isExit()
    {
        return isExit;
    }
    //getter för dörrarrayen
    public Door[] getDoors()
    {
        return doors;
    }

    /**
     * Skriver ut beskrivningen på rummet samt dess dörrar om det inte är utgången
     */
    public void doNarrative()
    {
        System.out.println(roomDescription); //rummets beskrivning
        if(!isExit) {
            listDoors(); //dörrarna i rummet
        }
    }

    /**
     * Skriver ut en beskrivning av dörrarna i rummet, hjälpmetod till doNarrative.
     */
    private void listDoors(){
        if(doors.length == 1){
            //TODO mer generisk för alla rum med en dörr
            System.out.println("There is only one way to go, the entrance to the cave. " + "(" + doors[0].getCardinalDirection() + ")");
        }//om det bara finns en dörr, precis i början
        else
        {
            String useComma = doors.length == 2 ? " " : ", "; //inget komma före "and" om det bara finns två dörrar
            System.out.print("There are " + doors.length + " doors. They point ");
            for (int i = 0; i < doors.length-1; i++)
            {
                System.out.print(doors[i].getCardinalDirection()+useComma);
            }
            System.out.print("and "+ doors[doors.length-1].getCardinalDirection()+".\n");
        }//dörrantal > 1
    }
}