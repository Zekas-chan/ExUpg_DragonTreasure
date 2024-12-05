public class Room {
    private String roomDescription;
    private Door[] doors;


    /**
     * Konstruerar ett nytt rum
     * @param description Textbeskrivning av rummet, typ "Det är dammigt och luktar gympaskor"
     * @param doors En array där varje element är en dörr
     * @throws IllegalArgumentException om det är för många dörrar i arrayen
     */
    public Room(String description, Door[] doors) throws IllegalArgumentException
    {
        if (doors.length > 4){
            throw new IllegalArgumentException("Too many doors!");
        }
        this.roomDescription = description;
        this.doors = doors;

    }

    /**
     * Fungerar i nuläget för testning.
     */
    public void doNarrative()
    {
        System.out.println(roomDescription); //rummets beskrivning
        listDoors(); //dörrarna i rummet
    }

    /**
     * Skriver ut en beskrivning av dörrarna i rummet.
     */
    private void listDoors(){
        if(doors.length == 1){
            System.out.println("There is only one door, the one you came from.");
        }//om det bara finns en dörr
        else
        {
            System.out.print("There are " + doors.length + " doors. They point ");
            for (int i = 0; i < doors.length-1; i++)
            {
                System.out.print(doors[i].getCardinalDirection()+", ");
            }
            System.out.print("and "+ doors[doors.length-1].getCardinalDirection()+".");
        }//dörrantal > 1



    }
}

