package net.avh4.lifeline.lifelinegalaxy;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.hamcrest.MatcherAssert.assertThat;
import net.avh4.framework.uilayer.Color;

import org.junit.Test;

public class GalaxyCanvasTest {

	GalaxyCanvas subject;

	@Test
	public void testBlankCanvas() throws Exception {
		subject = new GalaxyCanvas();
		assertThat(subject, isApproved());
	}

	@Test
	public void testOneNode() throws Exception {
		subject = new GalaxyCanvas();
		subject.addNode("Ukevember", 700, 100, Color.RED);
		assertThat(subject, isApproved());
	}

	@Test
	public void testManyNodes() throws Exception {
		subject = new GalaxyCanvas();
		subject.addNode("Ukevember", 700, 100, Color.RED);
		subject.addNode("Khan Academy", 125, 450, Color.BLUE);
		assertThat(subject, isApproved());
	}

	@Test
	public void testLinkedNodes() throws Exception {
		subject = new GalaxyCanvas();
		subject.addNode("Ukevember", 700, 100, Color.RED);
		subject.addNode("Tutorial Videos", 625, 160, Color.RED);
		assertThat(subject, isApproved());
	}

	@Test
	public void testForkedNodes() throws Exception {
		subject = new GalaxyCanvas();
		subject.addNode("Node 1", 400, 300, Color.RED);
		subject.addNode("Node 2", 400, 150, Color.RED);
		subject.addNode("Node 3", 500, 375, Color.RED);
		assertThat(subject, isApproved());
	}

	@Test
	public void testNodesInLine() throws Exception {
		subject = new GalaxyCanvas();
		subject.addNode("Node 1", 400, 300, Color.RED);
		subject.addNode("Node 2", 400, 150, Color.RED);
		subject.addNode("Node 3", 450, 175, Color.RED);
		assertThat(subject, isApproved());
	}

}
