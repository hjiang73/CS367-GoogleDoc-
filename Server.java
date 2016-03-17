///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            P3
// Files:            "Server.java"
//                   "Database.java"
//                   "Document.java"
//                   "User.java"
//                   "Operation.java"
//                   "WAL.java"
//                   "SimpleStack.java"
//                   "SimpleQueue.java"
// Semester:         CS367 Fall 2015
//
// Author:           Han Jiang
// Email:            hjiang73@wisc.edu
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
// Lab Section:      02
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     You Wu
// Email:            wu278@wisc.edu
// CS Login:         ywu
// Lecturer's Name:  Jim Skrentny
// Lab Section:      01
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Server class represents the high level server. 
 * It opens the input file and parses it. As it is parsing each line, 
 * it creates a Operation object and inserts the Operation into a queue. 
 * Once the entire file is parsed, the server takes
 * Operations out of the queue one at a time 
 * and applies them to the database.
 * <p>Bugs: None known
 *
 * @author Han Jiang & You Wu
 */

public class Server {
	//Data field
	private String inputFileName;
	private String outputFileName;
	private SimpleQueue<Operation> operations;
	private Database db;
	public Server(String inputFileName, String outputFileName) {
		//Creates a new server with the 
		//given input file name and output file name
		//constructors
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		operations = new SimpleQueue<Operation>();
		db = new Database();
	}

	/**Run the server. 
	 * This method will call initialize and process method sequently.
	 */
	public void run(){
		initialize();
		process();
	}

	/**
	 * Initializes the server based on the information from the input file. 
	 * This is where to create document objects 
	 * and queue all operations in the input file
	 */
	public void initialize() {
		File inFile = new File(inputFileName);
		//read input file
		try {
			Scanner sc = new Scanner(inFile);
			//create a new scanner
			String line = sc.nextLine();
			//read lines
			//get the number of documents to be created
			int numDoc = Integer.parseInt(line);

			//get the rolsize,colsize and docname
			for(int i = 0; i < numDoc; i++){
				line = sc.nextLine();
				String[] docs = line.split(",");
				String docName= docs[0];
				int rowSize = Integer.parseInt(docs[1]);
				int colSize = Integer.parseInt(docs[2]);
				//create the list of users
				List<User> userlist = new ArrayList<User>();
				for(int j=3; j<docs.length; j++){
					User user = new User(docs[j]);
					userlist.add(user);
				}
				//if there are duplicate users, throw exception      
				for (int k = 0; k < userlist.size(); k++) {
					User tmp = userlist.get(k);
					for(int m = k+1;m<userlist.size(); m++){
						if(tmp.getUserId().equals(
								userlist.get(m).getUserId())){
							throw new IllegalArgumentException();
						}
					}
				}
				//create a new document
				Document doc = new Document(docName,rowSize,colSize,userlist);
				//add the document to the database
				db.addDocument(doc);
			}
			//read operation lines
			while (sc.hasNextLine()){
				line = sc.nextLine();
				String[] ops = line.split(",");
				//get timestamp,userId,docname,opname
				long timestamp = Long.parseLong(ops[0]);
				String userId = ops[1];
				String docname = ops[2];
				String opname = ops[3];
				Operation.OP op = Operation.OP.valueOf(opname.toUpperCase());
				//create set,add,sub,mul,div operations and add to queue of ops
				if(op.equals(Operation.OP.SET) || op.equals(Operation.OP.ADD)
						|| op.equals(Operation.OP.SUB)
						|| op.equals(Operation.OP.MUL) || 
						op.equals(Operation.OP.DIV)){
					int rowPos = Integer.parseInt(ops[4]);
					int colPos = Integer.parseInt(ops[5]);
					int constant = Integer.parseInt(ops[6]);
					Operation op1 = 
							new Operation(docname,userId,op,
									rowPos,colPos,constant,timestamp);
					operations.enqueue(op1);
				}
				//create clear operations and add to queue of ops
				if(op.equals(Operation.OP.CLEAR)){
					int rowPos1 = Integer.parseInt(ops[4]);
					int colPos1 = Integer.parseInt(ops[5]);
					Operation op2 = new Operation(docname,
							userId,op,rowPos1,colPos1,timestamp);
					operations.enqueue(op2);
				}	
				//create undo,redo operations and add to queue of ops
				if(op.equals(Operation.OP.UNDO)||
						op.equals(Operation.OP.REDO)){
					Operation op3 = 
							new Operation(docname,userId,op,timestamp);
					operations.enqueue(op3);
				}
			}
		} catch (FileNotFoundException e1) {

		}
	}

	/**
	 *Processes each operation. 
	 *Once queued all operations, 
	 *extracting one operation from the operation queue one at a time, 
	 *updating the database and logging everything to the output file.
	 */
	public void process() {
		//create the string like format
		String content = "";
		while(!operations.isEmpty()){
			Operation tmp = operations.dequeue();
			content += "----------Update Database----------"+"\n";
			content += tmp.toString();
			content += "\n";
			content += db.update(tmp);
			content += "\n";

		}
		try {
			//write the contents to the files
			File file = new  File(outputFileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e3) {
		}

	}
	/**
	 * main method
	 */
	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("Usage: java Server [input.txt] [output.txt]");
			System.exit(0);
		}
		Server server = new Server(args[0], args[1]);
		server.run();
	}
}
