package Sercan;

// The CollisionChecker class is responsible for detecting collisions between entities, objects, and tiles in the game.
// It checks for collisions with tiles, objects, NPCs, and the player, and updates the game state accordingly.
public class CollisionChecker {

    // Reference to the GameLoop object:
    // This allows the CollisionChecker to interact with the game's entities, objects, and tiles.
    GameLoop gl;

    // Constructor:
    // The CollisionChecker constructor takes a GameLoop object as a parameter.
    // This allows the CollisionChecker to access the game's entities, objects, and tiles.
    public CollisionChecker(GameLoop gl) {
        this.gl = gl;
    }

    // checkTile method:
    // This method checks for collisions between an entity and the tiles in the game world.
    // - entity: The entity (player or NPC) to check for collisions.
    public void checkTile(Entity entity) {
        // Calculate the entity's position in the world coordinates, including its solid area (collision box).
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Convert the entity's world coordinates to tile coordinates.
        int entityLeftCol = entityLeftWorldX / gl.totalSize;
        int entityRightCol = entityRightWorldX / gl.totalSize;
        int entityTopRow = entityTopWorldY / gl.totalSize;
        int entityBottomRow = entityBottomWorldY / gl.totalSize;

        // Variables to store the tile numbers at the entity's collision points.
        int tileNum1, tileNum2;

        // Check for collisions based on the entity's direction.
        switch (entity.direction) {
            case "up":
                // Adjust the top row for the entity's movement speed.
                entityTopRow = (entityTopWorldY - entity.speed) / gl.totalSize;
                // Get the tile numbers at the entity's left and right collision points.
                tileNum1 = gl.tileOp.mapSize[entityLeftCol][entityTopRow];
                tileNum2 = gl.tileOp.mapSize[entityRightCol][entityTopRow];
                // If either tile has collision enabled, set the entity's collision flag to true.
                if (gl.tileOp.tile[tileNum1].collision == true || gl.tileOp.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                // Adjust the bottom row for the entity's movement speed.
                entityBottomRow = (entityBottomWorldY + entity.speed) / gl.totalSize;
                // Get the tile numbers at the entity's left and right collision points.
                tileNum1 = gl.tileOp.mapSize[entityLeftCol][entityBottomRow];
                tileNum2 = gl.tileOp.mapSize[entityRightCol][entityBottomRow];
                // If either tile has collision enabled, set the entity's collision flag to true.
                if (gl.tileOp.tile[tileNum1].collision == true || gl.tileOp.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                // Adjust the left column for the entity's movement speed.
                entityLeftCol = (entityLeftWorldX - entity.speed) / gl.totalSize;
                // Get the tile numbers at the entity's top and bottom collision points.
                tileNum1 = gl.tileOp.mapSize[entityLeftCol][entityTopRow];
                tileNum2 = gl.tileOp.mapSize[entityLeftCol][entityBottomRow];
                // If either tile has collision enabled, set the entity's collision flag to true.
                if (gl.tileOp.tile[tileNum1].collision == true || gl.tileOp.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                // Adjust the right column for the entity's movement speed.
                entityRightCol = (entityRightWorldX + entity.speed) / gl.totalSize;
                // Get the tile numbers at the entity's top and bottom collision points.
                tileNum1 = gl.tileOp.mapSize[entityRightCol][entityTopRow];
                tileNum2 = gl.tileOp.mapSize[entityRightCol][entityBottomRow];
                // If either tile has collision enabled, set the entity's collision flag to true.
                if (gl.tileOp.tile[tileNum1].collision == true || gl.tileOp.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // checkObject method:
    // This method checks for collisions between an entity and the objects in the game world.
    // - entity: The entity (player or NPC) to check for collisions.
    // - player: A boolean flag indicating whether the entity is the player.
    // Returns the index of the collided object, or 999 if no collision occurred.
    public int checkObject(Entity entity, boolean player) {
        int index = 999; // Default value indicating no collision.

        // Loop through all objects in the game.
        for (int i = 0; i < gl.obj.length; i++) {
            if (gl.obj[i] != null) {
                // Adjust the entity's and object's solid area positions for collision detection.
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gl.obj[i].solidArea.x = gl.obj[i].worldX + gl.obj[i].solidArea.x;
                gl.obj[i].solidArea.y = gl.obj[i].worldY + gl.obj[i].solidArea.y;

                // Check for collisions based on the entity's direction.
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed; // Move the entity's solid area up.
                        if (entity.solidArea.intersects(gl.obj[i].solidArea)) { // Check for intersection.
                            if (gl.obj[i].collision == true) {
                                entity.collisionOn = true; // Set collision flag if the object has collision.
                            }
                            if (player == true) {
                                index = i; // Return the index of the collided object if the entity is the player.
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed; // Move the entity's solid area down.
                        if (entity.solidArea.intersects(gl.obj[i].solidArea)) { // Check for intersection.
                            if (gl.obj[i].collision == true) {
                                entity.collisionOn = true; // Set collision flag if the object has collision.
                            }
                            if (player == true) {
                                index = i; // Return the index of the collided object if the entity is the player.
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed; // Move the entity's solid area left.
                        if (entity.solidArea.intersects(gl.obj[i].solidArea)) { // Check for intersection.
                            if (gl.obj[i].collision == true) {
                                entity.collisionOn = true; // Set collision flag if the object has collision.
                            }
                            if (player == true) {
                                index = i; // Return the index of the collided object if the entity is the player.
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed; // Move the entity's solid area right.
                        if (entity.solidArea.intersects(gl.obj[i].solidArea)) { // Check for intersection.
                            if (gl.obj[i].collision == true) {
                                entity.collisionOn = true; // Set collision flag if the object has collision.
                            }
                            if (player == true) {
                                index = i; // Return the index of the collided object if the entity is the player.
                            }
                        }
                        break;
                }

                // Reset the solid area positions after collision detection.
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gl.obj[i].solidArea.x = gl.obj[i].solidAreaDefaultX;
                gl.obj[i].solidArea.y = gl.obj[i].solidAreaDefaultY;
            }
        }

        return index; // Return the index of the collided object, or 999 if no collision occurred.
    }

    // checkNPC method:
    // This method checks for collisions between an entity and the NPCs in the game world.
    // - entity: The entity (player or NPC) to check for collisions.
    // - npc: An array of NPCs to check for collisions.
    // Returns the index of the collided NPC, or 999 if no collision occurred.
    public int checkNPC(Entity entity, Entity[] npc) {
        int index = 999; // Default value indicating no collision.

        // Loop through all NPCs in the game.
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) { // Check if the NPC exists.
                // Adjust the entity's and NPC's solid area positions for collision detection.
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                npc[i].solidArea.x = npc[i].worldX + npc[i].solidArea.x;
                npc[i].solidArea.y = npc[i].worldY + npc[i].solidArea.y;

                // Check for collisions based on the entity's direction.
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed; // Move the entity's solid area up.
                        if (entity.solidArea.intersects(npc[i].solidArea)) { // Check for intersection.
                            entity.collisionOn = true; // Set collision flag.
                            index = i; // Return the index of the collided NPC.
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed; // Move the entity's solid area down.
                        if (entity.solidArea.intersects(npc[i].solidArea)) { // Check for intersection.
                            entity.collisionOn = true; // Set collision flag.
                            index = i; // Return the index of the collided NPC.
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed; // Move the entity's solid area left.
                        if (entity.solidArea.intersects(npc[i].solidArea)) { // Check for intersection.
                            entity.collisionOn = true; // Set collision flag.
                            index = i; // Return the index of the collided NPC.
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed; // Move the entity's solid area right.
                        if (entity.solidArea.intersects(npc[i].solidArea)) { // Check for intersection.
                            entity.collisionOn = true; // Set collision flag.
                            index = i; // Return the index of the collided NPC.
                        }
                        break;
                }

                // Reset the solid area positions after collision detection.
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                npc[i].solidArea.x = npc[i].solidAreaDefaultX;
                npc[i].solidArea.y = npc[i].solidAreaDefaultY;
            }
        }

        return index; // Return the index of the collided NPC, or 999 if no collision occurred.
    }

    // checkPlayer method:
    // This method checks for collisions between an entity and the player.
    // - entity: The entity (NPC) to check for collisions.
    public void checkPlayer(Entity entity) {
        // Adjust the entity's and player's solid area positions for collision detection.
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gl.player.solidArea.x = gl.player.worldX + gl.player.solidArea.x;
        gl.player.solidArea.y = gl.player.worldY + gl.player.solidArea.y;

        // Check for collisions based on the entity's direction.
        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed; // Move the entity's solid area up.
                if (entity.solidArea.intersects(gl.player.solidArea)) { // Check for intersection.
                    entity.collisionOn = true; // Set collision flag.
                    gl.gameState = gl.dialogState; // Switch to dialog state.
                    entity.speak(); // Trigger the NPC's dialogue.
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed; // Move the entity's solid area down.
                if (entity.solidArea.intersects(gl.player.solidArea)) { // Check for intersection.
                    entity.collisionOn = true; // Set collision flag.
                    gl.gameState = gl.dialogState; // Switch to dialog state.
                    entity.speak(); // Trigger the NPC's dialogue.
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed; // Move the entity's solid area left.
                if (entity.solidArea.intersects(gl.player.solidArea)) { // Check for intersection.
                    entity.collisionOn = true; // Set collision flag.
                    gl.gameState = gl.dialogState; // Switch to dialog state.
                    entity.speak(); // Trigger the NPC's dialogue.
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed; // Move the entity's solid area right.
                if (entity.solidArea.intersects(gl.player.solidArea)) { // Check for intersection.
                    entity.collisionOn = true; // Set collision flag.
                    gl.gameState = gl.dialogState; // Switch to dialog state.
                    entity.speak(); // Trigger the NPC's dialogue.
                }
                break;
        }

        // Reset the solid area positions after collision detection.
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;

        gl.player.solidArea.x = gl.player.solidAreaDefaultX;
        gl.player.solidArea.y = gl.player.solidAreaDefaultY;
    }
}