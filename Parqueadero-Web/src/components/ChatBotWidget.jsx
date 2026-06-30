import { useState, useRef, useEffect } from "react";

const ChatBotWidget = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");
    const messagesEndRef = useRef(null);

    // Auto-scroll al final del chat cuando hay mensajes nuevos
    useEffect(() => {
        messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [messages]);

    const sendMessage = async () => {
        if (!input.trim()) return;
        
        const userMessage = { sender: "user", text: input };
        setMessages([...messages, userMessage]);
        setInput(""); // Limpiar input inmediatamente

        try {
            const response = await fetch("http://localhost:5000/chat", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ message: userMessage.text })
            });

            const data = await response.json();
            setMessages((prev) => [...prev, { sender: "bot", text: data.reply }]);
        } catch (error) {
            console.error("Error con el chatbot:", error);
            setMessages((prev) => [...prev, { sender: "bot", text: "Ups, hubo un error conectando con el servidor." }]);
        }
    };

    return (
        <>
            {/* BOTÓN FLOTANTE CIRCULAR (Para abrir el chat) */}
            <div 
                style={{
                    position: "fixed",
                    bottom: "25px",
                    right: "25px",
                    width: "70px",
                    height: "70px",
                    backgroundColor: "#2563eb", /* Azul oficial */
                    borderRadius: "50%",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    cursor: "pointer",
                    boxShadow: "0px 6px 20px rgba(0, 0, 0, 0.3)",
                    zIndex: 99999,
                    transition: "transform 0.2s, opacity 0.2s",
                    opacity: isOpen ? 0 : 1, // Desaparece si el chat está abierto
                    transform: isOpen ? 'scale(0)' : 'scale(1)',
                    pointerEvents: isOpen ? 'none' : 'auto'
                }}
                className="hover:scale-110" 
                onClick={() => setIsOpen(true)}
            >
                <img 
                    src="/Bot.png" 
                    alt="Bot Icon" 
                    style={{
                        width: "80%",
                        height: "80%",
                        borderRadius: "50%",
                        objectFit: "cover"
                    }} 
                />
            </div>

            {/* VENTANA DEL CHAT (Estilizada exactamente como la imagen) */}
            {isOpen && (
                <div 
                    style={{
                        position: "fixed",
                        bottom: "25px", /* Mismo nivel que el botón */
                        right: "25px",
                        width: "360px",
                        height: "500px",
                        backgroundColor: "#f7f9fc", /* Fondo muy claro */
                        borderRadius: "16px",
                        boxShadow: "0px 10px 40px rgba(0, 0, 0, 0.2)",
                        display: "flex",
                        flexDirection: "column",
                        zIndex: 100000,
                        overflow: "hidden",
                        fontFamily: "'Inter', 'Segoe UI', Roboto, Helvetica, Arial, sans-serif"
                    }}
                >
                    {/* ENCABEZADO (Blue Header with Icon) */}
                    <div style={{ 
                        backgroundColor: "#2563eb", 
                        color: "white", 
                        padding: "12px 16px", 
                        display: "flex", 
                        alignItems: "center", 
                        justifyContent: "space-between",
                        borderBottom: "1px solid rgba(255,255,255,0.1)"
                    }}>
                        <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
                            {/* Ícono de Bot */}
                            <div style={{
                                width: "35px",
                                height: "35px",
                                backgroundColor: "white",
                                borderRadius: "50%",
                                display: "flex",
                                alignItems: "center",
                                justifyContent: "center"
                            }}>
                                <img src="/Bot.png" alt="Bot" style={{ width: "22px", height: "22px" }} />
                            </div>
                            <span style={{ fontWeight: "600", fontSize: "16px" }}>Parqueadero SC Bot</span>
                        </div>
                        {/* Botón de Cerrar (X) */}
                        <button 
                            onClick={() => setIsOpen(false)} 
                            style={{ 
                                background: "none", 
                                border: "none", 
                                color: "white", 
                                cursor: "pointer", 
                                fontSize: "20px", 
                                padding: "0 5px",
                                opacity: 0.8
                            }}
                            className="hover:opacity-100"
                        >✕</button>
                    </div>

                    {/* CUERPO DE MENSAJES */}
                    <div style={{ 
                        flex: 1, 
                        padding: "20px", 
                        overflowY: "auto", 
                        display: "flex", 
                        flexDirection: "column", 
                        gap: "12px"
                    }}>
                        {messages.length === 0 && (
                            <div style={{ textAlign: "center", marginTop: "40px", color: "#718096" }}>
                                <p style={{ fontSize: "14px", fontStyle: "italic" }}>
                                    ¡Hola! Soy tu asistente. Pregúntame sobre tarifas o disponibilidad.
                                </p>
                            </div>
                        )}
                        
                        {messages.map((msg, i) => (
                            <div key={i} style={{ display: "flex", flexDirection: "column" }}>
                                {/* Label "You" o "Bot" */}
                                <span style={{
                                    fontSize: "11px",
                                    fontWeight: "600",
                                    color: "#a0aec0",
                                    marginBottom: "3px",
                                    alignSelf: msg.sender === "user" ? "flex-end" : "flex-start",
                                    marginRight: msg.sender === "user" ? "8px" : "0",
                                    marginLeft: msg.sender === "bot" ? "8px" : "0"
                                }}>
                                    {msg.sender === "user" ? "You" : "Parqueadero Bot"}
                                </span>
                                
                                {/* Burbuja de Mensaje */}
                                <div 
                                    style={{
                                        maxWidth: "80%",
                                        padding: "10px 14px",
                                        fontSize: "14px",
                                        lineHeight: "1.4",
                                        borderRadius: "16px",
                                        boxShadow: "0px 1px 3px rgba(0,0,0,0.1)",
                                        wordBreak: "break-word",
                                        
                                        // Estilos condicionales según remitente
                                        alignSelf: msg.sender === "user" ? "flex-end" : "flex-start",
                                        backgroundColor: msg.sender === "user" ? "#2563eb" : "#e2e8f0", /* Azul vs Gris */
                                        color: msg.sender === "user" ? "white" : "#1a202c", /* Texto blanco vs Negro */
                                        
                                        // Bordes redondeados específicos
                                        borderTopRightRadius: msg.sender === "user" ? "4px" : "16px",
                                        borderTopLeftRadius: msg.sender === "bot" ? "4px" : "16px",
                                    }}
                                >
                                    {msg.text}
                                </div>
                            </div>
                        ))}
                        <div ref={messagesEndRef} />
                    </div>

                    {/* CAJA DE ENTRADA (Estilizada exactamente como la imagen) */}
                    <div style={{ 
                        padding: "12px", 
                        backgroundColor: "white", 
                        borderTop: "1px solid #e2e8f0",
                        display: "flex", 
                        alignItems: "center",
                        gap: "10px"
                    }}>
                        {/* Input de Texto */}
                        <div style={{ flex: 1, position: "relative" }}>
                            <input
                                type="text"
                                value={input}
                                onChange={(e) => setInput(e.target.value)}
                                onKeyDown={(e) => e.key === "Enter" && sendMessage()}
                                placeholder="Escribe tu mensaje..."
                                style={{ 
                                    width: "100%",
                                    padding: "12px 16px", 
                                    borderRadius: "24px", 
                                    border: "1px solid #cbd5e1", 
                                    backgroundColor: "#f1f5f9",
                                    fontSize: "14px",
                                    outline: "none",
                                    transition: "border-color 0.2s"
                                }}
                                className="focus:border-blue-400 focus:bg-white"
                            />
                        </div>

                        {/* BOTÓN DE ENVIAR (Circular Azul ➤) */}
                        <button
                            onClick={sendMessage}
                            style={{ 
                                width: "48px",
                                height: "48px",
                                backgroundColor: "#2563eb", 
                                color: "white", 
                                border: "none", 
                                borderRadius: "50%", 
                                cursor: "pointer", 
                                display: "flex",
                                alignItems: "center",
                                justifyContent: "center",
                                fontSize: "18px",
                                boxShadow: "0px 3px 6px rgba(37, 99, 235, 0.3)",
                                transition: "background-color 0.2s"
                            }}
                            className="hover:bg-blue-700"
                        >
                            <span style={{ transform: "translateX(1px)" }}>➤</span>
                        </button>
                    </div>
                </div>
            )}
        </>
    );
};

export default ChatBotWidget;