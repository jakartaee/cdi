package org.jboss.cdi.tck.audit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Remes
 */
public class Assertion extends SectionElement {

    private String id = "";
    private String note;
    private boolean testable = true;
    private Group group;
    private List<Test> tests;

    public Assertion() {
        this.tests = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Test> getTests() {
        return tests;
    }

    public boolean isTestable() {
        return testable;
    }

    public void setTestable(boolean testable) {
        this.testable = testable;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getText();
    }

}
