function GameRowCard({ game, notify }) {
  function handleDelete() {
    fetch(`http://localhost:8080/game/${game.gameId}`, {
      method: "DELETE",
    })
      .then(() => notify({ action: "delete", game: game }))
      .catch((error) => notify({ action: "delete", error: error }));
  }

  return (
    <tr key={game.gameId}>
      <td>{game.title}</td>
      <td>{game.esrbRating}</td>
      <td>{game.description}</td>
      <td>{game.price}</td>
      <td>{game.studio}</td>
      <td>{game.quantity}</td>
      <td>
        <button
          id="deleteButton"
          className="btn btn-danger mr-3"
          type="button"
          onClick={handleDelete}
        >
          Delete
        </button>

        <button
          className="btn btn-secondary"
          type="button"
          onClick={() => notify({ action: "edit-form", game: game })}
        >
          Edit
        </button>

        <button
          className="btn btn-success"
          type="button"
            onClick={() => notify({ action: "add-invoice-form", game: game })}
        >
          Invoice
        </button>
      </td>
    </tr>
  );
}

export default GameRowCard;
