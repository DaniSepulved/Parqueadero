// import { Form, useNavigate } from 'react-router-dom'
import "./Pagos.css"
// import { alertaRedireccion, alertaError } from '../helpers/funciones'
import ChatBotWidget from '../components/ChatBotWidget';
import Footer from '../components/Footer';
import { Outlet, Link } from "react-router-dom";

// let apiPagos = "http://localhost:8080/api/pagos"

function Pagos() {
    return (
        <>
            <div className="pagos-bg">

                {/* Cabecera superior interna de la sección de facturación */}
                <header className='pagos-header'>
                    <h1 className='pagos-logo'>ParqueaderoSC</h1>
                    <button className='pagos-bell'>
                        <i className='fa-solid- fa-bell'></i>
                    </button>
                </header>
                <div className='pagos-container'>

                    {/* Tarjeta izquierda: Resumen estático del ticket generado por el parqueadero */}
                    <div className='pagos-card'>
                        <h2 className='pagos-tittle'>Detalles</h2>
                        <p><strong>Tipo de servicio: </strong>Reservas de Parqueo</p>
                        <p><strong>Monto a pagar:</strong> $7.500 </p>
                        <p><strong>Fecha:</strong> 2025-02-01 </p>
                        <p><strong>Espacio:</strong> A-12 </p>
                        <p><strong>Factura:</strong> 0001 </p>
                    </div>

                    {/* Tarjeta derecha: Listado de pasarelas y billeteras virtuales integradas */}
                    <div className='pagos-card'>
                        <h2 className='pagos-tittle'>Metodo de pago</h2>
                        <button className='metodo'>
                            <div className="metodo-left">
                                <img src="./Nequi.png" className='icono' />
                                <Link to="/pagos/nequi"><span>Nequi</span></Link>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>
                        <button className="metodo">
                            <div className="metodo-left">
                                <img src="/Bancolombia.png" className="icono" />
                                <span>Bancolombia</span>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>
                        <button className="metodo">
                            <div className="metodo-left">
                                <img src="/Daviplata.png" className="icono" />
                                <span>Tarjeta debito / Daviplata</span>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>       
                        <button className="metodo">
                            <div className="metodo-left">
                                <img src="/TarjetaCredito.png" className="icono" />
                                <span>Tarjeta credito / debito con CVV</span>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>            
                    </div>  
                </div>
                {/* <Outlet /> */}
            </div> 
            {/* <ChatBotWidget /> */}
            <Footer />
        </>
    );
}

export default Pagos;