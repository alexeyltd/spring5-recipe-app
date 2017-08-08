package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

	private static final Long ID_NOTES = 1L;
	private static final String RECIPE_NOTES = "description";
	NotesToNotesCommand command;

	@Before
	public void setUp() throws Exception {
		command = new NotesToNotesCommand();
	}

	@Test
	public void testNullObject() {
		assertNull(command.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(command.convert(new Notes()));
	}

	@Test
	public void convert() throws Exception {
//		given
		Notes notes = new Notes();
		notes.setId(ID_NOTES);
		notes.setRecipeNotes(RECIPE_NOTES);

//		when
		NotesCommand notesCommand = command.convert(notes);

//		then
		assertNotNull(notesCommand);
		assertEquals(ID_NOTES, notesCommand.getId());
		assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
	}

}