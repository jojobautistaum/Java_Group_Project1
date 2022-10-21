import { useState } from "react";
import "./Style.css";

function GameForm({ game: initialGame, notify }) {
  const [game, setGame] = useState(initialGame);
  const isAdd = initialGame.gameId === 0;

  function handleChange(evt) {
    const clone = { ...game };
    clone[evt.target.name] = evt.target.value;
    setGame(clone);
  }

  function handleSubmit(evt) {
    evt.preventDefault();

    const url = `http://localhost:8080/game`;
    const method = isAdd ? "POST" : "PUT";
    const expectedStatus = isAdd ? 201 : 204;

    const init = {
      method,
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(game),
    };

    fetch(url, init)
      .then((response) => {
        if (response.status === expectedStatus) {
          if (isAdd) {
            return response.json();
          } else {
            return game;
          }
        }
        return Promise.reject(
          `Didn't receive expected status: ${expectedStatus}`
        );
      })
      .then((result) =>
        notify({
          action: isAdd ? "add" : "edit",
          game: result,
        })
      )
      .catch((error) => notify({ error: error }));
  }

  return (
    <>
      <h1>{isAdd ? "Add" : "Edit"} Game</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="title">Title</label>
          <select name="title" value={game.title} onChange={handleChange}>
            <option value="starArcade">Star Arcad</option>
            <option value="kraken">Kraken</option>
            <option value="superMario">super mario</option>
            <option value="photo">PhotoPhoto</option>
            <option value="starWar">star war</option>
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="studio">ESRB Rating</label>
          <select name="studio" value={game.studio} onChange={handleChange}>
            <option value="everyone">EVERYONE</option>
            <option value="everyone10">EVERYONE 10+</option>
            <option value="teen">TEEN</option>
            <option value="mature">MATURE</option>
            <option value="rp">RATING PENDING</option>
            <option value="adults">ADULTS ONLY</option>
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="description">Description</label>
          <input
            type="text"
            id="description"
            name="description"
            className="form-control"
            value={game.description}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="price">Price</label>
          <input
            type="text"
            id="price"
            name="price"
            className="form-control"
            value={game.price}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="studio">Studio</label>
          <select name="studio" value={game.studio} onChange={handleChange}>
            <option value="dreamWorks">Dream works</option>
            <option value="soul">Soul</option>
            <option value="wildcard">Wildcard</option>
            <option value="biggerHammer">bigger Hammer Studio</option>
            <option value="xbox">Xbox</option>
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="quantity">quantity</label>
          <input
            type="text"
            id="quantity"
            name="quantity"
            className="form-control"
            value={game.quantity}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <button
            id="saveButton"
            className="btn btn-primary mr-3"
            type="submit"
          >
            Save
          </button>
          <button
            className="btn btn-secondary"
            type="button"
            onClick={() => notify({ action: "cancel" })}
          >
            Cancel
          </button>
        </div>
      </form>
    </>
  );
}

export default GameForm;
