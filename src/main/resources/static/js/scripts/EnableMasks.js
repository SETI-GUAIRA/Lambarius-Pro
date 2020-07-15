var LambariusPro = LambariusPro || {}

LambariusPro.MaskCpfCnpj = (function(){

  class MaskCpfCnpj {
    constructor() {
    }
    enable() {
      var options = {
        onKeyPress: function (cpfcnpj, e, field, options) {
          var masks = ['000.000.000-009', '00.000.000/0000-00']
          var mask = (cpfcnpj.length > 14) ? masks[1] : masks[0]
          $('.js-documento').mask(mask, options)
        }
      }

      $('.js-documento').mask('000.000.000-009', options)

      $('.js-documento').on('blur', validaDocumento.bind(this))
    }
  }


	function validaDocumento(){
		var valor = $('.js-documento').val().length;
		if(valor < 14){
			$('.js-documento').val('');

		}else if(valor > 14 && valor < 18){
			$('.js-documento').val('');
		}
	}

	return MaskCpfCnpj;

}());


$(() => {
  var dateMask = new LambariusPro.DateMask()
  dateMask.enable();

  var maskCpfCnpj = new LambariusPro.MaskCpfCnpj()
  maskCpfCnpj.enable();
});
