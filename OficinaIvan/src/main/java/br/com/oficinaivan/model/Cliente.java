package br.com.oficinaivan.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.oficinaivan.util.Util;

/**
 * Classe de modelo que representa um cliente.
 */
@Entity
@Table(name="cliente")
@NamedQueries(value={
		@NamedQuery(name="verificaClienteNomeIgual", query="select count(id) from Cliente where nome = :nome"),
		@NamedQuery(name="verificaClienteNomeIgualAlteracao", query="select count(id) from Cliente where nome = :nome and id != :id")
})
public class Cliente implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2591515719111539931L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="nome", length=255, nullable=false)
    private String nome;
	
	@Column(name="data_nascimento")
    private Date dataNascimento;
	
	@Transient
	private String dataNascimentoFormatada;
	
	@Column(name="email", length=100)
    private String email;
	
	@Column(name="telefone", length=10)
    private String telefone;
	
	@Column(name="celular", length=11)
    private String celular;
	
	@Column(name="rg", length=12)
    private String rg;
	
	@Column(name="cpf", length=14)
    private String cpf;
	
	@Column(name="cnpj", length=18)
    private String cnpj;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="endereco_id", nullable=false)
    private Endereco endereco;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
    private List<Carro> listaCarro;
    
    @Column(name="data_email_enviado")
    private Date dataEmailEnviado;

    public Cliente() { }
    
    public Cliente(Long id) {
        this.id = id;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the listaCarro
     */
    public List<Carro> getListaCarro() {
        return listaCarro;
    }

    /**
     * @param listaCarro the listaCarro to set
     */
    public void setListaCarro(List<Carro> listaCarro) {
        this.listaCarro = listaCarro;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the dataEmailEnviado
     */
    public Calendar getDataEmailEnviado() {
        if(this.dataEmailEnviado != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.dataEmailEnviado);
            return calendar;
        }
        return null;
    }

    /**
     * @param dataEmailEnviado the dataEmailEnviado to set
     */
    public void setDataEmailEnviado(Date dataEmailEnviado) {
        this.dataEmailEnviado = dataEmailEnviado;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime
				* result
				+ ((dataEmailEnviado == null) ? 0 : dataEmailEnviado.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((listaCarro == null) ? 0 : listaCarro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataEmailEnviado == null) {
			if (other.dataEmailEnviado != null)
				return false;
		} else if (!dataEmailEnviado.equals(other.dataEmailEnviado))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaCarro == null) {
			if (other.listaCarro != null)
				return false;
		} else if (!listaCarro.equals(other.listaCarro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", dataNascimento="
				+ dataNascimento + ", email=" + email + ", telefone="
				+ telefone + ", celular=" + celular + ", rg=" + rg + ", cpf="
				+ cpf + ", cnpj=" + cnpj + ", endereco=" + endereco
				+ ", listaCarro=" + listaCarro + ", dataEmailEnviado="
				+ dataEmailEnviado + "]";
	}

	public String getDataNascimentoFormatada() {
		return Util.formatarData(dataNascimento);
	}

	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}
}
