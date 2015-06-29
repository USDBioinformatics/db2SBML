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
public class SpeciesList {
    
    String id;
    String name;
    String compartment;
    double ia;
    double ic;
    String su;
    String cf;
    boolean hosu;
    boolean bc;
    boolean constant;
    String annotation;

    public SpeciesList() {
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
    
     public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }

    public boolean getconstant() {
        return constant;
    }

    public void setconstant(boolean constant) {
        this.constant = constant;
    }
    
     public double getia() {
        return ia;
    }

    public void setia(double ia) {
        this.ia = ia;
    }
    
     public double getic() {
        return ic;
    }

    public void setic(double ic) {
        this.ic = ic;
    }
    
    public boolean gethosu() {
        return hosu;
    }

    public void sethosu(boolean hosu) {
        this.hosu = hosu;
    }
    
    public boolean getbc() {
        return bc;
    }

    public void setbc(boolean bc) {
        this.bc = bc;
    }
    
     public String getcompartment() {
        return compartment;
    }

    public void setcompartment(String compartment) {
        this.compartment = compartment;
    }
    
     public String getsu() {
        return su;
    }

    public void setsu(String su) {
        this.su = su;
    }
    
     public String getcf() {
        return cf;
    }

    public void setcf(String cf) {
        this.cf = cf;
    }
    
    
}
