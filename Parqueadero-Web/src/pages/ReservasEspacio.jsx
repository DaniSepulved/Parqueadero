import "./ReservasEspacio.css";
import Footer from '../components/Footer';

const ReservasEspacio = () => {
  // Instanciación de una colección de control
  const espacios = Array.from({ length: 12 }, (_, i) => i + 1); // Simula 12 espacios de parqueo

  return (
    <>
    <div className="reserva-container">
      <div className="overlay"></div>

      <div className="contenido">
        
        {/* Formulario */}
        <div className="reserva-card">
          <h2>RESERVA TU ESPACIO AHORA</h2>

          <div className="inputs-grid">
            <input type="date" />
            <input type="time" />
            <input type="date" />
            <input type="time" />
          </div>

          <select>
            <option>Moto</option>
            <option>Carro</option>
            <option>Bus</option>
          </select>

          <button>BUSCAR DISPONIBILIDAD</button>
        </div>

        {/* Parqueadero */}
        <div className="parking-container">
          <h3>Selecciona tu espacio</h3>

          <div className="parking-grid">
            {espacios.map((_, i) => (
              <div
                key={i}
                className={`slot ${i < 5 ? "ocupado" : "libre"}`}
              >
                {i + 1}
              </div>
            ))}
          </div>
        </div>

      </div>
    </div>
    <Footer />
  </>  
  );
};

export default ReservasEspacio;