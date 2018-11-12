import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.simpleframework.http.Query;

public class Funcionario extends Usuario {
	private String profissao;
	private String carteiraTrabalho;
	private float salario;
	private LocalDate dataAdmissao;
	
	public Funcionario(String cpf, String nome, boolean sexo, LocalDate dataNascimento, String telefone, String email, String profissao, String carteiraTrabalho, float salario, LocalDate dataAdmissao) {
		super(cpf, nome, sexo, dataNascimento, telefone, email);
		this.profissao = profissao;
		this.carteiraTrabalho = carteiraTrabalho;
		this.salario = salario;
		this.dataAdmissao = dataAdmissao;
	}
	
	public Funcionario(JSONObject json) {
		super(json);
		this.profissao = (String)json.get("profissao");
		this.carteiraTrabalho = (String)json.get("carteiraTrabalho");
		this.salario = (float)json.getDouble("salario");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		this.dataAdmissao  = LocalDate.parse((String)json.get("dataAdmissao"),formatter);
	}

	public Funcionario(Query query) {
		super(query);
		this.profissao = query.get("profissao");
		this.carteiraTrabalho = query.get("carteiraTrabalho");
		this.salario = query.getFloat("salario");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		this.dataAdmissao = LocalDate.parse(query.get("dataAdmissao"),formatter);
	}
	
	public void atualizadados(String cpf, String nome, boolean sexo, LocalDate dataNascimento, String telefone, String email, String profissao, String carteiraTrabalho, float salario, LocalDate dataAdmissao) {
		super.atualizaDados(cpf, nome, sexo, dataNascimento, telefone, email);
		this.profissao = profissao;
		this.carteiraTrabalho = carteiraTrabalho;
		this.salario = salario;
		this.dataAdmissao = dataAdmissao;
	}
	
	public String toString() {
		return super.toString()+"\nProfissao: " + this.profissao + "\nCarteiraTrabalho: " + this.carteiraTrabalho + "\nSalario:" + this.salario + "\nDataAdmissao:" + this.dataAdmissao;
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
