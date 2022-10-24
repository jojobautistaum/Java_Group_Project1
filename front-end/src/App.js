import React from "react";
import Console from "./components/pages/Console";
import TShirt from "./components/pages/TShirt";
import Game from "./components/pages/Game";
import Invoice from "./components/pages/Invoice";
import NavTabs from "./components/NavTabs";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Footer from "./components/Footer";

const App = () => {
  return (
    <BrowserRouter>
      <NavTabs />
      <div id="mainPart">
        <Routes>
          <Route path="/" element={<Game />} />
          <Route path="/console" element={<Console />} />
          <Route path="/tShirt" element={<TShirt />} />
          <Route path="/invoice" element={<Invoice />} />
        </Routes>
      </div>
      <Footer />
    </BrowserRouter>
  );
};

export default App;
