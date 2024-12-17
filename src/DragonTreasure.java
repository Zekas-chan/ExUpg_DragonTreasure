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
        //local variables
        Scanner input = new Scanner(System.in);//Creates a scanner and pauses the program to wait for user input
        levelLoader loader = new levelLoader(); //skapar en level när den anropas

        //create player
        System.out.println("Name your hero: ");
        Player player = new Player(input.nextLine());//Assigns "player" the name that was input

        //select level
        System.out.println("Select level(0, 1): ");
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
    public void printDragon()
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
}//class