import Navbar from 'components/Navbar';
import Home from 'pages/Home';
import Login from 'pages/Login';
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
      </Routes>
    </div>
  );
}

export default App;
