<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul id="menu">
	<li><a href="<c:url value='/'></c:url>">In�cio</a></li>
	<li><a href="<c:url value='/clienteController/listar'></c:url>">Cliente</a></li>
	<li><a href="<c:url value='/servicoController/listar'></c:url>">Servi�o</a></li>
	<li><a href="<c:url value='/historicoController/novo'></c:url>">Hist�rico</a></li>
	<li><a href="<c:url value='/marcaController/listar'></c:url>">Marcas</a></li>
</ul>
