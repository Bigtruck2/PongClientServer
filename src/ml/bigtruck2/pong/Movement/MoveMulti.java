package ml.bigtruck2.pong.Movement;

import ml.bigtruck2.pong.Game.Game;

import java.io.IOException;
import java.net.*;

public class MoveMulti extends Movement {
    private Game game;
    private boolean receiving = false;
    private DatagramSocket datagramSocket;

    public MoveMulti(Game game) {
        super(game);
        this.game = game;

        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetAddress.getLocalHost(),25577);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(datagramSocket.getInetAddress());

    }
    @Override
    public void tick(){
        int lasty = game.ya;
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
        System.out.println("E");
        if(!receiving&& datagramSocket.getInetAddress() != null&& game.ya != lasty){
            sendYa();
        }

    }
    private synchronized int sendYa() {
        receiving = true;
        try {
            String packet = "01"+ game.ya;
            DatagramPacket datagramPacket = new DatagramPacket(packet.getBytes(),packet.length(), InetAddress.getLocalHost(),25577);
            System.out.println(new String(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength()));
            System.out.println(game.ya);
            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket1 = new DatagramPacket(buffer,0,buffer.length);
            //datagramSocket.receive(datagramPacket1);
            //String packet1 = datagramPacket1.getData().toString();

            //System.out.println(packet1);
            datagramSocket.send(datagramPacket);
            System.out.println("sent");


        } catch (IOException e) {
            e.printStackTrace();
        }
        receiving = false;
        return 0;
    }

}
