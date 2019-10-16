package by.dma.arrays;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:39
 * @since : 2019.07
 **/
class Player {
    private String first;
    private String last;
    private int score;
    private Club club;

    Player(String first, String last, int score) {
        this.first = first;
        this.last = last;
        this.score = score;
    }

    String getFirst() {
        return first;
    }

    String getLast() {
        return last;
    }

    int getScore() {
        return score;
    }

    public Club getClub() {
        return club;
    }

    public Player setClub(Club club) {
        this.club = club;
        return this;
    }

    @Override
    public String toString() {
        return "Player{"
                + "club=" + club
                + ", first='" + first + '\''
                + ", last='" + last + '\''
                + ", score=" + score
                + '}';
    }
}
