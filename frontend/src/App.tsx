import './assets/styles/custom.scss';
import './App.css';
import { useState } from 'react';
import Routes from 'Routes';
import { AuthContext, AuthcontextData } from 'AuthContext';

function App() {
  const [authContextData, setAuthContextData] = useState<AuthcontextData>({
    authenticated: false
  });
  return (
    <AuthContext.Provider value={{ authContextData, setAuthContextData }}>
      <Routes />
    </AuthContext.Provider>
  );
}

export default App;
