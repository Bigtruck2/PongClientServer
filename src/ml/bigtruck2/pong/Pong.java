package ml.bigtruck2.pong;

import ml.bigtruck2.pong.Game.Game;
import ml.bigtruck2.pong.Game.Mode;

import javax.swing.*;
import java.awt.*;

public class Pong {
    public static void main(String[] args) {
        Mode mode;
        String[] opt = {"Singleplayer","Multiplayer","Server"};
        int ans = JOptionPane.showOptionDialog(null,"Mode","Mode",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opt,opt[0]);
        switch (ans) {
            case 0 -> mode = Mode.SINGLEPLAYER;
            case 1 -> mode = Mode.MULTIPLAYER;
            case 2 -> mode = Mode.SERVER;
            default -> throw new IllegalStateException("Unexpected value: " + ans);
        }
        System.out.println(mode);
        Dimension dimension = new Dimension(800,600);
        Window window = new Window(dimension,"Pong");
        window.create();
        Game game = new Game(window.getCanvas(),mode);
        game.start();
    }
}
