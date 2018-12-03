import React from 'react'
import Login from './containers/Login.js';
import Home from './containers/Home';
import Scheduler from './containers/Scheduler.js';
import Profile from './containers/Profile.js'
import Register from './containers/Register.js'
import Consults from './containers/Consults.js'
import { BrowserRouter, Switch, Route } from 'react-router-dom';

export default () => (
	<BrowserRouter>
		<Switch>
			<Route exact path='/' component={Home}/>
			<Route path='/login' component={Login}/>
			<Route path='/scheduler' component={Scheduler}/>
			<Route path='/profile' component={Profile}/>
			<Route path='/register' component={Register}/>
			<Route path='/consults' component={Consults}/>
		</Switch>
	</BrowserRouter>
)