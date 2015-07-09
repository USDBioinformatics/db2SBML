/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtosbml;

import java.io.File ;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.sbml.jsbml.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
/**
 *
 * @author Aafaque
 */
public class Dbtosbml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Model model;
        String insertstmt;
        String insertmodel="",insertspecies="",insertcompartment="",insertfunction="",insertunitdef="",insertunits="",insertreaction="",insertreactant="",insertproduct="";
        String insertmodifier="",insertklaw="",insertrules="",insertconstraint="",insertdelay="",inserttrigger="",insertevent="",inserteventassign="",insertparameter="";
        String insertstatement = "";
        String server,user,password,dbname,filepath;
        
        String Filedata="";
        
        String cwd = System.getProperty("user.dir");
                
        if(args.length == 0)
        {
            server = "localhost";
            user = "root" ;
            password = "root" ;
            dbname = "sbmldb2" ;
            
             /**
             * Path to extract the SBML files from database, where cwd is "github\db2sbml\dbtosbml_standalone_Project\dbtosbml" so add a folder in this directory
             * and mention folder name instead of extractedbm folder
             */
            
            filepath = cwd + "\\extractedbm\\" ;
        }
        
        else
        {
            server = args[0];
            user = args[1];
            password = args[2];
            dbname = args[3];
            filepath = args[4];
        }
        
        try
        {
            Filedata = readFileAsString(cwd + "\\sbmldbschema.sql");
        }
        catch (Exception e) {
			e.printStackTrace();
            }
        
        Mysqlconn sql = new Mysqlconn(server,user,password,dbname);
        //String modelids.getId() = "MorrisonAllegra" ;
        
       
        ASTNode math = null ;
        int level=0,version=0;
        
        ArrayList<modellist> modelidlist = sql.getmodels();
        insertstatement = "LOCK TABLES `model` WRITE,`species` WRITE,`compartment` WRITE,`functiondefinition` WRITE,";
        insertstatement = insertstatement + "`listofunitdefinitions` WRITE,`listofunits` WRITE,`reaction` WRITE,`simplespeciesreference` WRITE," ;
        insertstatement = insertstatement  + "`modifierspeciesreference` WRITE,`kineticlaw` WRITE,`parameter` WRITE,`sbmlconstraint` WRITE,"   ;
        insertstatement = insertstatement + "`event` WRITE,`sbmltrigger` WRITE,`delay` WRITE,`eventassignment` WRITE,`rules` WRITE"  + ";" ;
        
        for (modellist modelids : modelidlist) 
        {
        
            ArrayList<modellist> modellevel = sql.getmodeldetails(modelids.getId());
        
            for (modellist modellv : modellevel) 
            {
                level = modellv.getlevel();
                version = modellv.getversion();
            }
        
        SBMLDocument doc = new SBMLDocument(level,version);
        
        ArrayList<modellist> modellists = sql.getmodeldetails(modelids.getId());
        
        if(!modellists.isEmpty())
            insertmodel = insertmodel + "\nInsert Into model (id, name,SBML_level,version,notes,annotation) Values" ;
            for (modellist models : modellists) 
            {
                insertmodel = insertmodel + "(\'" + models.getId() + "\',\'" + models.getName()+ "\'," + models.getlevel() + "," + models.getversion()+ ",\'" + models.getnotes() + "\',\'" + models.getannotation().toString() +  "\')," ;
                model = doc.createModel(models.getId());
                model.setName(models.getName());
                // System.out.println("model : " + models.getId());
                //model.setNotes(models.getnotes());  // there is some null exception is command line run but run perfectly from netbeans so ommented out
                if(!models.getannotation().equals(""))
                {
                    Annotation annot = new Annotation(models.getannotation().toString());
                    model.setAnnotation(annot);
                }
                doc.setModel(model);
            }
            if(!modellists.isEmpty())
            {
            insertmodel = insertmodel.substring(0, insertmodel.length()-1);
            insertmodel = insertmodel + ';' ;
            }
     //   insertmodel = insertmodel + "\nUNLOCK TABLES;";
      //  System.out.println(insertmodel);
           
        
        ArrayList<SpeciesList> specieslist = sql.getspecies(modelids.getId());
        
        if(!specieslist.isEmpty())
            insertspecies = insertspecies + "\nInsert Into species (id, name, compartment, initialAmount, initialConcentration,substanceUnits,hasOnlySubstanceUnits,boundaryCondition,constant,conversionFactor,model_id,annotation) Values" ;
            for (SpeciesList species : specieslist) 
             {
                 insertspecies = insertspecies + "(\'" + species.getId() + "\',\'" + species.getName()+ "\',\'" + species.getcompartment() + "\'," + species.getia()+ "," + species.getic()+ ",\'" + species.getsu() + "\'," + species.gethosu()+ "," + species.getbc() + "," + species.getconstant()+ "," + species.getcf() + ",\'" + modelids.getId() + "\',\'" + species.getannotation() + "\')," ;
                 Species sp = doc.getModel().createSpecies(species.getId());
                 sp.setName(species.getName());
                 sp.setCompartment(species.getcompartment());
                 sp.setConstant(species.getconstant());
                 sp.setInitialAmount(species.getia()) ;
                 sp.setInitialConcentration(species.getic());
                 sp.setHasOnlySubstanceUnits(species.gethosu());
                 if(doc.getModel().getLevel() == 3)
                 sp.setConversionFactor(species.getcf());
                 sp.setBoundaryCondition(species.getbc());
                 sp.setSubstanceUnits(species.getsu());
                 if(!species.getannotation().equals(""))
                 {
                      Annotation annot = new Annotation(species.getannotation().toString());
                      sp.setAnnotation(annot);
                 }
                  // doc.getModel().addSpecies(sp) ;
            }
            if(!specieslist.isEmpty())
            {
            insertspecies = insertspecies.substring(0, insertspecies.length()-1);
            insertspecies = insertspecies + ';' ;
            }
          
          
        ArrayList<CompartmentList> complist = sql.getcompartments(modelids.getId());
          
        if(!complist.isEmpty())
            insertcompartment = insertcompartment + "\nInsert Into compartment (id, name,constant,model_id,spacialDimensions,size,units) Values" ;
          
            for (CompartmentList comp : complist) 
             {
                 insertcompartment = insertcompartment + "(\'" + comp.getId() + "\',\'" + comp.getName()+ "\'," + comp.getconstant()+ ",\'" + modelids.getId() + "\'," + comp.getspatialdimensions()+ "," + comp.getsize()+ "," + comp.getunits()+ "\')," ;
                 Compartment c = doc.getModel().createCompartment(comp.getId());
                 c.setName(comp.getName());
                 c.setConstant(comp.getconstant());
                 c.setSize(comp.getsize()) ;
                 c.setSpatialDimensions(comp.getspatialdimensions());
                 if(comp.getspatialdimensions() != 0 )
                 c.setUnits(comp.getunits());
                  // doc.getModel().addSpecies(sp) ;
             }
            if(!complist.isEmpty())
            {
            insertcompartment = insertcompartment.substring(0, insertcompartment.length()-1);
            insertcompartment = insertcompartment + ';' ;
            }
            
          
        ArrayList<functionList> funclist = sql.getfunctions(modelids.getId());
        
        if(!funclist.isEmpty())
            insertfunction = insertfunction + "\nInsert Into functiondefinition (id, xmlns,model_id) Values" ;
            
            for (functionList func : funclist) 
            {
                insertfunction = insertfunction + "(\'" + func.getId() + "\',\'" + func.getxmlns()+ "\',\'" + modelids.getId() + "\')," ;
                FunctionDefinition fd = doc.getModel().createFunctionDefinition(func.getId());

                try
                {
                    math = ASTNode.parseFormula(func.getxmlns()) ;
                     fd.setMath(math);
                }
                catch (Exception e) {
                         e.printStackTrace();
                }

            }
            if(!funclist.isEmpty())
            {
            insertfunction = insertfunction.substring(0, insertfunction.length()-1);
            insertfunction = insertfunction + ';' ;
            }

        ArrayList<unitList> unitlist = sql.getunitlist(modelids.getId());
        
        if(!unitlist.isEmpty())
            insertunitdef = insertunitdef + "\nInsert Into listofunitdefinitions (id,name,model_id) Values" ;

            for (unitList units : unitlist) 
            {
                insertunitdef = insertunitdef + "(\'" + units.getId() + "\',\'" + units.getName()+ "\',\'" + modelids.getId() + "\')," ;
                   
                UnitDefinition ud = doc.getModel().createUnitDefinition(units.getId());
                ud.setName(units.getName());
                ArrayList<unitList> unitdeflist = sql.getunitdef(units.getId());
                
                if(!unitdeflist.isEmpty())
                    insertunits = insertunits + "\nInsert Into listofunits (listofunitdefinitions_id,kind, scale,exponent,multiplier) Values" ;

                     for (unitList unitdef : unitdeflist) 
                     {
                         insertunits = insertunits + "(\'" + units.getId() + "\',\'" + unitdef.getkind() + "\'," + unitdef.getscale() + "," + unitdef.getexponent() + "," + unitdef.getmultiplier() + ")," ;
                         Unit u = ud.createUnit(Unit.Kind.valueOf(unitdef.getkind()));
                         u.setScale(unitdef.getscale());
                         u.setExponent(unitdef.getexponent());
                         u.setMultiplier(unitdef.getmultiplier());
                     }
                     // doc.getModel().addSpecies(sp) ;
                     if(!unitdeflist.isEmpty())
                     {
                     insertunits = insertunits.substring(0, insertunits.length()-1);
                     insertunits = insertunits + ';' ;
                     }
            }
            if(!unitlist.isEmpty())
            {
            insertunitdef = insertunitdef.substring(0, insertunitdef.length()-1);
            insertunitdef = insertunitdef + ';' ;
            }
          
        ArrayList<reactionList> reactionlist = sql.getreactons(modelids.getId());
        
        if(!reactionlist.isEmpty())
            insertreaction = insertreaction + "\nInsert Into reaction (id,name, reversible,fast,model_id,compartment,annotation) Values" ;
          
            for (reactionList reaction : reactionlist) 
            {
                insertreaction = insertreaction + "(\'" + reaction.getId() + "\',\'" + reaction.getName()+ "\'," + reaction.getreversible()+ "," + reaction.getfast() +  ",\'" + modelids.getId() + "\',\'" + reaction.getcompartment()+ "\',\'" + reaction.getannotation() + "\')," ;
                Reaction rn = doc.getModel().createReaction(reaction.getId());
                rn.setName(reaction.getName());
                if(doc.getModel().getLevel() == 3)
                 rn.setCompartment(reaction.getcompartment());
                rn.setFast(reaction.getfast());
                rn.setReversible(reaction.getreversible());
                if(!reaction.getannotation().equals(""))
                 {
                     Annotation annot = new Annotation(reaction.getannotation().toString());
                     rn.setAnnotation(annot);
                 }

                ArrayList<reactionList> reactantlist = sql.getreactants(reaction.getId());
                
                if(!reactantlist.isEmpty())
                    insertreactant = insertreactant + "\nInsert Into simplespeciesreference (reaction_id,species, sboTerm,stoichiometry,speciestype,constant) Values" ;
                    for (reactionList reactant : reactantlist) 
                    {
                        insertreactant = insertreactant + "(\'" + reaction.getId() + "\',\'" + reactant.getspecies() + "\',\'" + reactant.getsboTerm()+ "\'," + reactant.getstoichometry()+  "," + reactant.getconstant() + ",\'reactants\')," ;
                        SpeciesReference rt = new SpeciesReference();
                        rt.setName(reactant.getspecies());
                        rt.setSpecies(reactant.getspecies());
                       //rt.setSBOTerm(reactant.getsboTerm());
                        rt.setStoichiometry(reactant.getstoichometry());
                    //    rt.setConstant(reactant.getconstant());
                        rn.addReactant(rt);
                    }
                    if(!reactantlist.isEmpty())
                    {
                    insertreactant = insertreactant.substring(0, insertreactant.length()-1);
                    insertreactant = insertreactant + ';' ;
                    }

                ArrayList<reactionList> productlist = sql.getproducts(reaction.getId());
                
                 if(!productlist.isEmpty())
                    insertproduct = insertproduct + "\nInsert Into simplespeciesreference (reaction_id,species, sboTerm,stoichiometry,constant,speciestype) Values" ;
                    for (reactionList product : productlist) 
                    {
                        insertproduct = insertproduct + "(\'" + reaction.getId() + "\',\'" + product.getspecies() + "\',\'" + product.getsboTerm()+ "\'," + product.getstoichometry()+  "," + product.getconstant() + ",\'products\')," ;
                        SpeciesReference pr = new SpeciesReference();
                        pr.setName(product.getspecies());
                        pr.setSpecies(product.getspecies());
                     //   pr.setSBOTerm(product.getsboTerm());
                        pr.setStoichiometry(product.getstoichometry());
                    //    pr.setConstant(product.getconstant());
                        rn.addProduct(pr);
                    }
                    if(!productlist.isEmpty())
                    {
                    insertproduct = insertproduct.substring(0, insertproduct.length()-1);
                    insertproduct = insertproduct + ';' ;
                    }

                ArrayList<reactionList> modifierlist = sql.getmodifiers(reaction.getId());
                
                if(!modifierlist.isEmpty())
                    insertmodifier = insertmodifier + "\nInsert Into modifierspeciesreference (reaction_id,species, sboTerm,speciestype) Values" ;
                    for (reactionList modifier : modifierlist) 
                    {
                        insertmodifier = insertmodifier + "(\'" + reaction.getId() + "\',\'" + modifier.getspecies()+ "\',\'" + modifier.getsboTerm()+ "\',\'modifiers\')," ;
                        ModifierSpeciesReference m = new ModifierSpeciesReference();
                        m.setName(modifier.getspecies());
                        m.setSpecies(modifier.getspecies());
                    //    m.setSBOTerm(modifier.getsboTerm());
                        rn.addModifier(m);
                    }
                    if(!modifierlist.isEmpty())
                    {
                    insertmodifier = insertmodifier.substring(0, insertmodifier.length()-1);
                    insertmodifier = insertmodifier + ';' ;
                    }

                ArrayList<reactionList> klawlist = sql.getkineticlaws(reaction.getId());

                if(!klawlist.isEmpty())
                    insertklaw = insertklaw + "\nInsert Into kineticlaw (reaction_id,kid, math,annotation) Values" ;
                    for (reactionList klaw : klawlist) 
                    {
                        insertklaw = insertklaw + "(\'" + reaction.getId() + "\',\'" + klaw.getId()+ "\',\'" + klaw.getmath()+ "\',\'" + klaw.getannotation()+ "\')," ;
                        KineticLaw kl = rn.createKineticLaw();
                         try
                        {
                            math = ASTNode.parseFormula(klaw.getmath()) ;
                            kl.setMath(math);
                            if(!klaw.getannotation().equals(""))
                            {
                                Annotation annot = new Annotation(klaw.getannotation().toString());
                                kl.setAnnotation(annot);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(!klawlist.isEmpty())
                    {
                    insertklaw = insertklaw.substring(0, insertklaw.length()-1);
                    insertklaw = insertklaw + ';' ;
                    }
                 
            }
            if(!reactionlist.isEmpty())
            {
            insertreaction = insertreaction.substring(0, insertreaction.length()-1);
            insertreaction = insertreaction + ';' ;
            }
          
          
        ArrayList<parameterList> paralist = sql.getparameters(modelids.getId());
        
        if(!paralist.isEmpty())
            insertparameter = insertparameter + "\nInsert Into parameter (id,name,value,units,constant,model_id) Values" ;
          
            for (parameterList para : paralist) 
            {
                insertparameter = insertparameter + "(\'" + para.getId() + "\',\'" + para.getName()+ "\'," + para.getvalue()+ "," + para.getunits()+ "," + para.getconstant()+ ",\'" + modelids.getId() + "\')," ;
                Parameter par = doc.getModel().createParameter(para.getId());
                    par.setName(para.getId());
                    par.setConstant(para.getconstant());
                    par.setUnits(para.getunits());
                    par.setValue(para.getvalue());       
            }
            if(!paralist.isEmpty())
            {
            insertparameter = insertparameter.substring(0, insertparameter.length()-1);
            insertparameter = insertparameter + ';' ;
            }

        ArrayList<constraintList> conslist = sql.getconstraints(modelids.getId());
         
        if(!conslist.isEmpty())
            insertconstraint = insertconstraint + "\nInsert Into sbmlconstraint (math,message,model_id) Values" ;
          
            for (constraintList constraint : conslist) 
             {
                 insertconstraint = insertconstraint + "(\'" + constraint.getmath()+ "\',\'" + constraint.getmessage()+ "\',\'" + modelids.getId() + "\')," ;
                 Constraint cons = doc.getModel().createConstraint();
                 try
                 {
                     math = ASTNode.parseFormula(constraint.getmath()) ;
                      cons.setMath(math);
                      cons.setMessage(constraint.getmessage());
                 }
                 catch (Exception e) {
                          e.printStackTrace();
                 }

             }
            if(!conslist.isEmpty())
            {
            insertconstraint = insertconstraint.substring(0, insertconstraint.length()-1);
            insertconstraint = insertconstraint + ';' ;
            }
          
        ArrayList<eventsList> eventlist = sql.getevents(modelids.getId());
        
        if(!eventlist.isEmpty())
            insertevent = insertevent + "\nInsert Into event (id,name,UseValuesFromTriggerTime,model_id) Values" ;
            
            for (eventsList events : eventlist) 
            {
                 insertevent = insertevent + "(\'" + events.getId() + "\',\'" + events.getName()+ "\'," + events.getuservalues()+ ",\'" + modelids.getId() + "\')," ;
                 Event ev = doc.getModel().createEvent(events.getId());
                 ev.setName(events.getName());
                // ev.setUseValuesFromTriggerTime(events.getuservalues());

                ArrayList<eventsList> triggerlist = sql.gettriggers(events.getId());
                
                if(!triggerlist.isEmpty())
                  inserttrigger = inserttrigger + "\nInsert Into sbmltrigger (event_id,initialvalue,persisent,math) Values" ;
                  for (eventsList triggers : triggerlist) 
                  {
                          Trigger tr = doc.getModel().createTrigger();
                           try
                              {
                                   math = ASTNode.parseFormula(triggers.getmath()) ;
                                   tr.setMath(math);
                                   tr.setInitialValue(triggers.getinitialval());
                                   tr.setPersistent(triggers.getpersistent());
                              }
                              catch (Exception e) {
                                       e.printStackTrace();
                              }
                  }
                  if(!triggerlist.isEmpty())
                  {
                  inserttrigger = inserttrigger.substring(0, insertmodel.length()-1);
                  inserttrigger = inserttrigger + ';' ;
                  }

                ArrayList<eventsList> delaylist = sql.getdelays(events.getId());
                
                if(!delaylist.isEmpty())
                  insertdelay = insertdelay + "\nInsert Into delay (event_id,math) Values" ;
                  for (eventsList delays : delaylist) 
                  {
                          Delay d = doc.getModel().createDelay();
                           try
                              {
                                   math = ASTNode.parseFormula(delays.getmath()) ;
                                   d.setMath(math);
                              }
                              catch (Exception e) {
                                       e.printStackTrace();
                              }
                  }
                  if(!delaylist.isEmpty())
                  {
                  insertdelay = insertdelay.substring(0, insertdelay.length()-1);
                  insertdelay = insertdelay + ';' ;
                  }

                ArrayList<eventsList> evasslist = sql.geteventassignments(events.getId());
                
                if(!evasslist.isEmpty())
                  inserteventassign = inserteventassign + "\nInsert Into eventassignment (event_id,variable,math) Values" ;
                  for (eventsList evassign : evasslist) 
                  {
                          EventAssignment ea = doc.getModel().createEventAssignment();
                           try
                              {
                                   math = ASTNode.parseFormula(evassign.getmath()) ;
                                   ea.setMath(math);
                              }
                              catch (Exception e) {
                                       e.printStackTrace();
                              }
                  }
                   if(!evasslist.isEmpty())
                   {
                  inserteventassign = inserteventassign.substring(0, inserteventassign.length()-1);
                  inserteventassign = inserteventassign + ';' ;
                   }

            }
            if(!eventlist.isEmpty())
            {
            insertevent = insertevent.substring(0, insertevent.length()-1);
            insertevent = insertevent + ';' ;
            }
          
        ArrayList<ruleslist> rulelist = sql.getrules(modelids.getId());
        
        if(!rulelist.isEmpty())
            insertrules = insertrules + "\nInsert Into rules (id,math,ruletype,model_id) Values" ;
            for (ruleslist rules : rulelist) 
             {
                 insertrules = insertrules + "(\'" + rules.getId() + "\',\'" + rules.getmath() + "\',\'" + rules.getruletype() + "\',\'"  +  modelids.getId() + "\')," ;
                 if(rules.getruletype().equals("assignmentrule"))
                 {
                      Rule r = doc.getModel().createAssignmentRule();
                      r.setMetaId(rules.getId());
                      try
                      {
                          math = ASTNode.parseFormula(rules.getmath()) ;
                          r.setMath(math);
                      }
                      catch (Exception e) {
                          e.printStackTrace();
                      }
                 }
             }
            if(!rulelist.isEmpty())
            {
            insertrules = insertrules.substring(0, insertrules.length()-1);
            insertrules = insertrules + ';' ;
            }
          
          SBMLWriter writer = new SBMLWriter();
          try
          {
                String Path = filepath + modelids.getId() + ".xml" ;
                writer.write(doc, Path);
                
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(Path);
                Element root = document.getDocumentElement();
                Element newdataset = document.createElement("dataset");
                root.appendChild(newdataset);
                
                ArrayList<dataset> datasetlist = sql.getdataset(modelids.getId());
                for (dataset ds : datasetlist) 
                {
                    //System.out.println(ds.getexpcond());
                    
                    Element name = document.createElement("experimentalcondition");
                    name.setAttribute("bioelement", ds.getbioel());
                    name.setAttribute("name", ds.getName());
                    name.setAttribute("descr", ds.getdescr());
                    name.setAttribute("expcond", ds.getexpcond());
                    name.setAttribute("value", String.valueOf(ds.getvalue()));
                    name.setAttribute("type", ds.gettype());
                    name.setAttribute("uri", ds.geturi());
                    newdataset.appendChild(name);
                    
                }
                
                root.appendChild(newdataset);
                DOMSource source = new DOMSource(document);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                StreamResult result = new StreamResult(filepath +  modelids.getId() + "d.xml");
                transformer.transform(source, result);
                System.out.println("Files : " + modelids.getId() + ".xml and " + modelids.getId() + "d.xml have been generated successfully !!!" );
                
          }
          catch (Exception e) {
			e.printStackTrace();
            }
          
        insertstatement = insertstatement + "\n\n" + insertmodel + "\n" + insertspecies + "\n" + insertcompartment + "\n" + insertfunction ;
        insertstatement = insertstatement + "\n" + insertparameter + "\n" + insertreaction + "\n" + insertreactant + "\n" + insertproduct ;
        insertstatement = insertstatement + "\n" + insertmodifier + "\n" + insertklaw + "\n" + insertunitdef + "\n" + insertunits ;  
        insertstatement = insertstatement + "\n" + insertrules + "\n" + insertconstraint + "\n" + insertevent + "\n" + inserttrigger + "\n" + insertdelay + "\n" + inserteventassign;
        
        insertcompartment = "";insertmodel = "" ;insertspecies = "" ;
        
        //System.out.println("document : " + doc);
        }
        insertstatement = insertstatement + "\nUNLOCK TABLES;";
        Filedata = Filedata + "\n\n\n" + insertstatement ;
        
        try 
        {
            wrtireStringToFile(Filedata,filepath + "sbmldb.sql");
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();
        }
        //System.out.println(insertstatement);
    }
    
    private static String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
    
    private static void wrtireStringToFile(String filecontent,String filePath) throws IOException 
    {
                File file = new File(filePath);
                    if (!file.exists())
                    {
                        file.createNewFile();
                    }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(filecontent);
                bw.close();
    }
    
}
