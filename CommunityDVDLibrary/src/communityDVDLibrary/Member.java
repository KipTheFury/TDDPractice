package communityDVDLibrary;

public class Member {

	private int priorityPoints;

	public void awardPriorityPoints(int points) {

		priorityPoints += points;
	}

	public Object getPriorityPoints() {

		return priorityPoints;
	}
}
