import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import { BrowserRouter, Link } from 'react-router-dom';
import './NavBar.css';

export default class NavBar extends Component {
	constructor(props){
		super(props)
	}

	handleClick = (page) => {
		window.location.replace("http://localhost:3000" + page);
	}

	logout = () => {
		window.sessionStorage.setItem("logged", false)
		window.location.replace("http://localhost:3000/login")
	}

	render(){
		return (
			<div>
				<AppBar position="static" className="appBar">
					<Toolbar>
						<Typography variant="h6" color="inherit">
						</Typography>
					</Toolbar>
				</AppBar>
				<Drawer
			        variant="permanent"
			    >
			    <div/>
			    	<List>
			        	{this.props.items && this.props.items.map((item, index) => (
				            <ListItem button key={item.name}>
				            	<ListItemText primary={item.name} onClick={() => this.handleClick(item.page)}/>
				            </ListItem>
			          	))}
			          	{window.sessionStorage.getItem("logged") && <ListItem button>
			            	<ListItemText primary="Logout" onClick={this.logout}/>
			            </ListItem>}
			        </List>
			    </Drawer>
			</div>
		)
	}
}