package engine;

import Creatures.Player;
import Rooms.*;

import java.util.Scanner;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 *
 * Definierar ett spelbart dungeonobjekt
 */
public class Dungeon {
    private Room currentRoom; //rummet spelaren befinner sig i
    private String welcomeMessage; //välkomstmeddelande
    private Room[][] map; //spelnivåns layout
    private Player player; //spelaren
    private int mapX; //koordinatvariabler
    private int mapY;

    /**
     * Konstruktor för dungeon. Måste ha:
     * @param map som representerar spelnivå
     * @param welcomeMessage string för välkomstmeddelande
     * @param player ett spelarobjekt
     */
    public Dungeon(Room [][] map, String welcomeMessage, Player player)
    {
        this.map = map;
        this.welcomeMessage = welcomeMessage;
        this.player = player;

        //Hitta startrummet
        findStart: //loop label
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                if (map[i][j] instanceof StartRoom)
                {
                    this.currentRoom = map[i][j];
                    this.mapX = j;
                    this.mapY = i;
                    break findStart;
                }

            }
        }
    }

    /**
     * Kör spelet genom en loop som frågar efter vad spelaren vill göra
     */
    public void playGame() throws InterruptedException {
        //lokala variabler
        Scanner reader = new Scanner(System.in);
        char user_input;
        boolean shouldNarrate = true;

        //välkomna spelaren
        System.out.println(welcomeMessage + ", " + player.getName());
        System.out.println("You can exit the game to the main menu at any time by entering [q]");

        GameMenu:
        do //menu loop start
        {
            System.out.println(); //mellanrum för varje handling spelaren tar

            if (currentRoom.monsterPresent()&& currentRoom.getMonster().isAlive()) currentRoom.doBattle(player);//Kollar om rummet har ett monster samt om det är vid liv. Spelaren skickas då in i doBattle loopen
            if (!player.isAlive()) { //Om spelaren failar sin isAlive check = är stendöd, kommer main menyn visas igen
                System.out.println("You've died! D: You will be sent back to the main menu.");
                break;
            }

            //beskriv rummet
            if(shouldNarrate) currentRoom.doNarrative();

            //key hints - om spelaren har items som kan användas
            if(player.hasPotion())
            {
                System.out.println("To use a potion, press [h]");
            }
            boolean needsKey = false;
            for(Door door : currentRoom.getDoors()){
                if(door.isLocked()){
                    needsKey = true;
                    break;
                }
            }//Lägger till så programmet inte konstant skriver ut "to use the key..." när vi bara har nyckeln i inventory
            if(player.hasKey()&& needsKey)
            {
                System.out.println("To use the key, press [u]");
            }

            System.out.print("What will you do?: ");

            try
            {
                user_input = Character.toLowerCase(reader.nextLine().charAt(0));//converts the user input into a char and takes the first letter, to compare
            } catch (StringIndexOutOfBoundsException e) //om input är tom
            {
                user_input = 'x'; //går till default case
            }
            switch (user_input)
            {
                case 'p': //för att plocka upp items
                    if(currentRoom.hasLoot())
                    {
                        shouldNarrate = false;
                        System.out.println("You pick up the " + currentRoom.getLootName());
                        player.pickUpItem(currentRoom.getLoot());//Spelare plockar upp item och skriver ut the key hints
                    }else
                    {
                        System.out.println("There is nothing to pick up.");
                    }
                    break;
                case 'h':
                    player.usePotion();
                    break;
                case 'u':
                    if(player.hasKey())
                    {
                        for(Door door : currentRoom.getDoors())
                        {
                            if(door.isLocked())
                            {
                                door.unlockDoor();
                            }
                        }
                    }
                    break;
                case 'n': //alla leder till movement
                case 'w':
                case 's':
                case 'e':
                    shouldNarrate = move (user_input); //om rörelse händer returneras true och doNarrative körs för nästa rum
                    break;
                case 'q': //avslutar programmet
                    System.out.println("Exiting to main menu.");
                    break GameMenu;
                case 'x': //om user_input är tom, för att nå default
                default: //ogiltig input
                    System.out.println("That is not a valid input. Try again.");
                    shouldNarrate = false;
                    break;
            }
        }while (!hasWon());//While loop körs tills hasWon är sant (spelaren befinner sig i utgången)
    }

    /**
     * Kontrollerar om spelaren är i utgången och avslutar spelet.
     * @return true eller false, beroende på om spelaren är i ett ExitRoom.
     */
    private boolean hasWon(){
        if(currentRoom instanceof ExitRoom && player.hasTreasure())
        { //spelaren kan bara vinna i slutrummet
            printTreasure();
            System.out.println("You leave the cave with the treasure and your life. Congratulations, you won!");
            return true;
        }else if (currentRoom instanceof ExitRoom)
        {
            System.out.println("You found the exit. But did you forget something?");
            return true;
        }
        return false;
    }

    /**
     * Hjälpmetod till playGame som sköter rörelse.
     * @param input Spelarens avsedda rörelseriktning
     * @return Sant om spelaren rör sig, annars false
     */
    private boolean move(char input)
    {
        boolean allowed = false; //om rörelsen tillåts
        String cancelMessage = "Not a valid direction."; //meddelande om rörelsen inte tillåts; default

        for (Door door : currentRoom.getDoors()) //för varje dörr i Doors[]
        {
			if (door.getOrientation() == input && !door.isLocked()) //om en dörr med rätt riktning finns samt om den är olåst
			{
				allowed = true; //om inputriktningen == dörrens riktning och den inte är låst
				break; //avsluta loop
            }
            else if (door.getOrientation() == input && door.isLocked()) //om en dörr med rätt riktning finns men den är låst
            {
                //TODO behöver kanske uppdateras när vi har nycklar
                cancelMessage = "The door is locked.";
                break;
            }
            else //om ingen dörr i den riktningen hittas
            {
                cancelMessage = "There is no door there.";
            }

        }
        if(!allowed) //om allowed aldrig blev sant, avbryt rörelsen
        {
            System.out.println(cancelMessage);
            return allowed;
        }
        switch(input)
        {
            case 'n':
                do
                {
                    currentRoom = map[--mapY][mapX]; //inkrementering FÖRE variabeln används
                }
                while (currentRoom == null); //åtminstone en gång, tills antingen ett rum nås eller IndexOutOfBoundsException kastas
                return allowed;
            case 'e': //alla andra cases är samma mönster men i andra riktningar
                do
                {
                    currentRoom = map[mapY][++mapX];
                }
                while (currentRoom == null);
                return allowed;
            case 's':
                do
                {
                    currentRoom = map[++mapY][mapX];
                }
                while (currentRoom == null);
                return allowed;
            case 'w':
                do
                {
                    currentRoom = map[mapY][--mapX];
                }
                while (currentRoom == null);
                return allowed;
            default: //inputs andra än nsew
                System.out.println(cancelMessage);
                return allowed;
        }
    }

    /**
     * Skriver ut en skatt
     */
    private void printTreasure()
    {
        System.out.println(
                "                  _.--.\n" +
                        "              _.-'_:-'||\n" +
                        "          _.-'_.-::::'||\n" +
                        "     _.-:'_.-::::::'  ||\n" +
                        "   .'`-.-:::::::'     ||\n" +
                        "  /.'`;|:::::::'      ||_\n" +
                        " ||   ||::::::'      _.;._'-._\n" +
                        " ||   ||:::::'   _.-!oo @.!-._'-.\n" +
                        " '.  ||:::::.-!() oo @!()@.-'_.||\n" +
                        "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n" +
                        "     `>'-.!@%()@'@_%-'_.-o _.|'||\n" +
                        "      ||-._'-.@.-'_.-' _.-o  |'||\n" +
                        "      ||=[ '-._.-\\U/.-'    o |'||\n" +
                        "      || '-.]=|| |'|      o  |'||\n" +
                        "      ||      || |'|        _| ';\n" +
                        "      ||      || |'|    _.-'_.-'\n" +
                        "      |'-._   || |'|_.-'_.-'\n" +
                        "      '-._'-.|| |' `_.-'\n" +
                        "           '-.||_/.-'\n");
    }
}