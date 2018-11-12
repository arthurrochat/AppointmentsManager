import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileSystem<T> {
	
	void write(String data, String path) {
		try {
			FileWriter fileWriter = new FileWriter(path, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write(data);
			writer.newLine();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void write(ArrayList<String> data, String path) {
		try {
			FileWriter fileWriter = new FileWriter(path, false);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for(String row : data) {
				writer.write(row);
				writer.newLine();
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	ArrayList<String> read(String path) {
		ArrayList<String> data = new ArrayList();
		String line;
		try {
			FileReader fi = new FileReader(path);
			BufferedReader reader = new BufferedReader(fi);
			line = reader.readLine();
			while(line != null) {
				data.add(line);
				line = reader.readLine();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return data;
		}
		
	}
	
}
