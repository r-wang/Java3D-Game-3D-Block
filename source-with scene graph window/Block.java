package blocksGame;

import java.util.*;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;

/**
 * <p>
 * Block is the basic unit composing a board in each level
 * </p>
 * 
 * @author Ruiqi Wang
 * @version 1.0.0
 */

public class Block {
	private Appearance ap = new Appearance();

	private Box box;

	private boolean isLandedOn;

	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f blue = new Color3f(0.3f, 0.3f, 0.8f);
	Color3f specular = new Color3f(0.9f, 0.9f, 0.9f);

	Material blueMat = new Material(blue, blue, blue, specular, 25.0f);

	private boolean isRolledOver;

	private int x;

	private int y;

	private int color;

	/**
	 * Initializes a block with both positions to arguments.
	 * 
	 * @param iniX
	 *            x-dimension relative position
	 * @param iniY
	 *            y-dimension relative position
	 */

	public Block(int iniX, int iniY) {

		blueMat.setLightingEnable(true);
		blueMat.setDiffuseColor(black);
		x = iniX;
		y = iniY;
		isLandedOn = false;
		isRolledOver = false;
		boxGridScene();
	}

	/**
	 * Sets the x-dimension position of the block @ param newX x-dimension
	 * position of the block
	 */
	public void setX(int newX) {
		x = newX;
	}

	/**
	 * Gets the x-dimension position of the block
	 * 
	 * @return newX x-dimension position of the block
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the y-dimension position of the block
	 * 
	 * @param newY
	 *            y-dimension position of the block
	 */
	public void setY(int newY) {
		y = newY;
	}

	/**
	 * Gets the y-dimension position of the block
	 * 
	 * @return newY y-dimension position of the block
	 */
	public int getY() {
		return y;
	}

	/**
	 * Renders the block
	 * 
	 * @return A TransformGroup for the scene of a block
	 */
	public TransformGroup boxGridScene() {

		ap.setCapability(Appearance.ALLOW_MATERIAL_READ);
		ap.setCapability(Appearance.ALLOW_MATERIAL_WRITE);

		ap.setMaterial(blueMat);

		Transform3D t3d = new Transform3D();
		t3d.set(new Vector3f(1.2f * (x + 1), 1.2f * (y + 1), 0));
		TransformGroup tg = new TransformGroup(t3d);
		Box box = new Box(0.5f, 0.5f, 0.5f, Box.GENERATE_NORMALS, ap);

		tg.addChild(box);
		return tg;

	}

	/**
	 * Updates the color of the block to mark it as stepped one
	 * 
	 */
	public void updateBoxGridColor() {
		blueMat = new Material(black, black, blue, specular, 25.0f);
		ap.setMaterial(blueMat);
	}

	/**
	 * Checks if this block in the board is being landed on
	 * 
	 * @return If this block is landed on
	 */
	public boolean isLandedOn() {
		return isLandedOn;
	}

	/**
	 * Marks the block as being landed on or not
	 * 
	 * @param bool
	 *            If the block is being landed on
	 */
	public void setLandedOn(boolean bool) {
		isLandedOn = bool;
	}

	/**
	 * Checks if this block in the board has been stepped on yet
	 * 
	 * @return If this block has been stepped on
	 */
	public boolean isRolledOver() {
		return isRolledOver;
	}

	/**
	 * Marks this block as having been stepped on already or not
	 * 
	 * @param bool
	 *            If this block has been stepped on
	 */
	public void setRolledOver(boolean bool) {
		isRolledOver = bool;
	}

	/**
	 * Gets current color of this block
	 * 
	 * @return current color of this block
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Sets current color of this block
	 * 
	 * @param newColor
	 *            New color to be assigned to this block
	 */
	public void setColor(int newColor) {
		color = newColor;
	}

}
