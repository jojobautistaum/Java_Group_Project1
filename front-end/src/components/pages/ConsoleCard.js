function ConsoleRowCard({ console, notify }) {
  function handleDelete() {
    fetch(`http://localhost:8080/console/${console.consoleId}`, {
      method: "DELETE",
    })
      .then(() => notify({ action: "delete", console: console }))
      .catch((error) => notify({ action: "delete", error: error }));
  }

  return (
    <tr key={console.consoleId}>
      <td>{console.model}</td>
      <td>{console.manufacturer}</td>
      <td>{console.memoryAmount}</td>
      <td>{console.processor}</td>
      <td>{console.price}</td>
      <td>{console.quantity}</td>
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
          onClick={() => notify({ action: "edit-form", console: console })}
        >
          Edit
        </button>

        <button
          className="btn btn-success"
          type="button"
            onClick={() => notify({ action: "add-invoice-form", console: console })}
        >
          Invoice
        </button>
      </td>
    </tr>
  );
}

export default ConsoleRowCard;
