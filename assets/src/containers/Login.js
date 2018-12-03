import React, { Component } from 'react';
import './Login.css';
import PropTypes from 'prop-types';
import NavBar from '../components/NavBar';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogActions from '@material-ui/core/DialogActions';
import Request from '../Util/Request.js';

export default class Login extends Component {
	constructor(props) {
		super(props)
		this.handleChange = this.handleChange.bind(this)
		this.state = {
			user: '',
			password: '',
			open: false
		}
	}

	handleChange = (prop, value) => {
		this.setState({ [prop]: value })
	}
	onUserChange = (e) => {
		this.handleChange('user', e.target.value)
	}
	onPasswordChange = (e) => {
		this.handleChange('password', e.target.value)
	}
	onClose = () => {
		this.handleChange('open', false)
	}
	onSubmit = () => {
		let data = `user=${this.state.user}&password=${this.state.password}`

		Request('login', data, (response) => {
			window.sessionStorage.setItem("logged", true)
			window.sessionStorage.setItem("user", this.state.user)
			window.location.replace("http://localhost:3000/");
		}, () => {
			this.setState({open: true})
		})
	}

	render() {
		return (
			<div>
				<NavBar title="Login"/>
				<form>
					<h1>Login</h1>
					<TextField
						id="user"
						label="Usuário"
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
				    	onClick={this.onSubmit}
				    >
				    	Entrar
				    </Button>
			    </form>
			    <Dialog
	          		open={this.state.open}
	         		onClose={this.onClose}
	          		aria-labelledby="alert-dialog-title"
	          		aria-describedby="alert-dialog-description"
		        >
	          		<DialogContent>
	            		<DialogContentText id="alert-dialog-description">
		              		Senha inválida
		            	</DialogContentText>
		          	</DialogContent>
		          	<DialogActions>
		            	<Button onClick={this.onClose} color="primary">
		              		Fechar
		            	</Button>
		          	</DialogActions>
		        </Dialog>
			</div>
		)
	}
}