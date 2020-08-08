LambariusPro = LambariusPro || {};

LambariusPro.DialogoExcluir = (function () {

  function DialogoExcluir() {
    this.exclusaoBtn = $('.js-exclusao-btn');
  }

  DialogoExcluir.prototype.iniciar = function () {
    this.exclusaoBtn.on('click', onExcluirClicado.bind(this));

    if (window.location.search.indexOf('excluido') > -1) {
      swal('Pronto!', 'Excluído com sucesso!', 'success');
    }
  }

  function onExcluirClicado(event) {
    event.preventDefault();
    var botaoClicado = $(event.currentTarget);
    var url = botaoClicado.data('url');
    var objeto = botaoClicado.data('objeto');


    swal({
      title: 'Você tem certeza?',
      text: 'Excluir "' + objeto + '"? Você não poderá recuperar depois.',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
      buttons: {
        cancel: {
          text: "Sair",
          value: false,
          visible: true
        },
        confirm: {
          text: "Sim, excluir!",
          value: true,
          visible: true
        },
      }
    }).then((value) => {
      if (value) {
        onExcluirConfirmado(url, value);
      }
    });
  }

  function onExcluirConfirmado(url, value) {
    $.ajax({
      url: url,
      method: 'DELETE',
      success: onExcluidoSucesso.bind(this),
      error: onErroExcluir.bind(this)
    });
  }

  function onExcluidoSucesso() {
    var urlAtual = window.location.href;
    var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
    var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';

    window.location = novaUrl;
  }

  function onErroExcluir(e) {
    swal('Oops!', e.responseText, 'error');
  }

  return DialogoExcluir;

}());

$(function () {
  var dialogo = new LambariusPro.DialogoExcluir();
  dialogo.iniciar();
});
