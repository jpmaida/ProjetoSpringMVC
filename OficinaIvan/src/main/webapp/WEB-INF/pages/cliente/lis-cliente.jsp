<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form id="idForm" method="post">
	
	<h3>Lista de Clientes</h3>
	
	<c:choose>
		<c:when test="${empty listaCliente}">
			Não existem clientes cadastrados.
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
						<th>Nome</th>
						<th>Ações</th>
					</tr>
				</thead>
				<c:forEach items="${listaCliente}" var="cliente" varStatus="contador">
					<c:choose>
						<c:when test="${contador.count % 2 == 0}">
							<tr class="even">
								<td>${cliente.nome}</td>
								<td style="text-align: center;">
									<a href="<c:url value='/clienteController/detalhar?id=${cliente.id}&isAlterar=false'></c:url> "><img src="<c:url value='/recursos/img/edit.png' />" title="Editar" alt="Editar" /></a> 
									&nbsp;
									<a href="<c:url value='/clienteController/excluir?id=${cliente.id}'></c:url> "><img src="<c:url value='/recursos/img/cancel.png' />" title="Excluir" alt="Excluir" /></a>
								</td>
							</tr> 
						</c:when>
						<c:otherwise>
							<tr class="odd">
								<td>${cliente.nome}</td>
								<td style="text-align: center;">
									<a href="<c:url value='/clienteController/detalhar?id=${cliente.id}&isAlterar=false'></c:url> "><img src="<c:url value='/recursos/img/edit.png' />" title="Editar" alt="Editar" /></a>  
									&nbsp;
									<a href="<c:url value='/clienteController/excluir?id=${cliente.id}'></c:url> "><img src="<c:url value='/recursos/img/cancel.png' />" title="Excluir" alt="Excluir" /></a>
								</td>
							</tr> 
						</c:otherwise>
					</c:choose>				
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</form>

<div class="botao-wrapper">
	<button class="botao-novo">Novo</button>
</div>

<script type="text/javascript">
	function novo(){
		$('#idForm').prop('action', "<c:url value='/clienteController/novo' />");
		$('#idForm').submit();
	}

	function voltar(){
		$('#idForm').prop('action', "<c:url value='/' />");
		$('#idForm').submit();
	}
</script>