package ml.bigtruck2.pong;

import javax.swing.*;
import java.awt.*;

public class Window {
    private final JFrame jFrame;
    private final Dimension dimension;
    private final String title;
    private final Canvas canvas = new Canvas();
    public Window(Dimension dimension,String title){
        jFrame = new JFrame();
        this.dimension = dimension;
        this.title = title;
    }
    public void create(){
        jFrame.setSize(dimension);
        jFrame.setResizable(false);
        jFrame.setTitle(title);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        canvas.setSize(dimension);
        jFrame.add(canvas);
        jFrame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
