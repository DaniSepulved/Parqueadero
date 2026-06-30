import "./Cuenta.css";
import Footer from '../components/Footer';
import { Link, useNavigate } from "react-router-dom"; // Importamos useNavigate
import ChatBotWidget from '../components/ChatBotWidget';

const Cuenta = () => {
  const navigate = useNavigate();
  
  const idUsuarioLogueado = localStorage.getItem("idUsuario");
  const token = localStorage.getItem("token");
  const nombreUsuario = localStorage.getItem("nombre") || "USUARIO";

  const handleEliminarCuenta = async () => {
    const confirmar = window.confirm(
      "¿Estás completamente seguro de eliminar tu cuenta? Esta acción no se puede deshacer y perderás todos tus datos."
    );

    if (!confirmar) return; 

    try {
      const response = await fetch(`http://localhost:8080/api/usuarios/${idUsuarioLogueado}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        }
      });

      if (response.ok || response.status === 204) {
        alert("Tu cuenta ha sido eliminada correctamente. Esperamos verte pronto.");
        
        localStorage.clear(); 
        
        navigate("/"); 
      } else {
        throw new Error("El servidor rechazó la solicitud de eliminación");
      }

    } catch (error) {
      console.error("Error al eliminar la cuenta:", error);
      alert("No fue posible eliminar la cuenta en este momento. Inténtalo más tarde.");
    }
  };

  return (
    <>
      <div className="cuenta-container">
        <div className="overlay"></div>

        <div className="cuenta-card">
          <h1>GESTIÓN DE CUENTA</h1>

          <div className="avatar">👤</div>

          <p className="bienvenida">
            BIENVENIDO, {nombreUsuario.toUpperCase()}
          </p>

          <Link to="/cuenta/informacion" className="btn-cuenta info">
            Información de mi Cuenta
            <span>Ver detalle en tiempo real</span>
          </Link>

          <Link to="/cuenta/actualizar" className="btn-cuenta editar">
             Actualizar Cuenta
            <span>Modificar contraseña y datos personales</span>
          </Link>

          <button className="btn-cuenta eliminar" onClick={handleEliminarCuenta}>
             Eliminar Cuenta
          </button>

          <p className="footer-text">
            Para asistencia, contacta a soporte
          </p>
        </div>
      </div>
      <Footer />
      <ChatBotWidget />
    </>
  );
};

export default Cuenta;