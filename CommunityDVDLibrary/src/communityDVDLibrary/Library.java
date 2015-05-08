package communityDVDLibrary;

import java.util.ArrayList;
import java.util.List;

public class Library {

	private List<Title> titles = new ArrayList<Title>();
	private List<Title> newTitles = new ArrayList<Title>();

	public void donate(Title title, Member member) {

		titles.add(title);
		newTitles.add(title);

		member.awardPriorityPoints(10);
		title.registerNewCopy();
	}

	public List<Title> listTitles() {

		return titles;
	}

	public List<Title> listNewTitles() {

		return newTitles;
	}
}
