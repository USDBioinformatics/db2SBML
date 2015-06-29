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
public class eventsList {
    
    String id;
    String name;
    String math;
    String variable;
    boolean uservalues;
    boolean initialval;
    boolean persistent;
    String annotation;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }

    public eventsList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getuservalues() {
        return uservalues;
    }

    public void setuservalues(boolean uservalues) {
        this.uservalues = uservalues;
    }
    
    public boolean getinitialval() {
        return initialval;
    }

    public void setinitialval(boolean initialval) {
        this.initialval = initialval;
    }
    
    public boolean getpersistent() {
        return persistent;
    }

    public void setpersistent(boolean persistent) {
        this.persistent = persistent;
    }
    
     public String getmath() {
        return math;
    }

    public void setmath(String math) {
        this.math = math;
    }
    
     public String getvariable() {
        return variable;
    }

    public void setvariable(String variable) {
        this.variable = variable;
    }
    
    
}
