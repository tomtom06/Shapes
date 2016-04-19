package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import graphics.shapes.SCircle;
import graphics.shapes.ui.Editor;
import graphics.shapes.ui.ShapeDraftman;

public class FontAttributes extends Attributes {

	public static String FONT_ID = "FONT";
	private Font font;
	private Color fontColor;
	
	public FontAttributes() {		
		this.font = new Font(Font.MONOSPACED, Font.PLAIN, 20);		//new Font(String name, int style, int size);
		this.fontColor = Color.BLACK;
	}
	
	public FontAttributes(Font font, Color fontColor) {
		this.font = font;
		this.fontColor = fontColor;
	}
	
	public Font font() {
		return this.font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public Color fontColor() {
		return this.fontColor;
	}
	
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
	public String getId() {
		return FONT_ID;
	}
	
	public Rectangle getBounds(String s) {
		FontRenderContext context = ShapeDraftman.shapesView.getGraphics().getFontMetrics().getFontRenderContext();
		return font.getStringBounds(s, context).getBounds();
	}
}