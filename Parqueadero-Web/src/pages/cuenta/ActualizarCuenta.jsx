import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./ActualizarCuenta.css";

// Permite al usuario editar la información de su perfil mediante un formulario dinámico conectado a Spring Boot.
const ActualizarCuenta = () => {
  const navigate = useNavigate();

  // 1. Definimos los estados para amarrar los valores de tus inputs
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [correo, setCorreo] = useState("");
  const [password, setPassword] = useState("");
  const [cargando, setCargando] = useState(true);

  const idUsuarioLogueado = localStorage.getItem("idUsuario");
  const token = localStorage.getItem("token");

  // 2. Cargamos los datos actuales del usuario al abrir la pantalla
  useEffect(() => {
    const cargarDatosUsuario = async () => {
      if (!idUsuarioLogueado) {
        alert("Sesión no válida. Por favor inicia sesión de nuevo.");
        navigate("/");
        return;
      }

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
          // Mapeamos lo que devuelva tu base de datos a los inputs
          setNombre(user.nombre || "");
          setApellido(user.apellido || "");
          setCorreo(user.email || "");
          setPassword(user.password || "");
        }
      } catch (error) {
        console.error("Error al obtener datos del usuario:", error);
      } finally {
        setCargando(false);
      }
    };

    cargarDatosUsuario();
  }, [idUsuarioLogueado, token, navigate]);

  // 3. Función para enviar los cambios con el método PUT
  const handleGuardarCambios = async (e) => {
    e.preventDefault(); // Evita que se recargue la página si metes el botón en un form

    if (!nombre || !correo) {
      alert("El nombre y el correo electrónico son campos obligatorios.");
      return;
    }

    const datosModificados = {
      nombre,
      apellido,
      email: correo,
      password
    };

    try {
      const response = await fetch(`http://localhost:8080/api/usuarios/${idUsuarioLogueado}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(datosModificados)
      });

      if (!response.ok) {
        throw new Error("Error al actualizar la información en el servidor");
      }

      alert("¡Tus datos se han actualizado correctamente! 💾");
      navigate("/cuenta"); // Lo mandamos de vuelta a la gestión de cuenta

    } catch (error) {
      console.error(error);
      alert("No fue posible actualizar los datos de la cuenta.");
    }
  };

  if (cargando) {
    return (
      <div className="actualizar-container">
        <h2 style={{ color: "white", textAlign: "center" }}>Cargando datos del usuario...</h2>
      </div>
    );
  }

  return (
    <div className="actualizar-container">
      <div className="actualizar-card">
        <h1>Datos del Usuario</h1>

        {/* Agregamos el value y el onChange a cada uno de tus inputs originales */}
        <input 
          type="text" 
          placeholder="Nombre completo" 
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
        />
        <input 
          type="text" 
          placeholder="Apellido" 
          value={apellido}
          onChange={(e) => setApellido(e.target.value)}
        />
        <input 
          type="email" 
          placeholder="Correo electrónico" 
          value={correo}
          onChange={(e) => setCorreo(e.target.value)}
        />
        <input
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        {/* Le asignamos el evento del clic a tu botón */}
        <button onClick={handleGuardarCambios}>
          Guardar Cambios
        </button>

        <Link to="/cuenta" style={{ color: "#aaa", textDecoration: "none", fontSize: "14px", marginTop: "15px", display: "block", textAlign: "center" }}>
          Cancelar y regresar
        </Link>
      </div>
    </div>
  );
};

export default ActualizarCuenta;