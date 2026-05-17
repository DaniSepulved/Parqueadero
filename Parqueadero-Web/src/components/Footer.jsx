import React from "react";
import "./Footer.css";
import logo from "/Logo.jpg"; // Asegúrate de tener tu logo en /public

// Componente funcional constante llamado 'Footer'
const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        
        {/* IZQUIERDA */}
        <div className="footer-about">
          <h3>ACERCA DE PARQUEADERO SC</h3>
          <img src={logo} alt="Logo The Parking" className="footer-logo" />
          <p>
            En <strong>ParqueaderoSC</strong> trabajamos cada día para ofrecerte
            un servicio de estacionamiento rápido, seguro y confiable.
          </p>
          <p>
            Nuestra misión es brindarte comodidad y tranquilidad al dejar tu
            vehículo en manos de un equipo responsable, con tecnología moderna
            para el control de acceso, pagos y reservas en línea.
          </p>
          <p>
            Con nosotros, tu vehículo siempre está en buenas manos 🚗✨
          </p>
        </div>

        {/* DERECHA */}
        <div className="footer-links">
          <div>
            <h3>NUESTROS SERVICIOS</h3>
            <ul>
              <li>Reserva de espacios en línea</li>
              <li>Pagos automáticos</li>
              <li>Control de acceso digital</li>
              <li>Tarifas por hora y día</li>
            </ul>
          </div>

          <div>
            <h3>INFORMACIÓN</h3>
            <ul>
              <li>Términos y condiciones</li>
              <li>Política de privacidad</li>
              <li>Contáctanos</li>
            </ul>
          </div>
        </div>
      </div>

      {/* PARTE INFERIOR */}
      <div className="footer-bottom">
        <p>
          Calle 50 # 45-22 | Itagüí, Antioquia | Tel: (604) 555-1212 | contacto@parqueaderossc.com
        </p>
        <p>© 2025 ParqueaderoSC. Todos los derechos reservados.</p>
      </div>
    </footer>
  );
};

export default Footer;
