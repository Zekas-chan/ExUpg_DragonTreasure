import Player.Player;
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

        //Hitta startrummet - måste ligga sist pga return, eller om det finns något sätt att avbryta båda loops samtidigt.
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
                    break findStart; //osäker på hur jag bryter båda loops annars
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
            //TODO item check, monster check, player death check(kanske sker i doBattle?)
            System.out.println(); //mellanrum för varje handling spelaren tar

            if (currentRoom.monsterPresent()&& currentRoom.getMonster().isAlive()) currentRoom.doBattle(player);
            if (!player.isAlive()) {
                System.out.println("You've died! D:");
                break;
            }
            //om det finns monster sker en strid
            //if (currentRoom.monsterPresent) currentRoom.doBattle();

            //beskriv rummet och eventuella invånare
            if(shouldNarrate) currentRoom.doNarrative();

            //om det finns items ska deras beskrivning och ledtråd för att plocka upp visas
            //if (currentRoom.hasItem) System.out.println("You see" + currentRoom.getItem.getItemDescription + ". You can pick it up [p]")

            System.out.print("What will you do?: ");
            user_input = Character.toLowerCase(reader.nextLine().charAt(0));//converts the user input into a char and takes the first letter, to compare
            //FIXME kastar IndexOutOfBoundsException om spelaren inte ger någon input
            switch (user_input)
            {
                case 'p': //för att plocka upp items
                    //TODO implementation
                    shouldNarrate = false;
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
                default: //ogiltig input
                    System.out.println("That is not a valid input. Try again.");
                    shouldNarrate = false;
                    break;
            }
        }while (!hasWon());//While loop körs tills hasWon är sant (spelaren befinner sig i utgången)
    }

    /**
     * Kontrollerar om spelaren är i utgången och avslutar spelet.
     * TODO olika prints beroende på om spelaren har skatt med sig eller inte.
     * @return true eller false, beroende på om spelaren är i ett ExitRoom.
     */
    private boolean hasWon(){
        if(currentRoom instanceof ExitRoom)
        { //spelaren kan bara vinna i slutrummet
            printTreasure();
            System.out.println("You leave the cave with the treasure and your life. Congratulations, you won!");
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

    /**
     * Skriver ut en drake
     */
    private void printDragon()
    {
        System.out.println(
                "                                                  .~))>>\n" +
                        "                                                 .~)>>\n" +
                        "                                               .~))))>>>\n" +
                        "                                             .~))>>             ___\n" +
                        "                                           .~))>>)))>>      .-~))>>\n" +
                        "                                         .~)))))>>       .-~))>>)>\n" +
                        "                                       .~)))>>))))>>  .-~)>>)>\n" +
                        "                   )                 .~))>>))))>>  .-~)))))>>)>\n" +
                        "                ( )@@*)             //)>))))))  .-~))))>>)>\n" +
                        "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n" +
                        "            (( @.@).              //))))) .-~)>>)))))>>)>\n" +
                        "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n" +
                        "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n" +
                        "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n" +
                        "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n" +
                        "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n" +
                        "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n" +
                        "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n" +
                        " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n" +
                        " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n" +
                        "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n" +
                        "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n" +
                        "       (*.@*              / ((((        \\| | |  \\       .       b `.\n" +
                        "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n" +
                        "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n" +
                        "                       /   /   `(((((()    )    (((((~      `,  b  ;\n" +
                        "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n" +
                        "                 _.-~_.-~           /  /'                _.'~bb _.'\n" +
                        "               ((((~~              / /'              _.'~bb.--~\n" +
                        "                                  ((((          __.-~bb.-~\n" +
                        "                                              .'  b .~~\n" +
                        "                                              :bb ,' \n" +
                        "                                              ~~~~\n");
    }
}