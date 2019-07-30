import React, {Component} from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';
// export default class header extends Component{
//     render(){
// return(
//     <div className="App-header">
//         <ul>
//             <li className="Home-link">
//                 <Link to="/home">Home</Link>
//             </li>
//             <li className="App-link">
//                 <Link to="/about">About</Link>
//             </li>
//             <li className="App-link">
//                 <Link to="/signIn">Sign In</Link>
//             </li>
//         </ul>
//     </div>
//
// );
export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }
    render() {
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            <NavbarToggler onClick={this.toggle}/>
            <Collapse isOpen={this.state.isOpen} navbar>
                <Nav className="ml-auto" navbar>
                    <NavItem>
                        <NavLink href="/visitor">Get Started</NavLink>
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>;
    }
}