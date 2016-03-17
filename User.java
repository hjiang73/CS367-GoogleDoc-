public class User {
    private String userId;
    private SimpleStack<WAL> undo;
    private SimpleStack<WAL> redo;
    public User(String userId) {
    	if(userId.equals(null)){
    		throw new IllegalArgumentException();
    	}
        this.userId = userId;
        undo = new SimpleStack<WAL>();
        redo = new SimpleStack<WAL>();
    }

    public WAL popWALForUndo() {
    	
        if(undo.isEmpty()){
        	return null;
        }
        else{
        	return undo.pop();
        }

    }

    public WAL popWALForRedo() {
    	 if(redo.isEmpty()){
         	return null;
         }
         else{
         	return redo.pop();
         }
      
    }

    public void pushWALForUndo(WAL trans) {
        if(trans == null){
        	throw new IllegalArgumentException();//TODO push WAL for undo
        }
        undo.push(trans);
      
    }

    public void pushWALForRedo(WAL trans) {
    	if(trans == null){
        	throw new IllegalArgumentException();//TODO push WAL for undo
        }
        redo.push(trans);
    
    }

    public void clearAllRedoWAL() {
        redo.clear();
    }

    public void clearAllUndoWAL() {
        undo.clear();
    }

    public String getUserId() {
       return this.userId; //TODO return user id.
        //throw new RuntimeException("getUserId not implemented");
    }
}
