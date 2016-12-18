package org.rjj.config.parser;

import groovy.xml.SAXBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderAdapter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * Created by rj on 15/12/16.
 */
public class XMLParser{

    private Element root;
    private List<Node> similar = null;
    private List<Node> all = null;

    public XMLParser(File file) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(file);
            root = document.getDocumentElement();
            root.normalize();
            if (root == null) throw new ParserConfigurationException("No root element was found");
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for all nodes and list them under 'all'. Use <code>getAll()</code> to get list of
     * all nodes.
     */
    public void lookForAll() {
        all = new ArrayList<>();
        NodeList list = root.getChildNodes();

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            lookDeep(node);
        }
    }

        private void lookDeep(Node node) {
            if (node.getNodeName().matches("[a-zA-Z0-9_]+"))
                all.add(node);
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node1 = list.item(i);
                lookDeep(node1);
            }
        }

    /**
     * Looks for a particular tag with defined attributes. Returns a <code>Node</code>.
     * @param tagName the name of the tag to search for.
     * @param attrs arrays of attributes.
     * @return <code>XMLParser</code> with matching Node list in <code>getSimilar()</code>.
     */
    public XMLParser look(String tagName, Attr ... attrs) {
        if (all == null) lookForAll();
        similar = new ArrayList<>();

        final boolean[] attrSatisfied = new boolean[attrs.length];
        Arrays.fill(attrSatisfied, false);
        all.forEach(node -> {
            if (tagName.equals(node.getNodeName())) {
                int satNo = 0;
                for (Attr attr : attrs) {
                    NamedNodeMap nodemap = node.getAttributes();
                    //Check for every attributes in the node
                    for (int i = 0; i < nodemap.getLength(); i++) {
                        //Check if attribute name matches
                        if (attr.name.equals(nodemap.item(i).getNodeName())) {
                            //Check if attribute value matches
                            if (attr.value.equals(nodemap.item(i).getNodeValue())) {
                                attrSatisfied[satNo++] = true;
                                break;
                            }
                        }
                    }
                    if (satNo == attrs.length) {
                        similar.add(node);
                    }
                }
            }
        });

        return this;
    }

    /**
     * Get the main root element. So every xml must have atleast one root element.
     * @return
     * the main root element.
     */
    public Element getRoot() {
        return root;
    }

    /**
     * Gets a list of matching Nodes after calling <code>look(...)</code> method.
     * @return
     * a list of Nodes.
     */
    public List<Node> getSimilar() {
        return similar;
    }

    /**
     * Gets the list of all nodes within the xml file.
     * @return
     */
    public List<Node> getAll() {
        return all;
    }


    private boolean isEmptyByContext(String val) {
        val = val.trim();
        if (val.startsWith("<") || val.endsWith(">")) return false;
        return true;
    }

    /**
     * A representor class used to represent an attribute's name and value.
     */
    public static class Attr {
        public String name;
        public String value;

        private Attr(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public static Attr __(String name, String value) {
            return new Attr(name, value);
        }
    }

    //_______________put value into the file__________________//



}