import { Link } from "react-router-dom"
import { alertaRedireccion } from "../helpers/funciones"
import { useNavigate } from "react-router-dom"
import { useAuth } from "../context/AuthContext"

const MenuLateral = () => {
  let redireccion = useNavigate()

  const nombre = localStorage.getItem('nombre') || "Usuario";
  const apellido = localStorage.getItem('apellido') || "";

// Función que se dispara al hacer clic en el botón de salida.
  function cerrarSesion() {
    alertaRedireccion("Cerrando sesión", "/", redireccion)
  }

  return (
    <aside className="aplicacion__menu-lateral">
      <h1 className="aplicacion__menu-lateral-logo">Parqueadero<span className="aplicacion__menu-lateral-logo--resaltado">SC</span></h1>
      <h2>Usuario: {nombre} {apellido}</h2>
      <img className="aplicacion__menu-lateral-logo-imagen" src="Logo.jpg" alt="Logo" />
      <nav className="aplicacion__menu-lateral-navegacion">
        <Link className="aplicacion__menu-lateral-navegacion-item" to="">Inicio</Link>
        <Link className="aplicacion__menu-lateral-navegacion-item" to="/reservas">Reservas Espacio</Link>
        <Link className="aplicacion__menu-lateral-navegacion-item" to="/cuenta">Cuenta</Link>
        <Link className="aplicacion__menu-lateral-navegacion-item" to="/pagos">Pagos</Link>
        <Link className="aplicacion__menu-lateral-navegacion-item" to="/contacto">Contactenos</Link>
        <button onClick={cerrarSesion} type='button' className="aplicacion__menu-lateral-navegacion-item">Cerrar sesión</button>
      </nav>
    </aside>
  )
}

export default MenuLateral;