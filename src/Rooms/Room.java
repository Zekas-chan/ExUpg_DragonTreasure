package Rooms;

import Items.*;
import Creatures.*;
import java.lang.Thread; //Imports to pause during battle

/**
 * @Author Saga Gillback, Ella Ni Chana, Philip Larsson
 */
public class Room {
    private String roomDescription;
    private Door[] doors;
    private Monster monster;
    private Item loot;

    /**
     * Konstruktor #1 - vanligt rum
     * @param description Textbeskrivning av rummet, typ "Det är dammigt och luktar gympaskor"
     * @param doors En array där varje element är en dörr
     * @throws IllegalArgumentException om det är för många eller få dörrar i arrayen
     */
    public Room(String description, Door[] doors) throws IllegalArgumentException {
        if (doors.length > 4 || doors.length < 1) {
            throw new IllegalArgumentException("Invalid amount of doors!");
        }
        this.roomDescription = description;
        this.doors = doors;
        this.monster = null;
    }

    /**
     * Konstruktor #2 - Rum med monster
     * Konstruerar ett rum med fientlig invånare
     * @param monster Monstret som rummet ska ha
     */
    public Room(String description, Door[] doors, Monster monster)
    {
        this(description, doors);
        this.monster = monster;
    }

    /**
     * Konstruktor #3 - Rum med loot
     * Konstruerar ett rum med loot som spelaren kan plocka upp
     * @param loot Det item som går att plocka upp i rummet
     */
    public Room(String description, Door[] doors, Item loot)
    {
        this(description, doors);
        this.loot = loot;
    }

    /**
     * Konstruktor #4 - Rum med både monster och loot
     */
    public Room(String description, Door[] doors, Monster monster, Item loot)
    {
        this(description, doors);
        this.monster = monster;
        this.loot = loot;
    }

    public Room(String description, Door[] doors, Item loot) throws IllegalArgumentException
    {
        if (doors.length > 4 || doors.length < 1)
        {
            throw new IllegalArgumentException("Invalid amount of doors!");
        }
        this.roomDescription = description;
        this.doors = doors;
        this.loot = loot;
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

    public Monster getMonster() {
        return monster;
    }

    /**
     * Skriver ut beskrivningen på rummet samt dess dörrar om det inte är utgången
     */
    public void doNarrative()
    {
        System.out.println(roomDescription); //rummets beskrivning
        if (hasLoot()) System.out.println("You see " + getLootName() + ". You can pick it up [p]");
        listDoors(); //dörrarna i rummet
    }

    public Item getLoot()
    {
        Item temp = this.loot;
        this.loot = null;
        return temp;
    }

    public String getLootName()
    {
        return this.loot.getName();
    }

    public boolean hasLoot(){
        return this.loot != null;
    }

    public boolean monsterPresent(){
        return monster != null;
        //If there is a monster in the room return true.
    }

    public void doBattle(Player player) throws InterruptedException {
        monster.monsterFlavourText();
        int battleInterval = 400;
        while (monster.isAlive()){
            player.takeDamage(monster.getDamage());//Write out damage
            System.out.printf("%s %s attacks you and does %s damage!\n",monster.getPrefix(), monster.getAttackingName(), monster.getDamage());
            Thread.sleep(battleInterval);
            if (!player.isAlive()){
                return; //Write out that you've died, break the whole game
            }
            monster.takeDamage(player.getDamage());
            System.out.printf("You attack %s and you do %s damage!\n",monster.getDefendingName(), player.getDamage());
            Thread.sleep(battleInterval);
            if (!monster.isAlive()){
                System.out.println("You've slain the creature!");
            }
        }
        System.out.printf("Your remaining health is %s.", player.getHealth());
        //While monster ==isAlive
        /*Player.Player health, Player.Monster health, Player.Player dmg, Player.Monster dmg,
        * doBattle loop
        * Player.Monster dmg
        * Player.Player.isAlive check (If player is dead, break and exit game)
        * Player.Player dmg
        * Player.Monster.isAlive check (When monster dies, isAlive = false)*/
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