public class Glass {
	private boolean up;
	private boolean down;
	private int x;
	private int y;

	public Glass(int x, int y, boolean up, boolean down) {
		this.up = up;
		this.down = down;
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public void setDown(boolean down) {
		this.down = down;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public boolean getUp() {
		return this.up;
	}
	public boolean getDown() {
		return this.down;
	}
}