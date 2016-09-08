package org.jboss.cdi.tck.mapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import org.jboss.cdi.tck.audit.Assertion;
import org.jboss.cdi.tck.audit.Group;
import org.jboss.cdi.tck.audit.Section;
import org.jboss.cdi.tck.audit.SectionElement;
import org.jboss.cdi.tck.audit.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Tomas Remes
 */
public class Mapper {

    private String script = "<script>\n"
            + "function changeVisibility(divId, hrefId)\n"
            + "{\n"
            + " var divElement = document.getElementById(divId);\n"
            + " var hrefElement = document.getElementById(hrefId);"
            + " if(divElement.style.display == 'none') {\n"
            + "   divElement.style.display=\"block\";\n"
            + "   hrefElement.innerHTML = '<img src=\"images/minus.png\"/>';\n"
            + " } else {\n"
            + "   divElement.style.display=\"none\";\n"
            + "   hrefElement.innerHTML = '<img src=\"images/plus.png\"/>';\n"
            + " }"
            + "}\n</script>";

    private Document specHtml;
    private Document coverageHtml;
    private TckAuditSaxParser auditParser;
    private List<Section> auditSections;
    private String tckVersion;
    private static final Logger log = Logger.getLogger(Mapper.class.getName());
    private final static String SPEC_WITH_ASSERTIONS = "spec/target/generated-docs/cdi-spec-with-assertions.html";

    public Mapper(String tckAuditURL, String coverageReportURL, String pathToCdiSpec, String tckVersion) {
        this.auditParser = new TckAuditSaxParser(tckAuditURL);
        this.tckVersion = tckVersion;
        try {
            this.specHtml = Jsoup.parse(new File(pathToCdiSpec), "utf-8");
        } catch (IOException e) {
            new Exception("Cannot find " + pathToCdiSpec + " :" + e.getStackTrace().toString());
        }

        try {
            this.coverageHtml = Jsoup.connect(coverageReportURL).maxBodySize(0).timeout(0).get();
        } catch (IOException e) {
            new Exception("Cannot find " + coverageReportURL + " :" + e.getStackTrace().toString());
        }
    }

    private void parseTckXmlAudit() {
        auditParser.parseDocument();
        auditSections = auditParser.getSections();
    }

    private void parseCoverageHtml() {
        for (Section section : auditSections) {
            if (!section.getSectionElements().isEmpty()) {
                Element sectionElement = coverageHtml.getElementById(section.getTitle());
                if (sectionElement != null) {
                    Elements assertionTextElements = sectionElement.select("div[class=results]");

                    for (SectionElement auditSectionElement : section.getSectionElements()) {
                        if (auditSectionElement instanceof Assertion) {
                            Assertion assertion = (Assertion) auditSectionElement;
                            compareAssertionToElements(assertion, assertionTextElements);
                        } else {
                            for (Assertion assertion : ((Group) auditSectionElement).getAssertions()) {
                                compareAssertionToElements(assertion, assertionTextElements);
                            }
                        }
                    }
                } else {
                    log.warning("Section " + sectionElement + " cannot be found!");
                }
            }
        }
    }

