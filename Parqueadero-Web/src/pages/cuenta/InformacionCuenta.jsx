import "./InformacionCuenta.css";

// Renderiza de forma exclusiva y en modo lectura los datos del usuario activo
// que se encuentran guardados en el almacenamiento del navegador
const InformacionCuenta = () => {
  return (
    <div className="info-container">

      <div className="info-card">

        <h1>INFORMACIÓN DE MI CUENTA</h1>

        <div className="avatar">
          👤
        </div>

        <p className="bienvenida">
          Bienvenido, {localStorage.getItem("nombre")}
        </p>

        <div className="campo">
          <label>Nombre</label>
          <input
            type="text"
            value={localStorage.getItem("nombre")}
            readOnly
          />
        </div>

        <div className="campo">
          <label>Apellido</label>
          <input
            type="text"
            value={localStorage.getItem("apellido")}
            readOnly
          />
        </div>

        <div className="campo">
          <label>Email</label>
          <input
            type="text"
            value={localStorage.getItem("usuario")}
            readOnly
          />
        </div>

      </div>

    </div>
  );
};

export default InformacionCuenta;