package a1;

import java.awt.*;

/**
 * Item Interface
 */
public interface Item {
    Constants.ItemType getType(); //return type of the object
    void eat(); //consume the item
    Cell getCell();
    Color getColour();
}
