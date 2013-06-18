package blocksGame;

import java.util.*;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.interpolators.RotPosScaleTCBSplinePathInterpolator;
import com.sun.j3d.utils.behaviors.interpolators.TCBKeyFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;

/**
 * Player steps on the collections of blocks which compose a board for the game
 * 
 * @author qiqi
 * 
 */
public class Player {

	private int x = 0;

	private int y = 0;

	private Transform3D t3d;

	TransformGroup tg;

	/**
	 * Constructs a player with the specified position
	 * 
	 * @param inix
	 *            x-dimension position
	 * @param iniy
	 *            y-dimension position
	 */
	public Player(int inix, int iniy) {
		x = inix;
		y = iniy;
	}

	/**
	 * Gets x-dimension position
	 * 
	 * @return x-dimension position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets x-dimension position
	 * 
	 * @param newX
	 *            x-dimension position
	 */
	void setX(int newX) {
		x = newX;
	}

	/**
	 * Gets y-dimension position
	 * 
	 * @return y-dimension position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets y-dimension position
	 * 
	 * @param newY
	 *            y-dimension position
	 */
	public void setY(int newY) {
		y = newY;
	}

	/**
	 * Renders the scene of the player
	 * 
	 * @return A transformGroup for the scene of the player
	 */
	public TransformGroup playerScene() {
		int flags = ObjectFile.RESIZE;
		ObjectFile f = new ObjectFile(flags);
		Scene s = null;
		try {
			s = f.load("diamond.obj");
		} catch (Exception e) {
			System.out.println("error :" + e.toString());
		}
		BranchGroup root = s.getSceneGroup();

		t3d = new Transform3D();

		t3d.setTranslation(new Vector3f(1.2f * (x + 1) - 0.4f,
				1.2f * (y + 1) + 0.3f, 1.5f));

		t3d.rotY(-Math.PI / 2);

		tg = new TransformGroup(t3d);

		tg.setTransform(t3d);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(root);

		return tg;
	}

	/**
	 * Updates the position of the player
	 * 
	 * @param x
	 *            x-dimension position
	 * @param y
	 *            y-dimension position
	 */
	public void update(int x, int y) {

		t3d.setTranslation(new Vector3f(1.2f * (x + 1) - 0.4f,
				1.2f * (y + 1) + 0.3f, 1.5f));
		tg.setTransform(t3d);
	}

}
