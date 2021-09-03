package ml.bigtruck2.pong.Game;

import ml.bigtruck2.pong.Movement.MoveClient;
import ml.bigtruck2.pong.Movement.MoveMulti;
import ml.bigtruck2.pong.Movement.MoveServer;
import ml.bigtruck2.pong.Movement.Movement;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private final Canvas canvas;
    private Thread thread;
    private boolean running;
    private final KeyListen keyListen = new KeyListen();
    public int scoreA = 0;
    public int scoreB = 0;
    public int ya = 225;
    public int yb = 225;
    public int ballx = 400;
    public int bally = 300;
    private Movement move;
    public Game(Canvas canvas, Mode mode){
        this.canvas = canvas;
        switch (mode) {
            case SINGLEPLAYER -> move = new MoveClient(this);
            case MULTIPLAYER -> move = new MoveMulti(this);
            case SERVER -> move = new MoveServer(this);
        }
    }
    public void tick(){
        keyListen.tick();
        move.tick();
    }
    public void render(){
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        if(bufferStrategy == null){
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();


        graphics.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Default", Font.PLAIN, 24));
        graphics.drawString("Player 1:" + scoreA + "Player 2:" + scoreB,100,100);
        graphics.fillRect(20,ya,30,150);
        graphics.fillRect(750,yb,30,150);
        graphics.fillRect(ballx,bally,30,30);

        bufferStrategy.show();
        graphics.dispose();
    }
    @Override
    public void run() {
        canvas.addKeyListener(keyListen);
        int fps = 60;
        double tpt = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        while (running){
            now = System.nanoTime();
            delta += (now-lastTime)/tpt;
            timer += now-lastTime;
            lastTime = now;

            if(delta>=1) {
                render();
                tick();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000){
                ticks=0;
                timer=0;

            }
        }
        stop();
    }
    public synchronized void start(){
        if (running)
            return;
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyListen getKeyListen() {
        return keyListen;
    }
}
