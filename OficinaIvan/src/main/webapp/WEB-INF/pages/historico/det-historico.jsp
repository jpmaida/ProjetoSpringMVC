<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<h3>Histórico de Serviços</h3>

	<sf:form method="post" action="novo" modelAttribute="carro" id="idForm">
		<input type="hidden" id="idHiddenListaAutoComplete" value="${listaCarro}" />
		<sf:hidden path="id" id="idHiddenIdCarro" />

		<div>
			<label>Placa: </label>
			<sf:input path="placa" id="idInputPlaca" class="placaMask" maxlength="8" />
		</div>		
	</sf:form>
	<div>
		<h5>Histórico:</h5>
		<div>
			<c:forEach items="${listaServico}" var="servico">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					Descrição: ${servico.descricao} <br/>
					Data: ${servico.dataFormatada} <br/>
					Observação: ${servico.observacao} <br/>
					Quilometragem: ${servico.quilometragem} <br/>
					Material: ${servico.material}
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="botao-wrapper">
		<a class="botao-voltar" href="<c:url value='/' />">Voltar</a>
		<button id="idButtonGerarRelatorio">Gerar Relatório</button>
	</div>

	<div id="dialog-message" title="Relatório" hidden></div>

	<div id="dialog-validation" title="Alerta" hidden>
		<p>
			<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
			Selecione uma placa.
		</p>
	</div>

<script type="text/javascript">
	function voltar(){}

	$(document).ready(
		function(){
			var $json = $.parseJSON($('#idHiddenListaAutoComplete').val().replace(/'/g, "\""));
			var $inputIdCarro = $('#idHiddenIdCarro');

			$('#idInputPlaca').autocomplete({
				source: function (request, response) {					
					response($.map($json, function(item, index){
						return item.placa.indexOf(request.term) == -1 ? null : { label: item.placa, value: item.placa, id: item.id };  
					}));
				},
				select: function( event, ui ) {
					$inputIdCarro.val(ui.item.id);
				},
				close: function( event, ui ) {
					$('#idForm').submit();
				}
			});

			$('#idButtonGerarRelatorio').button({
				icons: {
					primary: "ui-icon-print"
				}
			}).click(
				function(){
					var placa = $('#idInputPlaca').val();
					if(placa == ''){
						$( "#dialog-validation" ).dialog('open');
					} else {
						var $dialog = $( "#dialog-message" );
						var $iframe = $('<iframe>');
						$dialog.html('');
						$iframe.prop('src', "<c:url value='/historicoController/gerarRelatorioPDF' />"+"?placa="+placa+"&id="+$('#idHiddenIdCarro').val());
						$iframe.css('width', '100%');
						$iframe.css('height', '100%');
						$dialog.append($iframe);
						$dialog.dialog('open');
					}
				}
			);

			$( "#dialog-message" ).dialog({
				modal: true,
				autoOpen: false,
				width: 500,
				buttons: {
					Voltar: function() {
						$( this ).dialog( "close" );
					}
				}
			});

			$( "#dialog-validation" ).dialog({
				modal: true,
				autoOpen: false,
				buttons: {
					Voltar: function() {
						$( this ).dialog( "close" );
					}
				}
			});
		}
	);
</script>
