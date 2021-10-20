(function ($) {

    //VARIABLES
    // Inicializar Cloud Firestore a través de Firebase
    firebase.initializeApp({
        apiKey: "AIzaSyDpXUBztI9rl_kR2lmyFmaZ9snqlo6ho88",
        authDomain: "bancodealimentos-2f99b.firebaseapp.com",
        projectId: "bancodealimentos-2f99b",
        storageBucket: 'gs://bancodealimentos-2f99b.appspot.com'
    });
    firebase.auth().setPersistence(firebase.auth.Auth.Persistence.SESSION); //el usuario estará logeado sólo en la ventana actual de navegación (una vez cerrada, o en alguna otra ventana, la sesión será inválida)

    var db = firebase.firestore();

    //FUNCIONES
    function uploadData(formObject) {
        let idToAssign = -1;

        db.collection(formObject.tipoNoticia).get().then((querySnapshot) => {
            let docID;
            querySnapshot.forEach((doc) => {
                docID = parseInt(doc.data().id);

                if (docID > idToAssign) {
                    idToAssign = docID;
                }
            });
        }).then(function () {
            idToAssign += 1;

            var imageAsData = $("#cuadroImagen").data("imageData");
            var imageRef = firebase.storage().ref().child(formObject.archivo.name);

            imageRef.putString(imageAsData, 'data_url').then(() => {
                console.log('Imagen subida con éxito');
                imageRef.getDownloadURL().then(function (imageURL) {
                    db.collection(formObject.tipoNoticia).doc("noticia" + idToAssign).set({
                        author: formObject.autor,
                        description: formObject.contenido,
                        id: idToAssign,
                        imageId: imageURL,
                        title: formObject.titulo,
                        year: new Date().getFullYear()
                    }).then(function () {
                        console.log("Creación de doc exitosa");
                        location.reload();
                    }).catch(function (error) {
                        console.error("Error al crear doc", error);
                    });
                }).catch(function (error) {
                    console.error("Error obteniendo URL de imagen", error);
                });
            }).catch(function (error) {
                console.error("Error subiendo imagen", error);
            });
        }).catch(function (error) {
            console.error("Error leyendo doc", error);
        });
    }

    function manageFields() {
        var emptyFieldFound = false;
        $(".campoNoticia").each(function () {
            if ($(this).val().length == 0) {
                if (!emptyFieldFound) {
                    $(this).focus();
                }
                emptyFieldFound = true;
                $(this).addClass("campoInvalido");
            }
        })

        if ($('#botonCargarImagenFalso').val() == "") {
            if (!emptyFieldFound) {
                $('#botonCargarImagen').focus();
            }
            emptyFieldFound = true;
            $('#cuadroImagen').addClass("campoInvalido");
        } else {
            $('#cuadroImagen').removeClass("campoInvalido");
        }

        return !emptyFieldFound;
    }

    //CODIGO PRINCIPAL:
    firebase.auth().onAuthStateChanged(function (user) {
        if (!user) {
            window.location.replace("login.html"); //si no está loggeado el usuario, mandar a login
        }
    });

    $(document).ready(function () {
        
        //JQUERY
        $('label').css("color", "black");
        $('label').css("font-weight", "bold");
        $('td').on('click', function () {
            $(this).parent().find('label').css("color", "black");
        });
        $('input[type="radio"]:checked + label').css("color", "var(--colorEnfocado)");
        $('input[type="radio"]').on('change', function () {
            var label = $(this).next();
            if ($(this).prop("checked")) {
                label.css("color", "var(--colorEnfocado)");
            } else {
                label.css("color", "black");
            }
        });
        $('#cuadroImagen').on('click', function () {
            var inputCargar = $('#botonCargarImagenFalso');

            inputCargar.click();
        });

        $('#botonCargarImagen').on('click', function () {
            var inputCargar = $('#botonCargarImagenFalso');

            inputCargar.click();
        });

        $('#botonCargarImagenFalso').on('change', function () {
            $('#cuadroImagen').removeClass("campoInvalido");
            $('#missingImage').prop("hidden", true);

            var filename = this.files[0].name;

            if (this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#cuadroImagen').css("background-image", "url(" + e.target.result + ")");
                    $('#cuadroImagen').data("imageData", e.target.result);
                }
                reader.readAsDataURL(this.files[0]);
            }
        });

        $(".campo").on('focusout', function () {
            if ($(this).val().length > 0) {
                $(this).removeClass("campoInvalido");
            }
        });

        $('#tarjetaPrincipalNoticia').on('submit', function (submitEvent) {
            submitEvent.preventDefault();
            allFieldsFilled = manageFields();

            if (allFieldsFilled) {
                const data = new FormData(submitEvent.target);
                const formObject = Object.fromEntries(data.entries());
                uploadData(formObject);
            }
        });

        $('#botonCerrarSesion').on('click', function () {
            firebase.auth().signOut();
        });
    });
})(jQuery);