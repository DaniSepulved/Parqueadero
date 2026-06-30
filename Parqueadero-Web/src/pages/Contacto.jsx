import React, { useRef } from "react";
import emailjs from "@emailjs/browser";
import './Contacto.css';
import ChatBotWidget from '../components/ChatBotWidget';
import Footer from '../components/Footer';

// Referencia mutable para acceder directamente al nodo del formulario
const Contacto = () => {
  const form = useRef();

  // Envía los datos del formulario a EmailJS de manera asíncrona.
  const sendEmail = (e) => {
    e.preventDefault();

    emailjs
      .sendForm(
        "service_z5thm1j",
        "template_btzrvbh",
        form.current,
        "AmpKmdVTZp44Beeyr"
      )
      .then(
        (result) => {
          alert("✅ Mensaje enviado con éxito. ¡Gracias por contactarnos!");
          form.current.reset();
        },
        (error) => {
          alert("❌ Ocurrió un error al enviar el mensaje: " + error.text);
        }
      );
  };

  return (
    <>
      <div className="contact-container">
        <h2>CONTÁCTANOS</h2>
        <p>
          Por favor diligencia el formulario y te responderemos lo antes posible.
          ¡Gracias!
        </p>

        <form ref={form} onSubmit={sendEmail} className="contact-form">
          <div className="left-side">
            <input type="text" name="nombre" placeholder="Nombre*" required />
            <input type="email" name="email" placeholder="Email*" required />
            <input type="tel" name="telefono" placeholder="Teléfono" />
          </div>
          <div className="right-side">
            <textarea name="mensaje" placeholder="Mensaje" required></textarea>
            <button type="submit">ENVIAR AHORA!</button>
          </div>
        </form>
      </div>

      <div className="map-container" style={{ marginTop: '40px' }}>
        <iframe
          src="https://www.google.com/maps/d/u/0/embed?mid=1CA6Vefn21NDCXVrFj6tmXgZwZalAGo8&ehbc=2E312F"
          width="100%"
          height="500"
          style={{ border: 0, borderRadius: "12px" }}
          allowFullScreen=""
          loading="lazy"
        ></iframe>
      </div>
      <ChatBotWidget /> 
      <Footer />
    </>
  );
};

export default Contacto;
