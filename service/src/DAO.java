
public interface DAO<T> {
	
	void save(String data);
	
	String get(String id);
	
	void update(T elm);
	
	void delete(T elm);
	
}
