import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { enrutador } from './routes/enrutador';
import { AuthProvider } from './context/AuthContext';
import './index.css';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AuthProvider> {/* <--- ¡ENVUELVE TODO AQUÍ! */}
      <RouterProvider router={createBrowserRouter(enrutador)} />
    </AuthProvider>
  </StrictMode>
);