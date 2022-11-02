import './styles.css';

import { useContext, useEffect, useState } from 'react';
import history from 'util/history';
import { AuthContext } from 'AuthContext';
import { isAuthenticated } from 'util/auth';
import { getAuthData, removeAuthData } from 'util/storage';
import { getTokenData } from 'util/token';
import { Link } from 'react-router-dom';
import { BASE_URL } from 'util/request';

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
    const [listNotify, setListNotify] = useState<Notification[]>([]);
    useEffect(() => {
        let url = BASE_URL + "/notification/stream/" + getAuthData().userId;
        const sse2 = new EventSource(url);
        sse2.addEventListener("sse-notify", (event) => {
            // console.log(event.data);
            const data = JSON.parse(event.data);
            // console.log(data);
            setListNotify(data);
            // console.log(listNotify);
            // const numberId = getAuthData().userId
            // setUserActiveId(numberId);
        });

        sse2.onerror = () => {
            sse2.close();
        };
        return () => {
            sse2.close();
        };
    }, [setListNotify]);


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
                        <div className='notification'>
                            <span>Notifications {listNotify?.length}</span>
                        </div>
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