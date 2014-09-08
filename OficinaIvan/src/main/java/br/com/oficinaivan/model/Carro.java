package br.com.oficinaivan.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe de modelo que representa um carro.
 */
@Entity
@Table(name="carro")
@NamedQueries(value={
		@NamedQuery(name="verificaPlacaDuplicada", query="select count(id) from Carro where placa = :placa"),
		@NamedQuery(name="verificaPlacaDuplicadaAlteracao", query="select count(id) from Carro where placa = :placa and id != :id")
})
public class Carro implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 6019042579841560051L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="modelo", length=45)
    private String modelo;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_marca", nullable=false)
	private Marca marca;
	
	@Column(name="ano", length=9)
    private String ano;
	
	@Column(name="placa", length=8, unique=true, nullable=false)
    private String placa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente", nullable=false, referencedColumnName="id")
    private Cliente cliente;
	
	@OneToMany(mappedBy="carro")
	private List<Servico> listaServico;

	public Carro() { }
	
	public Carro(Long id) {
		this.id = id;
	}
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((listaServico == null) ? 0 : listaServico.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
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
		Carro other = (Carro) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaServico == null) {
			if (other.listaServico != null)
				return false;
		} else if (!listaServico.equals(other.listaServico))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Servico> getListaServico() {
		return listaServico;
	}

	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", modelo=" + modelo + ", ano=" + ano
				+ ", placa=" + placa + "]";
	}
}
