package communityDVDLibrary;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Title {

    private final String name;
    private final String director;
    private final int releaseYear;

    private int availableCopies = 0;

    private final Queue<Member> reservationQueue = new ArrayBlockingQueue<Member>(10);
    private final Queue<Member> priorityReservationQueue = new ArrayBlockingQueue<Member>(10);

    private EmailAlert emailAlert = new EmailAlert();

    public Title(String name, String director, int releaseYear) {

        this.name = name;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public void registerNewCopy() {

        availableCopies += 1;

        sendCopyAvailableEmailAlert();
    }

    public void borrowCopy() {

        if (availableCopies > 0) {
            availableCopies -= 1;
        } else {
            throw new RuntimeException(String.format("There are no copies of [%s] available", name));
        }
    }

    public void returnCopy() {

        availableCopies += 1;

        sendCopyAvailableEmailAlert();
    }

    private void sendCopyAvailableEmailAlert() {

        if (priorityReservationQueue.peek() != null) {
            emailAlert.sendReservationAlertEmail(priorityReservationQueue.poll());
        } else if (reservationQueue.peek() != null) {
            emailAlert.sendReservationAlertEmail(reservationQueue.poll());
        }
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getAvailableCopies() {

        return availableCopies;
    }

    public void addToReservationQueue(Member member) {

        reservationQueue.add(member);
    }

    public Queue<Member> getReservationQueue() {

        return reservationQueue;
    }

    public void addToPriorityReservationQueue(Member member) {

        priorityReservationQueue.add(member);
    }

    public Queue<Member> getPriorityReservationQueue() {

        return priorityReservationQueue;
    }

    public void setEmailAlert(EmailAlert emailAlert) {
        this.emailAlert = emailAlert;
    }
}
