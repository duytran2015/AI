package nqueens;

public class Queen {
	private int row;
	private int column;
        private static final int N=17;
	
	public Queen(int r, int c){
		row = r;
		column  = c;
	}
	
	/**
	 * Determines whether this queen can attack another 
	 */
	public boolean canAttack(Queen q){
		boolean canAttack=false;
		
		//test rows and columns
		if(row==q.getRow() || column==q.getColumn())
			canAttack=true;
		//test diagonal
		else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
			canAttack=true;
			
		return canAttack;
	}
	
	/**
	 * moves the piece down
	 */
	public void moveDown(int spaces){
		row+=spaces;
		
		//bound check/reset
		if(row>(N-1) && row%(N-1)!=0){
			row=(row%(N-1))-1;
		}
		else if(row>(N-1) && row%(N-1)==0){
			row=(N-1);
		}
	}
	
	/**
	 * row setter
         */
	public void setRow(int r){
		row = r;
	}
	
	/**
	 * row getter
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * column setter
	 */
	public void setColumn(int c){
		column = c;
	}
	
	/**
	 * column getter
	 */
	public int getColumn(){
		return column;
	}
	
	public String toString(){
		return "("+row+", "+column+")";
	}
}
