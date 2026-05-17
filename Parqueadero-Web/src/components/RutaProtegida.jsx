import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from '../context/AuthContext';

function RutaProtegida() {
  // Obtiene el estado de autenticación del contexto
  const { isAuthenticated } = useAuth();

  // Si está autenticado, renderiza las rutas hijas; de lo contrario, redirige al login.
  return isAuthenticated ? <Outlet /> : <Navigate to="/" />;
}

export default RutaProtegida;