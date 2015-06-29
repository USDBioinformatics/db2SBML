/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtosbml;

/**
 *
 * @author Aafaque
 */
public class functionList {
    
        String id;
    String xmlns;
    String annotation;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }

    public functionList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getxmlns() {
        return xmlns;
    }

    public void setxmlns(String xmlns) {
        this.xmlns = xmlns;
    }

}
