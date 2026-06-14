import { useState, useEffect } from "react";
import "./InformacionCuenta.css";

const InformacionCuenta = () => {
  // Estados para pintar la información real de la BD
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [email, setEmail] = useState("");
  const [cargando, setCargando] = useState(true);

  const idUsuarioLogueado = localStorage.getItem("idUsuario");
  const token = localStorage.getItem("token");

  useEffect(() => {
    const obtenerDatosDeBaseDeDatos = async () => {
      if (!idUsuarioLogueado) return;

      try {
        const response = await fetch(`http://localhost:8080/api/usuarios/${idUsuarioLogueado}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          }
        });

        if (response.ok) {
          const user = await response.json();
          setNombre(user.nombre || "");
          setApellido(user.apellido || "");
          setEmail(user.email || "");
          
          // Opcional: actualizas el localStorage por si acaso
          localStorage.setItem("nombre", user.nombre);
          localStorage.setItem("apellido", user.apellido);
        }
      } catch (error) {
        console.error("Error al traer info de cuenta:", error);
      } finally {
        setCargando(false);
      }
    };

    obtenerDatosDeBaseDeDatos();
  }, [idUsuarioLogueado, token]);

  if (cargando) {
    return <div className="info-container"><h2 style={{color: "white"}}>Cargando información...</h2></div>;
  }

  return (
    <div className="info-container">
      <div className="info-card">
        <h1>INFORMACIÓN DE MI CUENTA</h1>

        <div className="avatar">👤</div>

        <p className="bienvenida">
          Bienvenido, {nombre}
        </p>

        <div className="campo">
          <label>Nombre</label>
          <input
            type="text"
            value={nombre}
            readOnly
          />
        </div>

        <div className="campo">
          <label>Apellido</label>
          <input
            type="text"
            value={apellido}
            readOnly
          />
        </div>

        <div className="campo">
          <label>Email</label>
          <input
            type="text"
            value={email}
            readOnly
          />
        </div>
      </div>
    </div>
  );
};

export default InformacionCuenta;