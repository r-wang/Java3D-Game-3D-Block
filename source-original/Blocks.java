package blocksGame;

import java.util.*;

import javax.media.j3d.BranchGroup;
/**
 * This class represents a collection of blocks which compose
 * a board for each level of the game 
 * 
 * @author qiqi
 *
 */
public class Blocks {

	private Vector blocks = new Vector();
	BranchGroup bg = new BranchGroup();
	
	/**
	 * Renders the scene of the blocks
	 *
	 * @return a BranchGroup for the scene of the blocks
	 */
	public  BranchGroup blocksScene(){
		
		Iterator iterator = blocks.iterator();
		
		while(iterator.hasNext()){
			bg.addChild(((Block) iterator.next()).boxGridScene());
		}
		return bg;
	}
	
	/**
	 * Get a block by position
	 * 
	 * @param x x-dimentsion 
	 * @param y y-dimentsion
	 * @return The block in the specific dimensin of the board composed by blocks
	 */
	public Block getBlock(int x, int y){
		Iterator iterator = blocks.iterator();
		while(iterator.hasNext()){
			Block b = (Block) iterator.next();
			if ((b.getX()==x)&&(b.getY()==y)) {
				return b;
			}
		}
		return null;
	}
	
	/**
	 * Gets an iterator for the blocks
	 * 
	 * @return Iterator of the collection of blocks
	 */
	public Iterator getIterator() {
		
		return blocks.iterator();
		
	}
	
	/**
	 * Adds a block to the collection of blocks
	 * 
	 * @param block  The block to be added
	 */
	public void addBlock(Block block){
		blocks.addElement(block);
	}
	
}
