import React, { createContext, useContext, useState, useEffect } from 'react';

// Creación del contexto global para la autenticación
const AuthContext = createContext(null);
export const AuthProvider = ({ children }) => {
    // Evalúa si hay un token activo.
    const [isAuthenticated, setIsAuthenticated] = useState(() => {
        return !!localStorage.getItem('token');
    });
    // Recupera el rol guardado en el navegador
    const [userRole, setUserRole] = useState(() => {
        return localStorage.getItem('rol') || null;
    });

    // Escucha cambios en el almacenamiento local para sincronizar el estado de la sesión
    useEffect(() => {
        const handleStorageChange = () => {
            setIsAuthenticated(!!localStorage.getItem('token'));
            setUserRole(localStorage.getItem('rol') || null);
        };
        window.addEventListener('storage', handleStorageChange);
        return () => window.removeEventListener('storage', handleStorageChange);
    }, []);

    // Registra el inicio de sesión
    const login = (token, email, role, nombre, apellido, id) => {
        localStorage.setItem('token', token);
        localStorage.setItem('usuario', email);
        localStorage.setItem('rol', role);

        localStorage.setItem('nombre', nombre);
        localStorage.setItem('apellido', apellido);
        localStorage.setItem('idUsuario', id);

        setIsAuthenticated(true);
        setUserRole(role);
    };

    // Cierra la sesión activa.
    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('usuario');
        localStorage.removeItem('rol');
        setIsAuthenticated(false);
        setUserRole(null);
    };

    // Empaqueta las variables de estado y las funciones para que sean accesibles externamente
    const contextValue = {
        isAuthenticated,
        userRole,
        login,
        logout
    };

    return (
        <AuthContext.Provider value={contextValue}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};