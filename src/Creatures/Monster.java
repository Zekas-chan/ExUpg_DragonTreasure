package Creatures;

public class Monster extends Creature {
	private String attackingName;
	private String prefix;
	private String defendingName;
	private String flavourText;

	//constructor for monster
	public Monster (String attackingName, String prefix, String defendingName, String flavourText, int health, int damage) {
		super(health, damage); //Calls fighting and readies it to be set at the creation of the monsters
		this.attackingName = attackingName;
		this.flavourText = flavourText;
		this.prefix = prefix;
		this.defendingName = defendingName;
    }
	public Monster (String attackingName, String prefix, String defendingName, int health, int damage) {
		this(attackingName,prefix, defendingName, null, health, damage); //Overload the constructor för att se till att inmatningen blir null när vi inte vill skriva ut ascii arten
	}
	//Just Prefix här är mindre viktigt eftersom vi har spelet på engelska men det funkar väl nog ändå
	public String getAttackingName (){
		return attackingName;
	}//Attacking name = dragon, drake, odjur
	public String getPrefix(){
		return prefix;
	}//Prefix = A, En, Ett
	public String getDefendingName(){
		return defendingName;
	}//Defending name = the dragon, draken, odjuret
	/*Alltså pretty much
	* en drake attackerar dig!
	* drake, attacking name
	* En, prefix
	* du attackerar draken
	* draken, defending name
	* */

	public void monsterFlavourText(){
		if (flavourText != null)
			System.out.println(flavourText);//If the flavourtext isn't null skrivs ascii art av draken ut, gör så att den inte råkas skriva ut på monster
	}
}


