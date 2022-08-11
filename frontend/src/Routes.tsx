
import Navbar from 'components/Navbar';
import Home from 'pages/Home';
import Login from 'pages/Login';
import Auth from 'pages/Login/Auth';
import Users from 'pages/Users';
import { Router, Route, Switch } from "react-router-dom";
import history from "util/history";

const Routes = () => (

    <Router history={history}>
        <Navbar />
        <Switch>
            <Route exact path="/">
                <Login />
            </Route>
            <Route path="/home">
                <Home />
            </Route>

            <Route path="/login/auth">
                <Auth />
            </Route>
            <Route path="/users">
                <Users />
            </Route>
        </Switch>

    </Router>


);

export default Routes;