package br.com.oficinaivan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.oficinaivan.util.Util;

/**
 * Classe de modelo que representa um servico.
 */
@Entity
@Table(name="servico")
public class Servico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7509801075943652321L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
    private Long id;
	
	@Column(name="descricao", length=100, nullable=false)
    private String descricao;
	
	@Column(name="data_cadastro_servico", nullable=false)
    private Date dataCadastroServico;
	
	@Column(name="data")
    private Date data;
	
	@Transient
	private String dataFormatada;
	
	@Column(name="observacao", length=500)
    private String observacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente", nullable=false, referencedColumnName="id")
    private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_carro", nullable=false, referencedColumnName="id")
    private Carro carro;
    
    @Column(name="quilometragem", length=9)
    private String quilometragem;
    
    @Column(name="material", length=300)
    private String material;

    public Servico() {
    	this.dataCadastroServico = new Date();
    }
    
    public Servico(Long id) {
    	this.dataCadastroServico = new Date();
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the carro
     */
    public Carro getCarro() {
        return carro;
    }

    /**
     * @param carro the carro to set
     */
    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    /**
     * @return the quilometragem
     */
    public String getQuilometragem() {
        return quilometragem;
    }

    /**
     * @param quilometragem the quilometragem to set
     */
    public void setQuilometragem(String quilometragem) {
        this.quilometragem = quilometragem;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the dataCadastroServico
     */
    public Date getDataCadastroServico() {
        return dataCadastroServico;
    }

    /**
     * @param dataCadastroServico the dataCadastroServico to set
     */
    public void setDataCadastroServico(Date dataCadastroServico) {
        this.dataCadastroServico = dataCadastroServico;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return this.data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }
    
    @Override
	public String toString() {
		return "Servico [id=" + id + ", descricao=" + descricao
				+ ", dataCadastroServico=" + dataCadastroServico + ", data="
				+ data + ", observacao=" + observacao
				+ ", quilometragem=" + quilometragem
				+ ", material=" + material + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carro == null) ? 0 : carro.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime
				* result
				+ ((dataCadastroServico == null) ? 0 : dataCadastroServico
						.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((material == null) ? 0 : material.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((quilometragem == null) ? 0 : quilometragem.hashCode());
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
		Servico other = (Servico) obj;
		if (carro == null) {
			if (other.carro != null)
				return false;
		} else if (!carro.equals(other.carro))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataCadastroServico == null) {
			if (other.dataCadastroServico != null)
				return false;
		} else if (!dataCadastroServico.equals(other.dataCadastroServico))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (quilometragem == null) {
			if (other.quilometragem != null)
				return false;
		} else if (!quilometragem.equals(other.quilometragem))
			return false;
		return true;
	}

	public String getDataFormatada() {
		return Util.formatarData(data);
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
}