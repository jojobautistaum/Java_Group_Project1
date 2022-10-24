import React from "react";
import { useState, useEffect } from "react";
import ConsoleForm from "./ConsoleForm.js";
import ConsoleCard from "./ConsoleCard.js";
import InvoiceForm from "./InvoiceForm.js";
import "./Style.css";

function Console() {
  const [consoles, setConsoles] = useState([]);
  const [invoice, setInvoice] = useState({});
  const [showForm, setShowForm] = useState(false);
  const [showInvoiceForm, setShowInvoiceForm] = useState(false);
  const [scopedConsole, setScopedConsole] = useState({});
  const [error, setError] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/console")
      .then((response) => response.json())
      .then((result) => setConsoles(result))
      .catch(console.log);
  }, []);

  function fetchByManufacturer(evt) {
    if (evt.target.value === "") {
      setConsoles([]);
    } else {
      fetch("http://localhost:8080/console/manufacturer/" + evt.target.value)
        .then((response) => response.json())
        .then((result) => setConsoles(result))
        .catch(console.log);
    }
  }

  function addClick() {
    setScopedConsole({ consoleId: 0 });
    setShowForm(true);
  }

  function notify({ action, console, error }) {
    if (error) {
      setError(error);
      setShowForm(false);
      return;
    }

    switch (action) {
      case "add":
        setConsoles([...consoles, console]);
        break;
      case "edit":
        setConsoles(
          consoles.map((e) => {
            if (e.consoleId === console.consoleId) {
              return console;
            }
            return e;
          })
        );
        break;
      case "edit-form":
        setScopedConsole(console);
        setShowForm(true);
        return;
      case "delete":
        setConsoles(consoles.filter((e) => e.consoleId !== console.consoleId));
        break;
      case "add-invoice-form":
        invoice.itemType = "console";
        invoice.itemId = console.consoleId;
        setShowInvoiceForm(true);
        return;
      case "add-invoice":
        setScopedConsole(console);
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
    return <ConsoleForm console={scopedConsole} notify={notify} />;
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
            Add a Console
          </button>
        </div>
        <div>
          <select
            className="col col-md-3 pan"
            name="manufacturer"
            onChange={fetchByManufacturer}
          >
            <option value="">Get Console By Manufacturer</option>
            <option value="sony">Sony</option>
            <option value="microsoft">Microsoft</option>
            <option value="apple">Apple</option>
          </select>
        </div>
      </div>
      {error && <div className="alert alert-danger">{error}</div>}
      <div>
        <h1>Consoles</h1>
        <table id="table-console">
          <thead>
            <tr>
              <th>Model</th>
              <th>Manufacturer</th>
              <th>Memory Amount</th>
              <th>Processor </th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {consoles.map((r) => (
              <ConsoleCard key={r.consoleId} console={r} notify={notify} />
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default Console;
