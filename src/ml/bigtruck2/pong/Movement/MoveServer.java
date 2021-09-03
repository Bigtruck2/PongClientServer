package ml.bigtruck2.pong.Movement;

import ml.bigtruck2.pong.Game.Game;

import java.io.IOException;
import java.net.*;

public class MoveServer extends Movement implements Runnable{
    private final int port = 25574;
    private final Game game;
    DatagramSocket datagramSocket;
    private boolean receiving = false;
    public MoveServer(Game game) {
        super(game);
        this.game = game;
        try {
            datagramSocket = new DatagramSocket(25577);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void tick(){

        if(game.getKeyListen().w){
            if(game.ya > 0) {
                game.ya -= 10;
            }
        }
        if (game.getKeyListen().s){
            if(game.ya < 600-150) {
                game.ya += 10;
            }
        }
        hitCheck();
        scoreCheck();
    }
    private synchronized int getYb() {
        receiving = true;
        DatagramPacket datagramPacket = null;
        try {
            byte[] buffer = new byte[1024];
            datagramPacket = new DatagramPacket(buffer,0,buffer.length);
            datagramSocket.receive(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pos = new String(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength()).substring(2);
        int loc;
        if(pos.contains("-")){
            loc = 0;
        }else {
            loc = Integer.parseInt(pos);
        }
        receiving = false;
        return loc;
    }

    @Override
    public void run() {
        while (true){
            game.yb = getYb();
        }
    }
}
