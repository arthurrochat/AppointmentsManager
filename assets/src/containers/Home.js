import React, { Component } from 'react';
import NavBar from '../components/NavBar';
import Login from './Login';
import Profile from './Profile';

function Redirect(state) {
	return state && state.logged === "true" ? <Profile/> : <Login/>;
}
export default class Home extends Component {
	constructor(props){
		super(props)
		this.state = {
			logged: window.sessionStorage.getItem("logged")
		}
	}
	render() {
	    return (
	    	<div>
	      		<Redirect logged={this.state.logged}/>
	    	</div>
	    );
  	}
}
