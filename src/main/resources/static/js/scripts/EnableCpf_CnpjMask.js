var LambariusPro = LambariusPro || {}

LambariusPro.Cpf_CnpjMask = (function () {
  class Cpf_CnpjMask {
    constructor() {
      this.value = $('.cpf_cnpj')
    }
    enable() {
      this.value.on('change', ApplyMask.bind(this))
    }
  }

  function ApplyMask(value) {
    value = value.replace(/\D/g, "")

    if (value.length > 14) {
      value = value.slice(0, -1)
    }

    // check if cnpj
    if (value.length > 11) {
      value = value.replace(/(\d{2})(\d)/, "$1.$2")
      value = value.replace(/(\d{3})(\d)/, "$1.$2")
      value = value.replace(/(\d{3})(\d)/, "$1/$2")
      value = value.replace(/(\d{4})(\d)/, "$1-$2")
    } else {
      //cpf 111.222.333-44
      value = value.replace(/(\d{3})(\d)/, "$1.$2")
      value = value.replace(/(\d{3})(\d)/, "$1.$2")
      value = value.replace(/(\d{3})(\d)/, "$1-$2")
    }

    return value
  }

  return Cpf_CnpjMask
}())

$(() => {
  var cpfCnpjMask = new LambariusPro.Cpf_CnpjMask();
  cpfCnpjMask.enable();
});
