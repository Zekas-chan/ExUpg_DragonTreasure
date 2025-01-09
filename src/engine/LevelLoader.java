package engine;

import Creatures.Monster;
import Items.*;
import Rooms.*;

/**
 * Håller spelets alla rum samt en metod för att välja bland dem.
 */
public class LevelLoader {

	public Room[][] selectLevel (int level)
	{
		switch (level)
		{
			case 0:
				return test();
			case 1:
				return Level_1();
			default:
				return test();
		}
	}

	/**
	 * Testlevel, endast för testsyfte för oss, men roligt att ha!
	 * @return En enklare level som bara går rakt i en riktning.
	 */
	private Room[][] test()
	{
		Door[] door_start = {new Door('w', false)};
		Room start = new StartRoom("Right start, w", door_start);

		Door[] test1_door =
				{
					new Door('w', false),
					new Door('e', false)
				};
		Monster testMonster = new Monster("monster", "A", "the monster", printDragon(), 6, 1);

		Weapon weapon  = new Weapon("SWORD","sworder", 3, "swordest");

		Potion testPot = new Potion("potion", "heals you", 5);

		Treasure testSwag = new Treasure("chest of treasure", "much coin", 3 );

		Key testKey = new Key("A key", "key");
		Room test1 = new Room("WHERE POTION", test1_door, testSwag);

		Door[] fight_door =
				{
						new Door('w', false),
						new Door('e', false)
				};
		Room fight = new Room ("Fight time", fight_door);

		Door[] door_exit = {new Door('e', false)};
		Room exit = new ExitRoom("Test room exit", door_exit);

		return new Room[][]{{exit, fight, test1, start}};

	}

	/**
	 * Level 1, från klassdiagrammet.
	 * @return En array av rum som har dörrar.
	 */
	private Room[][] Level_1(){
		/* 4x3 map

        null    dead    monster     exit
        Start   torch   null        null
        null    key     potion      dragon/treasure
         */
		Door[] door_start = {new Door('e', false)}; //door(s) for the room
		StartRoom start = new StartRoom("You see a cave entrance to the east. To enter the cave, type \"e\" and [Enter].", door_start); //the room

		Door[] door_torch =
				{
						new Door('n', false),
						new Door('s', false)
				};
		Room torch = new Room("The door to the west has collapsed behind you. You see a torch on the wall.", door_torch);

		Door[] door_dead =
				{
						new Door('s', false),
						new Door('e', false)
				};
		Item lootWeapon = new Weapon("sword", "", 3, ""); //Skapar item och möjligt monster, läggs in i rummet
		Room dead = new Room("You see a dead person in the corner.", door_dead, lootWeapon);

		Door[] door_monster =
				{
						new Door('w', false),
						new Door('e', true),
						new Door('s', false)
				};
		Monster fightMonster = new Monster("monster", "A", "the monster",10,1);
		Room monster = new Room("You smell a musky smell.", door_monster, fightMonster);

		Door[] door_potion =
				{
						new Door('n', false),
						new Door('w', false),
						new Door('e', true)
				};
		Item lootPotion = new Potion("health potion", "", 4);
		Room potion = new Room("You hear a low growling.", door_potion, lootPotion);

		Door[] door_key =
				{
						new Door('n', false),
						new Door('e', false)
				};
		Item lootKey = new Key("key", "");
		Room key = new Room("You hear water rushing nearby.", door_key, lootKey);

		Door[] door_treasure = {new Door('w', false)};
		Item lootTreasure = new Treasure("chest of wonders", "filled with delights", 4);
		Monster dragon = new Monster("dragon", "A", "the dragon", printDragon(), 18,1);
		Room treasure = new Room("WOW, you see TREASURE!", door_treasure, dragon, lootTreasure); //Syns bättre, item och monster skapas och läggs in i rummet.

		Door[] door_exit = {new Door('w', false)};
		ExitRoom exit = new ExitRoom("Congratulations, you made it!", door_exit);

		return new Room[][]
				{
					{null, dead, monster, exit}, //array [0][n]
					{start, torch, null, null}, //array [1][n]
					{null, key, potion, treasure}, //array [2][n]
				};
	}
	private String printDragon() //Funktionen för att skriva ut drakens acsii art
	{
		return
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
						"                                              ~~~~\n";
	}
}
