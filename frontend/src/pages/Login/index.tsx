import { Link } from 'react-router-dom';
import './styles.css';

const Login = () => {


    return (
        <div className='login-container'>
            <div className='login-content base-card'>
                <h2 className='login-title'>
                    login
                </h2>
                <div className='login-form'>
                    <form className="login-form">
                        <div className='mb-4'>
                            <input type="text" className='base-input form-control' name='username' placeholder='Username' />

                        </div>
                        <div className='mb-2'>
                            <input type="password" className='base-input form-control' name='password' placeholder='Password' />

                        </div>
                    </form>
                    <button className='btn btn-primary login-submit'>Login</button>
                    <hr />
                    <div className='login-recovery-bottom'>
                        <Link to="#recovery" className="login-link-recover">
                            Forgot password
                        </Link>
                        <div className="signup-container">
                            <span className="not-registered">No account?</span>
                            <Link to="#signup" className="login-link-register">
                                SIGN UP.
                            </Link>
                        </div>
                    </div>
                </div>

            </div>
        </div >
    )
}
export default Login;