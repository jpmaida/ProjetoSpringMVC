<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
<h3>Listagem de Serviços</h3>

<c:choose>
	<c:when test="${empty listaServico}">
		Não existem serviços cadastrados.	
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
			<tbody>
				<c:forEach items="${listaServico}" var="servico" varStatus="contador">
					<c:choose>
						<c:when test="${contador.count % 2 == 0}">
							<tr class="even">
						</c:when>
						<c:otherwise>
							<tr class="odd">
						</c:otherwise>
					</c:choose>
					
						<td>${servico.descricao}</td>
						<td style="text-align: center;">
							<a href="<c:url value='/servicoController/detalhar/${servico.id}/false' /> "><img src="<c:url value='/recursos/img/edit.png' />" title="Editar" alt="Editar" /></a>  
							&nbsp;
							<a href="<c:url value='/servicoController/excluir/${servico.id}' /> "><img src="<c:url value='/recursos/img/cancel.png' />" title="Excluir" alt="Excluir" /></a>
						</td>
					</tr> 
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
<div class="botao-wrapper">
	<form method="post" id="idForm">
		<button class="botao-novo">Novo</button>
	</form>
</div>

<script type="text/javascript">
	function novo(){
		$('#idForm').prop('action', "<c:url value='/servicoController/novo' />");
		$('#idForm').submit();
	}
</script>