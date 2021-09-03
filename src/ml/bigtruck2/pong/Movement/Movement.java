package ml.bigtruck2.pong.Movement;

import ml.bigtruck2.pong.Game.Game;

public abstract class Movement {
    private int velx = 2;
    private int vely = 2;
    private final Game game;
    public Movement(Game game){
        this.game = game;
    }
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
        if(game.getKeyListen().down){
            if(game.yb < 600-150) {
                game.yb += 10;
            }
        }
        if(game.getKeyListen().up){
            if(game.yb > 0) {
                game.yb -= 10;
            }
        }
        hitCheck();
        scoreCheck();

    }
    public void hitCheck(){
        if (game.bally > 570) {
            vely *= -1;
        }
        if (game.bally < 0) {
            vely *= -1;
        }



        if (game.bally > game.ya && game.bally < game.ya + 150 && game.ballx <= 50 && game.ballx >= 45) {
            velx *= -1;
            game.ballx = 50;
            System.out.println("hit a");
        }
        if (game.bally > game.yb && game.bally < game.yb + 150 && game.ballx >= 720 && game.ballx <= 725) {
            velx *= -1;
            game.ballx = 720;
            System.out.println("hit b");

        }
        game.ballx += velx;
        game.bally += vely;
    }
    public void scoreCheck(){
        if (game.ballx > 780) {
            game.ballx = 385;
            game.bally = 285;
            velx *= -1;
            game.scoreA += 1;
        }
        if (game.ballx < 0) {
            game.ballx = 385;
            game.bally = 285;
            velx *= -1;
            game.scoreB += 1;
        }
    }
}
