package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SPolygon;
import graphics.shapes.SPolygonRegulier;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.*;

public class ShapeDraftman implements ShapeVisitor {
	
	public static ShapesView shapesView;
	private Graphics g;
	
	public ShapeDraftman(ShapesView shapesView) {
		ShapeDraftman.shapesView = shapesView;
	}

	public void visitRectangle(SRectangle rectangle) {
		ColorAttributes color = (ColorAttributes) rectangle.getAttributes(ColorAttributes.COLOR_ID);
		selectShape(rectangle);
		if (color.isFilled()) {
			g.setColor(color.filledColor);
			g.fillRect(rectangle.getRectangle().x, rectangle.getRectangle().y, rectangle.getWidth(), rectangle.getHeight());
		}
		if (color.isStroked()) {
			g.setColor(color.strokedColor);
			g.drawRect(rectangle.getRectangle().x, rectangle.getRectangle().y, rectangle.getWidth(), rectangle.getHeight());
		}
	}
	
	public void visitCircle(SCircle cercle) {
		ColorAttributes color = (ColorAttributes) cercle.getAttributes(ColorAttributes.COLOR_ID);
		selectShape(cercle);
		if (color.isFilled()) {
			g.setColor(color.filledColor);
			g.fillOval(cercle.getBounds().x, cercle.getBounds().y, cercle.getBounds().width, cercle.getBounds().height);
		}
		if (color.isStroked()) {
			g.setColor(color.strokedColor);
			g.drawOval(cercle.getBounds().x, cercle.getBounds().y, cercle.getBounds().width, cercle.getBounds().height);
		}
	}
	
	public void visitPolygon(SPolygon polygon) {
		ColorAttributes color = (ColorAttributes) polygon.getAttributes(ColorAttributes.COLOR_ID);
		selectShape(polygon);
		if (color.isFilled()) {
			g.setColor(color.filledColor);
			g.fillPolygon(polygon.x, polygon.y, polygon.nPoints);
		}
		if (color.isStroked()) {
			g.setColor(color.strokedColor);
			g.drawPolygon(polygon.x, polygon.y, polygon.nPoints);
		}
	}
	
	public void visitPolygonRegulier(SPolygonRegulier sPolygonRegulier) {
		ColorAttributes color = (ColorAttributes) sPolygonRegulier.getAttributes(ColorAttributes.COLOR_ID);
		selectShape(sPolygonRegulier);
		if (color.isFilled()) {
			g.setColor(color.filledColor);
			g.fillPolygon(sPolygonRegulier.x, sPolygonRegulier.y, sPolygonRegulier.nPoints);
		}
		if (color.isStroked()) {
			g.setColor(color.strokedColor);
			g.drawPolygon(sPolygonRegulier.x, sPolygonRegulier.y, sPolygonRegulier.nPoints);
		}
	}
	
	public void visitText(SText text) {
		ColorAttributes color = (ColorAttributes) text.getAttributes(ColorAttributes.COLOR_ID);
		FontAttributes font = (FontAttributes) text.getAttributes(FontAttributes.FONT_ID);
		selectShape(text);
		g.setFont(font.font());
		int espaceBas = font.descenderLine(text.getText()); 
		if (color.isFilled()) {
			g.setColor(color.filledColor);
			g.fillRect(text.getBounds().x, text.getBounds().y, font.getBounds(text.getText()).width, font.getBounds(text.getText()).height + espaceBas);
		}
		if (color.isStroked()) {
			g.setColor(color.strokedColor);
			g.drawRect(text.getBounds().x, text.getBounds().y, font.getBounds(text.getText()).width, font.getBounds(text.getText()).height + espaceBas);
		}
		
		g.setColor(font.fontColor());
		g.drawString(text.getText(), text.getBounds().x, text.getBounds().y + text.getBounds().height);
	}
	
	public void visitCollection(SCollection collection) {
		selectShape(collection);
		for (Iterator<Shape> it = collection.iterator(); it.hasNext();) {
			Shape shape = (Shape) it.next();
			shape.accept(this);
		}
	}	
	
	public void selectShape(Shape s) {
		SelectionAttributes selection = (SelectionAttributes) s.getAttributes(SelectionAttributes.SELECTION_ID);
		Rectangle tmp = s.getBounds();
		if(selection.isSelected()) {
			shapesView.getGraphics().setColor(Color.BLUE);
			shapesView.getGraphics().drawRect(tmp.x - 4, tmp.y -4 , 4, 4);
		}
	}
	
	public void setGraphics(Graphics g) {
		this.g = g;
	}
}