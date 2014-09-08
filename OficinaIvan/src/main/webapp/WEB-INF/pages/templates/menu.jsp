<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul id="menu">
	<li><a href="<c:url value='/'></c:url>">Início</a></li>
	<li><a href="<c:url value='/clienteController/listar'></c:url>">Cliente</a></li>
	<li><a href="<c:url value='/servicoController/listar'></c:url>">Serviço</a></li>
	<li><a href="<c:url value='/historicoController/novo'></c:url>">Histórico</a></li>
	<li><a href="<c:url value='/marcaController/listar'></c:url>">Marcas</a></li>
</ul>
