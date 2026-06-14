import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';
import { alertaError, alertaRedireccion, generaToken } from '../helpers/funciones';
import { useAuth } from '../context/AuthContext'; 

// URLs de las APIs para autenticación y registro
let apiAuth = "http://localhost:8080/api/auth/login";
let apiRegistro = "http://localhost:8080/api/usuarios";
// let apiAuth = "https://proyecto-sena-9lzp.onrender.com/api/auth/login";
// let apiRegistro = "https://proyecto-sena-9lzp.onrender.com/api/usuarios";

// Estados locales para controlar los campos reactivos de los formularios
function Login() {
    const [getNombre, setNombre] = useState("");
    const [getPassword, setPassword] = useState("");
    const [getApellido, setApellido] = useState("");
    const [getEmail, setEmail] = useState("");
    const [usuarios, setUsuarios] = useState([]); 

    const navigate = useNavigate();
    const { login: authLogin } = useAuth(); 

    // Verificar si el usuario ya tiene un token válido al cargar el componente
    function iniciarSesion() {
        fetch(apiAuth, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                email: getEmail,
                password: getPassword,
            }),
        })
        .then((res) => {
            if (!res.ok) {
                return res.json().then(err => { throw new Error(err.message || "Credenciales inválidas"); });
            }
            return res.json();
        })
        .then((data) => {
            // Llama a la función 'login' del contexto.
            console.log("Datos del backend:", data);
            authLogin(data.token, data.email, data.role, data.nombre, data.apellido, data.id);
            alertaRedireccion("Bienvenido " + data.email, "/home", navigate);
        })
        .catch((err) => {
            alertaError("Error", err.message || "Email o contraseña incorrectos", "error");
        });
    }

    // Función para registrar un nuevo usuario.
    function registrarUsuario() {
        fetch(apiRegistro, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                nombre: getNombre,
                apellido: getApellido,
                email: getEmail,
                password: getPassword,
            }),
        })
        .then(res => {
            if (!res.ok) {
                return res.json().then(err => { throw new Error(err.message || "Error al registrar usuario"); });
            }
            return res.json();
        })
        .then(() => {
            console.log("Usuario registrado");
            // Limpiar campos después del registro exitoso
            setNombre('');
            setApellido('');
            setEmail('');
            setPassword('');
            // Redirigir al usuario al login para que inicie sesión con su nueva cuenta
            alertaRedireccion("Registro exitoso", "/home", navigate);
        })
        .catch(err => {
            alertaError("Error", err.message || "Error al registrar usuario", "error");
        });
    }

    return (
        <div className="container">
            <input id="signup_toggle" type="checkbox" />
            <form className="form">
                <div className="form_front">
                    <div className="form_details">Login</div>
                    <input 
                        onChange={(e) => setEmail(e.target.value)} 
                        type="text" 
                        className="input" 
                        placeholder="Email" 
                        value={getEmail}
                    />
                    <input 
                        onChange={(e) => setPassword(e.target.value)} 
                        type="password" 
                        className="input" 
                        placeholder="Password" 
                        value={getPassword} 
                    />
                    <button type='button' onClick={iniciarSesion} className="btn">Login</button>
                    <span className="switch">Don't have an account?
                        <label htmlFor="signup_toggle" className="signup_tog">
                            Sign Up
                        </label>
                    </span>
                </div>
                <div className="form_back">
                    <div className="form_details">SignUp</div>
                    <input 
                        onChange={(e) => setNombre(e.target.value)} 
                        type="text" 
                        className="input" 
                        placeholder="Nombre" 
                        value={getNombre} 
                    />
                    <input 
                        onChange={(e) => setApellido(e.target.value)} 
                        type="text" 
                        className="input" 
                        placeholder="Apellido" 
                        value={getApellido} 
                    />
                    <input 
                        onChange={(e) => setEmail(e.target.value)} 
                        type="text" 
                        className="input" 
                        placeholder="Email" 
                        value={getEmail} 
                    />
                    <input 
                        onChange={(e) => setPassword(e.target.value)} 
                        type="password" 
                        className="input" 
                        placeholder="Password" 
                        value={getPassword} 
                    />
                    <button type="button" onClick={registrarUsuario} className="btn">Signup</button>
                    <span className="switch">Already have an account?
                        <label htmlFor="signup_toggle" className="signup_tog">
                            Sign In
                        </label>
                    </span>
                </div>
            </form>
        </div>
    );
}

export default Login;