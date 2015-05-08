package communityDVDLibrary;

public class Title {

	private String name;
	private String director;
	private int releaseYear;
	private int rentalCopies = 0;
	private EmailAlert emailAlert;

	public Title(String name, String director, int releaseYear,
			EmailAlert emailAlert) {

		this.name = name;
		this.director = director;
		this.releaseYear = releaseYear;

		this.emailAlert = emailAlert;
	}

	public void registerNewCopy() {

		rentalCopies += 1;
		emailAlert.sendEmail();

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

	public int getRentalCopies() {

		return rentalCopies;
	}

}
