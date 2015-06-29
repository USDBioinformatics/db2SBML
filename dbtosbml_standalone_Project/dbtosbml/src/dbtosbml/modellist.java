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
public class modellist {
    
    String id;
    String notes;
    String name;
    String annotation;
    int level;
    int version ;
    
    public String getannotation() {
        return annotation;
    }

    public void setannotation(String annotation) {
        this.annotation = annotation;
    }
    
    public modellist() {
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
    
    public String getnotes() {
        return notes;
    }

    public void setnotes(String notes) {
        this.notes = notes;
    }
    
     public int getlevel() {
        return level;
    }

    public void setlevel(int level) {
        this.level = level;
    }
    
     public int getversion() {
        return version;
    }

    public void setversion(int version) {
        this.version = version;
    }

    
}
