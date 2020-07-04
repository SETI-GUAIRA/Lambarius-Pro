var LambariusPro = LambariusPro || {};

LambariusPro.DateMask = (function () {
  class DateMask {
    constructor() {
      this.input = $('.datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' });
      this.data = $('[data-mask]').inputmask();
    }
    enable() {
      this.input;
      this.data;
    }
  }

  return DateMask
}())

$(() => {
  var dateMask = new LambariusPro.DateMask();
  dateMask.enable();
});
