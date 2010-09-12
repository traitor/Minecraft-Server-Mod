public class Player {
    public dy player;

    public Player(dy actualPlayer) {
        this.player = actualPlayer;
    }

    public void sendMessage(String msg) {
        player.a.b(new az(msg));
    }

    public void kick(String reason) {
        player.a.c(reason);
    }

    public void ban(String reason) {
    }

    public void banip(String reason) {
    }
}
