(function ($) {

    //VARIABLES

    // Inicializar Cloud Firestore a través de Firebase
    firebase.initializeApp({
        apiKey: "AIzaSyDpXUBztI9rl_kR2lmyFmaZ9snqlo6ho88",
        authDomain: "bancodealimentos-2f99b.firebaseapp.com",
        projectId: "bancodealimentos-2f99b"
    });

    var db = firebase.firestore();

    //FUNCIONES

    function showErrorMessage() {
        $('#mensajeError').fadeIn();
    }

    function loginRequest(email, password) {
        firebase.auth().signInWithEmailAndPassword(email, password)
            .then((userCredential) => {
                // User exists in Authentication

                db.collection("adminUsers").get()
                    .then((querySnapshot) => {
                        var adminEmail;
                        var userMatched = false;

                        querySnapshot.forEach((doc) => {
                            if (!userMatched) {
                                adminEmail = doc.data().email;
                                if (email === adminEmail) {
                                    userMatched = true;
                                    window.location.replace("noticia.html");
                                }
                            }
                        });

                        if (!userMatched) {
                            showErrorMessage();
                        }
                    })
                    .catch(function (error) {
                        console.error("Error al buscar cuenta", error);
                    });
            })
            .catch((error) => {
                //User does not exist
                showErrorMessage();
            });
    }

    function manageFields() {
        var emptyFieldFound = false;
        $(".campo").each(function () {
            if ($(this).val().length == 0) {
                if (!emptyFieldFound) {
                    $(this).focus();
                }
                emptyFieldFound = true;
                $(this).addClass("campoInvalido");
            }
        })

        return !emptyFieldFound;
    }

    //CÓDIGO PRINCIPAL
    $(document).ready(function () {

        //JQUERY
        $(".campo").on('focusout', function () {
            if ($(this).val().length > 0) {
                $(this).removeClass("campoInvalido");
            }
        });

        $(".campo").on('focusin', function () {
            $('#mensajeError').fadeOut();
        });

        $('#tarjetaPrincipalLogin').on('submit', function (submitEvent) {
            submitEvent.preventDefault();
            allFieldsFilled = manageFields();

            if (allFieldsFilled) {
                const data = new FormData(submitEvent.target);
                const formObject = Object.fromEntries(data.entries());
                loginRequest(formObject.email, formObject.contraseña);
            }
        });
    });

})(jQuery);