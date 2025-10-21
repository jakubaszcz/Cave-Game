package cavegame.entity;

// Player class that implements the entity interface
public class Player implements Entity {

    // Coordinates of the player
    private Entity.Coordinates coordinates = new Entity.Coordinates(0.0F, 0.0F, 0.0F);

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
