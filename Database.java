import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Database {
	List<Document> docdb;//TODO define some class variables.

	public Database() {
		docdb = new ArrayList<Document>(); 

	}

	public void addDocument(Document doc) {
		if(doc==null){
			throw new IllegalArgumentException();
		}
		if(docdb.contains(doc)){
			throw new IllegalArgumentException();
		}
		else{
			docdb.add(doc); 
		}

	}

	public List<Document> getDocumentList() {
		return docdb;

	}

	public String update(Operation operation) {
		Iterator<Document> docitr= docdb.iterator();
		Document founddoc = null;
		String docname = operation.getDocName();
		while(docitr.hasNext()){
			Document tmp = docitr.next();
			if(tmp.getDocName().equals(docname)){
				founddoc = tmp;
			}
		}
		if(!founddoc.getAllUserIds().
				contains(operation.getUserId())){
			throw new IllegalArgumentException();

		}

		founddoc.update(operation);
		return founddoc.toString();

	}

	public Document getDocumentByDocumentName(String docName) {
		Document founddoc = null;
		Iterator<Document> itr = docdb.iterator(); 
		while(itr.hasNext()){
			Document tmp = itr.next();
			if(tmp.getDocName().equals(docName)){
				founddoc = tmp;
			}
		}
		return founddoc;
	}

}
