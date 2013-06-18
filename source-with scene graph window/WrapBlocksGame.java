package blocksGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.vp.*;
import com.tornadolabs.j3dtree.Java3dTree;

public class WrapBlocksGame extends JPanel implements KeyListener {

	private static final int PWIDTH = 512;
	private static final int PHEIGHT = 512;

	private static final int BOUNDSIZE = 100;

	private static final Point3d USERPOSN = new Point3d(7.5, -8, 22);
	private TimeBehavior timeBehavior;
	BranchGroup bgTime;
	private SimpleUniverse su;
	private BranchGroup sceneBG;
	private BoundingSphere bounds; // for environment nodes
	private Player player;

	private Level level;
	private Board board;
	private Canvas3D canvas3D;

	private int currentStepCount = 0;
	private boolean isWinLevel = false;
	private boolean isWinGame = false;
	private int currentLevel = 1;
	private int remainingTime = 0;
	private int maxTime = 0;
	
	private Java3dTree j3dTree;

	/**
	 * Initializes the scene of the application
	 */
	public WrapBlocksGame() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
		GraphicsConfiguration config = SimpleUniverse
				.getPreferredConfiguration();
		canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		canvas3D.setFocusable(true); // give focus to the canvas
		canvas3D.requestFocus();
		canvas3D.addKeyListener(this);

		su = new SimpleUniverse(canvas3D);

		j3dTree = new Java3dTree();
		
		level = new Level();
		level.load(1);
		maxTime = level.getMaxTime();
		this.setRemainingTime(level.getMaxTime());

		createSceneGraph();
		initUserPosition(); // set user's viewpoint

		su.addBranchGraph(sceneBG);
		
