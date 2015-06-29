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
public class constraintList {
    
        String math;
    String message;
    String annotation;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }

    public constraintList() {
    }

    public String getmath() {
        return math;
    }

    public void setmath(String math) {
        this.math = math;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }
    
}
