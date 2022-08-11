import './styles.scss';
import Navbar from "./Navbar";

import { Switch } from 'react-router-dom';
import Users from './Users';
import PrivateRoute from 'components/PrivateRoute';

const Admin = () => {
    return (
        <div className="admin-container">
            <Navbar />
            <div className="admin-content">
                <Switch>
                    <PrivateRoute path="/posts">
                        <h1>Categories CRUD</h1>
                    </PrivateRoute>
                    <PrivateRoute path="/admin/users" roles={['ROLE_ADMIN']}>
                        <Users />
                    </PrivateRoute>
                </Switch>
            </div>
        </div>
    );
}
export default Admin;