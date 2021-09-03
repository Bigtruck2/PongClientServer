package ml.bigtruck2.pong.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListen implements KeyListener {
    private final boolean[] keys = new boolean[256];
    public boolean w,s,up,down;
    public void tick(){
        w = keys[KeyEvent.VK_W];
        s = keys[KeyEvent.VK_S];
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
