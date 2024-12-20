import Rooms.*;

/**
 * Håller spelets alla rum samt en metod för att välja bland dem.
 */
public class levelLoader{

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
	 * Testlevel
	 * @return En enklare level som bara går rakt i en riktning.
	 */
	private Room[][] test()
	{
		Door[] door_start = {new Door('w', false)};
		Room start = new StartRoom("Right start", door_start);
		Room start2 = new StartRoom("Wrong start", door_start);

		Door[] test1_door =
				{
					new Door('w', false),
					new Door('e', false)
				};
		Room test1 = new Room("Test room 1", test1_door);

		Door[] test2_door =
				{
						new Door('w', false),
						new Door('e', false)
				};
		Room test2 = new Room("Test room 2", test2_door);

		Door[] test3_door =
				{
						new Door('w', false),
						new Door('e', false)
				};
		Room test3 = new Room("Test room 3", test3_door);

		Door[] door_exit = {new Door('e', false)};
		Room exit = new ExitRoom("Test room exit", door_exit);

		return new Room[][]{{exit, test1, start, test2, test3, start2}};

	};

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
		Room torch = new Room("The door to the west has collapsed. You see a torch on the wall.", door_torch);

		Door[] door_dead =
				{
						new Door('s', false),
						new Door('e', false)
				};
		Room dead = new Room("You see a dead person in the corner.", door_dead);

		Door[] door_monster =
				{
						new Door('w', false),
						new Door('e', false),
						new Door('s', false)
				};
		Room monster = new Room("You smell a musky smell.", door_monster);

		Door[] door_potion =
				{
						new Door('n', false),
						new Door('w', false),
						new Door('e', true)
				};
		Room potion = new Room("You hear a low growling.", door_potion);

		Door[] door_key =
				{
						new Door('n', false),
						new Door('e', false)
				};
		Room key = new Room("You hear water nearby.", door_key);

		Door[] door_treasure = {new Door('w', false)};
		Room treasure = new Room("Wow, TREASURE!", door_treasure);

		Door[] door_exit = {new Door('w', false)};
		ExitRoom exit = new ExitRoom("Congratulations, you made it!", door_exit);

		return new Room[][]
				{
					{null, dead, monster, exit}, //array [0][n]
					{start, torch, null, null}, //array [1][n]
					{null, key, potion, treasure}, //array [2][n]
				};
	}
}