    private void writeToSpec() {
        //append changing visibility script to head
        specHtml.select("head").after(script);
        Element styleElement = specHtml.select("style").first();
        styleElement.append(".assertionsDiv {background-color: #F2FAF2; display: block; padding: 10pt 10pt 5pt 10pt; \n}" +
                ".groupAssertion { padding-left:15pt }\n" +
                ".notTestableAssertion { background-color: #E1F0FF; }\n" +
                ".assertionTest { font-family: monospace; }");

        for (Section section : auditSections) {
            if (!section.getSectionElements().isEmpty()) {
                Element sectionElement = specHtml.getElementById(section.getId());
                String wrapDivId = "assertions" + section.getId();
                String hrefId = "href" + section.getId();

                // append hidden div with assertions and link to display the div
                if (sectionElement != null) {
                    sectionElement.after("<div id=" + wrapDivId + " class=\"assertionsDiv\"></div>");
                    sectionElement.after(
                            "<p><a id=" + hrefId + " onclick=\"changeVisibility('" + wrapDivId + "', '" + hrefId
                                    + "')\";\"><img src=\"images/minus.png\"/></a> TCK assertions</p>");
                    Element wrapDiv = sectionElement.parent().getElementById(wrapDivId);

                    for (SectionElement auditSectionElement : section.getSectionElements()) {
                        if (auditSectionElement instanceof Assertion) {
                            Assertion assertion = (Assertion) auditSectionElement;
                            writeAssertionToDivElement(assertion, wrapDiv);
                        } else {
                            Group group = (Group) auditSectionElement;
                            wrapDiv.append("<p>" + group.getText() + "</p>");
                            for (Assertion assertion : group.getAssertions()) {
                                writeAssertionToDivElement(assertion, wrapDiv);
                            }
                        }
                    }
                } else {
                    log.warning("Section " + sectionElement + " cannot be found!");
                }
            }
        }

        try {
            FileOutputStream fileInputStream = new FileOutputStream(new File(SPEC_WITH_ASSERTIONS));
            fileInputStream.write(specHtml.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates string starting with given assertion id (if exists) and followed by bracket
     * and by given assertion text where is replaced all occurrence of "<" and ">" characters.
     *
     * @param assertion
     * @return html p element containing assertion text starting with bracketed assertion id (if any)
     */
    private String adjustAssertionText(Assertion assertion) {
        StringBuilder strBuild = new StringBuilder(assertion.getGroup() != null ? "<p class=\"groupAssertion\">" : "<p>");
        strBuild.append(assertion.isTestable() ? "" : "<p class=\"notTestableAssertion\">");
        strBuild.append("<strong>" + assertion.getId().concat(") </strong>"));
        strBuild.append(assertion.getText().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
        if (!assertion.isTestable() && assertion.getNote() != null) {
            strBuild.append("<br>Note: <i>" + assertion.getNote() + "</i></br>");
        }
        strBuild.append("</p>");
        return strBuild.toString();
    }

    private void compareAssertionToElements(Assertion assertion, Elements assertionTextElements) {
        for (Element assertionTextElement : assertionTextElements) {
            String assertionElement = assertionTextElement.text().replace("|", "");
            // replace two or more spaces by just one
            String assertionAudit = assertion.getText().trim().replaceAll("\\s+", " ");
            if (assertionElement.contains(assertionAudit)) {
                Elements coverageElements = assertionTextElement.select("div[class=coverageMethod");
                String testPackageName = assertionTextElement.select("div[class=packageName]").text();
                for (Element coverageElement : coverageElements) {
                    String test = coverageElement.text().replace("github", "");
                    try {
                        URL url = new URL(coverageElement.select("a").attr("href"));
                        url = new URL(url.toString().replace("master", tckVersion));
                        assertion.getTests().add(new Test(test, url, testPackageName));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void writeAssertionToDivElement(Assertion assertion, Element wrapDiv) {
        String assertionText = adjustAssertionText(assertion);
        wrapDiv.append(assertionText);
        if (!assertion.getTests().isEmpty()) {
            wrapDiv.append(
                    assertion.getGroup() != null ? "<ul class=\"groupAssertion\" id=" + assertion.getId() + ">" : "<ul id=" + assertion.getId() + ">");
            for (Test test : assertion.getTests()) {
                String testClassName = test.getTestMethodName().substring(0, test.getTestMethodName().indexOf("."));
                String testMethodName = test.getTestMethodName().substring(test.getTestMethodName().indexOf("."));
                String listItem =
                        "<li class=\"assertionTest\"><a target=\"_blank\" href=" + test.getRefUrl()
                                + ">" + testClassName + "</a>" + testMethodName + "</li>";
                wrapDiv.select("ul[id=" + assertion.getId()).append(listItem);
            }
        }
    }

    public static void main(String[] args) {
        log.addHandler(new ConsoleHandler());
        log.info("Starting CDI TCK assertions mapper.");

        if (args.length < 4) {
            log.severe("The TCK mapper accepts  4 arguments!");
        } else {
            Mapper mapper = new Mapper(args[0], args[1], args[2], args[3]);
            mapper.parseTckXmlAudit();
            mapper.parseCoverageHtml();
            mapper.writeToSpec();
            log.info("CDI TCK assertions mapper succesfully finished.");
        }

    }
}
