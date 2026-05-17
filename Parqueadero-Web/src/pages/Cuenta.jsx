import "./Cuenta.css";
import Footer from '../components/Footer';
import { Link } from "react-router-dom";
import { eliminarCuenta } from "../helpers/funciones";

// Componente para validar la cuenta del usuario
const Cuenta = () => {
  return (
    <>
      <div className="cuenta-container">
        <div className="overlay"></div>

        <div className="cuenta-card">

          <h1>GESTIÓN DE CUENTA</h1>

          <div className="avatar">
            👤
          </div>

          <p className="bienvenida">
            BIENVENIDO, USUARIO
          </p>

          {/* Redirecciona a las secciones de información y actualización de cuenta, o elimina tu cuenta de forma permanente. */}
          <Link to="/cuenta/informacion" className="btn-cuenta info">
            📇 Información de mi Cuenta
            <span>Ver y editar detalle</span>
          </Link>

          {/* Redirecciona a las secciones de información y actualización de cuenta, o elimina tu cuenta de forma permanente. */}
          <Link to="/cuenta/actualizar" className="btn-cuenta editar">
            ⚙️ Actualizar Cuenta
            <span>Modificar plan, datos personales</span>
          </Link>

          {/* Aparace una ventana emergente de confirmación para eliminar la cuenta de forma permanente. */}
          <button className="btn-cuenta eliminar"
          onClick={eliminarCuenta}>
            🗑️ Eliminar Cuenta
          </button>

          <p className="footer-text">
            Para asistencia, contacta a soporte
          </p>

        </div>
      </div>
      <Footer />
    </>
  );
};

export default Cuenta;