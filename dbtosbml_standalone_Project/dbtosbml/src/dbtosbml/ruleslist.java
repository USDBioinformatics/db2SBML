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
public class ruleslist {
    
     String id;
    String math;
    String ruletype;

    public ruleslist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmath() {
        return math;
    }

    public void setmath(String math) {
        this.math = math;
    }
    
     public String getruletype() {
        return ruletype;
    }

    public void setruletype(String ruletype) {
        this.ruletype = ruletype;
    }

    
}
