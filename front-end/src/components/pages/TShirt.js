import React from "react";
import { useState, useEffect } from "react";
import TShirtForm from "./TShirtForm.js";
import TShirtCard from "./TShirtCard.js";
import InvoiceForm from "./InvoiceForm.js";
import "./Style.css";

function TShirt() {
  const [tShirts, setTShirts] = useState([]);
  const [invoice, setInvoice] = useState({});
  const [showForm, setShowForm] = useState(false);
  const [showInvoiceForm, setShowInvoiceForm] = useState(false);
  const [scopedTShirt, setScopedTShirt] = useState({});
  const [error, setError] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/t-shirt")
      .then((response) => response.json())
      .then((result) => setTShirts(result))
      .catch(console.log);
  }, []);

  function fetchBySize(evt) {
    if (evt.target.value === "") {
      setTShirts([]);
    } else {
      fetch("http://localhost:8080/t-shirt/size/" + evt.target.value)
        .then((response) => response.json())
        .then((result) => setTShirts(result))
        .catch(console.log);
    }
  }

  function fetchByColor(evt) {
    if (evt.target.value === "") {
      setTShirts([]);
    } else {
      fetch("http://localhost:8080/t-shirt/color/" + evt.target.value)
        .then((response) => response.json())
        .then((result) => setTShirts(result))
        .catch(console.log);
    }
  }

  function addClick() {
    setScopedTShirt({ tShirtId: 0 });
    setShowForm(true);
  }

  function notify({ action, tShirt, error }) {
    if (error) {
      setError(error);
      setShowForm(false);
      return;
    }

    switch (action) {
      case "add":
        setTShirts([...tShirts, tShirt]);
        break;
      case "edit":
        setTShirts(
          tShirts.map((e) => {
            if (e.tShirtId === tShirt.tShirtId) {
              return tShirt;
            }
            return e;
          })
        );
        break;
      case "edit-form":
        setScopedTShirt(tShirt);
        setShowForm(true);
        return;
      case "delete":
        setTShirts(tShirts.filter((e) => e.tShirtId !== tShirt.tShirtId));
        break;
      case "add-invoice-form":
        invoice.itemType = "t_shirt";
        invoice.itemId = tShirt.tShirtId;
        setShowInvoiceForm(true);
        return;
      case "add-invoice":
        setScopedTShirt(tShirt);
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
    return <TShirtForm tShirt={scopedTShirt} notify={notify} />;
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
            Add a TShirt
          </button>
        </div>
        <div>
          <select
            className="col col-md-3 pan"
            name="size"
            onChange={fetchBySize}
          >
            <option value="">Get TShirt By Size</option>
            <option value="XS">XS</option>
            <option value="S">S</option>
            <option value="M">M</option>
            <option value="L">L</option>
            <option value="XL">XL</option>
          </select>

          <select
            className="col col-md-3 pan"
            name="color"
            onChange={fetchByColor}
          >
            <option value="">Get TShirt By Color</option>
            <option value="red">Red</option>
            <option value="yellow">Yellow</option>
            <option value="green">Green</option>
            <option value="blue">Blue</option>
            <option value="purple">Purple</option>
            <option value="black">Black</option>
          </select>
        </div>
      </div>
      {error && <div className="alert alert-danger">{error}</div>}
      <div>
        <h1>T-Shirts</h1>
        <table id="table-tshirt">
          <thead>
            <tr>
              <th>Size</th>
              <th>Color</th>
              <th>Description</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {tShirts.map((r) => (
              <TShirtCard key={r.tShirtId} tShirt={r} notify={notify} />
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default TShirt;
