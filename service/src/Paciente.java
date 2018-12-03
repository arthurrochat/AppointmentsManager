import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.simpleframework.http.Query;

public class Paciente extends Usuario{
	private String plano;//unimax
	private String tipoPlano;//coletivo por ades�o
	private String codigoPlano;
	private String validade;
	
	public Paciente(String cpf, String nome, String sexo, String dataNascimento, String telefone, String email, String plano, String tipoPlano, String codigoPlano, String validade, String usuario){
		super(cpf, nome, sexo, dataNascimento, telefone, email, usuario);
		this.plano = plano;
		this.tipoPlano = tipoPlano;
		this.codigoPlano = codigoPlano;
		this.validade = validade;
	}
	
	public Paciente(JSONObject json) {
		super(json);
		this.plano = (String)json.get("plano");
		this.tipoPlano = (String)json.get("tipoPlano");
		this.codigoPlano = (String)json.get("codigoPlano");
		this.validade = (String)json.get("validade");
	}
	
	public Paciente(Query query) {
		super(query);
		this.plano = query.get("plano");
		this.tipoPlano = query.get("tipoPlano");
		this.codigoPlano = query.get("codigoPlano");
		this.validade = query.get("validade");
	}
	
	public String toString() {
		return "{"+super.toString()+ "\","  + "\"plano\":" + "\"" + this.plano + "\"," + "\"tipoPlano\":" + "\"" + this.tipoPlano + "\"," + "\"codigoPlano\":"  + "\"" + this.codigoPlano + "\"," + "\"validade\":" + "\"," + "\"" + this.validade + "\"" + "}\n";
	}
	
	public JSONObject toJson() {
		JSONObject obj = super.toJson();
		obj.put("plano", this.plano);
		obj.put("tipoPlano", this.tipoPlano);
		obj.put("codigoPlano", this.codigoPlano);
		obj.put("validade", this.validade);
		obj.put("tipo", 1);
		return obj;
	}

}
