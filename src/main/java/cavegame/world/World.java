package cavegame.world;

public class World {

    // Block enum to store the type of block
    public enum Block {
        AIR,
        GRASS,
        DIRT,
        STONE
    }

    // World coordinates class to store the size of the world
    public static final class WorldCoordinates {
            int width;
            int height;
            int depth;

        public WorldCoordinates(int width, int height, int depth) {
                this.width = width;
                this.height = height;
                this.depth = depth;

            }
    };

    // Array of block to store the blocks of the world
    private final Block[][][] blocks;

    // Constructor
    public World(WorldCoordinates worldCoordinates) {
        blocks = new Block[worldCoordinates.width][worldCoordinates.height][worldCoordinates.depth];

        // Initialize the blocks to AIR
        for (int z = 0; z < worldCoordinates.depth; z++) {
            for (int y = 0; y < worldCoordinates.height; y++) {
                for (int x = 0; x < worldCoordinates.width; x++) {
                    blocks[x][y][z] = Block.AIR;
                }
            }
        }
    }

    // Getters and setters for the blocks
    public Block getBlock(int x, int y, int z) {
        if (isBlockInBounds(x, y, z)) {
            return blocks[x][y][z];
        } else {
            return Block.AIR;
        }
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (isBlockInBounds(x, y, z)) {
            blocks[x][y][z] = block;
        }
    }

    // Break the block
    public void breakBlock(int x, int y, int z) {
        if (isBlockInBounds(x, y, z)) {
            blocks[x][y][z] = Block.AIR;
        }
    }

    // Check if the block is in bounds
    private boolean isBlockInBounds(int x, int y, int z) {
        return x >= 0 && x < blocks.length && y >= 0 && y < blocks[0].length && z >= 0 && z < blocks[0][0].length;
    }

}
