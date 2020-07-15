var LambariusPro = LambariusPro || {}

LambariusPro.DateMask = (function () {
  class DateMask {
    constructor() {
      this.input = $('.datemask').inputmask({ 'placeholder': 'dd/mm/yyyy' })
      this.data = $('[data-mask]').inputmask()
    }
    enable() {
      this.input
      this.data
    }
  }

  return DateMask
}())

LambariusPro.Cpf_CnpjMask = (function () {
  class Cpf_CnpjMask {
    constructor() {}
    enable() {
      var options = {
        onKeyPress: function (cpfcnpj, e, field, options) {
          var masks = ['999.999.999-999', '00.000.000/0000-00'];
          var mask = (cpfcnpj.length > 14) ? masks[1] : masks[0];
          $('.js-documento').inputmask(mask, options);
        }
      };

      $('.js-documento').inputmask('999.999.999-999', options);

      $('.js-documento').on('change', validaDocumento.bind(this));
    }
  }

  function validaDocumento() {
    var valor = $('.js-documento').val().length;
    if (valor < 14) {
      $('.js-documento').val('');

    } else if (valor > 14 && valor < 18) {
      $('.js-documento').val('');
    }
  }

  return Cpf_CnpjMask
}())


$(() => {
  var dateMask = new LambariusPro.DateMask()
  dateMask.enable()

  var cpf_cnpjMask = new LambariusPro.Cpf_CnpjMask()
  cpf_cnpjMask.enable()
})
