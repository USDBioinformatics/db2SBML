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
public class parameterList {
    
        String id;
    String name;
    String units;
    double value;
    boolean constant;
    String annotation;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }

    public parameterList() {
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

    public boolean getconstant() {
        return constant;
    }

    public void setconstant(boolean constant) {
        this.constant = constant;
    }
    
     public double getvalue() {
        return value;
    }

    public void setvalue(double value) {
        this.value = value;
    }
    
     public String getunits() {
        return units;
    }

    public void setunits(String units) {
        this.units = units;
    }
    
    
}
