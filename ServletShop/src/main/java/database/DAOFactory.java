package database;

public class DAOFactory {
	
	public DataBaseDAOInterface createObject(int i) {
		DataBaseDAOInterface executor = null;
		switch (i){
		case 0:
			executor = new DbExecutor();
			break;
		case 1:
			break;
		default:
			executor = new DbExecutor();
		}
		return executor;
	}
	
	public static DataProductDAOInterface createProductImpl(int i) {
		DataProductDAOInterface executor = null;
		switch (i){
		case 0:
			executor = new ProductDBService(DbConnector.getInstance());
			break;
		case 1:
			break;
		default:
			executor = new ProductDBService(DbConnector.getInstance());
		}
		return executor;
	}

}
