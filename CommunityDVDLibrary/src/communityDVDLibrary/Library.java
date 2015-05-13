package communityDVDLibrary;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Title> titles = new ArrayList<Title>();
    private final List<Title> newTitles = new ArrayList<Title>();

    private EmailAlert emailAlert = new EmailAlert();

    private final List<Member> memberList = new ArrayList<Member>();

    public void addNewMember(String name, String email) {

        Member newMember = new Member(name, email);

        if (memberList.contains(newMember)) {
            throw new RuntimeException(String.format("[%s] is already a member of the Library", name));
        } else {
            memberList.add(newMember);
            emailAlert.sendWelcomeEmail(newMember);
        }
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void donate(Title title, Member member) {

        if (!titles.contains(title)) {
            newTitles.add(title);
        }

        titles.add(title);

        member.awardDonationPriorityPoints();
        title.registerNewCopy();
    }

    public List<Title> listTitles() {

        return titles;
    }

    public List<Title> listNewTitles() {

        return newTitles;
    }

    public void sendWeeklyNewsletter() {
        emailAlert.sendWeeklyNewsletter(newTitles);
        newTitles.clear();
    }

    public void setEmailAlert(EmailAlert emailAlert) {
        this.emailAlert = emailAlert;
    }
}
