import React, { Component } from 'react';
import NavBar from '../components/NavBar';
import Login from './Login';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Select from 'react-select';
import Request from '../Util/Request.js';
import './Login.css';

const options = [{label: 'Funcionário', value: 'employee'}, {label: 'Paciente', value: 'pacient'}]

export default class Register extends Component{
	constructor(props){
		super(props)
		this.state = {
			name: '',
			user: '',
			password: '',
			type: ''
		}
	}

	onNameChange = (e) => {
		this.setState({name: e.target.value})
	}

	onUserChange = (e) => {
		this.setState({user: e.target.value})
	}

	onPasswordChange = (e) => {
		this.setState({password: e.target.value})
	}

	onSubmit = () => {
		let data = `name=${this.state.name}&user=${this.state.user}&password=${this.state.password}&type=${this.state.type.value}`
		Request('adicionarUsuario', data, () => {
			console.log('Success')
		}, () => {
			console.log('Failed')
		})
	}

	handleSelectChange = (op) => {
		this.setState({type: op})
	}

	render() {
		const items = [{name: 'Perfil', page: '/profile'}, {name: 'Agendamento', page:'/scheduler'}, {name: 'Consultas', page:'/consults'}]
		return (
			<div>
				<NavBar items={items}/>
				<form>
					<h1>Cadastramento</h1>
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
					<Select
						id="type"
						label="Tipo"
						className="select"
						value={this.state.type}
						onChange={this.handleSelectChange}
						options={options}
					/>
				    <Button 
				    	variant="contained"
				    	color="primary"
				    	className="submit"
				    	onClick={this.onSubmit}
				    >
				    	Cadastrar
				    </Button>
			    </form>
			</div>
		)
	}
}