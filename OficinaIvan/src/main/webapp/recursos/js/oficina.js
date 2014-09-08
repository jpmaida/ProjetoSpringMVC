/**
 * 
 */

$(document).ready(
	function(){
		$('.button').button();
		$('.botao-novo').button({
			icons: {
				primary: "ui-icon-circle-plus"
			}
		}).click(
			function(){
				novo();
			}
		);
		$('.botao-voltar').button({
			icons: {
				primary: "ui-icon-circle-arrow-w"
			}
		}).click(
			function(){
				voltar();
			}
		);
		$('.botao-inserir').button({
			icons: {
				primary: "ui-icon-disk"
			}
		});
		$('.botao-alterar').button({
			icons: {
				primary: "ui-icon-pencil"
			}
		});
		$('.botao-limpar').button({
			icons: {
				primary: "ui-icon-document"
			}
		});
		$('.botao-excluir').button({
			icons: {
				primary: "ui-icon-trash"
			}
		});
		
		$('.datepicker').datepicker({
			changeMonth: true,
			changeYear: true
		});
		$('.datepicker').datepicker( $.datepicker.regional[ "pt-BR" ] );
		
		$('.rgMask').bind('mouseover blur', function () {
				if(!/^[0-9]{2}\.[0-9]{3}\.[0-9]{3}\-[0-9]{1}$/.test($(this).val())){
					$(this).val('');
				}
			}
		);
		
		$('.cpfMask').bind('mouseover blur', function () {
				if(!/^[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}$/.test($(this).val())){
					$(this).val('');
				}
			}
		);
		
		$('.cnpjMask').bind('mouseover blur', function () {
				if(!/^[0-9]{2}\.[0-9]{3}\.[0-9]{3}\/[0-9]{4}\-[0-9]{2}$/.test($(this).val())){
					$(this).val('');
				}
			}
		);
		
		$('.dataMask').bind('mouseover blur', function () {
				if(!/^([0-2][0-9]|3[0-1])\/(0[0-9]|1[0-2])\/[0-9]{4}$/.test($(this).val())){
					$(this).val('');
				}
			}
		);
		
		$('.cepMask').bind('mouseover blur', function () {
				if(!/^[0-9]{2}\.[0-9]{3}\-[0-9]{3}$/.test($(this).val())){
					$(this).val('');
				}
			}
		);
		
		$('.anoMask').bind('mouseover blur', function () {
				if(!/^[0-9]{4}\/[0-9]{4}$/.test($(this).val())){
					$(this).val('');
				}
			}
		);
		
		$('.placaMask').bind('mouseover blur', function () {
				if(!/^[A-Z]{3}-[0-9]{4}$/.test($(this).val())){
					$(this).val('');
				}
			}
		);
		
		$('.placaMask').bind('keydown', function () {
			$(this).val($(this).val().toUpperCase());
		}
	);
	}
);