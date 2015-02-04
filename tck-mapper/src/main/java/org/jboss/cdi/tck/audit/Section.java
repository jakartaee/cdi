package org.jboss.cdi.tck.audit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Remes
 */
public class Section {

    private String id;
    private String title;
    private int level;

    public List<SectionElement> getSectionElements() {
        return sectionElements;
    }

    private List<SectionElement> sectionElements;

    public Section() {
        this.sectionElements = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getTitle() + " " + this.getLevel();
    }

}
