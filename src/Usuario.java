import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.simpleframework.http.Query;

public abstract class Usuario implements Serializable {
	private String cpf;
	private String nome;
	private boolean sexo;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;
	private String senha;
	protected UserDAO manager;
	
	public Usuario(String cpf, String nome, boolean sexo, LocalDate dataNascimento, String telefone, String email){
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.senha = cpf;
		
		this.manager = new UserDAO("users.txt");
	}
	
	public Usuario(JSONObject json) {
		this.cpf = (String)json.get("cpf");
		this.nome =  (String)json.get("nome");
		this.sexo = json.getBoolean("sexo");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		this.dataNascimento = LocalDate.parse((String)json.get("dataNascimento"),formatter);
		this.telefone =  (String)json.get("telefone");
		this.email =  (String)json.get("email");
		this.senha =  (String)json.get("senha");
		
		this.manager = new UserDAO("users.txt");
	}
	
	public Usuario(Query query) {
		this.cpf = query.get("cpf");
		this.nome = query.get("nome");
		if(query.getInteger("sexo") == 1)
			this.sexo = true;
		else
			this.sexo = false;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		this.dataNascimento = LocalDate.parse(query.get("dataNascimento"),formatter);
		this.telefone = query.get("telefone");
		this.email = query.get("email");
		this.senha = this.cpf;
		
		this.manager = new UserDAO("users.txt");
	}
	
	public void save() {
		this.manager.save(this);
	}
	
	public String read(String user) {
		return manager.get(user);
	}
	
	public void update() {
		manager.update(this);
	}
	
	public void delete() {
		manager.delete(this);
	}
	
	public String getCPF() {
		return this.cpf;
	}
	
	public boolean alteraSenha(String senhaAtual, String senhaNova) {
		if(this.senha.equalsIgnoreCase(senhaAtual)) {
			this.senha = senhaNova;
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Cpf: " + this.cpf + "\nNome: " + this.nome + "\nSexo: " + this.sexo + "\nData de Nascimento:" + this.dataNascimento + "\nTelefone: " + this.telefone + "\nEmail: " + this.email;
	}
	
	public void atualizaDados(String cpf, String nome, boolean sexo, LocalDate dataNascimento, String telefone, String email){
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.senha = cpf;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("cpf", this.cpf);
		obj.put("nome", this.nome);
		obj.put("sexo", this.sexo);
		obj.put("dataNascimento", this.dataNascimento);
		obj.put("telefone", this.telefone);
		obj.put("email", this.email);
		obj.put("senha", this.senha);
		return obj;
	}
}
