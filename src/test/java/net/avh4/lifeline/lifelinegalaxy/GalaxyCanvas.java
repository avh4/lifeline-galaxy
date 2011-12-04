package net.avh4.lifeline.lifelinegalaxy;

import java.util.ArrayList;
import java.util.HashMap;

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
	private final HashMap<Integer, ArrayList<Node>> lastNodeForColor = new HashMap<Integer, ArrayList<Node>>();
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
		final Node node = getClosestNode(x, y, color);
		if (node != null) {
			scene.add(new SceneLine(Color.darken(.5, color), interpolate(
					node.x, x, .9), interpolate(node.y, y, .9), interpolate(x,
					node.x, .9), interpolate(y, node.y, .9)));
		}
		scene.add(new SceneOval(x - NODE_WIDTH / 2, y - NODE_WIDTH / 2,
				NODE_WIDTH, NODE_WIDTH, color));
		scene.add(new SceneLabel(label, x + NODE_WIDTH / 2, y + NODE_WIDTH / 2,
				Font.PFENNIG, 12, color));
		addNode(x, y, color);
	}

	private void addNode(final int x, final int y, final int color) {
		if (lastNodeForColor.get(color) == null) {
			lastNodeForColor.put(color, new ArrayList<Node>());
		}
		lastNodeForColor.get(color).add(new Node(x, y, color));
	}

	private Node getClosestNode(final int x, final int y, final int color) {
		final ArrayList<Node> points = lastNodeForColor.get(color);
		if (points == null) {
			return null;
		}
		// Find the closest point from the list
		Node closest = null;
		double closestDistance = Double.MAX_VALUE;
		for (final Node n : points) {
			final double distance = Math.sqrt((n.x - x) * (n.x - x) + (n.y - y)
					* (n.y - y));
			if (distance < closestDistance) {
				closestDistance = distance;
				closest = n;
			}
		}
		return closest;
	}

	private int interpolate(final int to, final int from, final double percent) {
		return (int) (percent * to + (1 - percent) * from);
	}

	@Override
	public void click(final int x, final int y) {
		addNode("Node " + nodeCount, x, y, (nodeCount % 2 == 0) ? Color.RED
				: Color.BLUE);
		nodeCount++;
	}
}
