import java.util.Scanner;

/**
 * @Author Saga Gillback, Ella Ni Chana , Philip/Linnea Larsson
 */
public class Dungeon {
    private Room currentRoom;
    private String welcomeMessage;

    //testing getter only
    public Room[][] getMap()
    {
        return map;
    }

    private Room[][] map;
    private Player player;
    private boolean gameOver;

    private int mapX;
    private int mapY;


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

        do
        {
            if(currentRoom.isExit()){
                gameOver = true;
                currentRoom.doNarrative();
                return;
            }
            currentRoom.doNarrative();
            Scanner input = new Scanner(System.in);
            char user_input = Character.toLowerCase(input.nextLine().charAt(0));//converts the user input into a char and takes the first letter, to compare
            move (user_input);
        }while (!gameOver);

        currentRoom.doNarrative();
    }

    private void move(char c)
    {
        boolean allowed = false;
        switch(c)
        {
            case 'n':
                for(Door d: currentRoom.getDoors()){
                    if (d.getOrientation() == 'n' && !d.isLocked()){ //if orientation of door matches movement and door is not locked
                        allowed = true;
                        mapY--; //go up one array
                    }
                }
                if(allowed){
                    currentRoom = map[mapY][mapX];
                }else{
                    System.out.println("You can't go there.");
                }
                break;
            case 'e':
                for(Door d: currentRoom.getDoors()){
                    if (d.getOrientation() == 'e' && !d.isLocked()){ //if orientation of door matches movement and door is not locked
                        allowed = true;
                        mapX++; //go up one index
                    }
                }
                if(allowed){
                    currentRoom = map[mapY][mapX];
                }else{
                    System.out.println("You can't go there.");
                }
                break;
            case 's':
                for(Door d: currentRoom.getDoors()){
                    if (d.getOrientation() == 's' && !d.isLocked()){ //if orientation of door matches movement and door is not locked
                        allowed = true;
                        mapY++; //go down one array
                    }
                }
                if(allowed){
                    currentRoom = map[mapY][mapX];
                }else{
                    System.out.println("You can't go there.");
                }
                break;
            case 'w':
                for(Door d: currentRoom.getDoors()){
                    if (d.getOrientation() == 'w' && !d.isLocked()){ //if orientation of door matches movement and door is not locked
                        allowed = true;
                        mapX--; //go down one index
                    }
                }
                if(allowed){
                    currentRoom = map[mapY][mapX];
                }else{
                    System.out.println("You can't go there.");
                }
                break;
            default:
                return;
        }
    }


}
