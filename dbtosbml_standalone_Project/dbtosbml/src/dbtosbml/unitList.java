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
public class unitList {
    
        String id;
    String name;
    String kind;
    double exponent;
    double multiplier;
    int scale;
    String annotation;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }

    public unitList() {
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
    
    public String getkind() {
        return kind;
    }

    public void setkind(String kind) {
        this.kind = kind;
    }

    
     public double getexponent() {
        return exponent;
    }

    public void setexponent(double exponent) {
        this.exponent = exponent;
    }
    
     public double getmultiplier() {
        return multiplier;
    }

    public void setmultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
    
    public int getscale() {
        return scale;
    }

    public void setscale(int scale) {
        this.scale = scale;
    }
 
}
