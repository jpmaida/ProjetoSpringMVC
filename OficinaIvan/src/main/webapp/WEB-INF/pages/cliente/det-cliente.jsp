<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div>
		<h3>Detalhamento de Cliente</h3>
		
		<c:if test="${not empty MSGSUCCESS}">
			<div class="ui-widget sucesso-wrapper">
				<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
					<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
					<strong>Sucesso</strong> ${MSGSUCCESS}</p>
				</div>
			</div>
		</c:if>

		<div>
			<label>Nome: </label>
			${cliente.nome}
		</div>
		<div>
			<label>Data de Nascimento: </label>
			${cliente.dataNascimentoFormatada}
		</div>
		<div>
			<label>Email: </label>
			${cliente.email}
		</div>
		<div>
			<label>Telefone: </label>
			${cliente.telefone}
		</div>
		<div>
			<label>Celular: </label>
			${cliente.celular}
		</div>
		<div>
			<label>RG: </label>
			${cliente.rg}
		</div>
		<div>
			<label>CPF: </label>
			${cliente.cpf}
		</div>
		<div>
			<label>CNPJ: </label>
			${cliente.cnpj}
		</div>
		
		<h3>Endereço</h3>
		
		<div>
			<label>Logradouro: </label>
			${cliente.endereco.logradouro}
		</div>
		<div>
			<label>Número: </label>
			${cliente.endereco.numero}
		</div>
		<div>
			<label>Complemento: </label>
			${cliente.endereco.complemento}
		</div>
		<div>
			<label>CEP: </label>
			${cliente.endereco.cep}
		</div>
		<div>
			<label>Bairro: </label>
			${cliente.endereco.bairro}
		</div>
		<div>
			<label>Cidade: </label>
			${cliente.endereco.cidade}
		</div>
		<div>
			<label>Estado: </label>
			${cliente.endereco.estado}
		</div>
		
		<div class="botao-wrapper">
			<a class="botao-alterar" href="<c:url value='/clienteController/detalhar?id=${cliente.id}&isAlterar=true'></c:url>">Editar</a>
			<a class="button" href="<c:url value='/carroController/novo?cliente.id=${cliente.id}'></c:url>">Novo Carro</a>
			<a class="botao-voltar" href="<c:url value='/clienteController/listar' />">Voltar</a>
		</div>
		
		<h3>Lista de carros</h3>
		<c:choose>
			<c:when test="${empty cliente.listaCarro}">
				O cliente não possui carros cadastrados.
			</c:when>
			<c:otherwise>
				<c:forEach items="${cliente.listaCarro}" var="carro">
					<form method="post" action="<c:url value='/carroController/alterar'/>">
						
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

						<input type="hidden" name="cliente.id" value="${carro.cliente.id}" />
						<input type="hidden" name="id" value="${carro.id}" />
						<fieldset>
							<legend>${carro.placa}</legend>
							<div>
								<div>
									<label>Modelo : </label>
									<span>${carro.modelo}</span>
									<input type="text" name="modelo" class="inputAlteracao" value="${carro.modelo}" maxlength="45" />
								</div>
								<div>
									<label>Marca : </label>
									<span class="spanMarca">${carro.marca.nome}</span>
									<input type="hidden" class="hiddenMarca" value="${carro.marca.id}" />
									
									<select name="marca.id" class="inputAlteracao">
										<option value="">Selecione</option>
										<c:forEach items="${listaMarca}" var="marca">
											<option value="${marca.id}">${marca.nome}</option>
										</c:forEach>
									</select>
									<!--
									<select name="marca" class="inputAlteracao">
										<option value="">Selecione</option>
										<option value="Agrale">Agrale</option>
										<option value="Aston Martin">Aston Martin</option>
										<option value="Audi">Audi</option>
										<option value="Bentley">Bentley</option>
										<option value="BMW">BMW</option>
										<option value="Changan">Changan</option>
										<option value="Chery">Chery</option>
										<option value="GM/Chevrolet">GM/Chevrolet</option>
										<option value="Chrysler">Chrysler</option>
										<option value="Citroën">Citroën</option>
										<option value="Dodge">Dodge</option>
										<option value="Effa">Effa</option>
										<option value="Ferrari">Ferrari</option>
										<option value="Fiat">Fiat</option>
										<option value="Ford">Ford</option>
										<option value="Geely">Geely</option>
										<option value="Hafei">Hafei</option>
										<option value="Honda">Honda</option>
										<option value="Hyundai">Hyundai</option>
										<option value="Iveco">Iveco</option>
										<option value="Jac Motors">Jac Motors</option>
										<option value="Jaguar">Jaguar</option>
										<option value="Jeep">Jeep</option>
										<option value="Jinbei">Jinbei</option>
										<option value="Kia">Kia</option>
										<option value="Lamborghini">Lamborghini</option>
										<option value="Land Rover">Land Rover</option>
										<option value="Lifan">Lifan</option>
										<option value="Mahindra">Mahindra</option>
										<option value="Ferrari">Ferrari</option>
										<option value="Maserati">Maserati</option>
										<option value="Mercedes-Benz">Mercedes-Benz</option>
										<option value="MG Motors">MG Motors</option>
										<option value="Mini">Mini</option>
										<option value="Mitsubishi">Mitsubishi</option>
										<option value="Nissan">Nissan</option>
										<option value="Peugeot">Peugeot</option>
										<option value="Porsche">Porsche</option>
										<option value="Ram">Ram</option>
										<option value="Renault">Renault</option>
										<option value="Smart">Smart</option>
										<option value="Ssangyong">Ssangyong</option>
										<option value="Subaru">Subaru</option>
										<option value="Suzuki">Suzuki</option>
										<option value="Toyota">Toyota</option>
										<option value="Troller">Troller</option>
										<option value="Volkswagen">Volkswagen</option>
										<option value="Volvo">Volvo</option>
									</select>
									-->
								</div>
								<div>
									<label>Ano : </label>
									<span>${carro.ano}</span>
									<input type="text" name="ano" class="inputAlteracao anoMask" value="${carro.ano}" maxlength="9" />
									<h6 style="display: inline; color: red;">9999/9999</h6>
								</div>
								<div>
									<label>Placa <span class="obrigatorio">*</span> : </label>
									<span>${carro.placa}</span>
									<input type="text" name="placa" class="inputAlteracao placaMask" value="${carro.placa}" maxlength="8" />
									<h6 style="display: inline; color: red;">AAA-9999</h6>
								</div>
							</div>
							<div class="botao-wrapper">
								<a href="javascript:void(0)" onclick="habilitarAlteracao(this)" class="botao-alterar">Alterar</a>
								<button class="buttonGravarAlteracao botao-inserir">Confirmar</button>
								<a class="botao-excluir" href="<c:url value="/carroController/excluir/${carro.id}/${carro.cliente.id}"/>">Excluir</a>
							</div>
						</fieldset>
					</form>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('.inputAlteracao').hide();
		$('.buttonGravarAlteracao').hide();	
	});
	
	function habilitarAlteracao(button){
		var $selectMarca = $(button).parent().prev().children().children('select');
		$(button).next().show();
		$(button).hide();
		$selectMarca.val($(button).parent().prev().children().children('.hiddenMarca').val());
		$(button).parent().prev().children().children('span').hide();
		$(button).parent().prev().children().children('input').show();
		$selectMarca.show();
	}
</script>	