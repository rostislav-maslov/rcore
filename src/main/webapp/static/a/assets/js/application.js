function toggle_loading($elem, success){
  $elem.closest('.widget').toggleClass('widget-loading');
  if(success == true){
    $icon = $elem.closest('.widget').find('.widget-control-refresh i');
    $icon.attr('class', 'icon-ok-sign');
    setTimeout(function() {
      $icon.attr('class', 'icon-refresh');
    }, 1000);
  }
}

$(function() {
  // Change color of the widget
  $('.set-widget-color').on('click', function(){
    var color = $(this).data('widget-color');
    $(this).closest('.widget').attr('class', 'widget widget-' + color);
    return false;
  });
  // Toggle menu on click
  $('.menu-toggler').on('click', function(){
    $('.all-wrapper').toggleClass('hide-sub-menu');
    return false;
  });
  // Add scrollspy to side sub menu
  $('.main-content').scrollspy({ target: '.sub-sidebar-wrapper' });
  // Make table action buttons working
  $('.remove-tr').on('click', function(){
    $row = $(this).closest('tr');
    $row.addClass('danger');
    setTimeout(function(){ $row.remove(); }, 300);
    return false;
  });
  // Input Masks
  $(".mask_date").mask("99/99/9999");
  $(".mask_phone").mask("(999) 999-9999");
  $(".mask_taxid").mask("99-9999999");
  $(".mask_ssn").mask("999-99-9999");
  $(".mask_money").maskMoney({symbol:'$ '});
  // WYSIWYG Editor summernote
  $('.summernote').summernote({
    height: 150
  });
  // Datepicker
  $('.input-datepicker').datepicker();
  $('.input-timepicker').timepicker();
  $('.input-daterangepicker').daterangepicker();
  $('.input-colorpicker').colpick({
    onSubmit: function(hsb,hex,rgb,el) {
      $(el).val("#" + hex).prev('.input-group-addon').css('background-color', "#" + hex).css('border-color', "#" + hex);
      $(el).colpickHide();
    }
  });
  $('.input-colorpicker-simple').colpick({
    layout: 'hex',
    onSubmit: function(hsb,hex,rgb,el) {
      $(el).val("#" + hex).prev('.input-group-addon').css('background-color', "#" + hex).css('border-color', "#" + hex);
      $(el).colpickHide();
    }
  });
  $('.input-colorpicker-dark').colpick({
    colorScheme: 'dark',
    onSubmit: function(hsb,hex,rgb,el) {
      $(el).val("#" + hex).prev('.input-group-addon').css('background-color', "#" + hex).css('border-color', "#" + hex);
      $(el).colpickHide();
    }
  });
  $('.input-colorpicker-dark-simple').colpick({
    colorScheme: 'dark',
    layout: 'hex',
    onSubmit: function(hsb,hex,rgb,el) {
      $(el).val("#" + hex).prev('.input-group-addon').css('background-color', "#" + hex).css('border-color', "#" + hex);
      $(el).colpickHide();
    }
  });
  $("[data-toggle='tooltip']").tooltip();
  $('.widget-control-remove').on("click", function() {
    $(this).closest('.widget').slideUp("fast");
    return false;
  });
  $('.widget-control-minimize').on("click", function() {
    $elem = $(this)
    if($elem.closest('.widget').hasClass('widget-title-minimized')){
      $elem.closest('.widget').removeClass('widget-minimized').removeClass('widget-title-minimized').find('.widget-content').slideDown('fast');
    }else{
      $elem.closest('.widget').addClass('widget-minimized').find('.widget-content').slideUp('fast', function(){ $elem.closest('.widget').addClass('widget-title-minimized') });
    }
    return false;
  });
  $('.widget-control-refresh').on("click", function() {
    var $elem;
    $elem = $(this);
    toggle_loading($elem);
    setTimeout(function() {
      toggle_loading($elem, true);
    }, 1000);
    return false;
  });

  $('.chosen-select').chosen();
});
