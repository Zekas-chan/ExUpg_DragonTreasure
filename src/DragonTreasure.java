import Player.Player;
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
     * @param args används ej.
     */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        DragonTreasure dragon_treasure = new DragonTreasure();

        //main menu
        while(true)
        {
            System.out.println(
                    "\nWelcome to Dragon Treasure\n" +
                    "Play game [p]\n" +
                    "Show credits [c]\n" +
                    "Quit game [q]\n");
            System.out.print("Enter your choice and press [Enter]: ");
            switch(sc.nextLine().charAt(0)){
                case 'p':
                    Dungeon dungeon = dragon_treasure.setupGame();//Runs the dungeon setup code, stores separately in memory, stores in dungeon and returns the current placement
                    dungeon.playGame();
                    break;
                case 'c':
                    System.out.println("Made by: Saga Gillback, Linnea Larsson, Ella Ni Chana");
                    break;
                case 'q':
                    System.out.println("Program will exit.");
                    dragon_treasure.endGame();
                    break;
                default:
                    System.out.println("Not a valid option, please try again");
                    break;
            }
        }



    }//main



    /**
     * Skapar spelets dörrar och rum och gör dessa till en map samt skapar spelaren
     * @return Ovanstående i formen av en spelbar dungeon
     */
    public Dungeon setupGame()
    {
        //local variables
        Scanner input = new Scanner(System.in);//Creates a scanner and pauses the program to wait for user input
        levelLoader loader = new levelLoader(); //skapar en level när den anropas

        //create player
        System.out.println("Name your hero: ");
        Player player = new Player(input.nextLine());//Assigns "player" the name that was input

        //select level - mest för debugsyfte, ken ersättas med att hårdkoda level 1 vid inlämning
        System.out.println("Select level (0 - test, 1 - the dragon dungeon): ");
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