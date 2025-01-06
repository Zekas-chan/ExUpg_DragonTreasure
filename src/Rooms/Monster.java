package Rooms;

public class Monster extends Creature {
	private String attackingName;
	private String prefix;
	private String defendingName;
	private String flavourText;

	//constructor for monster
	public Monster (String attackingName, String prefix, String defendingName, String flavourText, int health, int damage) {//health and damage to set for monster & dragon separately
		super(health, damage); //Calls fighting and readies it to be set at the creation of the monsters
		this.attackingName = attackingName;
		this.flavourText = flavourText;
		this.prefix = prefix;
		this.defendingName = defendingName;
    }
	public Monster (String attackingName, String prefix, String defendingName, int health, int damage) {
		this(attackingName,prefix, defendingName, null, health, damage); //Overload the constructor to make sure the dragon ascii art isn't printed with the monster
	}
	public String getAttackingName (){
		return attackingName;
	}
	public String getPrefix(){
		return prefix;
	}
	public String getDefendingName(){
		return defendingName;
	}

	//public void monsterDesc(){System.out.println("En arg " + name + " attackerar dig!");}

	public void monsterFlavourText(){
		if (flavourText != null)
			System.out.println(flavourText);//If the flavourtext isn't null (Set to ascii art when making the dragon) then we print it
	}
}


