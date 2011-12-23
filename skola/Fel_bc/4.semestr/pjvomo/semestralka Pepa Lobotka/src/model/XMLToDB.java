package model;

import controller.BusinessFacade;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


/*******************************************************************************
 * Class providing XML import into database.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class XMLToDB {

//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /***************************************************************************
     * Document prepared for parsing.
     */
       private Document doc;


//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


    /***************************************************************************
     * Loads provided XML file and prepare it for parsing.
     *
     * @param xmlFile XML file containing data with orders.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public XMLToDB(File xmlFile) throws ParserConfigurationException, SAXException, IOException{

        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
    }

//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
 

    /***************************************************************************
     * Parses orders from XML and inserts into DB
     *
     * @throws SQLException
     */
    public void parseOrdersToDB() throws SQLException{
        
        NodeList order = doc.getChildNodes().item(1).getChildNodes();

        for (int i = 0; i < order.getLength(); i++){
            
            // Only if order
            if (order.item(i).getNodeName().equalsIgnoreCase("order")){

                // Customer data parsing
                Node customer = getNode(order.item(i), 1);

                String firstName = getNode(customer, 1).getTextContent();
                String sureName = getNode(customer, 2).getTextContent();
                String nick = getNode(customer, 3).getTextContent();

                Node address = getNode(customer, 4);

                String street = getNode(address, 1).getTextContent();
                String city = getNode(address, 2).getTextContent();
                int postCode = new Integer(getNode(address, 3).getTextContent());

                // Good data parsing
                Node shipping  = getNode(order.item(i), 2);

                String postType = getNode(shipping, 1).getTextContent();
                int postage = new Integer( getNode(shipping, 2).getTextContent() );

                Node product = getNode(order.item(i), 3);

                String productName = getNode(product, 1).getTextContent();
                int cost = new Integer( getNode(product, 2).getTextContent() );
                String specification = getNode(product, 3).getTextContent();

                // Purchase data parsing
                Node purchase = getNode(order.item(i), 4);

                Date boughtDate = new Date(  );
                int price = new Integer( getNode(purchase, 2).getTextContent() );
                Date deliveryDate = new Date(  );
                String seller = getNode(purchase, 4).getTextContent();

                // Order itself data parsing
                
                int costs = new Integer( getNode(order.item(i), 5).getTextContent() );

                Date orderDate = formatDate( getNode(order.item(i), 6).getTextContent() );
                Date payedDate = formatDate( getNode(order.item(i), 7).getTextContent() );
                Date sentDate = formatDate( getNode(order.item(i), 8).getTextContent() );

                int state = new Integer( getNode(order.item(i), 9).getTextContent() );
                String notation = getNode(order.item(i), 10).getTextContent();
                
                // Add new order to DB

                BusinessFacade.getInstance().makeNewOrder(firstName, sureName, nick, street,
                        city, postCode, productName, specification, postType, orderDate,
                        payedDate, deliveryDate, sentDate, boughtDate, state, seller,
                        price, postage, price, costs, notation);
            }
        }
    }


//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================

    /***************************************************************************
     * Gets certain Node's child according to given sequence.
     *
     * @param node Parent's node.
     * @param nodeSeq Number of which children should be returned.
     * @return Parent's children Node on given place.
     */
    private Node getNode(Node node, int nodeSeq){

        NodeList underNodes = node.getChildNodes();
        int ctr = 0;

        for (int i = 0; i < underNodes.getLength(); i++){

            if ( !underNodes.item(i).getNodeName().equalsIgnoreCase("#text") && !underNodes.item(i).getNodeName().equalsIgnoreCase("#comment")){
                ctr++;
                if (ctr == nodeSeq) return underNodes.item(i);
            }

        }
        return null;
    }

    /***************************************************************************
     * Reformats date in format:dd.mm.YYYY so that it could be used for Date
     * class constructor.
     *
     * @param dateToReformat Date in classic format.
     * @return Constructed Date class.
     */
    private Date formatDate(String dateToReformat){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<dateToReformat.length() ; i++){
            if (dateToReformat.charAt(i) == '.'){
                sb.append('-');
            }else{
                sb.append(dateToReformat.charAt(i));
            }
        }
        String unformattedDate = sb.toString();
        if (unformattedDate.length() != 0 ){
            String[] items = unformattedDate.split("-");
            Date formattedDate = new Date(Integer.valueOf(items[0]),Integer.valueOf(items[1]),Integer.valueOf(items[2]));
            //System.out.println("here was 2 : " + unformattedDate);
            return formattedDate;
        } else {
            //System.out.println("here was : " + unformattedDate);
            return new Date(true);
        }
    }


//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================    

 }

