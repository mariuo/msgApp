import Navbar from 'components/Navbar';
import Home from 'pages/Home';
import { Route, Routes } from 'react-router-dom';
import './App.css';
import './assets/styles/custom.scss';


function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<Home />} />
      </Routes>
    </>
  );
}

export default App;
