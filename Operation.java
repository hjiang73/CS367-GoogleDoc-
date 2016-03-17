public class Operation {
	// Enumeration of operator type.
	public enum OP {
		SET, //set,row,col,const -> set [row,col] to const
		CLEAR, //clear,row,col -> set [row,col] to 0
		ADD, //add,row,col,const -> add [row,col] by const
		SUB, //sub,row,col,const -> sub [row,col] by const
		MUL, //mul,row,col,const -> mul [row,col] by const
		DIV, //div,row,col,const -> div [row,col] by const
		UNDO, //undo the last operation
		REDO //redo the last undo
	}

	private String docName;
	private String userId;
	private OP op;
	private int rowIndex;
	private int colIndex;
	private int constant;
	private long timestamp;//TODO define some class variables.

	public Operation(String docName, String userId, OP op, int rowIndex, int
			colIndex, int constant, long timestamp) {
		if(!op.equals(OP.ADD)&&!op.equals(OP.SUB)&&
				op.equals(OP.MUL)&&!op.equals(OP.DIV)&&
				!op.equals(OP.SET))
		{
			throw new IllegalArgumentException();
		}
		if(op.equals(null)||docName.equals(null)||userId.equals(null))
		{
			throw new IllegalArgumentException();
		}
		if(rowIndex<0||colIndex<0||timestamp<0){
			throw new IllegalArgumentException();
		}
		this.docName = docName;//TODO constructor for operation class.
		this.userId = userId;
		this.op = op;
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.constant = constant;
		this.timestamp = timestamp;
	}

	public Operation(String docName, String userId, OP op, int rowIndex, int
			colIndex, long timestamp) {
		if(!op.equals(OP.CLEAR))
		{
			throw new IllegalArgumentException();
		}
		if(op.equals(null)||docName.equals(null)|userId.equals(null)){
			throw new IllegalArgumentException();
		}
		if(timestamp<0){
			throw new IllegalArgumentException();
		}
		this.docName = docName;
		this.userId = userId;
		this.op = op;
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.timestamp = timestamp;
	}

	public Operation(String docName, String userId, OP op, long timestamp) {
		if(!op.equals(OP.UNDO)&&!op.equals(OP.REDO))
		{
			throw new IllegalArgumentException();
		}
		if(op.equals(null)||docName.equals(null)|userId.equals(null)){
			throw new IllegalArgumentException();
		}
		if(timestamp<0){
			throw new IllegalArgumentException();
		}
		this.docName = docName;//TODO constructor for operation class.
		this.userId = userId;
		this.op = op;
		this.timestamp = timestamp;
	}

	public String getDocName() {
		return this.docName;
		
	}

	public String getUserId() {
		return this.userId;//TODO return user id.
		//throw new RuntimeException("getUserId not implemented");
	}

	public OP getOp() {
		return this.op;//TODO return operator type.
		//throw new RuntimeException("getOp not implemented");
	}

	public int getRowIndex() {
		if(op.equals(OP.UNDO)||op.equals(OP.REDO)){
			return -1;}//TODO return row index
		else return this.rowIndex;
	}

	public int getColIndex() {
		if(op.equals(OP.UNDO)||op.equals(OP.REDO)){
			return -1;}
		else return this.colIndex;//TODO return col index.
		// throw new RuntimeException("getColIndex not implemented");
	}

	public long getTimestamp() {
		return this.timestamp;//TODO return timtestamp.
		// throw new RuntimeException("getTimestamp not implemented");
	}

	public int getConstant() {
		if(op.equals(OP.UNDO)||op.equals(OP.REDO)||op.equals(OP.CLEAR)){
			return -1;}
		else return this.constant;//TODO return constant.
		//throw new RuntimeException("getConstant not implemented");
	}

	public String toString() {
		String s = "";
		if(this.op.equals(OP.ADD)|this.op.equals(OP.SUB)|
				this.op.equals(OP.MUL)|this.op.equals(OP.DIV)|
				this.op.equals(OP.SET)){

			s = this.timestamp+"\t"+this.docName+"\t"+
					this.userId+"\t"+this.op.toString().toLowerCase()+"\t"+
					"["+this.rowIndex+","+this.colIndex
					+"]"+"\t"+this.constant;
		}
		if(this.op.equals(OP.CLEAR)){
			s = this.timestamp+"\t"+this.docName+"\t"+
					this.userId+"\t"+this.op.toString().toLowerCase()+"\t"+
					"["+this.rowIndex+","+this.colIndex
					+"]";

		}
		if(this.op.equals(OP.UNDO)|this.op.equals(OP.REDO)) {
			s = this.timestamp+"\t"+this.docName+"\t"+
					this.userId+"\t"+this.op.toString().toLowerCase();
		}
		return s;
	} //TODO return formatted string representation of this operation.
	

}
