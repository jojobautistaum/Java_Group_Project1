import React from "react";
import { Link } from "react-router-dom";

function NavTabs() {
  return (
    <header className="container-fluid col-12">
      <nav>
        <ul>
          <li className="nav-item">
            <Link to="/">Game</Link>
          </li>

          <li className="nav-item">
            <Link to="/console">Console</Link>
          </li>

          <li className="nav-item">
            <Link to="/tShirt">Tshirts</Link>
          </li>

          <li className="nav-item">
            <Link to="/invoice">Invoice</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default NavTabs;
