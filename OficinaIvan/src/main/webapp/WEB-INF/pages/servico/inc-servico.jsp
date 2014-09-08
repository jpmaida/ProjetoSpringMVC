<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:url value='/servicoController/inserir' var="urlForm" />
<sf:form action="${urlForm}" method="post" modelAttribute="servico" id="idFormServico">
	<div class="box">
		<h3>Cadastro de Serviço</h3>

		<spring:hasBindErrors name="servico">
			<div class="ui-widget erro-wrapper">
				<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
					<p>
						<span style="font-weight: bold;">Alerta: </span>
						<sf:errors path="*"></sf:errors>
					</p>
				</div>
			</div>
		</spring:hasBindErrors>
		
		<sf:hidden path="cliente.id" />
		<sf:hidden path="cliente.nome" />
		
		<label>
			<span class="descricao">Carro<span class="obrigatorio">*</span> : </span>
			<sf:select class="input_text" path="carro.id" onchange="carregarCliente()">
				<sf:option value="">Selecione</sf:option>
				<sf:options items="${listaCarro}" itemLabel="placa" itemValue="id"/>
			</sf:select>
		</label>
		<label>
			<span class="descricao">Cliente<span class="obrigatorio">*</span> : </span>
			&nbsp
			<span style="color: white;">${servico.cliente.nome}</span>
		</label>
		<label>
			<span class="descricao">Descrição<span class="obrigatorio">*</span> : </span>
			<sf:input class="input_text" path="descricao" maxlength="100" />
		</label>
		<label>
			<span class="descricao">Data: </span>
			<sf:input class="input_text datepicker dataMask" path="data" maxlength="10"/>
		</label>
		<label>
			<span class="descricao">Observação: </span>
			<sf:textarea class="input_text" rows="5" cols="10" path="observacao"/>
		</label>
		<label>
			<span class="descricao">Quilometragem: </span>
			<sf:input class="input_text" path="quilometragem" maxlength="9" />
		</label>
		<label>
			<span class="descricao">Material: </span>
			<sf:input class="input_text" path="material" maxlength="300"/>
		</label>

		<div class="botao-wrapper">
			<button class="botao-inserir">Confirmar</button>
			<button class="botao-limpar">Limpar</button>
			<button class="botao-voltar">Voltar</button>
		</div>
	</div>
</sf:form>

<script type="text/javascript">
	function carregarCliente(){
		$('#idFormServico').prop('action', 'novo');
		$('#idFormServico').submit();
	}

	function voltar(){
		$('#idFormServico').prop('action', "<c:url value='/servicoController/listar' />");
		$('#idFormServico').submit();
	}
</script>
