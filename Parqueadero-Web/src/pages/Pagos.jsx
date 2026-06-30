import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import "./Pagos.css";
import Footer from '../components/Footer';
import ChatBotWidget from '../components/ChatBotWidget';

function Pagos() {
    //Se obtiene el ID de la reserva desde la URL para cargar los detalles específicos del pago.
    const { id } = useParams(); 
    
    // Estados locales para manejar la reserva, carga y errores.
    const [reserva, setReserva] = useState(null);
    const [cargando, setCargando] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Si no hay ID en la URL, no intentamos cargar nada.
        if (!id) {
            setCargando(false);
            return;
        }

        const obtenerDetallesReserva = async () => {
            try {
                const token = localStorage.getItem('token');
                
                const response = await fetch(`http://localhost:8080/api/reservas/${id}`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    }
                });

                if (!response.ok) {
                    throw new Error("No se pudo obtener la información de la reserva");
                }

                const data = await response.json();
                setReserva(data); 
            } catch (err) {
                console.error(err);
                setError(err.message);
            } finally {
                setCargando(false);
            }
        };

        obtenerDetallesReserva();
    }, [id]);

    if (cargando) {
        return <div className="pagos-bg"><div className="pagos-container"><h2 style={{color: 'white'}}>Cargando detalles del pago...</h2></div></div>;
    }

    if (error) {
        return <div className="pagos-bg"><div className="pagos-container"><h2 style={{color: 'red'}}>⚠️ Error: {error}</h2></div></div>;
    }

    return (
        <>
            <div className="pagos-bg">
                {/* Cabecera superior interna */}
                <header className='pagos-header'>
                    <h1 className='pagos-logo'>ParqueaderoSC</h1>
                    <Link to="/home" className="btn-regresar">
                        Regresar
                    </Link> 
                </header>
                
                <div className='pagos-container'>
                    {/* Tarjeta izquierda */}
                    <div className='pagos-card'>
                        <h2 className='pagos-tittle'>Detalles</h2>
                        {reserva ? (
                            <>
                                <p><strong>Tipo de servicio: </strong>Reservas de Parqueo</p>
                                {/* El monto lo puedes calcular dinámicamente o dejar base si manejas tarifa fija */}
                                <p><strong>Monto a pagar:</strong> {reserva.totalPagar ? `$${reserva.totalPagar.toLocaleString('es-CO')}` : "$0"}</p>
                                <p><strong>Fecha:</strong> {reserva.fechaReserva || "No especificada"}</p>
                                <p><strong>Espacio:</strong> Slot {reserva.espaciosParqueo?.numero || id} </p>
                                <p><strong>Factura / ID Reserva:</strong> #000{reserva.idReserva}</p>
                            </>
                        ) : (
                            <p style={{textAlign: 'center', fontStyle: 'italic'}}>
                                Selecciona una reserva activa desde el panel principal para proceder al pago.
                            </p>
                        )}
                    </div>

                    {/* Tarjeta derecha */}
                    <div className='pagos-card'>
                        <h2 className='pagos-tittle'>Metodo de pago</h2>
                        
                        <button className='metodo'>
                            <div className="metodo-left">
                                <img src="/Nequi.png" className='icono' alt="Nequi" />
                                <Link to="/pagos/nequi"><span>Nequi</span></Link>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>
                        
                        <button className="metodo">
                            <div className="metodo-left">
                                <img src="/Bancolombia.png" className="icono" alt="Bancolombia" />
                                <span>Bancolombia</span>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>
                        
                        <button className="metodo">
                            <div className="metodo-left">
                                <img src="/Daviplata.png" className="icono" alt="Daviplata" />
                                <span>Daviplata</span>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>       
                        
                        <button className="metodo">
                            <div className="metodo-left">
                                <img src="/TarjetaCredito.png" className="icono" alt="Tarjeta" />
                                <Link to="/pagos/Tarjeta"><span>Tarjeta de crédito / débito</span></Link>
                            </div>
                            <i className="fa-solid fa-chevron-right"></i>
                        </button>            
                    </div>  
                </div>
            </div> 
            <Footer />
            <ChatBotWidget />
        </>
    );
}

export default Pagos;