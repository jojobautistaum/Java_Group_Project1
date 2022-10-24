import React from "react";
import { useState, useEffect } from "react";
import GameForm from "./GameForm.js";
import GameCard from "./GameCard.js";
import InvoiceForm from "./InvoiceForm.js";
import "./Style.css";

function Game() {
  const [games, setGames] = useState([]);
  const [invoice, setInvoice] = useState({});
  const [showForm, setShowForm] = useState(false);
  const [showInvoiceForm, setShowInvoiceForm] = useState(false);
  const [scopedGame, setScopedGame] = useState({});
  const [error, setError] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/game")
      .then((response) => response.json())
      .then((result) => setGames(result))
      .catch(console.log);
  }, []);

  function fetchByTitle(evt) {
    if (evt.target.value === "") {
      setGames([]);
    } else {
      fetch("http://localhost:8080/game/title/" + evt.target.value)
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch(console.log);
    }
  }

  function fetchByEsrbRating(evt) {
    if (evt.target.value === "") {
      setGames([]);
    } else {
      fetch("http://localhost:8080/game/esrbRating/" + evt.target.value)
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch(console.log);
    }
  }

  function fetchByStudio(evt) {
    if (evt.target.value === "") {
      setGames([]);
    } else {
      fetch("http://localhost:8080/game/studio/" + evt.target.value)
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch(console.log);
    }
  }

  function addClick() {
    setScopedGame({
      gameId: 0,
      title: "",
      esrbRating: "",
      description: "",
      price: "",
      studio: "",
      quantity: "",
    });
    setShowForm(true);
  }

  function notify({ action, game, error }) {
    if (error) {
      setError(error);
      setShowForm(false);
      return;
    }

    switch (action) {
      case "add":
        setGames([...games, game]);
        break;
      case "edit":
        setGames(
          games.map((e) => {
            if (e.gameId === game.gameId) {
              return game;
            }
            return e;
          })
        );
        break;
      case "edit-form":
        setScopedGame(game);
        setShowForm(true);
        return;
      case "delete":
        setGames(games.filter((e) => e.gameId !== game.gameId));
        break;
      case "add-invoice-form":
        invoice.itemType = "game";
        invoice.itemId = game.gameId;
        setShowInvoiceForm(true);
        return;
      case "add-invoice":
        setScopedGame(game);
        setInvoice(invoice);
        break;
      default:
        break;
    }

    setError("");
    setShowForm(false);
    setShowInvoiceForm(false);
  }

  if (showForm) {
    return <GameForm game={scopedGame} notify={notify} />;
  }

  if (showInvoiceForm) {
    return <InvoiceForm invoice={invoice} notify={notify} />;
  }

  return (
    <>
      <div id="buttonPanel" className="row mt-2">
        <div>
          <button
            className="col col-md-3 pal btn btn-primary"
            type="button"
            onClick={addClick}
          >
            Add a Game
          </button>
        </div>
        <div>
          <select
            className="col col-md-3 pan"
            name="title"
            onChange={fetchByTitle}
          >
            <option value="">Get Game By Title</option>
            <option value="starArcade">Star Arcad</option>
            <option value="kraken">Kraken</option>
            <option value="superMario">super mario</option>
            <option value="photo">PhotoPhoto</option>
            <option value="starWar">star war</option>
          </select>

          <select
            className="col col-md-3 pan"
            name="esrbRating"
            onChange={fetchByEsrbRating}
          >
            <option value="">Get Game By ESRB Rating</option>
            <option value="everyone">EVERYONE</option>
            <option value="everyone10">EVERYONE 10+</option>
            <option value="teen">TEEN</option>
            <option value="mature">MATURE</option>
            <option value="rp">RATING PENDING</option>
            <option value="adults">ADULTS ONLY</option>
          </select>

          <select
            className="col col-md-3 pan"
            name="studio"
            onChange={fetchByStudio}
          >
            <option value="">Get Game By Studio</option>
            <option value="dreamWorks">Dream works</option>
            <option value="soul">Soul</option>
            <option value="wildcard">Wildcard</option>
            <option value="biggerHammer">bigger Hammer Studio</option>
            <option value="xbox">Xbox</option>
          </select>
        </div>
      </div>
      {error && <div className="alert alert-danger">{error}</div>}
      <div>
        <h1>Games</h1>
        <table id="table-game">
          <thead>
            <tr>
              <th>Title</th>
              <th>ESRB Rating</th>
              <th>Description</th>
              <th>Price</th>
              <th>Studio</th>
              <th>Quantity</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {games.map((r) => (
              <GameCard key={r.gameId} game={r} notify={notify} />
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default Game;
