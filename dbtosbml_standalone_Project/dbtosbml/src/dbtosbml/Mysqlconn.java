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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Mysqlconn {
    
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
    
        public Mysqlconn(String address,String user, String pass,String dbname) {
		
		try {
//			new com.mysql.jdbc.Driver();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=testuser&password=testpassword");
//			String connectionUrl = "jdbc:mysql://sbmldb.cv2lkks86nni.us-west-2.rds.amazonaws.com:3306/sbmldb";
                        String connectionUrl = "jdbc:mysql://" + address + ":3306/" + dbname;
			String connectionUser = user;
			String connectionPassword = pass;
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} catch (Exception e) {
			e.printStackTrace();
                }
//		} finally {
//			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
//			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
//			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
//		}
	}
        
        public ArrayList<modellist> getmodels()
        {
            ArrayList<modellist> modelslist = new ArrayList<modellist>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM model";
                ps = conn.prepareStatement(selectquery);
                ResultSet rs = ps.executeQuery();
                
                  while( (rs.next()) )
                {
                    modellist model = new modellist();
                    model.setId(rs.getString("id")) ;
                    model.setName(rs.getString("name")) ;
                    
                    modelslist.add(model);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
              return modelslist ;
        }
        
         public ArrayList<modellist> getmodeldetails(String model_id)
        {
            ArrayList<modellist> modelslist = new ArrayList<modellist>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM model where id = ?";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                  while( (rs.next()) )
                {
                    modellist model = new modellist();
                    model.setId(rs.getString("id")) ;
                    model.setName(rs.getString("name")) ;
                    model.setnotes(rs.getString("notes"));
                    model.setannotation(rs.getString("annotation"));
                    model.setlevel(rs.getInt("SBML_level"));
                    model.setversion(rs.getInt("version"));
                    
                    modelslist.add(model);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
              return modelslist ;
        }
        
        public ArrayList<CompartmentList> getcompartments(String model_id)
        {
            ArrayList<CompartmentList> compartmentlist = new ArrayList<CompartmentList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM compartment where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                  while( (rs.next()) )
                {
                    CompartmentList compartment = new CompartmentList();
                    compartment.setId(rs.getString("id")) ;
                    compartment.setName(rs.getString("name")) ;
                    compartment.setconstant(rs.getBoolean("constant"));
                    compartment.setsize(rs.getDouble("size"));
                    compartment.setspatialdimensions(rs.getDouble("spacialDimensions"));
                    compartment.setunits(rs.getString("units"));
                    
                    compartmentlist.add(compartment);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
              return compartmentlist ;
        }
       
        public ArrayList<SpeciesList> getspecies(String model_id)
        {
             ArrayList<SpeciesList> specieslist = new ArrayList<SpeciesList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM species where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    SpeciesList species = new SpeciesList();
                    species.setId(rs.getString("id")) ;
                    species.setName(rs.getString("name")) ;
                    species.setcompartment(rs.getString("compartment")) ;
                    species.setic(rs.getDouble("initialConcentration"));
                    species.setia(rs.getDouble("initialAmount"));
                    species.setsu(rs.getString("substanceUnits"));
                    species.sethosu(rs.getBoolean("hasOnlySubstanceUnits"));
                    species.setconstant(rs.getBoolean("constant"));
                    species.setbc(rs.getBoolean("boundaryCondition"));
                    species.setcf(rs.getString("conversionFactor"));
                    species.setannotation(rs.getString("annotation"));
                    specieslist.add(species);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return specieslist ;
        }
        
         public ArrayList<dataset> getdataset(String model_id)
        {
             ArrayList<dataset> datasetlist = new ArrayList<dataset>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT a.id as modelid,b.bioelement_id as bioelid,c.name as name,c.descr as descr,c.type as type,d.expcond_id as expcondid,d.value as value,e.uri_efo as uri FROM model a,bioelement_has_model b,bioelement c,bioelement_has_expcond d, expcond e where \n" +
                                        "a.id = b.model_id and b.bioelement_id = c.id and c.id = d.bioelement_id and d.expcond_id = e.id and a.id = ?";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    dataset ds = new dataset();
                    ds.setName(rs.getString("name")) ;
                    ds.setdescr(rs.getString("descr"));
                    ds.setvalue(rs.getDouble("value"));
                    ds.setbioel(rs.getString("bioelid"));
                    ds.setexpcond(rs.getString("expcondid"));
                    ds.seturi(rs.getString("uri"));
                    ds.settype(rs.getString("type"));
                    datasetlist.add(ds);
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return datasetlist ;
        }
        
        public ArrayList<functionList> getfunctions(String model_id)
        {
             ArrayList<functionList> funclist = new ArrayList<functionList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM functiondefinition where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    functionList func = new functionList();
                    func.setId(rs.getString("id")) ;
                    func.setxmlns(rs.getString("xmlns")) ;
                    funclist.add(func);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return funclist ;
        }
            
        public ArrayList<unitList> getunitlist(String model_id)
        {
             ArrayList<unitList> unitlist = new ArrayList<unitList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM listofunitdefinitions where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    unitList unit = new unitList();
                    unit.setId(rs.getString("id")) ;
                    unit.setName(rs.getString("name")) ;
                    unitlist.add(unit);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return unitlist ;
        }
        
        public ArrayList<unitList> getunitdef(String unit_id)
        {
             ArrayList<unitList> unitlist = new ArrayList<unitList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM listofunits where listofunitdefinitions_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, unit_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    unitList unit = new unitList();
                    unit.setkind(rs.getString("kind"));
                    unit.setscale(rs.getInt("scale")) ;
                    unit.setexponent(rs.getDouble("exponent")) ;
                    unit.setmultiplier(rs.getDouble("multiplier")) ;
                    unitlist.add(unit);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return unitlist ;
        }
        
        public ArrayList<reactionList> getreactons(String model_id)
        {
             ArrayList<reactionList> reactionlist = new ArrayList<reactionList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM reaction where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    reactionList reaction = new reactionList();
                    reaction.setId(rs.getString("id"));
                    reaction.setName(rs.getString("name"));
                    reaction.setcompartment(rs.getString("compartment"));
                    reaction.setfast(rs.getBoolean("fast"));
                    reaction.setreversible(rs.getBoolean("reversible"));
                    reaction.setannotation(rs.getString("annotation"));
                    reactionlist.add(reaction);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return reactionlist ;
        }
        
        public ArrayList<reactionList> getreactants(String reaction_id)
        {
             ArrayList<reactionList> reactionlist = new ArrayList<reactionList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM simplespeciesreference where reaction_id = ? and speciestype = ?";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, reaction_id);
                ps.setString(2, "reactants");
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    reactionList reaction = new reactionList();
                    reaction.setspecies(rs.getString("species"));
                    reaction.setsboTerm(rs.getString("sboTerm"));
                    reaction.setstoichometry(rs.getDouble("stoichiometry"));
                    reaction.setconstant(rs.getBoolean("constant"));
                    reactionlist.add(reaction);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return reactionlist ;
        }
        
        
        public ArrayList<reactionList> getproducts(String reaction_id)
        {
             ArrayList<reactionList> reactionlist = new ArrayList<reactionList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM simplespeciesreference where reaction_id = ? and speciestype = ?";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, reaction_id);
                ps.setString(2, "products");
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    reactionList reaction = new reactionList();
                    reaction.setspecies(rs.getString("species"));
                    reaction.setsboTerm(rs.getString("sboTerm"));
                    reaction.setstoichometry(rs.getDouble("stoichiometry"));
                    reaction.setconstant(rs.getBoolean("constant"));
                    reactionlist.add(reaction);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return reactionlist ;
        }
        
        
        public ArrayList<reactionList> getmodifiers(String reaction_id)
        {
             ArrayList<reactionList> reactionlist = new ArrayList<reactionList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM modifierspeciesreference where reaction_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, reaction_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    reactionList reaction = new reactionList();
                    reaction.setspecies(rs.getString("species"));
                    reaction.setsboTerm(rs.getString("sboTerm"));
                    reactionlist.add(reaction);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return reactionlist ;
        }
        
        
        public ArrayList<reactionList> getkineticlaws(String reaction_id)
        {
             ArrayList<reactionList> reactionlist = new ArrayList<reactionList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM kineticlaw where reaction_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, reaction_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    reactionList reaction = new reactionList();
                    reaction.setkid(rs.getString("kid"));
                    reaction.setmath(rs.getString("math"));
                    reaction.setannotation(rs.getString("annotation"));
                    reactionlist.add(reaction);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return reactionlist ;
        }
        
        public ArrayList<constraintList> getconstraints(String model_id)
        {
             ArrayList<constraintList> constlist = new ArrayList<constraintList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM sbmlconstraint where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    constraintList constraint = new constraintList();
                    constraint.setmath(rs.getString("math"));
                    constraint.setmessage(rs.getString("message"));
                    constlist.add(constraint);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return constlist ;
        }
        
        public ArrayList<parameterList> getparameters(String model_id)
        {
             ArrayList<parameterList> paralist = new ArrayList<parameterList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM parameter where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    parameterList parameters = new parameterList();
                    parameters.setId(rs.getString("id"));
                    parameters.setName(rs.getString("name"));
                    parameters.setconstant(rs.getBoolean("constant"));
                    parameters.setunits(rs.getString("units"));
                    parameters.setvalue(rs.getDouble("value"));
                    paralist.add(parameters);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return paralist ;
        }
        
        public ArrayList<eventsList> getevents(String model_id)
        {
             ArrayList<eventsList> eventslist = new ArrayList<eventsList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM event where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    eventsList events = new eventsList();
                    events.setId(rs.getString("id"));
                    events.setName(rs.getString("name"));
                    events.setuservalues(rs.getBoolean("useValuesFromTriggerTime"));
                    eventslist.add(events);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return eventslist ;
        }
        
        public ArrayList<eventsList> gettriggers(String event_id)
        {
             ArrayList<eventsList> eventslist = new ArrayList<eventsList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM sbmltrigger where event_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, event_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    eventsList events = new eventsList();
                    events.setinitialval(rs.getBoolean("initialvalue"));
                    events.setpersistent(rs.getBoolean("persistent"));
                    events.setmath(rs.getString("math"));
                    eventslist.add(events);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return eventslist ;
        }
        
        public ArrayList<eventsList> getpriorities(String event_id)
        {
             ArrayList<eventsList> eventslist = new ArrayList<eventsList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM priority where event_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, event_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    eventsList events = new eventsList();
                    events.setmath(rs.getString("math"));
                    eventslist.add(events);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return eventslist ;
        }
        
        public ArrayList<eventsList> getdelays(String event_id)
        {
             ArrayList<eventsList> eventslist = new ArrayList<eventsList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM delay where event_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, event_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    eventsList events = new eventsList();
                    events.setmath(rs.getString("math"));
                    eventslist.add(events);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return eventslist ;
        }
        
        public ArrayList<eventsList> geteventassignments(String event_id)
        {
             ArrayList<eventsList> eventslist = new ArrayList<eventsList>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM eventassignment where event_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, event_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    eventsList events = new eventsList();
                    events.setvariable(rs.getString("variable"));
                    events.setmath(rs.getString("math"));
                    eventslist.add(events);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return eventslist ;
        }
        
         public ArrayList<ruleslist> getrules(String model_id)
        {
             ArrayList<ruleslist> rulelist = new ArrayList<ruleslist>();
            try {
                PreparedStatement ps  ;
                String selectquery = "SELECT * FROM rules where model_id = ? ";
                ps = conn.prepareStatement(selectquery);
                ps.setString(1, model_id);
                ResultSet rs = ps.executeQuery();
                
                while( (rs.next()) )
                {
                    ruleslist rules = new ruleslist();
                    rules.setId(rs.getString("id"));
                    rules.setmath(rs.getString("math"));
                    rules.setruletype(rs.getString("ruletype"));
                    rulelist.add(rules);
                
                }
            }
            catch (Exception e) {
			e.printStackTrace();
            }
            
            return rulelist ;
        }
        
        
          public void connclose()
        {
            try {
                conn.close();
            }
            catch (Exception e) {
			e.printStackTrace();
            }
        }
}
