package Rooms;

public class StartRoom extends Room{

	/**
	 * Identisk till Room konstruktor #1
	 */
	public StartRoom(String description, Door[] doors) throws IllegalArgumentException
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
