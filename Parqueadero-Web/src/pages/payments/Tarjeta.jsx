import "./Tarjeta.css";
import { useState } from "react";
import { confirmarPago } from "../../helpers/funciones";
import { Link } from "react-router-dom";

function Tarjeta() {
  const [formData, setFormData] = useState({
    titular: "",
    numero: "",
    fecha: "",
    cvv: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    alert("Pago procesado correctamente ✅");
  };

  return (
    <div className="tarjeta-container">
      <div className="tarjeta-card">

        <h1>Pago con Tarjeta de Crédito o Débito</h1>

        <div className="logos">
          <img
            src="https://cdn-icons-png.flaticon.com/512/196/196578.png"
            alt="Visa"
          />
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/2/2a/Mastercard-logo.svg"
            alt="Mastercard"
          />
        </div>

        <form onSubmit={handleSubmit}>

          <div className="form-group">
            <label>Nombre del titular</label>
            <input
              type="text"
              name="titular"
              placeholder="Juan Pérez"
              value={formData.titular}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Número de tarjeta</label>
            <input
              type="text"
              name="numero"
              placeholder="1234 5678 9012 3456"
              maxLength="19"
              value={formData.numero}
              onChange={handleChange}
              required
            />
          </div>

          <div className="row">

            <div className="form-group">
              <label>Fecha de expiración</label>
              <input
                type="text"
                name="fecha"
                placeholder="MM/AA"
                maxLength="5"
                value={formData.fecha}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-group">
              <label>CVV</label>
              <input
                type="password"
                name="cvv"
                placeholder="123"
                maxLength="4"
                value={formData.cvv}
                onChange={handleChange}
                required
              />
            </div>

          </div>

          <div className="botones">
            <button type="button" className="btn-cancelar">
                <Link to="/pagos">Cancelar</Link>
            </button>

            <button type="submit" className="btn-pagar" onClick={confirmarPago}>  
                Pagar
            </button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default Tarjeta;