<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value='/carroController/inserir' var="urlForm" />
<sf:form modelAttribute="carro" method="post" action="${urlForm}" id="idForm" >
	<div class="box">
		<sf:hidden path="cliente.nome" />
		<sf:hidden path="cliente.id" />

		<h3>Cadastro de Carros</h3>

		<c:if test="${not empty MSGSUCCESS}">
			<div class="ui-widget sucesso-wrapper">
				<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
					<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
					<strong>Sucesso</strong> ${MSGSUCCESS}</p>
				</div>
			</div>
		</c:if>

		<spring:hasBindErrors name="carro">
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
			<span class="descricao">Cliente : </span><span style="color: white; font-weight: bold;">${carro.cliente.nome}</span>
		</label>
		
		<label>
			<span class="descricao">Modelo : </span>
			<sf:input class="input_text" path="modelo" maxlength="45" />
		</label>
		<label>
			<span class="descricao">Marca : </span>
			<sf:select class="input_text" path="marca.id">
				<sf:option value="">Selecione</sf:option>
				<sf:options items="${listaMarca}" itemLabel="nome" itemValue="id" />
			</sf:select>
			<%--
			<sf:select class="input_text" path="marca">
				<sf:option value="">Selecione</sf:option>
				<sf:option value="Agrale">Agrale</sf:option>
				<sf:option value="Aston Martin">Aston Martin</sf:option>
				<sf:option value="Audi">Audi</sf:option>
				<sf:option value="Bentley">Bentley</sf:option>
				<sf:option value="BMW">BMW</sf:option>
				<sf:option value="Changan">Changan</sf:option>
				<sf:option value="Chery">Chery</sf:option>
				<sf:option value="GM/Chevrolet">GM/Chevrolet</sf:option>
				<sf:option value="Chrysler">Chrysler</sf:option>
				<sf:option value="Citroën">Citroën</sf:option>
				<sf:option value="Dodge">Dodge</sf:option>
				<sf:option value="Effa">Effa</sf:option>
				<sf:option value="Ferrari">Ferrari</sf:option>
				<sf:option value="Fiat">Fiat</sf:option>
				<sf:option value="Ford">Ford</sf:option>
				<sf:option value="Geely">Geely</sf:option>
				<sf:option value="Hafei">Hafei</sf:option>
				<sf:option value="Honda">Honda</sf:option>
				<sf:option value="Hyundai">Hyundai</sf:option>
				<sf:option value="Iveco">Iveco</sf:option>
				<sf:option value="Jac Motors">Jac Motors</sf:option>
				<sf:option value="Jaguar">Jaguar</sf:option>
				<sf:option value="Jeep">Jeep</sf:option>
				<sf:option value="Jinbei">Jinbei</sf:option>
				<sf:option value="Kia">Kia</sf:option>
				<sf:option value="Lamborghini">Lamborghini</sf:option>
				<sf:option value="Land Rover">Land Rover</sf:option>
				<sf:option value="Lifan">Lifan</sf:option>
				<sf:option value="Mahindra">Mahindra</sf:option>
				<sf:option value="Ferrari">Ferrari</sf:option>
				<sf:option value="Maserati">Maserati</sf:option>
				<sf:option value="Mercedes-Benz">Mercedes-Benz</sf:option>
				<sf:option value="MG Motors">MG Motors</sf:option>
				<sf:option value="Mini">Mini</sf:option>
				<sf:option value="Mitsubishi">Mitsubishi</sf:option>
				<sf:option value="Nissan">Nissan</sf:option>
				<sf:option value="Peugeot">Peugeot</sf:option>
				<sf:option value="Porsche">Porsche</sf:option>
				<sf:option value="Ram">Ram</sf:option>
				<sf:option value="Renault">Renault</sf:option>
				<sf:option value="Smart">Smart</sf:option>
				<sf:option value="Ssangyong">Ssangyong</sf:option>
				<sf:option value="Subaru">Subaru</sf:option>
				<sf:option value="Suzuki">Suzuki</sf:option>
				<sf:option value="Toyota">Toyota</sf:option>
				<sf:option value="Troller">Troller</sf:option>
				<sf:option value="Volkswagen">Volkswagen</sf:option>
				<sf:option value="Volvo">Volvo</sf:option>
			</sf:select>
			--%>
			
		</label>
		<label>
			<span class="descricao">Ano : </span>
			<sf:input class="input_text anoMask" path="ano" maxlength="9" />
			<h6 style="display: inline; color: red;">9999/9999</h6>
		</label>
		<label>
			<span class="descricao">Placa<span class="obrigatorio">*</span> : </span>
			<sf:input class="input_text placaMask" path="placa" maxlength="8" />
			<h6 style="display: inline; color: red;">AAA-9999</h6>
		</label>

		<div class="botao-wrapper">
			<button class="botao-inserir">Confirmar</button>
			<button class="botao-voltar">Voltar</button>
		</div>
	</div>
</sf:form>

<script type="text/javascript">
	function voltar(){
		$('#idForm').prop('action', "<c:url value='/clienteController/novo' />");
		$('#idForm').submit();
	}
</script>