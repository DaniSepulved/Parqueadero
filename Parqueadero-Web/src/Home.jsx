import './Home.css';
import Contenido from './components/Contenido.jsx';
import MenuLateral from './components/MenuLateral.jsx';
import { Outlet } from 'react-router-dom';
import ChatBotWidget from './components/ChatBotWidget';
// import Footer from './components/Footer';

// Componente Principal: Home
const Home = () => {
  return (
  <>
    <div className="aplicacion">
      <MenuLateral />
      <div className="aplicacion__contenido">
        <div className="aplicacion__contenido-fondo"></div>
        <main className="aplicacion__principal">
          <section className="aplicacion__eslogan">
            <h2 className="aplicacion__eslogan-texto">
              <span>ParqueaderoSC</span>
            </h2>
          </section>

          {/* Seccion de misión, visión y valores de la empresa */}
          <section className="mvv">
              <h2 className="mvv__titulo">Nuestra Esencia</h2>
              <div className="mvv__cards">
                <div className="mvv__card">
                  <h3 className="mvv__card-titulo">Misión</h3>
                  <p className="mvv__card-texto">
                    Brindar a nuestros clientes un servicio de parqueadero seguro, ágil y confiable, 
                    garantizando comodidad y atención de calidad, con un compromiso constante hacia la innovación 
                    y la satisfacción del usuario.
                  </p>
                </div>

                <div className="mvv__card">
                  <h3 className="mvv__card-titulo">Visión</h3>
                  <p className="mvv__card-texto">
                    Ser el parqueadero líder en la región por nuestra eficiencia, 
                    tecnología avanzada y atención excepcional, contribuyendo a la movilidad urbana 
                    de forma sostenible y moderna.
                  </p>
                </div>

                <div className="mvv__card">
                  <h3 className="mvv__card-titulo">Valores</h3>
                  <ul className="mvv__valores-lista">
                    <li>✅ Responsabilidad</li>
                    <li>🤝 Compromiso</li>
                    <li>💡 Innovación</li>
                    <li>🛡️ Seguridad</li>
                    <li>🌱 Sostenibilidad</li>
                  </ul>
                </div>
              </div>
            </section>

          <Contenido /> 

          <Outlet /> {/* Aquí se renderizan las páginas hijas como Cuenta, ReservasEspacio, etc. */}
        </main>
        <footer className="footer-home">
          <div className="footer-home__container">
            <div className="footer-home__about">
              <h3>ACERCA DE PARQUEADERO SC</h3>
              <img src="/Logo.jpg" alt="Logo ParqueaderoSC" className="footer-home__logo" />
              <p>
                En <strong>ParqueaderoSC</strong> trabajamos cada día para ofrecerte un servicio de parqueadero
                rápido, seguro y confiable.
              </p>
              <p>
                Nuestra misión es brindarte comodidad y tranquilidad con tecnología moderna para el control
                de acceso, pagos y reservas.
              </p>
              <p>Con nosotros, tu vehículo siempre está en buenas manos 🚗✨</p>
            </div>

            <div className="footer-home__links">
              <div>
                <h3>NUESTROS SERVICIOS</h3>
                <ul>
                  <li>Reserva de espacios</li>
                  <li>Pagos automáticos</li>
                  <li>Control digital</li>
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

          <div className="footer-home__bottom">
            <p>
              Calle 50 # 45-22 | Itagüí, Antioquia | Tel: (604) 555-1212 | contacto@parqueaderossc.com
            </p>
            <p>© 2025 ParqueaderoSC. Todos los derechos reservados.</p>
          </div>
        </footer>
      </div>
    </div>
    <ChatBotWidget />
  </>
  );
};

export default Home;