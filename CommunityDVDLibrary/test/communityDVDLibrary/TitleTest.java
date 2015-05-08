package communityDVDLibrary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(JUnitParamsRunner.class)
public class TitleTest {

	@Mock
	EmailAlert mockEmailAlert;

	Title title;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		title = new Title("The Abyss", "James Cameron", 1989, mockEmailAlert);
	}

	@Test
	public void canGetTitleNameDirectorAndReleaseYear() {

		assertEquals(title.getName(), "The Abyss");
		assertEquals(title.getDirector(), "James Cameron");
		assertEquals(title.getReleaseYear(), 1989);
	}

	@Test
	@Parameters({ "1", "2", "3" })
	public void canRegisterRentalCopies(int copies) throws Exception {

		for (int i = 0; i < copies; i++) {
			title.registerNewCopy();
		}

		assertEquals(title.getRentalCopies(), copies);
		verify(mockEmailAlert, times(copies)).sendEmail();
	}

}
