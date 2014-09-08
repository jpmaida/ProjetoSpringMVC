<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value='/clienteController/inserir' var="urlForm" />
<sf:form modelAttribute="cliente" action="${urlForm}" method="post" id="idForm">
	<div class="box">
		
		<h3>Cadastro de Cliente</h3>
		
		<spring:hasBindErrors name="cliente">
			<div class="ui-widget erro-wrapper">
				<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
					<p>
						<span style="font-weight: bold;">Alerta: </span>
						<sf:errors path="*"></sf:errors>
					</p>
				</div>
			</div>
		</spring:hasBindErrors>
		
		<label>
			<span class="descricao">Nome<span class="obrigatorio">*</span></span>
			<sf:input class="input_text" path="nome" maxlength="255" id="idInputNome" />
		</label>
		<label>
			<span class="descricao">Data de Nascimento</span>
			<sf:input class="input_text datepicker dataMask" path="dataNascimento" maxlength="10" cssStyle="width: 200px;" />
		</label>
		<label>
			<span class="descricao">Email</span>
			<sf:input class="input_text emailMask" path="email" maxlength="100" />
		</label>
		<label>
			<span class="descricao">Telefone</span>
			<sf:input class="input_text" path="telefone" maxlength="10"/>
		</label>
		<label>
			<span class="descricao">Celular</span>
			<sf:input class="input_text" path="celular" maxlength="11"/>
		</label>
		<label>
			<span class="descricao">RG</span>
			<sf:input class="input_text rgMask" path="rg" maxlength="12"/>
			<h6 style="display: inline; color: white;">99.999.999-9</h6>
		</label>
		<label>
			<span class="descricao">CPF</span>
			<sf:input class="input_text cpfMask" path="cpf" maxlength="14"/>
			<h6 style="display: inline; color: white;">999.999.999-99</h6>
		</label>
		<label>
			<span class="descricao">CNPJ</span>
			<sf:input class="input_text cnpjMask" path="cnpj" maxlength="18"/>
			<h6 style="display: inline; color: white;">99.999.999/9999-99</h6>
		</label>
		
		<label>
			<span class="descricao">Logradouro<span class="obrigatorio">*</span></span>
			<sf:input class="input_text" path="endereco.logradouro" maxlength="100" id="idInputNome" />
		</label>
		<label>
			<span class="descricao">Número<span class="obrigatorio">*</span></span>
			<sf:input class="input_text" path="endereco.numero" maxlength="10" id="idInputNumero" />
		</label>
		<label>
			<span class="descricao">Complemento</span>
			<sf:input class="input_text" path="endereco.complemento" maxlength="100"/>
		</label>
		<label>
			<span class="descricao">CEP</span>
			<sf:input class="input_text cepMask" path="endereco.cep" maxlength="10" />
			<h6 style="display: inline; color: white;">99.999-999</h6>
		</label>
		<label>
			<span class="descricao">Bairro<span class="obrigatorio">*</span></span>
			<sf:input class="input_text" path="endereco.bairro" maxlength="100" id="idInputBairro" />
		</label>
		<label>
			<span class="descricao">Cidade</span>
			<sf:input class="input_text" path="endereco.cidade" maxlength="100"/>
		</label>
		<label>
			<span class="descricao">Estado</span>
			<sf:input class="input_text" path="endereco.estado" maxlength="70"/>
		</label>
		
		<div class="botao-wrapper">
			<button class="botao-inserir">Confirmar</button>
			<button class="botao-limpar">Limpar</button>
			<button class="botao-voltar">Voltar</button>
		</div>
	</div>
</sf:form>

<script type="text/javascript">
	function voltar(){
		$('#idForm').prop('action', "<c:url value='/clienteController/listar' />");
		$('#idForm').submit();
	}
</script>