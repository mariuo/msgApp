import Cardbase from 'components/Cardbase';
import './styles.css';

const Login = () => {
    return (
        <div className='login-container base-card'>
            <h2>
                Login
            </h2>
            <div className='login-form'>
                <form className="login-form">
                    <input type="text" className='base-input' />
                    <input type="password" className='base-input' />
                </form>
                <button className='btn btn-primary'>Login</button>
            </div>
        </div >
    )
}
export default Login;