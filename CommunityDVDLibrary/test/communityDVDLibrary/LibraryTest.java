package communityDVDLibrary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LibraryTest {

    Library library;

    @Mock
    Title mockTitle;

    @Mock
    Member mockMember;

    @Mock
    EmailAlert mockEmailAlert;

    private final String testEmail = "JohnSmith@test.com";
    private final String testName = "John Smith";

    @Before
    public void setUp() throws Exception {

        library = new Library();

        MockitoAnnotations.initMocks(this);

        library.setEmailAlert(mockEmailAlert);
    }

    @Test
    public void canDonateTitles() throws Exception {

        library.donate(mockTitle, mockMember);
        assertTrue(library.listTitles().contains(mockTitle));

        verify(mockMember).awardDonationPriorityPoints();
        verify(mockTitle).registerNewCopy();
    }

    @Test
    public void addsNewTitlesToNewTitleList() throws Exception {

        library.donate(mockTitle, mockMember);

        assertTrue(library.listNewTitles().contains(mockTitle));
    }

    @Test
    public void canSendWeeklyNewsletter() throws Exception {

        library.sendWeeklyNewsletter();

        verify(mockEmailAlert).sendWeeklyNewsletter(library.listNewTitles());
    }

    @Test
    public void existingTitlesNotAddedToNewTitlesList() throws Exception {

        library.donate(mockTitle, mockMember);

        library.sendWeeklyNewsletter();

        library.donate(mockTitle, mockMember);

        assertFalse(library.listNewTitles().contains(mockTitle));
    }

    @Test
    public void canAddNewMembersToLibrary() throws Exception {

        Member expectedMember = new Member(testName, testEmail);

        library.addNewMember(testName, testEmail);

        assertTrue(library.getMemberList().contains(expectedMember));
        verify(mockEmailAlert).sendWelcomeEmail(expectedMember);
    }

    @Test(expected = RuntimeException.class)
    public void cannotAddExistingMemberToLibrary() throws Exception {

        library.addNewMember(testName, testEmail);
        library.addNewMember(testName, testEmail);
    }
}
