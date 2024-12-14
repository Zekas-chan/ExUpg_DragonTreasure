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
    private boolean gameOver; //avgör om spelet körs
    private int mapX; //koordinatvariabler
    private int mapY;

    /**
     * Konstruktor för dungeon. Måste ha
     * @param map som representerar spelnivå
     * @param welcomeMessage string för välkomstmeddelande
     * @param player ett spelarobjekt
     */
    public Dungeon(Room [][] map, String welcomeMessage, Player player)
    {
        this.map = map;
        this.welcomeMessage = welcomeMessage;
        this.player = player;

        this.currentRoom = map[1][0]; //start room location from SetupGame
        this.mapX = 0;
        this.mapY = 1;
    }//Makes sure the dungeon is set up properly, as to not start us in a void

    /**
     * Kör spelet genom en loop som frågar efter var spelaren vill gå
     */
    public void playGame()
    {
        System.out.println(welcomeMessage + ", " + player.getName());

        do //menu loop
        {
            System.out.println();
            if(currentRoom instanceof ExitRoom){ //kollar om spelet ska avslutas (om spelaren hittar utgången)
                gameOver = true;
                currentRoom.doNarrative();
                return;
                //FIXME ska skrivas ordentlig spelslutskod i del 2
            }
            currentRoom.doNarrative();
            System.out.print("Where will you go?: ");
            Scanner reader = new Scanner(System.in);
            char user_input = Character.toLowerCase(reader.nextLine().charAt(0));//converts the user input into a char and takes the first letter, to compare

            move (user_input);
        }while (!gameOver);//While loop körs tills gameOver är sant
    }

    /**
     * Hjälpmetod till playGame som sköter rörelse.
     * @param input Spelarens avsedda rörelseriktning
     */
    private void move(char input)
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
                cancelMessage = "The door is locked.";
                break;
            }
            else //om ingen dörr i den riktningen hittas
            {
                cancelMessage = "There is no door there.";
            }

        }

        switch(input)
        {
            case 'n':
                if(allowed){
                    do
                    {
                        currentRoom = map[--mapY][mapX]; //inkrementering FÖRE variabeln används
                    }
                    while (currentRoom == null); //åtminstone en gång, tills antingen ett rum nås eller IndexOutOfBoundsException kastas
                }else{ //om rörelsen inte tillåts
                    System.out.println(cancelMessage);
                }
                break;
            case 'e': //alla andra cases är samma mönster men i andra riktningar
                if(allowed){
                    do
                    {
                        currentRoom = map[mapY][++mapX];
                    }
                    while (currentRoom == null);
                }else{
                    System.out.println(cancelMessage);
                }
                break;
            case 's':
                if(allowed){
                    do
                    {
                        currentRoom = map[++mapY][mapX];
                    }
                    while (currentRoom == null);
                }else{
                    System.out.println(cancelMessage);
                }
                break;
            case 'w':
                if(allowed){
                    do
                    {
                        currentRoom = map[mapY][--mapX];
                    }
                    while (currentRoom == null);
                }else{
                    System.out.println(cancelMessage);
                }
                break;
            default: //inputs andra än nsew
                System.out.println(cancelMessage);
        }
    }
}