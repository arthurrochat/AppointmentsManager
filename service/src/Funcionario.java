import org.json.JSONObject;
import org.simpleframework.http.Query;

public class Funcionario extends Usuario {
	private String profissao;
	private String carteiraTrabalho;
	private String salario;
	private String dataAdmissao;
	
	public Funcionario(String cpf, String nome, String sexo, String dataNascimento, String telefone, String email, String profissao, String carteiraTrabalho, String salario, String dataAdmissao, String usuario) {
		super(cpf, nome, sexo, dataNascimento, telefone, email, usuario);
		this.profissao = profissao;
		this.carteiraTrabalho = carteiraTrabalho;
		this.salario = salario;
		this.dataAdmissao = dataAdmissao;
	}
	
	public Funcionario(JSONObject json) {
		super(json);
		this.profissao = (String)json.get("profissao");
		this.carteiraTrabalho = (String)json.get("carteiraTrabalho");
		this.salario = (String)json.get("salario");
		this.dataAdmissao  = (String)json.get("dataAdmissao");
	}

	public Funcionario(Query query) {
		super(query);
		this.profissao = query.get("profissao");
		this.carteiraTrabalho = query.get("carteiraTrabalho");
		this.salario = query.get("salario");
		this.dataAdmissao = query.get("dataAdmissao");
	}
	
	public String toString() {
		return "{" + super.toString() + "\"," + "\"profissao\":" + "\"" + this.profissao + "\"," + "\"carteiraTrabalho\":" + "\"" + this.carteiraTrabalho + "\"," + "\"salario\":" + "\"" + this.salario + "\"," + "\"dataAdmissao\":" + "\"" + this.dataAdmissao + "\"" + "}\n";
	}
	
	public JSONObject toJson() {
		JSONObject obj = super.toJson();
		obj.put("profissao", this.profissao);
		obj.put("carteiraTrabalho", this.carteiraTrabalho);
		obj.put("salario", this.salario);
		obj.put("dataAdmissao", this.dataAdmissao);
		obj.put("tipo", 2);
		return obj;
	}

}
