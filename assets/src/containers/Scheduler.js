import React, { Component } from 'react';
import DayPicker from 'react-day-picker';
import Select from 'react-select';
import NavBar from '../components/NavBar.js';
import Request from '../Util/Request.js';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import 'react-day-picker/lib/style.css';
import './Login.css';

const defaultSuggestions = [
	{ label: '10:00' },
	{ label: '10:30' },
	{ label: '11:00' },
	{ label: '11:30' },
	{ label: '12:00' },
	{ label: '12:30' },
	{ label: '13:00' },
	{ label: '13:30' },
	{ label: '14:00' },
	{ label: '14:30' },
	{ label: '15:00' },
	{ label: '15:30' },
	{ label: '16:00' },
	{ label: '16:30' },
	{ label: '17:00' },
	{ label: '17:30' }
].map(suggestion => ({
	value: suggestion.label,
	label: suggestion.label,
}));

export default class Scheduler extends React.Component {
	constructor(props){
		super(props)
		this.state = {
			name: '',
			day: '',
			month: '',
			time: ''
		}
		this.consults = [];
		Request('getAllSchedules', null, (response) => {
			this.consults = response.consults
		}, () => {
			console.log('Failed')
		})
	}

	handleClick = (day, modifies, e) => {
		let options = defaultSuggestions.slice()
		this.consults.forEach((consult) => {
			if(consult.day == day.getDate()) {	
				options = options.filter((option) => {
					return option.value !== consult.time
				})
			}
		})
		this.setState({consults: options})
		this.setState({day: day.getDate()})
		this.setState({month: day.getMonth()})
	}

	onSubmit = () => {
		let data = `name=${this.state.name}&day=${this.state.day}&month=${this.state.month}&time=${this.state.time}`
		Request('setConsult', data, (response) => {
			console.log('Success')
		}, () => {
			console.log('Failted')
		})
	}
	handleTimeChange = (op) => {
		this.setState({time: op.value})
	}
	onNameChange = (e) => {
		this.setState({name: e.target.value})
	}

	render() {
		let items = [{name: 'Perfil', page: '/profile'}, {name: 'Cadastramento', page: '/register'}, {name: 'Consultas', page:'/consults'}]
		return (
			<div>
				<NavBar items={items}/>
				<form>
					<h1>Agendamento</h1>
					<DayPicker onDayClick={this.handleClick}/>
		          	<TextField
		          		id="name"
						label="Nome"
						value={this.state.name}
						onChange={this.onNameChange}
						className="fieldScheduler"
		          	/>
		          	<Select
		            	textFieldProps={{
		              		label: 'Label',
		              		InputLabelProps: {
		                		shrink: true,
		              		},
		           		}}
		           		options={this.state.consults}
		           		onChange={this.handleTimeChange}
		            	placeholder="Selecione um horário"
		          	/>
		          	<Button 
				    	variant="contained"
				    	color="primary"
				    	className="submit"
				    	onClick={this.onSubmit}
				    >
				    	Agendar
				    </Button>
				</form>
			</div>
		)
	}
}