package enums;

import classes.Game;

public enum Tile {
    
    WATER_TILE(0, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0x90C3D4, true, Sprite.WATER_SPRITE),
    GRASS_TILE(1, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0xA1D490, false, Sprite.GRASS_SPRITE),
    VOID_TILE(2, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0, true, Sprite.VOID_SPRITE),
    MUD_TILE(3, Game.DEFAULT_TILE_SIZE, Game.DEFAULT_TILE_SIZE, 0x7D5D52, false, Sprite.MUD_SPRITE);
    
    private final int code;
    private final int sizeX;
    private final int sizeY;
    private final int color;
    private final boolean solid;
    private final Sprite sprite;
    

    private Tile(int code, int sizeX, int sizeY, int color, boolean solid, Sprite sprite) {
        this.code = code;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        this.solid = solid;
        this.sprite = sprite;
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

    public Sprite getSprite() {
        return sprite;
    }
    
    

}
