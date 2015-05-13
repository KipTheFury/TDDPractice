package communityDVDLibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    Title title;

    @Mock
    Member mockMember;

    @Mock
    EmailAlert mockEmailAlert;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        title = new Title("The Abyss", "James Cameron", 1989);
        title.setEmailAlert(mockEmailAlert);

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

        assertEquals(title.getAvailableCopies(), copies);
    }

    @Test
    public void canBorrowCopies() throws Exception {

        title.registerNewCopy();
        title.borrowCopy();

        assertEquals(0, title.getAvailableCopies());
    }

    @Test(expected = RuntimeException.class)
    public void cannotBorrowTitleWhenNoRentalCopiesAvailable() throws Exception {

        title.borrowCopy();
    }

    @Test
    public void canReturnCopies() throws Exception {

        title.returnCopy();

        assertEquals(1, title.getAvailableCopies());
    }

    @Test
    public void canAddMemberToReservationList() throws Exception {

        title.addToReservationQueue(mockMember);

        assertTrue(title.getReservationQueue().contains(mockMember));
    }

    @Test
    public void canAddMemberToPriorityReservationList() throws Exception {

        title.addToPriorityReservationQueue(mockMember);

        assertTrue(title.getPriorityReservationQueue().contains(mockMember));
    }

    @Test
    public void emailAlertSentToTopPriorityReservationMemberWhenNewCopyAdded() throws Exception {

        title.addToPriorityReservationQueue(mockMember);

        title.registerNewCopy();

        verify(mockEmailAlert).sendReservationAlertEmail(mockMember);
    }

    @Test
    public void emailAlertSentToTopReservationMemberWhenNewCopyAddedAndNoPriorityReservations() throws Exception {

        title.addToReservationQueue(mockMember);

        title.registerNewCopy();

        verify(mockEmailAlert).sendReservationAlertEmail(mockMember);
    }

    @Test
    public void emailAlertSentToTopPriorityReservationMemberWhenCopyReturned() throws Exception {

        title.addToPriorityReservationQueue(mockMember);

        title.returnCopy();

        verify(mockEmailAlert).sendReservationAlertEmail(mockMember);
    }

    @Test
    public void emailAlertSentToTopReservationMemberWhenCopyReturnedAndNoPriorityReservations() throws Exception {

        title.addToReservationQueue(mockMember);

        title.returnCopy();

        verify(mockEmailAlert).sendReservationAlertEmail(mockMember);
    }

}
