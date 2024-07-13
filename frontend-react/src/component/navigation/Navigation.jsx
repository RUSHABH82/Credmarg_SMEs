import {Link, Outlet} from "react-router-dom";
import styled from "styled-components";

const Nav = styled.nav`
    margin-bottom: 20px;
`;

const NavList = styled.ul`
    display: flex;
    justify-content: center;
    list-style: none;
    padding: 0;
`;

const NavItem = styled.li`
    margin: 0 10px;
`;

const NavLink = styled(Link)`
    text-decoration: none;
    color: #007bff;
    font-weight: bold;

    &:hover {
        text-decoration: underline;
        color: #0056b3;
    }
`;
export const Navigation = () => {

    return (<>
        <Nav>
            <NavList>
                <NavItem><NavLink to="/">Mailbox</NavLink></NavItem>
                <NavItem><NavLink to="/employees">Employees</NavLink></NavItem>
                <NavItem><NavLink to="/vendors">Vendors</NavLink></NavItem>
            </NavList>
        </Nav>


        <Outlet/>
    </>);
};
