import java.awt.GraphicsEnvironment;


public class Dim {
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	
	static int getScreenWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}
	
	static int getScreenHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
