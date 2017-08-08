package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	private static final Long ID_UNIT = 1L;
	private static final String DESCRIPTION_UNIT = "description";


	UnitOfMeasureCommandToUnitOfMeasure unitConverter;

	@Before
	public void setUp() throws Exception {
		unitConverter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testNullParameter() {
		assertNull(unitConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(unitConverter.convert(new UnitOfMeasureCommand()));
	}

	@Test
	public void convert() throws Exception {
//		given
		UnitOfMeasureCommand unit = new UnitOfMeasureCommand();
		unit.setId(ID_UNIT);
		unit.setDescription(DESCRIPTION_UNIT);

//		when
		UnitOfMeasure unitOfMeasure = unitConverter.convert(unit);

//		then
		assertNotNull(unitOfMeasure);
		assertEquals(ID_UNIT, unitOfMeasure.getId());
		assertEquals(DESCRIPTION_UNIT, unitOfMeasure.getDescription());

	}

}