import React, { Component } from 'react';
import NavBar from '../components/NavBar';
import Login from './Login';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Request from '../Util/Request.js';
import './Login.css';

function getUserData(profile) {
	let data = `user=${profile.state.user}`
	Request('getUser', data, (response) => {
		profile.setState({name: response.nome})
		profile.setState({password: response.senha})
	}, (response) => {
		console.log('failed')
	})
}

export default class Profile extends Component {
	constructor(props) {
		super(props)
		this.state = {
			user: window.sessionStorage.getItem("user"),
			name: '',
			password: ''
		}
		getUserData(this)
	}

	handleChange = (prop, value) => {
		this.setState({ [prop]: value })
	}
	onNameChange = (e) => {
		this.handleChange('name', e.target.value)
	}
	onPasswordChange = (e) => {
		this.handleChange('password', e.target.value)
	}
	onUserChange = (e) => {
		this.handleChange('user', e.target.value)
	}
	onSave = () => {
		let data = `user=${this.state.user}&name=${this.state.name}&password=${this.state.password}`
		Request('updateUser', data, (response) => {
			console.log('Success')
		}, (response) => {
			console.log('Fail')
		})
	}

	render() {
		const items = [{name: 'Agendamento', page:'/scheduler'}, {name: 'Cadastramento', page: '/register'}, {name: 'Consultas', page:'/consults'}]
		return (
			<div>
				<NavBar title="Profile" items={items}/>
				<form>
					<h1>Profile</h1>
					<TextField
						id="name"
						label="Nome"
						value={this.state.name}
						onChange={this.onNameChange}
					/>
					<TextField
						id="user"
						label="Usuario"
						value={this.state.user}
						onChange={this.onUserChange}
					/>
					<TextField
						id="password"
						label="Senha"
						type="password"
						value={this.state.password}
						onChange={this.onPasswordChange}
					/>
				    <Button 
				    	variant="contained"
				    	color="primary"
				    	className="submit"
				    	onClick={this.onSave}
				    >
				    	Alterar
				    </Button>
			    </form>
			</div>
		)
	}
}