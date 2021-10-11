(function ($) {
    
    
    //CODIGO PRINCIPAL:
    $(document).ready(function () {
        //JQUERY
        $('#botonCargarImagen').on('click', function (){
            var inputCargar = $('#botonCargarImagenFalso');
            
            inputCargar.click();
        })

        $('#botonCargarImagenFalso').on('change', function (){
            $('#missingImage').prop("hidden", true);
            if (this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#cuadroImagen').css("background-image", "url(" + e.target.result + ")");
                }
                reader.readAsDataURL(this.files[0]);
            }
        })
    });
})(jQuery);