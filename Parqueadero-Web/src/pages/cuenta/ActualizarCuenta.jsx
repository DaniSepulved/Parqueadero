import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./ActualizarCuenta.css";

// Permite al usuario editar la información de su perfil mediante un formulario dinámico conectado a Spring Boot.
const ActualizarCuenta = () => {
  const navigate = useNavigate();

  // Estados locales para manejar los datos del usuario y la carga inicial
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [correo, setCorreo] = useState("");
  const [password, setPassword] = useState("");
  const [cargando, setCargando] = useState(true);

  const idUsuarioLogueado = localStorage.getItem("idUsuario");
  const token = localStorage.getItem("token");

  // Permite cargar los datos del usuario desde la API al montar el componente
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

          // Se mapean los datos del usuario a los estados locales para que se muestren en los inputs
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

  // Función que se ejecuta al hacer clic en "Guardar Cambios", enviando los datos modificados al backend
  const handleGuardarCambios = async (e) => {
    e.preventDefault();

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

      alert("¡Tus datos se han actualizado correctamente!");
      navigate("/cuenta"); 

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

        {/* Inputs para los datos del usuario */}
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
        <button onClick={handleGuardarCambios}>
          Guardar Cambios
        </button>

        <Link to="/cuenta" className="btn-cancelar">
          Cancelar y regresar
        </Link>
      </div>
    </div>
  );
};

export default ActualizarCuenta;