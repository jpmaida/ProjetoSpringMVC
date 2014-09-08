<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div>
		<h3>Detalhamento da Marca</h3>
		
		<img src="<c:url value='/marcaController/carregarImagem?id=${marca.id}' />" title="Logotipo da marca" alt="Logotipo da marca" />

		<div>
			<div>
				<label>Nome: </label>
				${marca.nome}
			</div>
			<div>
				<label>Descrição: </label>
				${marca.descricao}
			</div>
		</div>
		
		<div class="botao-wrapper">
			<a class="botao-alterar" href="<c:url value='/marcaController/detalhar?id=${marca.id}&isAlterar=true'></c:url>">Editar</a>
			<a class="botao-voltar" href="<c:url value='/marcaController/listar' />">Voltar</a>
		</div>
	</div>