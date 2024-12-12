import java.util.Scanner;

/**
 * @Author Saga Gillback, Ella Ni Chana , Philip/Linnea Larsson
 */
public class Dungeon {
    private Room currentRoom;
    private String welcomeMessage;
    private Room[][] map;
    private Player player;
    private boolean gameOver;

    private int mapX;
    private int mapY;

    /**
     *
     * @param map
     * @param welcomeMessage
     * @param player
     */
    public Dungeon(Room [][] map, String welcomeMessage, Player player)
    {
        this.map = map;
        this.welcomeMessage = welcomeMessage;
        this.player = player;

        this.currentRoom = map[1][0]; //start room location from SetupGame
        this.mapX = 0;
        this.mapY = 1;
    }

    public void playGame()
    {
        System.out.println(welcomeMessage + ", " + player.getName());

        do //menu loop
        {
            if(currentRoom.isExit()){
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
        }while (!gameOver);
    }

    /**
     * Hjälpmetod till playGame som sköter rörelse.
     * @param input Spelarens avsedda rörelseriktning
     */
    private void move(char input)
    {
        boolean allowed = false;
        for (Door door : currentRoom.getDoors()) //för varje dörr i Doors[]
        {
			if (door.getOrientation() == input && !door.isLocked()) //kolla om en dörr med rätt riktning finns samt om den är låst
			{
				allowed = true; //om inputriktningen == dörrens riktning och den inte är låst
				break; //avsluta loop
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
                }else{
                    System.out.println("You can't go there.");
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
                    System.out.println("You can't go there.");
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
                    System.out.println("You can't go there.");
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
                    System.out.println("You can't go there.");
                }
                break;
            default: //inputs andra än nsew
                System.out.println("Not a valid move");
        }
    }


}
