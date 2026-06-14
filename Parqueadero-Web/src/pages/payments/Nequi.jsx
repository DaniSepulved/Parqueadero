import "./Nequi.css";
import { Link } from "react-router-dom";
import { confirmarPago } from "../../helpers/funciones";

// Representa la interfaz de pasarela simulada para pagos a través de cuentas Nequi.
function Nequi() {
  return (
    <div className="nequi-container">
      <div className="nequi-card">

        <button className="nequi-back">
          <Link to="/pagos">← REGRESAR</Link>
        </button>

        <h2 className="nequi-title">Pago con cuenta Nequi</h2>

        <div className="nequi-box">
          <div className="nequi-header">
            <img 
              src="/Nequi.png" 
              alt="Nequi" 
              className="nequi-logo"
            />
            <h3>Nequi</h3>
          </div>

          <label className="nequi-label">Número asociado</label>
          <input 
            type="text"
            className="nequi-input"
            placeholder="300XXXXXXXX"
          />

          <p className="nequi-terms">
            Al presionar CONTINUAR estás aceptando los  
            <span className="nequi-link"> términos y condiciones</span>
          </p>

          <div className="nequi-buttons">
            <button className="nequi-cancel">
              <Link to="/pagos">CANCELAR</Link>
            </button>
            <button className="nequi-continue" onClick={confirmarPago}>
              CONTINUAR
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Nequi;