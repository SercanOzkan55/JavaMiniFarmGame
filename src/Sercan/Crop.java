package Sercan;

// The Crop class represents a crop in the game, such as wheat, aubergine, or corn.
// This class manages the growth stages of the crop, the time required for each stage, and whether the crop is mature.
public class Crop {

    // Properties of the crop:
    // - x and y: The coordinates of the crop in the game world.
    // - name: The type of crop (e.g., "Wheat", "Aubergine", "Corn").
    // - growthStage: The current growth stage of the crop (0 = seed, 1 = sprout, 2 = mature).
    // - maxGrowthStage: The maximum growth stage (2 for all crops in this implementation).
    // - timeToNextStage: The time required (in milliseconds) for the crop to advance to the next growth stage.
    // - lastUpdateTime: The timestamp of the last time the crop was updated.
    public int x, y;
    public String name;
    public int growthStage = 0;
    public int maxGrowthStage = 2; // All crops have 3 growth stages (0, 1, 2).
    public int timeToNextStage; // Time required to advance to the next stage.
    public long lastUpdateTime;

    // Constructor:
    // The Crop constructor initializes a new crop with its position, planting time, and type.
    // - x, y: The coordinates where the crop is planted.
    // - plantedTime: The time when the crop was planted (in milliseconds since the epoch).
    // - name: The type of crop (e.g., "Wheat", "Aubergine", "Corn").
    public Crop(int x, int y, long plantedTime, String name) {
        this.x = x;
        this.y = y;
        this.lastUpdateTime = plantedTime; // Set the last update time to the planting time.
        this.name = name;

        // Set the time required for the crop to advance to the next growth stage based on its type.
        switch (name) {
            case "Wheat":
                this.timeToNextStage = 5000; // 5 seconds for wheat.
                break;
            case "Aubergine":
                this.timeToNextStage = 7000; // 7 seconds for aubergine.
                break;
            case "Corn":
                this.timeToNextStage = 6000; // 6 seconds for corn.
                break;
            default:
                this.timeToNextStage = 5000; // Default to 5 seconds for unknown crops.
        }
    }

    // update method:
    // This method updates the crop's growth stage based on the current time.
    // - currentTime: The current time in milliseconds.
    public void update(long currentTime) {
        // Check if the crop has not reached the maximum growth stage and if enough time has passed to advance.
        if (growthStage < maxGrowthStage && currentTime - lastUpdateTime >= timeToNextStage) {
            growthStage++; // Advance to the next growth stage.
            lastUpdateTime = currentTime; // Update the last update time to the current time.
            System.out.println(name + " growth stage updated: " + growthStage); // Log the growth stage update.
        }
    }

    // isMature method:
    // This method checks if the crop is fully mature (i.e., has reached the maximum growth stage).
    // Returns true if the crop is mature, false otherwise.
    public boolean isMature() {
        return growthStage == maxGrowthStage;
    }

    // getTileID method:
    // This method returns the tile ID corresponding to the crop's current growth stage.
    // The tile ID is used to render the appropriate image for the crop on the game map.
    public int getTileID() {
        switch (name) {
            case "Wheat":
                return 79 + growthStage; // Tile IDs for wheat: 79 (seed), 80 (sprout), 81 (mature).
            case "Aubergine":
                return 82 + growthStage; // Tile IDs for aubergine: 82 (seed), 83 (sprout), 84 (mature).
            case "Corn":
                return 85 + growthStage; // Tile IDs for corn: 85 (seed), 86 (sprout), 87 (mature).
            default:
                return 78 + growthStage; // Default tile IDs for unknown crops.
        }
    }
}