import './styles.css';

import { useContext, useEffect } from 'react';
import history from 'util/history';
import { AuthContext } from 'AuthContext';
import { isAuthenticated } from 'util/auth';
import { removeAuthData } from 'util/storage';
import { getTokenData } from 'util/token';
import { Link } from 'react-router-dom';

const Navbar = () => {
    const { authContextData, setAuthContextData } = useContext(AuthContext);


    useEffect(() => {
        if (isAuthenticated()) {
            setAuthContextData({
                authenticated: true,
                tokenData: getTokenData(),
            });

        } else {
            setAuthContextData({
                authenticated: false
            });
        }
    }, [setAuthContextData]);

    const handleLogoutClick = (event: React.MouseEvent<HTMLAnchorElement>) => {
        event.preventDefault();
        removeAuthData();
        setAuthContextData({
            authenticated: false,
        });
        history.replace('/');

    }


    return (
        <nav className='navbar navbar-expand navbar-dark bg-primary main-nav'>
            <div className='container-fluid'>
                <Link to="/" className="nav-logo-text">
                    <h4>msgApp</h4>
                </Link>
            </div>

            <div className="nav-login-logout">
                {authContextData.authenticated ? (
                    <>
                        <span className="nav-username">{authContextData.tokenData?.user_name}</span>
                        <a href="#logout" onClick={handleLogoutClick}>
                            LOGOUT
                        </a>
                    </>
                ) : (
                    <Link to="/login">
                        LOGIN
                    </Link>
                )}
            </div>
        </nav>
    )
}
export default Navbar;