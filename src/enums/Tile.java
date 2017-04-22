package enums;

import classes.Game;

public enum Tile {
    
    WATER_TILE(0, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0x90C3D4, true),
    GRASS_TILE(1, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0xA1D490, false),
    VOID_TILE(3, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0, true),
    MUD_TILE(4, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0x7D5D52, false);
    
    private final int code;
    private final int sizeX;
    private final int sizeY;
    private final int color;
    private final boolean solid;
    

    private Tile(int code, int sizeX, int sizeY, int color, boolean solid) {
        this.code = code;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        this.solid = solid;
    }

    public int getCode() {
        return code;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getColor() {
        return color;
    }
    
    public boolean isSolid() {
        return solid;
    }

}
