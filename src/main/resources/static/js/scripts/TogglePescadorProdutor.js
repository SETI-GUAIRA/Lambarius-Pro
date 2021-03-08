var LambariusPro = LambariusPro || {}

LambariusPro.TogglePescadorProdutor = (function () {

  class TogglePescadorProdutor {
    constructor() {
      this.checkPescador = $(".check-pescador")
      this.checkProdutor = $('.check-produtor')
      this.contentProdutor = $('.content-produtor')
      this.contentPescador = $('.content-pescador')
      this.pescador = $('#pescador') 
      this.produtor = $('#produtor') 
    }
    enable() {
      if (this.pescador.val()) {
        this.checkPescador.attr('checked', true)
        this.checkProdutor.attr('checked', false)
      }      

      if ($('#produtor option:selected').val()) {
        this.checkProdutor.attr('checked', true)
        this.checkPescador.attr('checked', false)
      }

      if (this.checkPescador.prop('checked')) {
        this.contentPescador.css({ display: "block" })
        this.contentProdutor.css({ display: "none" })
      }

      this.checkPescador.on('click', () => {
        this.pescador.val(''),
        this.contentPescador.css({ display: "block" })
        this.contentProdutor.css({ display: "none" })
      })

      this.checkProdutor.on('click', () => {
    	this.produtor.val('')
        this.contentProdutor.css({ display: "block" })
        this.contentPescador.css({ display: "none" })
      })
    }
  }

  return TogglePescadorProdutor
}())

$(() => {
  var TogglePescadorProdutor = new LambariusPro.TogglePescadorProdutor();
  TogglePescadorProdutor.enable();
});
