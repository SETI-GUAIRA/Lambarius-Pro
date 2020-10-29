var LambariusPro = LambariusPro || {}

LambariusPro.ConfirmDelete = (function () {
  class ConfirmDelete {
    constructor() {
      this.forms = $('.ToDelete')
    }
    enable() {
      for (let i = 0; i < this.forms.length; i++) {
        this.forms[i].addEventListener("submit", function (event) {
          const confirmar = confirm("Deseja excluir este registro?")
          if (!confirmar) {
            event.preventDefault()
          }
        })
      }
    }
  }

  return ConfirmDelete
}())

$(() => {
  var confirmDelete = new LambariusPro.ConfirmDelete();
  confirmDelete.enable();
});

