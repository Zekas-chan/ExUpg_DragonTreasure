import java.util.Scanner;

/**
 * @Author Saga Gillback, Ella Ni Chana , Philip/Linnea Larsson
 */
public class DragonTreasure {
    public static void main(String[] args) {

        DragonTreasure dragon_treasure = new DragonTreasure();
        System.out.println("Created dragontreasure"); //debug
        Dungeon dungeon = dragon_treasure.setupGame();//Runs the dungeon setup code, stores separately in memory, stores in dungeon and returns the current placement
        System.out.println("Created dungeon"); //debug

        dungeon.playGame();
    }//main

    public DragonTreasure()
    {
        //nothing needs doing
    }

    public Dungeon setupGame()
    {
        /* 4x4 map

        null    dead    monster    exit
        Start   torch    null       null
        null    key      pot        dragon/treasure
         */
        Door[] door_start = {new Door('e', false)}; //door(s) for the room
        Room start = new Room("You see a cave entrance", door_start); //the room

        Door[] door_torch = {new Door('n', false), new Door('s', false)};
        Room torch = new Room("The door to the west has collapsed. You see a torch on the wall and two doors", door_torch);

        Door[] door_dead = {new Door('s', false), new Door('e', false)};
        Room dead = new Room("You see a dead person in the corner and two doors", door_dead);

        Door[] door_monster = {new Door('w', false), new Door('e', false), new Door('s', false)};
        Room monster = new Room("You smell a musky smell and see three doors", door_monster);

        Door[] door_potion = {new Door('n', false), new Door('w', false), new Door('e', false)};
        Room potion = new Room("You hear a low growling and see three doors", door_potion);

        Door[] door_key = {new Door('n', false), new Door('e', false)};
        Room key = new Room("You hear water nearby and see two doors", door_key);

        Door[] door_treasure = {new Door('w', false)};
        Room treasure = new Room("Wow, TREASURE", door_treasure);

        Door[] door_exit = {new Door('w', false)};
        Room exit = new Room("Congrats, you made it", door_exit, true);

        Room[][] map =
                {
                    {null, dead, monster, exit}, //array [0][n]
                    {start, torch, null, null}, //array [1][n]
                    {null, key, potion, treasure}, //array [2][n]
                };


        System.out.println("Name your hero: ");
        Scanner input = new Scanner(System.in);//Creates a scanner and pauses the program to wait for user input
        Player player = new Player(input.nextLine());//Assigns "player" the name that was input

        return new Dungeon(map, "Welcome to the dungeon", player);
    }

    public void endGame()
    {
        System.exit(0);
    }

    private void printTreasure() {
        System.out.println(
                "                  _.--.\n" +
                        "              _.-'_:-'||\n" +
                        "          _.-'_.-::::'||\n" +
                        "     _.-:'_.-::::::'  ||\n" +
                        "   .'`-.-:::::::'     ||\n" +
                        "  /.'`;|:::::::'      ||_\n" +
                        " ||   ||::::::'      _.;._'-._\n" +
                        " ||   ||:::::'   _.-!oo @.!-._'-.\n" +
                        " \'.  ||:::::.-!() oo @!()@.-'_.||\n" +
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

    public void printDragon() {
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