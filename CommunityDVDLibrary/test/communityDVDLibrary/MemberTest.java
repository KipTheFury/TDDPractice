package communityDVDLibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import junitparams.JUnitParamsRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(JUnitParamsRunner.class)
public class MemberTest {

    Member member;

    @Mock
    private Title mockTitle_TheMatrix;

    @Mock
    private Title mockTitle_Avatar;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        member = new Member("TestName", "TestEmail");
    }

    @Test
    public void canAwardDonationPriorityPoints() {

        member.awardDonationPriorityPoints();

        assertEquals(10, member.getPriorityPoints());

    }

    @Test
    public void canBorrowDvd() throws Exception {

        member.borrowDVD(mockTitle_TheMatrix);

        assertTrue(member.getBorrowList().containsKey(mockTitle_TheMatrix));

        verify(mockTitle_TheMatrix).borrowCopy();
    }

    @Test(expected = RuntimeException.class)
    public void cannotBorrowMoreThanOneCopyOfATitle() throws Exception {

        member.borrowDVD(mockTitle_TheMatrix);
        member.borrowDVD(mockTitle_TheMatrix);
    }

    @Test
    public void canGetDueDateOfReturnOneWeekInAdvance() throws Exception {

        LocalDate nextWeek = LocalDate.now().plusWeeks(1);
        assertEquals(nextWeek, member.borrowDVD(mockTitle_TheMatrix));
    }

    @Test
    public void canListRentedTitlesAndDueDates() throws Exception {

        member.borrowDVD(mockTitle_TheMatrix);
        member.borrowDVD(mockTitle_Avatar);

        assertTrue(member.getBorrowList().containsKey(mockTitle_Avatar));
        assertTrue(member.getBorrowList().containsKey(mockTitle_TheMatrix));
        assertTrue(member.getBorrowList().containsValue(LocalDate.now().plusWeeks(1)));
    }

    @Test
    public void canReturnBorrowedDVDOnTimeForPriorityPoints() throws Exception {

        member.borrowDVD(mockTitle_Avatar);

        member.returnDVD(mockTitle_Avatar);

        assertFalse(member.getBorrowList().containsKey(mockTitle_Avatar));
        assertEquals(5, member.getPriorityPoints());

        verify(mockTitle_Avatar).returnCopy();
    }

    @Test
    public void noPriorityPointsIfDVDIsReturnedLate() throws Exception {

        Clock tenDaysAhead = Clock.fixed(Instant.now().plus(Duration.ofDays(10)), ZoneId.systemDefault());

        member.borrowDVD(mockTitle_Avatar);

        member.setClock(tenDaysAhead);

        member.returnDVD(mockTitle_Avatar);

        assertEquals(0, member.getPriorityPoints());
    }

    @Test(expected = RuntimeException.class)
    public void cannotReturnDVDThatMemberHasntBorrowed() throws Exception {

        member.returnDVD(mockTitle_TheMatrix);
    }

    @Test
    public void canReserveTitle() throws Exception {

        member.reserveTitle(mockTitle_Avatar, false);

        verify(mockTitle_Avatar).addToReservationQueue(member);
    }

    @Test
    public void canSpend20PriorityPointsToPriorityreserveTitle() throws Exception {

        member.awardDonationPriorityPoints();
        member.awardDonationPriorityPoints();

        member.reserveTitle(mockTitle_Avatar, true);

        assertEquals(0, member.getPriorityPoints());
        verify(mockTitle_Avatar).addToPriorityReservationQueue(member);

    }

    @Test(expected = RuntimeException.class)
    public void cannotPriorityReserveWithoutEnoughPriorityPoints() throws Exception {

        member.awardDonationPriorityPoints();
        member.reserveTitle(mockTitle_Avatar, true);
    }
}
