package Rooms;//We need to import player, it's moved to its own package
import Player.*;
import com.sun.source.tree.WhileLoopTree;

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Room {
    private String roomDescription;
    private Door[] doors;
    private Monster monster;

    /**
     * Konstruerar ett nytt rum
     *
     * @param description Textbeskrivning av rummet, typ "Det är dammigt och luktar gympaskor"
     * @param doors       En array där varje element är en dörr
     * @throws IllegalArgumentException om det är för många eller få dörrar i arrayen
     */

    public Room(String description, Door[] doors, Monster monster) throws IllegalArgumentException
    {
        if (doors.length > 4 || doors.length < 1)
        {
            throw new IllegalArgumentException("Invalid amount of doors!");
        }
        this.roomDescription = description;
        this.doors = doors;
        this.monster = monster;
    }
    public Room(String description, Door[] doors) throws IllegalArgumentException {
        if (doors.length > 4 || doors.length < 1) {
            throw new IllegalArgumentException("Invalid amount of doors!");
        }
        this.roomDescription = description;
        this.doors = doors;
        this.monster = null;
    }
    //getters
    public String getRoomDescription()
    {
        return roomDescription;
    }

    //getter för dörrarrayen
    public Door[] getDoors()
    {
        return doors;
    }

    /**
     * Skriver ut beskrivningen på rummet samt dess dörrar om det inte är utgången
     */
    public void doNarrative()
    {
        System.out.println(roomDescription); //rummets beskrivning
        listDoors(); //dörrarna i rummet
    }

    public boolean monsterPresent(){
        return monster != null;
        //If there is a monster in the room return true.
    }

    public void doBattle(Player player)
    {
        monster.monsterFlavourText();
        while (monster.isAlive()){
            player.takeDamage(monster.getDamage());//Write out damage
            System.out.printf("%s %s attacks you and does %s damage!\n",monster.getPrefix(), monster.getAttackingName(), monster.getDamage());
            if (!player.isAlive()){
                return; //Write out that you've died, break the whole game
            }
            monster.takeDamage(player.getDamage());
            System.out.printf("You attack %s and you do %s damage!\n",monster.getDefendingName(), player.getDamage());
            if (!monster.isAlive()){
                System.out.println("You've slain the creature!");
            }
        }
        System.out.println("Your remaining health is " + player.getHealth() + ".");//Printf?
        //While monster ==isAlive
        /*Player.Player health, Rooms.Monster health, Player.Player dmg, Rooms.Monster dmg,
        * doBattle loop
        * Rooms.Monster dmg
        * Player.Player.isAlive check (If player is dead, break and exit game)
        * Player.Player dmg
        * Rooms.Monster.isAlive check (When monster dies, isAlive = false)*/
        //You defeated the monster!
        //"Your remaining health is " Player.Player.getHealth + "."
    }

    /**
     * Skriver ut en beskrivning av dörrarna i rummet.
     */
    private void listDoors()
    {
        if (doors.length == 1)
        {
            System.out.println("There is only one door, the way back where you came from. You can only go " + doors[0].getMovementHint());
        }//om det bara finns en dörr, precis i början
        else
        {
			for (Door door : doors)
			{
				System.out.println("You can go " + door.getMovementHint());
			}//dörrantal > 1
        }
    }
}