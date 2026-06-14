import Swal from 'sweetalert2'

// Despliega una alerta de éxito animada de 1.5 segundos que muestra una cuenta
export function alertaRedireccion(mensaje, url, redireccion) {
    let timerInterval;
    Swal.fire({
        title: mensaje,
        html: "Será redireccionado en <b></b> milliseconds.",
        timer: 1500,
        icon: "success",
        timerProgressBar: true,

        // Se ejecuta cuando la alerta se dibuja en pantalla
        didOpen: () => {
            Swal.showLoading();
            const timer = Swal.getPopup().querySelector("b");
            timerInterval = setInterval(() => {
                timer.textContent = `${Swal.getTimerLeft()}`;
            }, 100);
        },

        // Se ejecuta justo cuando la alerta empieza a cerrarse
        willClose: () => {
            clearInterval(timerInterval);
            redireccion(url)
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
            console.log("I was closed by the timer");
        }
    });
}

// Muestra una ventana emergente estática para notificar estados como errores
export function alertaError(titulo, mensaje, icono) {
    Swal.fire({
        title: titulo,
        text: mensaje,
        icon: icono
    });
}

// Genera una cadena aleatoria formateada como token seguro temporal.
export function generaToken() {
    return "token-" + Math.random().toString(36).substring(2, 10) + "-" + Math.random().toString(36).substring(2, 10)
}

// Abre una ventana de doble confirmación con botones personalizados.
// export function eliminarCuenta() {
//   Swal.fire({
//     title: "¿Está seguro?",
//     text: "Esta acción eliminará su cuenta.",
//     icon: "warning",
//     showCancelButton: true,
//     confirmButtonText: "Sí, eliminar",
//     cancelButtonText: "Cancelar"
//   }).then((result) => {
//     if (result.isConfirmed) {
//       Swal.fire(
//         "Eliminado",
//         "La cuenta fue eliminada.",
//         "success"
//       );
//     }
//   });
// }

  export function confirmarPago() {
    Swal.fire({
        title: "¿Confirmar pago?",
        text: "¿Desea confirmar el pago de su reserva?",
        icon: "question",
        showCancelButton: true,
        confirmButtonText: "Sí, confirmar",
  }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                "Pago confirmado",
                "Su pago ha sido confirmado.",
                "success"
            );
        }
    });
}