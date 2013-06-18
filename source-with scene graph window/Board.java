package blocksGame;

import java.awt.Font;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.interpolators.RotPosScaleTCBSplinePathInterpolator;
import com.sun.j3d.utils.behaviors.interpolators.TCBKeyFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Text2D;

/**
 * Board is for to display the text messages to show user the progress and
 * details for the levels of the game
 * 
 * @author Ruiqi Wang
 * 
 */
public class Board {

	Text3D text;
	TransformGroup tg;

	String string;

	Transform3D t = new Transform3D();

	/**
	 * Renders the scene
	 * 
	 */
	public TransformGroup boardScene() {
		return boardScene("", new Vector3f(1.2f * (5 + 1), 1.2f * (13 + 1), 2));
	}

	/**
	 * Renders the scene with specified text
	 * 
	 * @param s
	 *            Text message to be displayed
	 */
	public TransformGroup boardScene(String s) {

		// Create the Font3D object
		Font font2d = new Font("Monospaced", Font.PLAIN, 1);
		Font3D font3d = new Font3D(font2d, new FontExtrusion());
		// Create the Text3D object and add it to a Shape3D object
		text = new Text3D(font3d, s);
		text.setCapability(Text3D.ALLOW_STRING_READ);
		text.setCapability(Text3D.ALLOW_STRING_WRITE);

		text.setAlignment(Text3D.ALIGN_CENTER);
		text.setPath(Text3D.PATH_RIGHT);
		Shape3D shape = new Shape3D(text);

		t.rotX(Math.PI / 4);

		// t . setScale (0.2);
		tg = new TransformGroup(t);
		tg.addChild(shape);

		TCBKeyFrame[] keyFrame = new TCBKeyFrame[2];

		// Create the first key frame at (0.5, 0.5)
		keyFrame[0] = new TCBKeyFrame(0.0f, 0, new Point3f(1.2f * (5 + 0),
				1.2f * (13 + 0), 2), new Quat4f(1.0f, 0.0f, 0.0f,
				(float) (5 * Math.PI / 8)), new Point3f(1.0f, 1.0f, 1.0f),
				0.0f, 0.0f, 0.0f);

		// Create the second key frame at (-0.5, 0.5)
		keyFrame[1] = new TCBKeyFrame(1.0f, 0, new Point3f(1.2f * (5 + 0),
				1.2f * (13 + 0), 1), new Quat4f(1.0f, 0.0f, 0.0f,
				(float) (5 * Math.PI / 8)), new Point3f(1.0f, 1.0f, 1.0f),
				0.0f, 0.0f, 0.0f);

		// Create an Alpha that loops indefinitely for
		// a duration of 4 seconds
		Alpha alpha = new Alpha(-1, 4000);

		// Create the TCB spline path interpolator
		RotPosScaleTCBSplinePathInterpolator i = new RotPosScaleTCBSplinePathInterpolator(
				alpha, tg, new Transform3D(), keyFrame);
		BoundingSphere s1 = new BoundingSphere(new Point3d(),
				Double.POSITIVE_INFINITY);
		i.setSchedulingBounds(s1);
		tg.addChild(i);

		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		return tg;

	}

	/**
	 * Renders the area with specified text and position
	 * 
	 * @param s
	 *            Text message to be displayed
	 * @param v
	 *            position to display the message
	 */
	public TransformGroup boardScene(String s, Vector3f v)
	// A shiny blue sphere located at (0,4,0)
	{

		// Create the Font3D object
		Font font2d = new Font("Monospaced", Font.PLAIN, 1);
		Font3D font3d = new Font3D(font2d, new FontExtrusion());
		// Create the Text3D object and add it to a Shape3D object
		text = new Text3D(font3d, s);
		text.setCapability(Text3D.ALLOW_STRING_READ);
		text.setCapability(Text3D.ALLOW_STRING_WRITE);

		text.setAlignment(Text3D.ALIGN_CENTER);
		text.setPath(Text3D.PATH_RIGHT);
		Shape3D shape = new Shape3D(text);

		// Scale the Text3D object using a TransformGroup
		t.rotX(Math.PI / 4);

		t.setTranslation(v);

		// t . setScale (0.2);
		tg = new TransformGroup(t);
		tg.addChild(shape);

		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		return tg;
	}

	/**
	 * Updates the text to be displayed
	 * 
	 * @param s
	 *            The text to be displayed
	 */
	public void updateText(String s) {

		text.setString(s);

	}

}
