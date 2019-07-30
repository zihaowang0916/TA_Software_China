import React, {Component} from 'react';
//import logo from './idlelogo.png';
import './App.css';
import Home from './home';
import Login from './Login';
import { BrowserRouter as Router, Route} from "react-router-dom";

class App extends Component {
    render() {
        return (
            <Router>
                <Route path="/" exact={true} component={Home}/>
                <Route path="/Login" component={Login}/>
            </Router>
        )
    }
}

export default App;

/*function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit the <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}*/
