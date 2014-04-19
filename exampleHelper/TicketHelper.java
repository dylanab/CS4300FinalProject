package ticksys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.lang.String;

public class TicketHelper {

	
	protected PreparedStatement getTicketsStatement;
	protected PreparedStatement getSingleTicketStatement;
	protected PreparedStatement getTicketsOfWorkerStatement;
	protected PreparedStatement addTicketStatement;
 	protected PreparedStatement closeTicketStatement;
	protected PreparedStatement assignTicketStatement;
	protected PreparedStatement assignTicketToWorkerStatement;
	protected PreparedStatement addAnnotationToTicketStatement;	
	protected PreparedStatement getLastAddedTicketStatement;
	protected PreparedStatement getAnnotationsForTicketStatement;

	/**
	 * Opens a DB connection, creates prepared statements
	 * @throws Exception
	 */
	public TicketHelper() throws Exception{
		
		String JDBC_URL = "jdbc:mysql://localhost/ticketSysDB";
		String DB_USER = "luke";
		String DB_PASS = "ukulele5";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                 JDBC_URL, DB_USER, DB_PASS);
            //initialize prepared statements here
            getTicketsStatement = conn.prepareStatement(
            		"select * from Ticket");
            getSingleTicketStatement = conn.prepareStatement(
            		"select * from Ticket where id=?");
            getTicketsOfWorkerStatement = conn.prepareStatement(
            		"select * from Ticket where worker_id=?");
            addTicketStatement = conn.prepareStatement(
            		"insert into Ticket (subject, status) values(?, 'queued')");
	    closeTicketStatement = conn.prepareStatement(
			"update Ticket set status='closed' where id=?");
	    assignTicketStatement = conn.prepareStatement(
			"update Ticket set status='assigned' where id=?");
	    assignTicketToWorkerStatement = conn.prepareStatement(
			"update Ticket set worker_id=? where id=?");
	    addAnnotationToTicketStatement = conn.prepareStatement(
			"insert into Annotation (ticket_id, note) values (?,?)");
	    getLastAddedTicketStatement = conn.prepareStatement(
			"select * from Ticket where id = (select max(id) from Ticket)");
	    getAnnotationsForTicketStatement = conn.prepareStatement(
			"select note from Annotation where ticket_id=?");
			
		}
		catch(SQLException sqle)
		{
			System.out.println("exception in TicketHelper():"+sqle.getMessage());
		}
		
		
	}//constructor

	
	/**
	 * returns a list of ticket objects
	 * @return
	 */
	public ArrayList<Ticket> getTicketList() throws Exception{
		ArrayList<Ticket> result = new ArrayList<Ticket>();
		//ArrayList<String> noteResult = new ArrayList<String>();
		ResultSet rs1;
		ResultSet rs2;
		
		try{
			rs1 = getTicketsStatement.executeQuery();
			
			while(rs1.next()){
				//add a ticket and all its annotations to the result
				int id = rs1.getInt("id");
				int worker_id = rs1.getInt("worker_id");
				String status = rs1.getString("status");
				String subject = rs1.getString("subject");
				
				getAnnotationsForTicketStatement.setInt(1, id);
				rs2 = getAnnotationsForTicketStatement.executeQuery();	
			
				ArrayList<String> noteResult = new ArrayList<String>();	
				while( rs2.next() ){
				//add all annotations of a ticket to the arraylist of annots
				    String annotation = rs2.getString("note");
				    noteResult.add(annotation);
				}
				
				Ticket ticket = new Ticket(id, worker_id, status, subject, noteResult);
				//Ticket ticket = new Ticket(id, worker_id, status, subject);
		System.out.println("in getTicketList. ticket annotations are: "+ticket.getAnnotations());
				result.add(ticket);
					
	//			noteResult.clear();
			}
			
		}
		catch(SQLException sqle){
			System.out.println("exception in getTicketList():"+sqle.getMessage());
		}
		
System.out.println("in getTicketList. result is: "+result.get(0).getSubject());		
System.out.println("in getTicketList. result is: "+result.get(0).getAnnotations());
		return result;
	}

	


	/**
 	*gets all the tickets of a worker
	*/
	public ArrayList<Ticket> getWorkerTickets() throws Exception{
		ArrayList<Ticket> result = new ArrayList<Ticket>();
		ResultSet rs1;		
		ResultSet rs2;

	 	try{
			rs1 = getTicketsOfWorkerStatement.executeQuery();

                        while(rs1.next()){
                                int id = rs1.getInt("id");
                                int worker_id = rs1.getInt("worker_id");
                                String status = rs1.getString("status");
                                String subject = rs1.getString("subject");

				getAnnotationsForTicketStatement.setInt(1, id);
                                rs2 = getAnnotationsForTicketStatement.executeQuery();

                                ArrayList<String> noteResult = new ArrayList<String>();
                                while( rs2.next() ){
                                    String annotation = rs2.getString("note");
                                    noteResult.add(annotation);
                                }

                        Ticket ticket = new Ticket(id, worker_id, status, subject, noteResult);



//                                Ticket ticket = new Ticket(id, worker_id, status, subject);
                                result.add(ticket);

                        }

                }
                catch(SQLException sqle){
                        System.out.println("exception in getTicketList():"+sqle.getMessage());
                }
System.out.println("check in getWorkerTickets");
System.out.println("in workerTickets. result is: "+result.get(0).getSubject());
		return result;

	}


	


	/**
 	*returns a single ticket
	*/	 
	public ArrayList<Ticket> getSingleTicket() throws Exception{
		ResultSet rs1;
		ResultSet rs2;
		ArrayList<Ticket> result = new ArrayList<Ticket>();
	
		try{
                        rs1 = getSingleTicketStatement.executeQuery();
	
                        while( rs1.next() ){
                        	int id = rs1.getInt("id");
                        	int worker_id = rs1.getInt("worker_id");
                        	String status = rs1.getString("status");
                        	String subject = rs1.getString("subject");


				getAnnotationsForTicketStatement.setInt(1, id);
                                rs2 = getAnnotationsForTicketStatement.executeQuery();

                                ArrayList<String> noteResult = new ArrayList<String>();
                                while( rs2.next() ){
				    String annotation = rs2.getString("note");
                                    noteResult.add(annotation);
				}
				
			Ticket ticket = new Ticket(id, worker_id, status, subject, noteResult);
	
                        //Ticket ticket = new Ticket(id, worker_id, status, subject);
			result.add(ticket);
                        }
                }
                catch(SQLException sqle){
                        System.out.println("exception in getSingleList():"+sqle.getMessage());
                }	

System.out.println("in getSingleTicket.result is: "+result.get(0).getSubject());
		return result;


	}//getSingleTicket()
	
	
	

	/**
 	* returns the id of the last ticket added
 	*/ 
	public int getLastTicketId() throws Exception{
	    ResultSet rs1;
	    int id = -1;
	    try{
		rs1 = getLastAddedTicketStatement.executeQuery();

		 rs1.next();
		 id = rs1.getInt("id");
		
	    }
	    catch(SQLException sqle){
		System.out.println("in getLastAddedTicket()"+sqle.getMessage());
	    }


	return id;

	}//getlastaddedticket




	
	/**
	 * loads the specified worker_id into the
	 * getTicketsOfWorkerStatement
	 * @throws Exception
	 */
	public void setWorkerId(int id) throws Exception{
		try{
			getTicketsOfWorkerStatement.setInt(1, id);
			assignTicketToWorkerStatement.setInt(1,id);
			//addTicketStatement.setInt(2, id);
		}
		catch(SQLException sqle){
			System.out.println("Exception in setWorkerId"+sqle.getMessage());
		}
		
		
		
	}
	
	
	/**
	 * loads the specified ticket id into the 
	 * getSingleTicketStatement and addTicketStatement
	 * and addAnnotationToTicketStatement
	 * @throws Exception
	 */
	public void setTicketId(int id) throws Exception{
		try{
			getSingleTicketStatement.setInt(1, id);
			addTicketStatement.setInt(1, id);
			closeTicketStatement.setInt(1, id);
			assignTicketStatement.setInt(1, id);
			assignTicketToWorkerStatement.setInt(2, id);
			addAnnotationToTicketStatement.setInt(1, id);
		}
		catch(SQLException sqle){
			System.out.println("Exception in "+sqle.getMessage());
		}
		
	}
	
	
	/**
	 * loads the specified subject into the 
	 * addTicketStatement
	 * @throws Exception
	 */
	public void setSubject(String subject) throws Exception{
		try{
			addTicketStatement.setString(1, subject);
		}
		catch(SQLException sqle){
			System.out.println("Exception in setSubject "+sqle.getMessage());
		}
		
		
	}

	/**
	* loads the specified annotation into the
	* addAnnotationToTicketStatement
	*/
	public void setAnnotation(String annotation) throws Exception{
	    try{
		addAnnotationToTicketStatement.setString(2, annotation);

	    }
	    catch(SQLException sqle){
		System.out.println("Exception in setANnotation "+sqle.getMessage());
	    }

	}
	
	/**
 	*adds a tickt to the database
	*/	
	public void addTicket() throws Exception{
		try{
			addTicketStatement.executeUpdate();

		}
		catch(SQLException sqle){
		    System.out.println("Exception in addTicket()"+sqle.getMessage());
		}

	}
	
	/**
 	*adds an annotation to the database
 	*/ 
	public void addAnnotation() throws Exception{

	    try{
		addAnnotationToTicketStatement.executeUpdate();
	    }
	    catch(SQLException sqle){
		System.out.println("Excpetion in addAnnotation() "+sqle.getMessage());
	    }


	}

	
	
}//class
