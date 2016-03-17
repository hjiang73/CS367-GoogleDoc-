import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;




public class Document {
	private String docName;//TODO define some class variables.
	private int rowSize;
	private int colSize;
	private List<User> userList;
	private int[][] table;
	public Document(String docName, int rowSize, int colSize, List<User>
	userList) {
		if(docName.equals(null)||rowSize<1||colSize<1||userList==null
				||userList.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		this.docName = docName;
		this.rowSize = rowSize;
		this.colSize = colSize;
		table = new int[rowSize][colSize];
		this.userList = userList;

	}

	public List<String> getAllUserIds() {
		List<String> allUserIds = new ArrayList<String>();
		Iterator<User> itr = this.userList.iterator(); 
		while(itr.hasNext()){
			allUserIds.add(itr.next().getUserId());//TODO get all user ids
		}
		return allUserIds;

	}
	public void update(Operation operation) {
		if(operation == null)
		{
			throw new IllegalArgumentException();
		}
		switch(operation.getOp())
		{
		case SET:
			if(operation.getRowIndex()<0||operation.getColIndex()<0||
					operation.getRowIndex()>rowSize-1||operation.getColIndex()>colSize-1)
			{
				throw new IllegalArgumentException();
			}
			int oldvalue = table[operation.getRowIndex()][operation.getColIndex()];
			table[operation.getRowIndex()][operation.getColIndex()]
					=operation.getConstant();
			WAL wal1 = new WAL(operation.getRowIndex(),operation.getColIndex(),oldvalue);
			User founduser1 = null;
			String user1 = operation.getUserId();
			Iterator<User> itr1 = this.userList.iterator(); //TODO return the user with given user id.
			while(itr1.hasNext()){
				User tmp = itr1.next();
				if(tmp.getUserId().equals(user1)){
					founduser1 = tmp;//TODO get all user ids
				}
			}
			founduser1.pushWALForUndo(wal1);
			founduser1.pushWALForRedo(wal1);
			break;


		case CLEAR:
			if(operation.getRowIndex()<0||operation.getColIndex()<0||
					operation.getRowIndex()>rowSize-1||operation.getColIndex()>colSize-1)
			{
				throw new IllegalArgumentException();
			}
			int oldvalue2 = table[operation.getRowIndex()][operation.getColIndex()];
			table[operation.getRowIndex()][operation.getColIndex()]
					=0;
			WAL wal2 = new WAL(operation.getRowIndex(),operation.getColIndex(),oldvalue2);
			User founduser2 = null;
			String user2 = operation.getUserId();
			Iterator<User> itr2 = this.userList.iterator(); //TODO return the user with given user id.
			while(itr2.hasNext()){
				User tmp = itr2.next();
				if(tmp.getUserId().equals(user2)){
					founduser2 = tmp;//TODO get all user ids
				}
			}
			founduser2.pushWALForUndo(wal2);
			founduser2.pushWALForRedo(wal2);
			break;
		case ADD:
			if(operation.getRowIndex()<0||operation.getColIndex()<0||
					operation.getRowIndex()>rowSize-1||operation.getColIndex()>colSize-1)
			{
				throw new IllegalArgumentException();
			}
			int oldvalue3 = table[operation.getRowIndex()][operation.getColIndex()];

			int sum	=table[operation.getRowIndex()][operation.getColIndex()]+operation.getConstant();

			table[operation.getRowIndex()][operation.getColIndex()] = sum;
			WAL wal3 = new WAL(operation.getRowIndex(),operation.getColIndex(),oldvalue3);
			User founduser3 = null;
			String user3 = operation.getUserId();
			Iterator<User> itr3 = this.userList.iterator(); 
			while(itr3.hasNext()){
				User tmp = itr3.next();
				if(tmp.getUserId().equals(user3)){
					founduser3 = tmp;
				}
			}
			founduser3.pushWALForUndo(wal3);
			founduser3.pushWALForRedo(wal3);
			break;

		case SUB:
			if(operation.getRowIndex()<0||operation.getColIndex()<0||
					operation.getRowIndex()>rowSize-1||operation.getColIndex()>colSize-1)
			{
				throw new IllegalArgumentException();
			}
			int oldvalue4 = table[operation.getRowIndex()][operation.getColIndex()];
			table[operation.getRowIndex()][operation.getColIndex()]
					-= operation.getConstant();
			System.out.println(table[operation.getRowIndex()][operation.getColIndex()]);
			WAL wal4 = new WAL(operation.getRowIndex(),operation.getColIndex(),oldvalue4);
			User founduser4 = null;
			String user4 = operation.getUserId();
			Iterator<User> itr4 = this.userList.iterator(); //TODO return the user with given user id.
			while(itr4.hasNext()){
				User tmp = itr4.next();
				if(tmp.getUserId().equals(user4)){
					founduser4 = tmp;//TODO get all user ids
				}
			}
			founduser4.pushWALForUndo(wal4);
			founduser4.pushWALForRedo(wal4);
			break;

		case MUL:
			if(operation.getRowIndex()<0||operation.getColIndex()<0||
					operation.getRowIndex()>rowSize-1||operation.getColIndex()>colSize-1)
			{
				throw new IllegalArgumentException();
			}
			int oldvalue5 = table[operation.getRowIndex()][operation.getColIndex()];
			table[operation.getRowIndex()][operation.getColIndex()]
					=table[operation.getRowIndex()][operation.getColIndex()]
							*operation.getConstant();
			WAL wal5 = new WAL(operation.getRowIndex(),operation.getColIndex(),oldvalue5);
			User founduser5 = null;
			String user5 = operation.getUserId();
			Iterator<User> itr5 = this.userList.iterator(); //TODO return the user with given user id.
			while(itr5.hasNext()){
				User tmp = itr5.next();
				if(tmp.getUserId().equals(user5)){
					founduser5 = tmp;//TODO get all user ids
				}
			}
			founduser5.pushWALForUndo(wal5);
			founduser5.pushWALForRedo(wal5);
			break;

		case DIV:
			
			if(operation.getRowIndex()<0||operation.getColIndex()<0||
					operation.getRowIndex()>rowSize-1||operation.getColIndex()>colSize-1)
			{
				throw new IllegalArgumentException();
			}
			int oldvalue6 = table[operation.getRowIndex()][operation.getColIndex()];
			if(operation.getConstant()==0){
				throw new IllegalArgumentException();
			}
			table[operation.getRowIndex()][operation.getColIndex()]
					=table[operation.getRowIndex()][operation.getColIndex()]
							/operation.getConstant();
			WAL wal6 = new WAL(operation.getRowIndex(),operation.getColIndex(),oldvalue6);
			User founduser6 = null;
			String user6 = operation.getUserId();
			Iterator<User> itr6 = this.userList.iterator(); //TODO return the user with given user id.
			while(itr6.hasNext()){
				User tmp = itr6.next();
				if(tmp.getUserId().equals(user6)){
					founduser6 = tmp;//TODO get all user ids
				}
			}
			founduser6.pushWALForUndo(wal6);
			founduser6.pushWALForRedo(wal6);
			break;

		case UNDO:
			User founduser7 = null;
			String user7 = operation.getUserId();
			Iterator<User> itr7 = this.userList.iterator(); //TODO return the user with given user id.
			while(itr7.hasNext()){
				User tmp = itr7.next();
				if(tmp.getUserId().equals(user7)){
					founduser7= tmp;//TODO get all user ids
				}
			}

			WAL tmp1 = founduser7.popWALForUndo();
			int value = tmp1.getOldValue();
			WAL aa = new WAL(tmp1.getRowIndex(),tmp1.getColIndex(),table[tmp1.getRowIndex()][tmp1.getColIndex()]);
			founduser7.pushWALForRedo(aa);
			founduser7.pushWALForUndo(aa);
			table[tmp1.getRowIndex()][tmp1.getColIndex()]
					=value;

			break;

		case REDO:
			User founduser8 = null;
			String user8 = operation.getUserId();
			Iterator<User> itr8 = this.userList.iterator(); 
			while(itr8.hasNext()){
				User tmp = itr8.next();
				if(tmp.getUserId().equals(user8)){
					founduser8= tmp;
				}
			}
			WAL tmp2 = founduser8.popWALForRedo();
			int value1 = tmp2.getOldValue();
			WAL bb = new WAL(tmp2.getRowIndex(),tmp2.getColIndex(),table[tmp2.getRowIndex()][tmp2.getColIndex()]);
			founduser8.pushWALForRedo(bb);
			founduser8.pushWALForUndo(bb);
			table[tmp2.getRowIndex()][tmp2.getColIndex()]
					=value1;
			break;

		default:  
			throw new IllegalArgumentException();
		}
	}

	public String getDocName() {
		return this.docName;

	}

	public User getUserByUserId(String userId) {
		User founduser = null;
		Iterator<User> itr = this.userList.iterator(); 
		while(itr.hasNext()){
			User tmp = itr.next();
			if(tmp.getUserId().equals(userId)){
				founduser = tmp;
			}
		}
		return founduser;
	}

	public int getCellValue(int rowIndex, int colIndex){
		if(rowIndex<0||colIndex<0||rowIndex >= rowSize|colIndex >=colSize){
			throw new IllegalArgumentException();
		}
		int cellValue = table[rowIndex][colIndex];
		return cellValue;

	}

	public String toString() {
		String s1 = "Document "+"Name: "+docName+"\t"+"Size: "+"["+rowSize+","+colSize+"]"+"\n"+
				"Table:"+"\n";
		for(int i = 0; i < rowSize ; i++){
			for(int j = 0; j< colSize;j++){
				s1 = s1+table[i][j]+"\t";
			}
			s1 = s1+"\n";
		}
		
		return s1;
	}
}
