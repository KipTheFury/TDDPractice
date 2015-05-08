package communityDVDLibrary;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LibraryTest {

	@Mock
	Title mockTitle;

	@Mock
	Member mockMember;

	Library library;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		library = new Library();
	}

	@Test
	public void canDonateTitles() throws Exception {

		library.donate(mockTitle, mockMember);
		assertTrue(library.listTitles().contains(mockTitle));

		verify(mockMember).awardPriorityPoints(10);
		verify(mockTitle).registerNewCopy();
	}

	@Test
	public void addsNewTitlesToNewTitleList() throws Exception {

		library.donate(mockTitle, mockMember);

		assertTrue(library.listNewTitles().contains(mockTitle));
	}

}
