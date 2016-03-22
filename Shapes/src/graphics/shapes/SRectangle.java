package graphics.shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {

	private Rectangle rect;
	
	public SRectangle(int largeur, int longueur) {
		this.rect = new Rectangle(0, 0, largeur, longueur);
	}
	
	public SRectangle(Point p, int largeur, int longueur) {
		this.rect = new Rectangle(p.x, p.y, largeur, longueur);
	}
	
	public Rectangle getRectangle() {
		return this.rect;
	}

	public Point getLoc() {
		return this.rect.getLocation();
	}

	public void setLoc(Point p) {
		this.rect.setLocation(p);
		
	}

	public void translate(int x, int y) {
		int dx = x - this.rect.x;
		int dy = y - this.rect.y;
		this.setLoc(new Point(dx, dy));
	}

	public Rectangle getBounds() {
		return this.rect;
	}

	public void accept(ShapeVisitor visitor) {
		// TODO Auto-generated method stub
	}

	public void draw(Graphics g) {
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
	
}