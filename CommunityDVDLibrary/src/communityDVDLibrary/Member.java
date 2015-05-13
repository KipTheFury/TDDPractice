package communityDVDLibrary;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Member {

    private static final int PRIORITY_RESERVE_POINTS = 20;
    private static final int ON_TIME_RETURN_POINTS = 5;
    private static final int DONATION_POINTS = 10;

    private int priorityPoints;
    private final Map<Title, LocalDate> borrowList = new HashMap<Title, LocalDate>();

    private Clock clock = Clock.systemDefaultZone();

    private final String name;
    private final String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void awardDonationPriorityPoints() {

        priorityPoints += DONATION_POINTS;
    }

    public Object getPriorityPoints() {

        return priorityPoints;
    }

    public Map<Title, LocalDate> getBorrowList() {
        return borrowList;
    }

    public LocalDate borrowDVD(Title title) {

        if (borrowList.containsKey(title)) {
            throw new RuntimeException(String.format("You have already borrowed a copy of [%s]", title.getName()));
        } else {

            LocalDate returnDate = LocalDate.now(clock).plusWeeks(1);

            borrowList.put(title, returnDate);
            title.borrowCopy();

            return returnDate;
        }
    }

    public void returnDVD(Title title) {

        if (borrowList.containsKey(title)) {

            if (borrowList.get(title).isAfter(LocalDate.now(clock).minusDays(1))) {

                priorityPoints += ON_TIME_RETURN_POINTS;
            }

            borrowList.remove(title);
            title.returnCopy();

        } else {

            throw new RuntimeException(String.format("You haven't borrowed [%s]", title.getName()));
        }
    }

    public void reserveTitle(Title title, boolean priority) {

        if (priority) {

            if (priorityPoints >= PRIORITY_RESERVE_POINTS) {

                title.addToPriorityReservationQueue(this);
                priorityPoints -= PRIORITY_RESERVE_POINTS;
            } else {

                throw new RuntimeException(String.format(
                        "You don't have enough points to join the priority reserve queue, you need [%d] more",
                        (PRIORITY_RESERVE_POINTS - priorityPoints)));
            }
        } else {

            title.addToReservationQueue(this);
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set a different clock for testing time sensitive actions.
     * 
     * @param clock
     *            the clock to set
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Member)) {
            return false;
        }
        Member other = (Member) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
