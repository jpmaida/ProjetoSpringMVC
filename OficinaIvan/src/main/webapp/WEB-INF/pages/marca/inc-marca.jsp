<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sf:form modelAttribute="marca" action="persistir" method="post" id="idForm" enctype="multipart/form-data">
	<div class="box">
		
		<input type="hidden" name="isAlterar" value="false" />

		<h3>Cadastro de Marca</h3>
		
		<spring:hasBindErrors name="marca">
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
			<sf:input class="input_text" path="nome" maxlength="100" />
		</label>
		<label>
			<span class="descricao">Descrição</span>
			<sf:input class="input_text" path="descricao" maxlength="100" />
		</label>
		<label>
			<span class="descricao">Logotipo<span class="obrigatorio">*</span></span>
			<input type="file" class="input_text" name="logotipo" />
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
		$('#idForm').prop('action', "<c:url value='/marcaController/listar' />");
		$('#idForm').submit();
	}
</script>