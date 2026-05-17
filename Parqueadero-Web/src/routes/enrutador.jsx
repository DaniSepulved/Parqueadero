import RutaProtegida from '../components/RutaProtegida'
import Home from '../Home' // Este es tu componente de layout principal para el área protegida
import Login from '../pages/Login'
import Cuenta from '../pages/Cuenta'
import ReservasEspacio from '../pages/ReservasEspacio'
import Pagos from '../pages/Pagos'
import Contacto from '../pages/Contacto'
import Nequi from '../pages/payments/Nequi'
import InformacionCuenta from '../pages/cuenta/InformacionCuenta'
import ActualizarCuenta from '../pages/cuenta/ActualizarCuenta'

// Maneja las rutas de la aplicación, incluyendo rutas protegidas para usuarios autenticados.
export let enrutador = [
  {
    path: '/',
    element: <Login />
  },
  {
    element: <RutaProtegida />,
    children: [
      { path: '/home', element: <Home /> },
      { path: '/cuenta', element: <Cuenta /> },
      { path: '/cuenta/informacion', element: <InformacionCuenta /> },
      { path: '/cuenta/actualizar', element: <ActualizarCuenta /> },
      { path: '/reservas', element: <ReservasEspacio /> },
      { path: '/contacto', element: <Contacto /> },
      { path: '/pagos', element: <Pagos /> },
      { path: '/pagos/nequi', element: <Nequi /> }
    ]
  },
];