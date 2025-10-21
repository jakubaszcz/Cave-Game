package cavegame.entity;

// Interface to define the entity
interface Entity {

    // Coordinates class to store the position of the entity
    public static class Coordinates {
        public float x;
        public float y;
        public float z;

        // Constructor
        Coordinates(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    // Getters and setters for the coordinates
    public Coordinates getCoordinates();
    public void setCoordinates(Coordinates coordinates);
}
