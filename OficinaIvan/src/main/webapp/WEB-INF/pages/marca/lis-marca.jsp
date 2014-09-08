<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form id="idForm" method="post" modelAttribute="marca">
	
	<sf:hidden path="id" id="idHiddenIdMarca" />

	<h3>Lista de Marcas</h3>
	
	<c:choose>
		<c:when test="${empty listaMarca}">
			Não existem marcas cadastrados.
		</c:when>
		<c:otherwise>
			
			<c:if test="${not empty MSGSUCCESS}">
				<div class="ui-widget sucesso-wrapper">
					<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
						<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
						<strong>Sucesso</strong> ${MSGSUCCESS}</p>
					</div>
				</div>
			</c:if>
			
			<table class="tabela">
				<thead>
					<tr>
						<th style="width: 90%;">Nome</th>
						<th style="width: 10%;">Ações</th>
					</tr>
				</thead>
				<c:forEach items="${listaMarca}" var="marca" varStatus="contador">
					<c:choose>
						<c:when test="${contador.count % 2 == 0}">
							<tr class="even">
								<td>${marca.nome}</td>
								<td style="text-align: center;">
									<img src="<c:url value='/recursos/img/edit.png' />" title="Editar" alt="Editar" onclick="detalhar(${marca.id})" /> 
									&nbsp;
									<img src="<c:url value='/recursos/img/cancel.png' />" title="Excluir" alt="Excluir" onclick="excluir(${marca.id})" />
								</td>
							</tr> 
						</c:when>
						<c:otherwise>
							<tr class="odd">
								<td>${marca.nome}</td>
								<td style="text-align: center;">
									<img src="<c:url value='/recursos/img/edit.png' />" title="Editar" alt="Editar" onclick="detalhar(${marca.id})" /> 
									&nbsp;
									<img src="<c:url value='/recursos/img/cancel.png' />" title="Excluir" alt="Excluir" onclick="excluir(${marca.id})" />
								</td>
							</tr> 
						</c:otherwise>
					</c:choose>				
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</sf:form>

<div class="botao-wrapper">
	<button class="botao-novo">Novo</button>
</div>

<script type="text/javascript">
	function novo(){
		$('#idForm').prop('action', "<c:url value='/marcaController/novo' />");
		$('#idForm').submit();
	}

	function voltar(){
		$('#idForm').prop('action', "<c:url value='/' />");
		$('#idForm').submit();
	}

	function detalhar(idMarca){
		var $form = $('#idForm');
		$form.prop('action', "<c:url value='/marcaController/detalhar' />");
		$('#idHiddenIdMarca').val(idMarca);
		$form.submit();
	}

	function excluir(idMarca){
		var $form = $('#idForm');
		$form.prop('action', "<c:url value='/marcaController/excluir' />");
		$('#idHiddenIdMarca').val(idMarca);
		$form.submit();
	}
</script>