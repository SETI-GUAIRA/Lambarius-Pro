var LambariusPro = LambariusPro || {}

LambariusPro.ToggleEndereco_Ilha = (function () {

  class ToggleEndereco_Ilha {
    constructor() {
      this.checkSim = $(".check-sim")
      this.checkNao = $('.check-nao')
      this.contentIlha = $('.content-ilha')
      this.contentEndereco = $('.content-endereco')
      this.logradouro = $('#logradouro')
      this.bairro = $('#bairro')
      this.numero = $('#numero')
      this.complemento = $('#complemento')
    }
    enable() {
      if (this.logradouro.val()
        && this.numero.val()
        && this.bairro.val()
        && this.complemento.val()) {
        this.checkSim.attr('checked', false)
        this.checkNao.attr('checked', true)
      }

      if ($('#ilha option:selected').val()) {
        this.checkNao.attr('checked', false)
        this.checkSim.attr('checked', true)
      }

      if (this.checkSim.prop('checked')) {
        this.contentEndereco.css({ display: "none" })
        this.contentIlha.css({ display: "block" })
      }

      this.checkSim.on('click', () => {
        this.logradouro.val('')
        this.numero.val('')
        this.bairro.val('')
        this.complemento.val('')
        this.contentEndereco.css({ display: "none" })
        this.contentIlha.css({ display: "block" })
      })

      this.checkNao.on('click', () => {
        $('#ilha').val('')
        this.contentIlha.css({ display: "none" })
        this.contentEndereco.css({ display: "block" })
      })
    }
  }

  return ToggleEndereco_Ilha
}())

$(() => {
  var toggleEndereco_Ilha = new LambariusPro.ToggleEndereco_Ilha();
  toggleEndereco_Ilha.enable();
});
