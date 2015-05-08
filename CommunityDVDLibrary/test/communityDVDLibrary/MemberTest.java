package communityDVDLibrary;

import static org.junit.Assert.assertEquals;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class MemberTest {

	Member member;

	@Before
	public void setUp() throws Exception {
		member = new Member();
	}

	@Test
	@Parameters({ "10", "20", "30" })
	public void canAddPriorityPoints(int points) {

		member.awardPriorityPoints(points);

		assertEquals(member.getPriorityPoints(), points);

	}
}
