package org.jboss.cdi.tck.audit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Remes
 */
public class Group extends SectionElement {


    private List<Assertion> assertions;

    public Group(){
        this.assertions = new ArrayList<>();
    }

    public List<Assertion> getAssertions() {
        return assertions;
    }




}
