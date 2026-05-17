import "./ActualizarCuenta.css";

// Permite al usuario editar la información de su perfil mediante un formulario.
const ActualizarCuenta = () => {
  return (
    <div className="actualizar-container">

      <div className="actualizar-card">

        <h1>Datos del Usuario</h1>

        <input type="text" placeholder="Nombre completo" />
        <input type="text" placeholder="Apellido" />
        <input type="email" placeholder="Correo electrónico" />
        <input type="text" placeholder="Teléfono" />
        <input type="text" placeholder="Dirección" />
        <input type="text" placeholder="País" />

        <button>
          Guardar Cambios
        </button>

      </div>

    </div>
  );
};

export default ActualizarCuenta;