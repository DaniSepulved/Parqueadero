from google import genai
from google.genai import types
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import requests
import uvicorn

client = genai.Client(api_key="AQ.Ab8RN6KBD_JRiAyxYSwB2vnvLE6NjcH3ytD4OLTK2CuMMk-_9g")

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],  
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class ChatRequest(BaseModel):
    message: str
    lat: float | None = None
    lon: float | None = None

SYSTEM_INSTRUCTION = """
Eres "ParqueaderoSC Bot", un asistente virtual amigable y eficiente para nuestro sistema de gestión de parqueaderos. 
Tu objetivo es ayudar a los usuarios a resolver dudas sobre tarifas, horarios, ubicaciones y disponibilidad.
Habla de manera concisa y servicial. Si te preguntan algo que no tiene que ver con parqueaderos, 
responde amablemente que solo estás capacitado para ayudar con temas del parqueadero.
"""

@app.post("/chat")
def chat(req: ChatRequest):
    msg = req.message.lower()
    contexto_adicional = ""

    # Agregar información de tarifas si el mensaje contiene palabras clave relacionadas
    if "tarifa" in msg or "precio" in msg or "costo" in msg:
        try:
            tarifa_data = requests.get("http://localhost:8080/api/tarifa").json()
            contexto_adicional += f"\n[INFO SISTEMA] Las tarifas actuales obtenidas de la base de datos son: {tarifa_data}. Usa estos datos reales para responderle al usuario."
        except Exception:
            contexto_adicional += "\n[INFO SISTEMA] Hubo un error al consultar las tarifas. Informa al usuario que intente más tarde."

    if "horario" in msg or "contacto" in msg or "información" in msg:
        contexto_adicional += "\n[INFO SISTEMA] El horario de atención es de lunes a viernes de 8 AM a 6 PM, y sábados y domingos de 8 AM a 10 PM. El teléfono de contacto es 313-762-0698."

    if "cercano" in msg or "ubicación" in msg or "lugar" in msg or "carrera" in msg or "calle" in msg:
        if "carrera" in msg or "mi ubicacion" in msg:
            direccion = msg.split("carrera")[-1].strip() if "carrera" in msg else msg.split("mi ubicacion")[-1].strip()
            link = f"https://www.google.com/maps/search/parqueaderos+cerca+de+{direccion.replace(' ', '+')}"
            contexto_adicional += f"\n[INFO SISTEMA] El usuario está buscando cerca de '{direccion}'. Muéstrale este enlace para ver opciones: {link}"
        elif req.lat and req.lon:
            contexto_adicional += f"\n[INFO SISTEMA] Coordenadas del usuario: Latitud {req.lat}, Longitud {req.lon}."
        else:
            contexto_adicional += "\n[INFO SISTEMA] El usuario quiere saber de ubicaciones pero no dio datos. Pídele la dirección."

    prompt_final = req.message
    if contexto_adicional:
        prompt_final = f"{contexto_adicional}\n\nPregunta del usuario: {req.message}"

    response = client.models.generate_content(
        model='gemini-2.5-flash',
        contents=prompt_final,
        config=types.GenerateContentConfig(
            system_instruction=SYSTEM_INSTRUCTION
        )
    )
    
    return {"reply": response.text}

if __name__ == "__main__":
    uvicorn.run("ChatBot:app", host="0.0.0.0", port=5000, reload=True)

# uvicorn ChatBot:app --reload --port 5000