		j3dTree.updateNodes(su);

	}

	private void createSceneGraph()
	// initilise the scene
	{
		sceneBG = new BranchGroup();
		sceneBG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		sceneBG.setCapability(BranchGroup.ALLOW_DETACH);
		sceneBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		sceneBG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		bounds = new BoundingSphere(new Point3d(0, 0, 0), BOUNDSIZE);

		lightScene(); // add the lights

		sceneBG.addChild(level.getBlocks().blocksScene());

		Blocks blks = level.getBlocks();

		Iterator it = blks.getIterator();
		int tempx = 0;
		int tempy = 0;

		while (it.hasNext()) {
			Block b = (Block) it.next();
			if (b != null) {
				if (b.isLandedOn()) {

					tempx = b.getX();
					tempy = b.getY();
					break;
				}
			}
		}

		board = new Board();

		player = new Player(tempx, tempy);

		sceneBG.addChild(board.boardScene());
		board.updateText("moves:" + currentStepCount + "(max:"
				+ level.getMaxSteps() + ")\n" + " Time Remaining:"
				+ level.getMaxTime());
		sceneBG.addChild(player.playerScene());
		sceneBG.addChild(backGroundScene());

		PointLight light = new PointLight();
		sceneBG.addChild(light);

		timeBehavior = new TimeBehavior(1000, this);

		BoundingSphere infniteBounds = new BoundingSphere(new Point3d(),
				Double.POSITIVE_INFINITY);
		timeBehavior.setSchedulingBounds(infniteBounds);
		timeBehavior.setEnable(true);
		bgTime = new BranchGroup();
		bgTime.addChild(timeBehavior);
		sceneBG.addChild(bgTime);
		bgTime.setCapability(BranchGroup.ALLOW_DETACH);
		
		player.update(tempx, tempy);
		
		
		
		j3dTree.recursiveApplyCapability(sceneBG);
		
		sceneBG.compile(); // fix the scene

	} // end of createSceneGraph()

	private BranchGroup backGroundScene() {
		BranchGroup root = new BranchGroup();
		Texture starTexture = null;
		try {
			// Load the stars texture

			TextureLoader loader = new TextureLoader("sky_and_clouds.JPG", this);
			starTexture = loader.getTexture();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// Create a backgound node with maximum bounds
		Background background = new Background();
		background.setApplicationBounds(new BoundingSphere(new Point3d(),
				Double.MAX_VALUE));

		BranchGroup backgroundGroup = new BranchGroup();
		// Set the texture for the background appearance
		Appearance backgroundAppearance = new Appearance();
		backgroundAppearance.setTexture(starTexture);
		// Create a detailed sphere with normal pointing inwards
		Sphere backgroundSphere = new Sphere(
				0.5f,
				Sphere.GENERATE_TEXTURE_COORDS | Sphere.GENERATE_NORMALS_INWARD,
				100, backgroundAppearance);
		backgroundGroup.addChild(backgroundSphere);
		background.setGeometry(backgroundGroup);
		root.addChild(background);
		// board.rot();
		return root;
	}

	private void lightScene()
	/* One ambient light, 2 directional lights */
	{
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

		// Set up the ambient light
		AmbientLight ambientLightNode = new AmbientLight(white);
		ambientLightNode.setInfluencingBounds(bounds);
		sceneBG.addChild(ambientLightNode);

		// Set up the directional lights
		Vector3f light1Direction = new Vector3f(-1.0f, -1.0f, -1.0f);
		// left, down, backwards
		Vector3f light2Direction = new Vector3f(1.0f, -1.0f, 1.0f);
		// right, down, forwards

		DirectionalLight light1 = new DirectionalLight(white, light1Direction);
		light1.setInfluencingBounds(bounds);
		sceneBG.addChild(light1);

		DirectionalLight light2 = new DirectionalLight(white, light2Direction);
		light2.setInfluencingBounds(bounds);
		sceneBG.addChild(light2);
	} // end of lightScene()

	private void initUserPosition()
	// Set the user's initial viewpoint using lookAt()
	{
		ViewingPlatform vp = su.getViewingPlatform();
		TransformGroup steerTG = vp.getViewPlatformTransform();

		Transform3D t3d = new Transform3D();
		steerTG.getTransform(t3d);

		// args are: viewer posn, where looking, up direction
		t3d.lookAt(USERPOSN, new Point3d(7.5, 8, 0), new Vector3d(0, 1, -1));
		t3d.invert();

		steerTG.setTransform(t3d);
	} // end of initUserPosition()

	private void addBlocks() {
	}

	private boolean isBlocksFinished() {
		Iterator it = level.getBlocks().getIterator();

		while (it.hasNext()) {
			Block block = (Block) it.next();
			if (block.isRolledOver() == false) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Sets the time remained
	 * 
	 * @param newTime
	 *            Time that remains
	 */
	public void setRemainingTime(int newTime) {
		remainingTime = newTime;
	}

	/**
	 * Gets the time remained
	 * 
	 * @return Time that remains
	 */
	public int getRemainingTime() {
		return remainingTime;
	}

	/**
	 * Updates the text to be displayed in the scene
	 */
	public void updateText() {

		if ((this.getRemainingTime() <= 0 || currentStepCount >= level
				.getMaxSteps())
				&& (!this.isBlocksFinished())) {
			board.updateText("Lose");

			this.canvas3D.removeKeyListener(this);
			this.sceneBG.removeChild(bgTime);
			this.canvas3D.removeKeyListener(this);

			Board b = new Board();
			BranchGroup branchGroup = new BranchGroup();
			branchGroup.addChild(b.boardScene("Game Over"));
			this.sceneBG.addChild(branchGroup);

		}

		board.updateText("moves:" + currentStepCount + "(max:"
				+ level.getMaxSteps() + ")\n" + " Time Remaining:"
				+ this.getRemainingTime());

	}

	public void keyPressed(KeyEvent ke) {

		if ((this.getRemainingTime() <= 0 || currentStepCount >= level
				.getMaxSteps() - 1)
				&& (!this.isBlocksFinished())) {

			this.canvas3D.removeKeyListener(this);
			this.sceneBG.removeChild(bgTime);

			Board b = new Board();
			BranchGroup branchgroup = new BranchGroup();
			branchgroup.addChild(b.boardScene("Game Over"));
			this.sceneBG.addChild(branchgroup);
		}

		// TODO Auto-generated method stub

		int xOffset = player.getX();
		int yOffset = player.getY();

		if (isWinGame) {
			isWinGame = true;
			this.canvas3D.removeKeyListener(this);
			this.sceneBG.removeChild(bgTime);

			Board b = new Board();
			BranchGroup branchgroup = new BranchGroup();
			branchgroup.addChild(b.boardScene("Win Game!"));
			this.sceneBG.addChild(branchgroup);
			return;
		}
		if (isWinLevel) {
			isWinLevel = false;
			this.sceneBG.addChild(bgTime);
			if (currentLevel == 3) {
				isWinGame = true;
				this.canvas3D.removeKeyListener(this);
				this.sceneBG.removeChild(bgTime);

				Board b = new Board();
				BranchGroup branchgroup = new BranchGroup();
				branchgroup.addChild(b.boardScene("Win Game!"));
				this.sceneBG.addChild(branchgroup);
				return;
			}

			currentLevel++;
			level.load(currentLevel);
			this.setRemainingTime(level.getMaxTime());
			System.err.println("this.remainingTime: " + this.remainingTime);
			currentStepCount = 0;
			sceneBG.detach();
			createSceneGraph();
			su.addBranchGraph(sceneBG);

			return;
		}

		int k = ke.getKeyCode();
		if (k == KeyEvent.VK_UP)
			yOffset += 1;
		else if (k == KeyEvent.VK_DOWN)
			yOffset -= 1;
		else if (k == KeyEvent.VK_RIGHT)
			xOffset += 1;
		else if (k == KeyEvent.VK_LEFT)
			xOffset -= 1;

		if ((k == KeyEvent.VK_UP) || (k == KeyEvent.VK_DOWN)
				|| (k == KeyEvent.VK_RIGHT) || (k == KeyEvent.VK_LEFT)) {

			Block block = level.getBlocks().getBlock(xOffset, yOffset);

			if (block != null) {

				player.setX(xOffset);
				player.setY(yOffset);
				block.updateBoxGridColor();
				block.setRolledOver(true);

				player.update(xOffset, yOffset);
				currentStepCount++;

				board.updateText("moves:" + currentStepCount + "(max:"
						+ level.getMaxSteps() + ")\n" + " Time Remaining:"
						+ this.getRemainingTime());
			}

			if (isBlocksFinished()) {
				if (currentLevel == 3) {
					isWinGame = true;
					this.canvas3D.removeKeyListener(this);
					this.sceneBG.removeChild(bgTime);

					Board b = new Board();
					BranchGroup branchgroup = new BranchGroup();
					branchgroup.addChild(b.boardScene("Win Game!"));
					this.sceneBG.addChild(branchgroup);

					isWinGame = true;
					return;
				}

				this.sceneBG.removeChild(bgTime);

				Board b = new Board();
				BranchGroup branchgroup = new BranchGroup();
				branchgroup
						.addChild(b
								.boardScene("Win Level! Press any key to continue...."));
				this.sceneBG.addChild(branchgroup);

				isWinLevel = true;

			}

		}

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

} // end of WrapCheckers3D class
