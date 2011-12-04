package net.avh4.lifeline.lifelinegalaxy;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneLabel;
import net.avh4.framework.uilayer.scene.SceneLine;
import net.avh4.framework.uilayer.scene.SceneOval;
import net.avh4.framework.uilayer.swing.scene.Font;

public class GalaxyCanvas implements SceneCreator, ClickReceiver {

	private final Scene scene;
	private int lastX = -1;
	private int lastY = -1;
	private int lastColor = 0x00000000;
	private int nodeCount = 0;
	private static final int NODE_WIDTH = 5;

	public static void main(final String args[]) {
		final GalaxyCanvas game = new GalaxyCanvas();
		UILayer.main(game, game, null);
		game.addNode("Ukevember", 700, 100, Color.RED);
	}

	public GalaxyCanvas() {
		scene = new Scene();
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	public void addNode(final String label, final int x, final int y,
			final int color) {
		if (lastColor == color) {
			scene.add(new SceneLine(Color.darken(.5, color), interpolate(lastX,
					x, .9), interpolate(lastY, y, .9),
					interpolate(x, lastX, .9), interpolate(y, lastY, .9)));
		}
		scene.add(new SceneOval(x - NODE_WIDTH / 2, y - NODE_WIDTH / 2,
				NODE_WIDTH, NODE_WIDTH, color));
		scene.add(new SceneLabel(label, x + NODE_WIDTH / 2, y + NODE_WIDTH / 2,
				Font.PFENNIG, 12, color));
		lastX = x;
		lastY = y;
		lastColor = color;
	}

	private int interpolate(final int to, final int from, final double percent) {
		return (int) (percent * to + (1 - percent) * from);
	}

	@Override
	public void click(final int x, final int y) {
		addNode("Node " + nodeCount, x, y, Color.RED);
		nodeCount++;
	}

}
