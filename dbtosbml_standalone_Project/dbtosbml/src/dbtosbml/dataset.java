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
public class dataset {
    
    String id;
    String name;
    String descr;
    double value;
    double unit;
    String ref;
    String uri;
    String expcond;
    String bioel;
    String type;

    public dataset() {
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
    
     public String geturi() {
        return uri;
    }

    public void seturi(String uri) {
        this.uri = uri;
    }

    public String getref() {
        return ref;
    }

    public void setref(String ref) {
        this.ref = ref;
    }
    
     public double getvalue() {
        return value;
    }

    public void setvalue(double value) {
        this.value = value;
    }
    
     public double getunit() {
        return unit;
    }

    public void setunit(double unit) {
        this.unit = unit;
    }
    
     public String getdescr() {
        return descr;
    }

    public void setdescr(String descr) {
        this.descr = descr;
    }
    
     public String getbioel() {
        return bioel;
    }

    public void setbioel(String bioel) {
        this.bioel = bioel;
    }
    
     public String getexpcond() {
        return expcond;
    }

    public void setexpcond(String expcond) {
        this.expcond = expcond;
    }
    
    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }
    
    
}
