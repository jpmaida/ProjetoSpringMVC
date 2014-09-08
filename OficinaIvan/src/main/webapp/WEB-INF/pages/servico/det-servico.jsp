<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<h3>Serviço</h3>
	<div>
		<fieldset>
			<legend>Carro</legend>
			<div>
				<label>Placa: </label>${servico.carro.placa}
			</div>
			<div>
				<label>Marca: </label>${servico.carro.marca.nome}
			</div>
			<div>
				<label>Modelo: </label>${servico.carro.modelo}
			</div>
			<div>
				<label>Ano: </label>${servico.carro.ano}
			</div>
		</fieldset>
	</div>
	<div>
		<fieldset>
			<legend>Cliente</legend>
			<div>
				<label>Nome: </label>${servico.cliente.nome}
			</div>
			<div>
				<label>CPF: </label>${servico.cliente.cpf}
			</div>
			<div>
				<label>RG: </label>${servico.cliente.rg}
			</div>
			<div>
				<label>CNPJ: </label>${servico.cliente.cnpj}
			</div>
			<div>
				<label>E-mail: </label>${servico.cliente.email}
			</div>
		</fieldset>
	</div>
	<div>
		<label>Descriço: </label>${servico.descricao}
	</div>
	<div>
		<label>Data: </label>${servico.data}
	</div>
	<div>
		<label>Observação: </label>${servico.observacao}
	</div>
	<div>
		<label>Quilometragem: </label>${servico.quilometragem}
	</div>
	<div>
		<label>Material: </label>${servico.material}
	</div>

	<div class="botao-wrapper">
		<a class="botao-alterar" href="<c:url value='/servicoController/detalhar/${servico.id}/true' />">Editar</a>
		<a class="botao-voltar" href="<c:url value='/servicoController/listar' />">Voltar</a>
	</div>