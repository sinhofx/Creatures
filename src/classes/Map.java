package classes;

import enums.Tile;
import java.util.Random;

public class Map {

    private int sizeX, sizeY;
    private Tile[][] map;
    private Random random;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        map = new Tile[sizeX][sizeY];
        random = new Random();
    }
    
    public void generateRandom() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                map[x][y] = Tile.values()[random.nextInt(2)];
            }
        }
    }

    public void generate() {
        generateRandom();
        smooth(Game.MAP_SIZE_X * 1000); //this is a pretty good metric
        makeMud();
        wrapVoid();
    }

    public void makeMud() {
        for (int y = 0; y < sizeY - 1; y++) {
            for (int x = 0; x < sizeX - 1; x++) {
                if (map[x][y].getCode() != map[x + 1][y].getCode()
                        || map[x][y].getCode() != map[x][y + 1].getCode()) {
                    map[x][y] = Tile.MUD_TILE;
                }
            }
        }
    }

    public void wrapVoid() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (x == 0 || x == sizeX - 1 || y == 0 || y == sizeY - 1) {
                   map[x][y] = Tile.VOID_TILE;
                }
            }
        }
    }

    public void smooth(int smoothness) {
        for (int i = 0; i < smoothness; i++) {
            int x0 = random.nextInt(sizeX - 1); //so square subgrid can be used
            int y0 = random.nextInt(sizeY - 1);
            int grassCount = 0;
            int waterCount = 0;
            for (int y = y0; y < y0 + 2; y++) {
                for (int x = x0; x < x0 + 2; x++) {
                    if (map[x][y] == Tile.GRASS_TILE) {
                        grassCount++;
                    } else if (map[x][y] == Tile.WATER_TILE) {
                        waterCount++;
                    }
                }
            }
            if (grassCount > 2) {
                for (int y = y0; y < y0 + 2; y++) {
                    for (int x = x0; x < x0 + 2; x++) {
                        map[x][y] = Tile.GRASS_TILE;
                    }
                }
            } else if (waterCount > 2) {
                for (int y = y0; y < y0 + 2; y++) {
                    for (int x = x0; x < x0 + 2; x++) {
                        map[x][y] = Tile.WATER_TILE;
                    }
                }
            }

        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Tile[][] getMap() {
        return map;
    }

}
