

public class Car {
	private int startPosX;
	private int startPosY;
	private int endPosX;
	private int endPosY;
	private boolean hasMoved;
	private boolean horizontal;
	private int id;

	public Car(int startPosX, int startPosY, int endPosX, int endPosY, boolean horizontal, boolean hasMoved, int id) {
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		this.endPosX = endPosX;
		this.endPosY = endPosY;
		this.horizontal = horizontal;
		this.hasMoved = hasMoved;
		this.id = id;
	}


	public int getStartPosX() { return this.startPosX; }
	public int getStartPosY() { return this.startPosY; }
	public int getEndPosX() { return this.endPosX; }
	public int getEndPosY() { return this.endPosY; }
	public boolean getHorizontal() { return this.horizontal; }
	public boolean getHasMoved() { return this.hasMoved; }
	public int getId() { return this.id; }
	public void setStartPosX(int x) {
		this.startPosX = x;
	}
	public void setStartPosY(int y) {
		this.startPosY = y;
	}
	public void setEndPosX(int x) {
		this.endPosX = x;
	}
	public void setEndPosY(int y) {
		this.endPosY = y;
	}
	public void setHasMoved(boolean moved) {
		this.hasMoved = moved;
	}
}