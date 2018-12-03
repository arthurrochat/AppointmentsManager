import React, { Component } from 'react';
import Login from './containers/Login.js';
import Router from './Router.js'
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Router/>
      </div>
    );
  }
}

export default App;
