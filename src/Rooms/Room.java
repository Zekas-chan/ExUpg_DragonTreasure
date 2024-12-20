package Rooms;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Room {
    private final String roomDescription;
    private final Door[] doors;

    /**
     * Konstruerar ett nytt rum
     *
     * @param description Textbeskrivning av rummet, typ "Det är dammigt och luktar gympaskor"
     * @param doors       En array där varje element är en dörr
     * @throws IllegalArgumentException om det är för många eller få dörrar i arrayen
     */
    public Room(String description, Door[] doors) throws IllegalArgumentException
    {
        if (doors.length > 4 || doors.length < 1)
        {
            throw new IllegalArgumentException("Invalid amount of doors!");
        }
        this.roomDescription = description;
        this.doors = doors;
    }

    //getters
    public String getRoomDescription()
    {
        return roomDescription;
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
        listDoors(); //dörrarna i rummet
    }

    public void doBattle()
    {
        //TODO implementation
    }

    /**
     * Skriver ut en beskrivning av dörrarna i rummet.
     */
    private void listDoors()
    {
        if (doors.length == 1)
        {
            System.out.println("There is only one door, the way back where you came from. You can only go " + doors[0].getMovementHint());
        }//om det bara finns en dörr, precis i början
        else
        {
			for (Door door : doors)
			{
				System.out.println("You can go " + door.getMovementHint());
			}//dörrantal > 1
        }
    }
}