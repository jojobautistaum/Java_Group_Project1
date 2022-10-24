import { useState } from "react";
import "./Style.css";

function TShirtForm({ tShirt: initialTShirt, notify }) {
  const [tShirt, setTShirt] = useState(initialTShirt);
  const isAdd = initialTShirt.tShirtId === 0;

  function handleChange(evt) {
    const clone = { ...tShirt };
    clone[evt.target.name] = evt.target.value;
    setTShirt(clone);
  }

  function handleSubmit(evt) {
    evt.preventDefault();

    const url = `http://localhost:8080/t-shirt`;
    const method = isAdd ? "POST" : "PUT";
    const expectedStatus = isAdd ? 201 : 204;

    const init = {
      method,
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(tShirt),
    };

    fetch(url, init)
      .then((response) => {
        if (response.status === expectedStatus) {
          if (isAdd) {
            return response.json();
          } else {
            return tShirt;
          }
        }
        return Promise.reject(
          `Didn't receive expected status: ${expectedStatus}`
        );
      })
      .then((result) =>
        notify({
          action: isAdd ? "add" : "edit",
          tShirt: result,
        })
      )
      .catch((error) => notify({ error: error }));
  }

  return (
    <>
      <h1>{isAdd ? "add" : "edit"} TShirt</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="size">Size</label>
          <select name="size" value={tShirt.size} onChange={handleChange}>
            <option value="">Select Size</option>
            <option value="XS">XS</option>
            <option value="S">S</option>
            <option value="M">M</option>
            <option value="L">L</option>
            <option value="XL">XL</option>
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="color">Color</label>
          <select name="color" value={tShirt.color} onChange={handleChange}>
            <option value="">Select Color</option>
            <option value="red">Red</option>
            <option value="yellow">Yellow</option>
            <option value="green">Green</option>
            <option value="blue">Blue</option>
            <option value="purple">Purple</option>
            <option value="black">Black</option>
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="description">Description</label>
          <input
            type="text"
            id="description"
            name="description"
            className="form-control"
            value={tShirt.description}
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
            value={tShirt.price}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="quantity">Quantity</label>
          <input
            type="text"
            id="quantity"
            name="quantity"
            className="form-control"
            value={tShirt.quantity}
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

export default TShirtForm;
