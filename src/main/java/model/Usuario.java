package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Column;

@Entity
@Table(name = "tb_usuarios")
@Data
public class Usuario {
	@Id
	@Column(name = "cod_usua")
	private int codigo;  //cod_usua
	
	@Column(name = "nom_usua")
	private String nombre;//nom_usua varchar 15
	
	@Column(name = "ape_usua")
	private String apellido;//ape_usua varchar 25
	
	@Column(name = "usr_usua")
	private String usuario;
	
	@Column(name = "cla_usua")
	private String clave;
	
	@Column(name = "fna_usua")
	private String fechnac;
	
	@Column(name = "idtipo")
	private int tipo;
	
	@Column(name = "est_usua")
	private int estado;
	
	public Usuario(int codigo, String nombre, String apellido, String usuario, String clave,String fechnac, int tipo, int estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.clave = clave;
		this.fechnac = fechnac;
		this.tipo = tipo;
		this.estado = estado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFechnac() {
		return fechnac;
	}

	public void setFechnac(String fechnac) {
		this.fechnac = fechnac;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Usuario() {
		super();
	}
	
	@ManyToOne
	@JoinColumn(name ="idtipo", insertable = false, updatable = false)
	private Tipo objTipo;//solo servira para consulta
	
}
