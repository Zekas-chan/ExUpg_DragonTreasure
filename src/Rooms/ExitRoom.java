package Rooms;

public class ExitRoom extends Room{

	/**
	 * Konstruerar ett nytt Exitroom
	 * @param description Textbeskrivning av rummet, typ "Det är dammigt och luktar gympaskor"
	 * @param doors En array där varje element är en dörr
	 * @throws IllegalArgumentException om det är för många eller få dörrar i arrayen
	 */
	public ExitRoom(String description, Door[] doors) throws IllegalArgumentException
	{
		super(description, doors);
	}

	/**
	 * Skriver inte ut en beskrivning av dörrarna, annars identisk
	 */
	@Override
	public void doNarrative()
	{
		System.out.println(super.getRoomDescription());
	}
}