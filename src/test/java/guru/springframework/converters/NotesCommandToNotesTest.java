package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

	private static final Long ID_NOTES = 1L;
	private static final String RECIPE_NOTES = "recipe";
	NotesCommandToNotes notesCommandToNotes;

	@Before
	public void setUp() throws Exception {
		notesCommandToNotes = new NotesCommandToNotes();
	}

	@Test
	public void testNullObject() {
		assertNull(notesCommandToNotes.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(notesCommandToNotes.convert(new NotesCommand()));
	}

	@Test
	public void convert() throws Exception {
//		given
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ID_NOTES);
		notesCommand.setRecipeNotes(RECIPE_NOTES);

//		when
		Notes notes = notesCommandToNotes.convert(notesCommand);

//		then
		assertNotNull(notes);
		assertEquals(ID_NOTES, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
	}

}