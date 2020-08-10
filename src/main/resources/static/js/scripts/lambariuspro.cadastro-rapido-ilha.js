LambariusPro = LambariusPro || {};

LambariusPro.IlhaCadastroRapido = (function() {
	
	function IlhaCadastroRapido() {
		this.modal = $('#modal-lg');
		this.botaoSalvar = this.modal.find('.js-modal-ilha-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeIlha = $('#nomeIlha');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-ilha');
	}
	
	IlhaCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeIlha.focus();
	}
	
	function onModalClose() {
		this.inputNomeIlha.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('is-invalid');
	}
	
	function onBotaoSalvarClick() {
		var nomeIlha = this.inputNomeIlha.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeIlha }),
			error: onErroSalvandoIlha.bind(this),
			success: onIlhaSalvo.bind(this)
		});
	}
	
	function onErroSalvandoIlha(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('is-invalid');
	}
	
	function onIlhaSalvo(ilha) {
		var comboIlha = $('#ilha');
		comboIlha.append('<option value=' + ilha.codigo + '>' + ilha.nome + '</option>');
		comboIlha.val(ilha.codigo);
		this.modal.modal('hide');
	}
	
	return IlhaCadastroRapido;
	
}());

$(function() {
	var ilhaCadastroRapido = new LambariusPro.IlhaCadastroRapido();
	ilhaCadastroRapido.iniciar();
});