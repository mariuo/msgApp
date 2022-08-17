
import Navbar from 'components/Navbar';
import Login from 'pages/Login';
import Auth from 'pages/Login/Auth';
import Users from 'pages/Login/Admin/Users';
import { Router, Route, Switch, Redirect } from "react-router-dom";
import history from "util/history";
import Home from 'pages/Home';
import ListsPosts from 'pages/Login/ListPosts';

const Routes = () => (

    <Router history={history}>
        <Navbar />
        <Switch>
            <Route exact path="/">
                <Home />
            </Route>
            <Route exact path="/home">
                <Home />
            </Route>
            <Route exact path="/login">
                <Login />
            </Route>
            <Route exact path="/login/posts">
                <ListsPosts />
            </Route>

            <Route path="/login/auth">
                <Auth />
            </Route>
            <Redirect from="/admin" to="/admin/users" exact />
            <Route path="/admin/users">
                <Users />
            </Route>
        </Switch>

    </Router>


);

export default Routes;