import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ReservasEspacio.css";
import Footer from "../components/Footer";

const ReservasEspacio = () => {

  const navigate = useNavigate();
  const [fechaEntrada, setFechaEntrada] = useState("");
  const [horaEntrada, setHoraEntrada] = useState("");
  const [fechaSalida, setFechaSalida] = useState("");
  const [horaSalida, setHoraSalida] = useState("");
  const [tipoVehiculo, setTipoVehiculo] = useState("Moto");
  const [espacioSeleccionado, setEspacioSeleccionado] = useState(null);

  const espacios = Array.from({ length: 12 }, (_, i) => i + 1);

  const obtenerIdTarifa = (vehiculo) => {
    switch (vehiculo) {
      case "Moto": return 1;
      case "Carro": return 2;
      case "Bus": return 3;
      default: return 1;
    }
  };

const crearReserva = async () => {
    if (!fechaEntrada || !horaEntrada || !fechaSalida || !horaSalida) {
      alert("Completa todas las fechas y horas");
      return;
    }
    if (!espacioSeleccionado) {
      alert("Selecciona un espacio");
      return;
    }

    const idUsuarioLogueado = localStorage.getItem('idUsuario');
    const token = localStorage.getItem('token'); 

    if (!idUsuarioLogueado) {
      alert("No se encontró una sesión activa. Por favor, vuelve a iniciar sesión.");
      return;
    }

    const reserva = {
        idUsuario: parseInt(idUsuarioLogueado),
        idTarifa: obtenerIdTarifa(tipoVehiculo),
        idEspacio: espacioSeleccionado,
        fechaReserva: fechaEntrada,
        horaInicio: `${fechaEntrada}T${horaEntrada}:00`,
        horaFin: `${fechaSalida}T${horaSalida}:00`
    };

    try {
        const response = await fetch(
          "http://localhost:8080/api/reservas",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              "Authorization": `Bearer ${token}` 
            },
            body: JSON.stringify(reserva)
          }
        );

        if (!response.ok) {
          throw new Error("Error al crear la reserva");
        }

        const data = await response.json();
        console.log("Reserva creada con éxito:", data);

        navigate(`/pagos/${data.idReserva || data.id}`); 

      } catch (error) {
        console.error(error);
        alert("No fue posible crear la reserva");
      }
    };

  return (
    <>
      <div className="reserva-container">
        <div className="overlay"></div>
        <div className="contenido">
          {/* FORMULARIO */}
          <div className="reserva-card">
            <h2>RESERVA TU ESPACIO AHORA</h2>
            <div className="inputs-grid">
              <input
                type="date"
                value={fechaEntrada}
                onChange={(e) => setFechaEntrada(e.target.value)}
              />
              <input
                type="time"
                value={horaEntrada}
                onChange={(e) => setHoraEntrada(e.target.value)}
              />
              <input
                type="date"
                value={fechaSalida}
                onChange={(e) => setFechaSalida(e.target.value)}
              />
              <input
                type="time"
                value={horaSalida}
                onChange={(e) => setHoraSalida(e.target.value)}
              />
            </div>
            <select
              value={tipoVehiculo}
              onChange={(e) => setTipoVehiculo(e.target.value)}
            >
              <option>Moto</option>
              <option>Carro</option>
              <option>Bus</option>
            </select>
            <button onClick={crearReserva}>
              RESERVAR
            </button>
          </div>

          {/* ESPACIOS */}
          <div className="parking-container">
            <h3>Selecciona tu espacio</h3>
            <div className="parking-grid">
              {espacios.map((espacio, i) => (
                <div
                  key={espacio}
                  className={`slot ${
                    i < 5
                      ? "ocupado"
                      : espacioSeleccionado === espacio
                      ? "seleccionado"
                      : "libre"
                  }`}
                  onClick={() => {
                    if (i >= 5) {
                      setEspacioSeleccionado(espacio);
                    }
                  }}
                >
                  {espacio}
                </div>

              ))}
            </div>
            {espacioSeleccionado && (
              <p>
                Espacio seleccionado: <strong>{espacioSeleccionado}</strong>
              </p>
            )}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default ReservasEspacio;