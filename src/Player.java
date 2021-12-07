/**
 * Osztály mely egy játékost reprezentál
 */
public class Player {

    private String name;
    private int score;

    /**
     * @param name a játékos neve
     * @param score a játékos által elért pont, nulla a játék kezdetekor de beolvsásánál már egy konkrét érték.
     */
    public Player(String name, int score){
        this.name=name;
        this.score=score;
    }

    /**
     * Visszaadja a játékos nevét
     * @return a játékos neve
     */
    public String getName() {
        return name;
    }

    /**
     * Visszaadja az adott játékos által elért pontot.
     * @return a játékos pontja
     */
    public int getScore() {
        return score;
    }

    /**
     * Beállítja a játékos által elért pontot.
     * @param score pont, amelyet elért a játékos
     */
    public void setScore(int score) {
        this.score = score;
    }
}