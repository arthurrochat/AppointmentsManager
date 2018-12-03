import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.simpleframework.http.Query;

public abstract class Usuario implements Serializable {
	private String cpf;
	private String nome;
	private String sexo;
	private String dataNascimento;
	private String telefone;
	private String email;
	private String senha;
	private String usuario;
	protected UsuarioDAO manager;
	
	public Usuario(String cpf, String nome, String sexo, String dataNascimento, String telefone, String email, String usuario){
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.senha = cpf;
		this.usuario = usuario;
		this.manager = new UsuarioDAO("users.txt");
	}
	
	public Usuario(JSONObject json) {
		this.cpf = (String)json.get("cpf");
		this.nome =  (String)json.get("nome");
		this.sexo = (String)json.get("sexo");
		this.dataNascimento = (String)json.get("dataNascimento");
		this.telefone =  (String)json.get("telefone");
		this.email =  (String)json.get("email");
		this.senha =  (String)json.get("senha");
		this.manager = new UsuarioDAO("users.txt");
	}
	
	public Usuario(Query query) {
		this.cpf = query.get("cpf");
		this.nome = query.get("name");
		this.sexo = query.get("sexo");
		this.dataNascimento = query.get("dataNascimento");
		this.telefone = query.get("telefone");
		this.email = query.get("email");
		this.senha = query.get("password");
		this.usuario = query.get("user");
		this.manager = new UsuarioDAO("users.txt");
	}
	
	public void save(String user) {
		this.manager.save(user);
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
	
	public String getUser() {
		return this.usuario;
	}
	
	public boolean alteraSenha(String senhaAtual, String senhaNova) {
		if(this.senha.equalsIgnoreCase(senhaAtual)) {
			this.senha = senhaNova;
			return true;
		}
		return false;
	}
	
	public void atualizaDados(String cpf, String nome, String sexo, String dataNascimento, String telefone, String email, String senha){
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.telefone = dataNascimento;
		this.email = email;
		this.senha = senha;
	}
	
	public String toString() {
		return "\"cpf\":" + "\"" + this.cpf + "\"," + "\"nome\":" + "\"" + this.nome + "\"," + "\"sexo\":" + "\"" + this.sexo + "\"," + "\"dataNascimento\":" + "\"" + this.dataNascimento + "\"," + "\"telefone\":" + "\"" + this.telefone + "\"," + "\"email\":" + "\"" + this.email + "\"," + "\"user\":" + "\"" + this.usuario + "\"," + "\"senha\":" + "\"" + this.senha;
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
		obj.put("user", this.usuario);
		return obj;
	}
}
