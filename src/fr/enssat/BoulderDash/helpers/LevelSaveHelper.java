package fr.enssat.BoulderDash.helpers;

import java.text.SimpleDateFormat;
import java.util.Locale;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * LevelSaveHelper
 *
 * Proceeds level save routine
 * Able to iterate on internal representation of a map and serialize it to XML
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-21
 */
public class LevelSaveHelper {
    private static String pathToDataStore = "res/levels";
    private String levelId = null;
    private DisplayableElementModel[][] groundGrid;
    private SimpleDateFormat dateFormatter;

    /**
     * Class constructor
     *
     * @param  levelId  Level identifier
     */
    public LevelSaveHelper(String levelId, DisplayableElementModel[][] groundGrid) {
        this.setLevelId(levelId);
        this.setGroundGrid(groundGrid);

        // Requirements
        this.dateFormatter = new SimpleDateFormat("yyy-MM-dd/HH:mm:ss", Locale.ENGLISH);

        if (this.levelId != null) {
            // Let's go.
            this.saveLevelData();
        }
    }

    /**
     * Gets level storage path
     *
     * @return  Level path, with file extension
     */
    private String getLevelPathInDataStore() {
        return this.pathToDataStore + "/" + this.getLevelId() + ".xml";
    }

    /**
     * Saves the level data into XML storage
     */
    private void saveLevelData() {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element bdLevel = document.createElementNS("fr.enssat.boulderdash", "bd-level");
            document.appendChild(bdLevel);

            // append child elements to root element
            bdLevel.appendChild(this.nameNode(document));
            bdLevel.appendChild(this.dateNode(document));
            bdLevel.appendChild(this.sizeNode(document));
            bdLevel.appendChild(this.gridNode(document));

            // Write to disk
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the name node
     *
     * @param   document  Document
     * @return  Name node
     */
    private Node nameNode(Document document) {
        String nameValue;

        nameValue = "Unknown Level Name";

        return this.textNode(document, "name", nameValue);
    }

    /**
     * Creates the date node
     *
     * @param   document  Document
     * @return  Date node
     */
    private Node dateNode(Document document) {
        // Get values
        String dateCreatedValue, dateModifiedValue;

        dateCreatedValue  = "0000-00-00/00:00:00";
        dateModifiedValue = "0000-00-00/00:00:00";

        // Create element
        Element dateElement = document.createElement("date");

        dateElement.setAttribute("format", "utc");

        dateElement.appendChild(this.textNode(document, "created", dateCreatedValue));
        dateElement.appendChild(this.textNode(document, "modified", dateModifiedValue));

        return dateElement;
    }

    /**
     * Creates the size node
     *
     * @param   document  Document
     * @return  Size node
     */
    private Node sizeNode(Document document) {
        // Get values
        Integer widthValue = 0, heightValue = 0;

        widthValue = this.getGroundGrid().length;

        if(widthValue > 0) {
            heightValue = this.getGroundGrid()[0].length;
        }

        // Create element
        Element sizeElement = document.createElement("size");

        sizeElement.appendChild(this.textNode(document, "width", widthValue.toString()));
        sizeElement.appendChild(this.textNode(document, "height", heightValue.toString()));

        return sizeElement;
    }

    /**
     * Creates the grid node
     *
     * @param   document  Document
     * @return  Grid node
     */
    private Node gridNode(Document document) {
        Element gridElement = document.createElement("grid");
        gridElement.setAttribute("state", "initial");

        // Iterate in MATRIX:{x}
        if(this.getGroundGrid().length > 0) {
            // XML structure matrix is the inverse of the internal representation (hence the weird loop)
            for (Integer curLineIndex = 0; curLineIndex < this.getGroundGrid()[0].length; curLineIndex++) {
                gridElement.appendChild(this.gridLineNode(document, curLineIndex));
            }
        }

        return gridElement;
    }

    /**
     * Creates the grid line node
     *
     * @param   document      Document
     * @param   curLineIndex  Current line index
     * @return  Grid line node
     */
    private Node gridLineNode(Document document, Integer curLineIndex) {
        Element gridLineElement = document.createElement("line");
        gridLineElement.setAttribute("index", curLineIndex.toString());

        // Iterate in MATRIX:X:{y}
        if(this.getGroundGrid().length > 0) {
            // XML structure matrix is the inverse of the internal representation (hence the weird loop)
            for (Integer curItemIndex = 0; curItemIndex < this.getGroundGrid().length; curItemIndex++) {
                gridLineElement.appendChild(this.gridLineItemNode(document, curLineIndex, curItemIndex));
            }
        }

        return gridLineElement;
    }

    /**
     * Creates the grid line item node
     *
     * @param   document      Document
     * @param   curLineIndex  Current line index
     * @param   curItemIndex  Current line item index
     * @return  Grid line item node
     */
    private Node gridLineItemNode(Document document, Integer curLineIndex, Integer curItemIndex) {
        Element gridLineItemElement = document.createElement("item");
        gridLineItemElement.setAttribute("index", curItemIndex.toString());

        gridLineItemElement.appendChild(this.gridLineItemSpriteNode(document, curLineIndex, curItemIndex));

        return gridLineItemElement;
    }

    /**
     * Creates the grid line sprite item node
     *
     * @param   document      Document
     * @param   curLineIndex  Current line index
     * @param   curItemIndex  Current line item index
     * @return  Grid line item sprite node
     */
    private Node gridLineItemSpriteNode(Document document, Integer curLineIndex, Integer curItemIndex) {
        String groupValue, nameValue, stateValue, convertibleValue;

        DisplayableElementModel curGridElement = this.getGroundGrid()[curItemIndex][curLineIndex];

        // Retrieve current values
        groupValue       = curGridElement.getGroupName();
        nameValue        = curGridElement.getSpriteName();
        stateValue       = curGridElement.getStateValue();
        convertibleValue = curGridElement.isConvertible() ? "1" : "0";

        // Create sprite XML element
        Element gridLineItemSpriteElement = document.createElement("sprite");

        // Sprite attributes
        gridLineItemSpriteElement.setAttribute("group", groupValue);
        gridLineItemSpriteElement.setAttribute("name", nameValue);
        gridLineItemSpriteElement.setAttribute("state", stateValue);

        if(convertibleValue == "1") {
            gridLineItemSpriteElement.setAttribute("convertible", convertibleValue);
        }

        return gridLineItemSpriteElement;
    }

    /**
     * Creates a bare text node
     *
     * @param   document  Document
     * @param   name      Element name
     * @param   value     Element value
     * @return  Text node
     */
    private Node textNode(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));

        return node;
    }

    /**
     * Gets the level identifier
     *
     * @return  Level identifier
     */
    public String getLevelId() {
        return this.levelId;
    }

    /**
     * Sets the level identifier
     *
     * @param  levelId  Level identifier
     */
    private void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * Gets the ground grid
     *
     * @return  Ground grid
     */
    public DisplayableElementModel[][] getGroundGrid() {
        return this.groundGrid;
    }

    /**
     * Sets the ground grid
     *
     * @param  groundGrid  Ground grid
     */
    private void setGroundGrid(DisplayableElementModel[][] groundGrid) {
        this.groundGrid = groundGrid;
    }
}
