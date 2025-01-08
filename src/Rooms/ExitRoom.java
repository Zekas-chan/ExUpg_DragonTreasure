package Rooms;

public class ExitRoom extends Room{

	/**
	 * Konstruerar ett nytt Exitroom, identisk till konstruktor #1
	 */
	public ExitRoom(String description, Door[] doors) throws IllegalArgumentException
	{
		super(description, doors);
	}

	/**
	 * Skriver inte ut en beskrivning av dörrarna, annars identisk till Room
	 */
	@Override
	public void doNarrative()
	{
		System.out.println(super.getRoomDescription());
	}
}