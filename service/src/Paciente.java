import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.simpleframework.http.Query;

public class Paciente extends Usuario{
	private static final int MAX_EXAMES = 100;
	private String plano;//unimax
	private String tipoPlano;//coletivo por adesão
	private String codigoPlano;
	private LocalDate validade;
	private String[] listaExames;
	private int quantidadeExames;
	
	public Paciente(String cpf, String nome, boolean sexo, LocalDate dataNascimento, String telefone, String email, String plano, String tipoPlano, String codigoPlano, LocalDate validade){
		super(cpf, nome, sexo, dataNascimento, telefone, email);
		this.plano = plano;
		this.tipoPlano = tipoPlano;
		this.codigoPlano = codigoPlano;
		this.validade = validade;
		this.listaExames = new String[MAX_EXAMES];
		this.quantidadeExames = 0;
	}
	
	public Paciente(JSONObject json) {
		super(json);
		this.plano = (String)json.get("plano");
		this.tipoPlano = (String)json.get("tipoPlano");
		this.codigoPlano = (String)json.get("codigoPlano");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		this.validade = LocalDate.parse((String)json.get("validade"),formatter);
		this.listaExames = new String[MAX_EXAMES];
		this.quantidadeExames = 0;
	}
	
	public Paciente(Query query) {
		super(query);
		this.plano = query.get("plano");
		this.tipoPlano = query.get("tipoPlano");
		this.codigoPlano = query.get("codigoPlano");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		this.validade = LocalDate.parse(query.get("validade"),formatter);
		this.listaExames = new String[MAX_EXAMES];
		this.quantidadeExames = 0;
	}
	
	public void atualizaDados(String cpf, String nome, boolean sexo, LocalDate dataNascimento, String telefone, String email, String plano, String tipoPlano, String codigoPlano, LocalDate validade){
		super.atualizaDados(cpf, nome, sexo, dataNascimento, telefone, email);
		this.plano = plano;
		this.tipoPlano = tipoPlano;
		this.codigoPlano = codigoPlano;
		this.validade = validade;
	}
	
	public String toString() {
		return super.toString()+"\nPlano de Saude: " + this.plano + "\nTipo de Plano: " + this.tipoPlano + "\nCodigo do Plano:" + this.codigoPlano + "\nValidade:" + this.validade;
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
	
	public int getExames(String[] listaExames) {
		listaExames = new String[MAX_EXAMES];
		
		for(int c = 0; c < this.quantidadeExames; c++) {
			listaExames[c] = this.listaExames[c];
		}
		return this.quantidadeExames;
	}
}
