import Navbar from 'components/Navbar';
import Home from 'pages/Home';
import Login from 'pages/Login';
import Auth from 'pages/Login/Auth';
import Users from 'pages/Users';
import { Route, Routes } from 'react-router-dom';
import './App.css';
import './assets/styles/custom.scss';


function App() {
  return (
    <div className='App'>
      <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/login/auth" element={<Auth />} />
        <Route path="/users" element={<Users />} />
      </Routes>
    </div>
  );
}

export default App;
