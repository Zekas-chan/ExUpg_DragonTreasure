import Rooms.*;

import java.util.Scanner;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 * Klassvariabler, konstruktorer, getters/setters, andra metoder
 */
public class DragonTreasure {
    private final String welcomeMessage;

    /**
     * Mer av en konfig än konstruktor
     */
    public DragonTreasure()
    {
        this.welcomeMessage = "Welcome to the dungeon";
    }

    /**
     * Startar spelet
     * @param args
     */
    public static void main(String[] args)
    {
        DragonTreasure dragon_treasure = new DragonTreasure();

        Dungeon dungeon = dragon_treasure.setupGame();//Runs the dungeon setup code, stores separately in memory, stores in dungeon and returns the current placement

        dungeon.playGame();
    }//main



    /**
     * Skapar spelets dörrar och rum och gör dessa till en map samt skapar spelaren
     * @return Ovanstående i formen av en spelbar dungeon
     */
    public Dungeon setupGame()
    {
        System.out.println("Welcome to Dragon Treasure");
        //local variables
        Scanner input = new Scanner(System.in);//Creates a scanner and pauses the program to wait for user input
        levelLoader loader = new levelLoader(); //skapar en level när den anropas

        //create player
        System.out.println("Name your hero: ");
        Player player = new Player(input.nextLine());//Assigns "player" the name that was input

        //select level - mest för debugsyfte, ken ersättas med att hårdkoda level 1 vid inlämning
        System.out.println("Select level(0 - test, 1 - the dungeon): ");
        int level = input.nextInt();
        Room[][] map = loader.selectLevel(level);

		//return playable dungeon
        return new Dungeon(map, welcomeMessage, player);
    }

    /**
     * Avslutar spelet.
     * TODO implementera i del 2
     */
    public void endGame()
    {
        System.exit(0);
    }
}//class