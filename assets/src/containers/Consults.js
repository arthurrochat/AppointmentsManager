import React, { Component } from 'react';
import NavBar from '../components/NavBar';
import Login from './Login';
import TextField from '@material-ui/core/TextField';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardHeader from '@material-ui/core/CardHeader';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import Select from 'react-select';
import Request from '../Util/Request.js';
import './Login.css';

export default class Consults extends Component{
	constructor(props){
		super(props)
		this.state = {
			name: '',
			user: '',
			password: '',
			type: '',
			consults: []
		}
		Request('getAllSchedules', null, (response) => {
			this.setState({consults: response.consults})
		}, () => {
			console.log('Failed')
		})
	}

	render() {
		const items = [{name: 'Perfil', page: '/profile'}, {name: 'Agendamento', page:'/scheduler'}, {name: 'Cadastramento', page:'/register'}]
		return (
			<div>
				<NavBar items={items}/>
				<form>
					<h1>Consultas</h1>
					<List>
			        	{this.state.consults.map((consult, index) => (
			        		<Card className="cardList">
			        			<CardHeader title={consult.name}/>
			        			<CardContent>
			        				<Typography>
			        					Dia: {consult.day}
			        				</Typography>
			        				<Typography>
			        					Mes: {consult.month}
			        				</Typography>
			        				<Typography>
			        					Hora: {consult.time}
			        				</Typography>

			        			</CardContent>
			        		</Card>
			          	))}
			        </List>
			    </form>
			</div>
		)
	}
}