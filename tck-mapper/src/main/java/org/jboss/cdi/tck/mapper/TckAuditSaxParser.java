package org.jboss.cdi.tck.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jboss.cdi.tck.audit.Assertion;
import org.jboss.cdi.tck.audit.Group;
import org.jboss.cdi.tck.audit.Section;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Tomas Remes
 */
public class TckAuditSaxParser extends DefaultHandler {

    List<Section> sections;
    List<Group> groups;

    private StringBuffer tmpVal;
    private Section tmpSection;
    private Assertion tmpAssertion;
    private Group tmpGroup;
    private String xmlFilePath;
    private boolean groupTextExist;
    private static final Logger log = Logger.getLogger(TckAuditSaxParser.class.getName());

    public TckAuditSaxParser(String xmlFilePath) {
        this.sections = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.xmlFilePath = xmlFilePath;
    }

    public void parseDocument() {

        log.fine("Starting parsing of audit file.");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {

            SAXParser sp = spf.newSAXParser();
            sp.parse(xmlFilePath, this);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            new Exception("Cannot find " + xmlFilePath + " :" + ie.getStackTrace().toString());
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    //Event Handlers
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //reset
        tmpVal = new StringBuffer();

        if (qName.equalsIgnoreCase("section")) {
            tmpSection = new Section();
            tmpSection.setId(attributes.getValue("id"));
            tmpSection.setTitle(attributes.getValue("title"));
            tmpSection.setLevel(Integer.valueOf(attributes.getValue("level")));
        } else if (qName.equalsIgnoreCase("group")) {
            tmpGroup = new Group();
            groupTextExist = true;
        } else if (qName.equals("assertion")) {
            tmpAssertion = new Assertion();
            if (attributes.getValue("id") != null) {
                tmpAssertion.setId(attributes.getValue("id"));
            }
            if (attributes.getValue("testable") != null) {
                tmpAssertion.setTestable(Boolean.valueOf(attributes.getValue("testable")));
            }
            if (tmpGroup != null) {
                tmpGroup.getAssertions().add(tmpAssertion);
                tmpAssertion.setGroup(tmpGroup);
            }else {
                tmpSection.getSectionElements().add(tmpAssertion);
            }
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tmpVal.append(new String(ch, start, length));
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("section")) {
            sections.add(tmpSection);

        } else if (qName.equalsIgnoreCase("text") && !groupTextExist) {

            tmpAssertion.setText(tmpVal.toString().replace("|", "").replace("_", "").replace("~", ""));
            //replaceAll("Section [0-9]?[0-9]\\.[0-9]*\\.*[0-9]*\\.*, ", ""));
        } else if (qName.equalsIgnoreCase("note")) {
            tmpAssertion.setNote(tmpVal.toString());
        } else if (qName.equalsIgnoreCase("text") && groupTextExist) {

            tmpGroup.setText(tmpVal.toString().replace("|", "").replace("_", "").replace("~", ""));
            groupTextExist = false;
        } else if(qName.equalsIgnoreCase("group")){
            //groups.add(tmpGroup);
            tmpSection.getSectionElements().add(tmpGroup);
            tmpGroup = null;
        }
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<Group> getGroups() {
        return groups;
    }

}
