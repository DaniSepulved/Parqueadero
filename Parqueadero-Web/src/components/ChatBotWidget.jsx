import { useState } from "react";

// Componente funcional principal llamado ChatBotWidget
const ChatBotWidget = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");

// Función asíncrona encargada de gestionar el envío de mensajes al servidor
    const sendMessage = async () => {
        if(!input.trim()) return;
        setMessages([...messages, { sender: "user", text: input }]);

        try {
            const response = await fetch("http://localhost:5000/chat", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ message: input })
            });

            const data = await response.json();
            setMessages((prev) => [...prev, { sender: "bot", text: data.reply }]);
        } catch (error) {
            console.error("Error con el chatbot:", error);
        }
        setInput("");
    };

// Estructura visual del componente
    return (
        <>
            <div 
            className="fixed bottom-0 right-0 m-5 w-20 h-20 bg-blue-600 rounded-full flex items-center justify-center cursor-pointer shadow-lg transition-transform hover:scale-110 z-[9999]" 
            onClick={() => setIsOpen(!isOpen)}
            >
            <img src="/Bot.png" alt="Bot" className="w-16 h-16 rounded-full object-cover" />
            </div>
            {isOpen && (
            <div className="fixed bottom-20 right-5 w-80 h-96 bg-white shadow-2xl rounded-lg flex flex-col z-[9999]">
                <div className="p-2 bg-blue-600 text-white font-bold rounded-t-lg">
                ChatBot Parqueadero
                </div>
                <div className="flex-1 p-2 overflow-y-auto">
                {messages.map((msg, i) =>(
                    <div 
                    key={i}
                    className={`my-1 p-2 rounded-lg max-w-[80%] ${
                        msg.sender === "user"
                        ? "bg-blue-100 self-end ml-auto"
                        : "bg-gray-200 self-start mr-auto"
                    }`}
                    >
                    {msg.text}
                    </div>
                ))}
                </div>
                <div className="p-2 flex gap-2">
                <input
                    className="flex-1 border p-2 rounded"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    onKeyDown={(e) => e.key === "Enter" && sendMessage()}
                    placeholder="Escribe un mensaje..."
                />
                <button
                    className="bg-blue-600 text-white px-3 rounded"
                    onClick={sendMessage}
                >
                    ➤
                </button>
                </div>
            </div>
            )}
        </>
    );
};

export default ChatBotWidget;