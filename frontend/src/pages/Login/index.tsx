import { Link } from 'react-router-dom';
import './styles.css';
import { useForm } from "react-hook-form";
import { requestBackEndLogin } from 'util/request';
import { useState } from 'react';

type FormData = {
    username: string;
    password: string;
}

const Login = () => {
    const [hasError, setHasError] = useState(false);

    const { register, handleSubmit, formState: { errors } } = useForm<FormData>();
    const onSubmit = (formData: FormData) => {
        requestBackEndLogin(formData)
            .then(response => {
                setHasError(false);
                console.log('SUCESSO', response)
            }).catch(error => {
                setHasError(true)
                console.log('ERROR', error)
            })
        console.log(formData)
    }
    return (
        <div className='login-container'>
            <div className='login-content base-card'>
                <h2 className='login-title'>
                    login
                </h2>
                {hasError &&
                    <div className='alert-danger alert' role="alert">

                        Login error

                    </div>
                }
                <div className='login-form'>
                    <form onSubmit={handleSubmit(onSubmit)} className="login-form">
                        <div className='mb-4'>
                            <input
                                {...register('username', {
                                    required: 'Required username'
                                })}
                                name="username"
                                type="text"
                                className='base-input form-control'
                                placeholder='Username'
                            />

                            <div className='invalid-feedback d-block'>{errors.username?.message}</div>
                        </div>
                        <div className='mb-2'>
                            <input
                                {...register('password', {
                                    required: 'Required password'
                                })}
                                name='password'
                                type="password"
                                className='base-input form-control'
                                placeholder='Password'
                            />

                            <div className='invalid-feedback d-block'>{errors.password?.message}</div>
                        </div>
                        <button className='btn btn-primary login-submit'>Login</button>
                    </form>
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