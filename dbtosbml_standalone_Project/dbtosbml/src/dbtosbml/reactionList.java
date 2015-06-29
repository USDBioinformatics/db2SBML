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
public class reactionList {
    
    String id;
    String kid;
    String math;
    String name;
    String compartment;
    double stoichometry;
    String species;
    String sboTerm;
    boolean reversible;
    boolean fast;
    boolean constant;
    String annotation;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }
    

    public reactionList() {
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
    
     public String getkid() {
        return kid;
    }

    public void setkid(String kid) {
        this.kid = kid;
    }
    
     public String getmath() {
        return math;
    }

    public void setmath(String math) {
        this.math = math;
    }

    public boolean getconstant() {
        return constant;
    }

    public void setconstant(boolean constant) {
        this.constant = constant;
    }
    
     public double getstoichometry() {
        return stoichometry;
    }

    public void setstoichometry(double stoichometry) {
        this.stoichometry = stoichometry;
    }
    
    
    public boolean getreversible() {
        return reversible;
    }

    public void setreversible(boolean reversible) {
        this.reversible = reversible;
    }
    
    public boolean getfast() {
        return fast;
    }

    public void setfast(boolean fast) {
        this.fast = fast;
    }
    
     public String getcompartment() {
        return compartment;
    }

    public void setcompartment(String compartment) {
        this.compartment = compartment;
    }
    
     public String getsboTerm() {
        return sboTerm;
    }

    public void setsboTerm(String sboTerm) {
        this.sboTerm = sboTerm;
    }
    
     public String getspecies() {
        return species;
    }

    public void setspecies(String species) {
        this.species = species;
    }
    
}
