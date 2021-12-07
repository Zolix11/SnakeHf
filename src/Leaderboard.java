import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Ez az osztály kezeli a játék egyjátékosmód eredményeit, melyet egy Arraylistben tárol és kiment egy json fájlba.
 */
public class Leaderboard {

    private final ArrayList<Player> leaderboardlist;

    /**
     * A konstruktor beolvassa a json fájltartalmát a privát változójába.
     */
    public Leaderboard() {
        leaderboardlist = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {

            Object array = jsonParser.parse(new FileReader("leaderboard.json"));
            JSONArray jsonArray = (JSONArray) array;
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject certainplayer = (JSONObject) jsonObject.get("Player");
                String nickname = String.valueOf(certainplayer.get("Nickname"));
                int score = Integer.parseInt(String.valueOf(certainplayer.get("Score")));
                leaderboardlist.add(new Player(nickname, score));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Frissíti a arraylist tartalmát, ha az elért pontja a játékosnak több mint 2 és rendezi csökkenő sorrendbe a collectiont.
     * @param player egy Player típusú strutúra
     */
    public void updateLeaderboard(Player player) {

        if(player.getScore()>=3){
            leaderboardlist.add(player);
            Collections.sort(leaderboardlist, (e1, e2) -> Integer.compare(e2.getScore(), e1.getScore()));
        }
    }

    /**
     * Kiírja a arraylist tartalmát a json fájlba.
     */
    public void writeOutLeaderboard() {
        try (FileWriter fw = new FileWriter("leaderboard.json")) {
            JSONArray array = arraylistTojsonArray();
            fw.write(array.toJSONString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visszaalakítja a az arraylistet a megfelelő json struktúrájó tömbbé.
     * @return JSONARRAY típusú tömböt ad vissza
     */
    public JSONArray arraylistTojsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Player p : leaderboardlist) {
            JSONObject certainpalyer = new JSONObject();
            certainpalyer.put("Nickname", p.getName());
            certainpalyer.put("Score", p.getScore());

            JSONObject player = new JSONObject();
            player.put("Player", certainpalyer);
            jsonArray.add(player);
        }
        return jsonArray;
    }

    /**
     * @return a privátmezőben található arraylistet, kirajzoláshoz van használva
     */
    public ArrayList<Player> getLeaderboardlist() {
        return leaderboardlist;
    }
}
