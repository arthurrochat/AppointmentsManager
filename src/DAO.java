
public interface DAO<T> {
	
	void save(T elm);
	
	String get(String id);
	
	void update(T elm);
	
	void delete(T elm);
	
}
