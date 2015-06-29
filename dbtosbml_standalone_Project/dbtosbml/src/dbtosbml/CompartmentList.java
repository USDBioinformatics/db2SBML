/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtosbml;

import java.util.ArrayList;

/**
 *
 * @author Aafaque
 */
public class CompartmentList {
    
    String id;
    boolean constant;
    String name;
    double spatialdimensions;
    double size;
    String units;
    String annotation;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }
    
    public CompartmentList() {
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
    
     public double getspatialdimensions() {
        return spatialdimensions;
    }

    public void setspatialdimensions(double spatialdimensions) {
        this.spatialdimensions = spatialdimensions;
    }
    
     public double getsize() {
        return size;
    }

    public void setsize(double size) {
        this.size = size;
    }
    
     public String getunits() {
        return units;
    }

    public void setunits(String units) {
        this.units = units;
    }
    
    
    
}
