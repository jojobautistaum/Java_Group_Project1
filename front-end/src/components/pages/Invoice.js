import React from "react";
import { useState, useEffect } from "react";
import InvoiceCard from "./InvoiceCard.js";
import "./Style.css";

function Invoice() {
  const [invoices, setInvoices] = useState([]);
  const [searchInput, setSearchInput] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/invoice")
      .then((response) => response.json())
      .then((result) => setInvoices(result))
      .catch(console.log);
  }, []);

  function fetchByCustomerName() {
    if (searchInput === "") {
      fetch("http://localhost:8080/invoice")
        .then((response) => response.json())
        .then((result) => setInvoices(result))
        .catch(console.log);
    } else {
      fetch("http://localhost:8080/invoice/customerName/" + searchInput)
        .then((response) => response.json())
        .then((result) => setInvoices(result))
        .catch(console.log);
    }
  }

  return (
    <>
      <div id="buttonPanel" className="row mt-2">
        <div className="row g-3 align-items-center">
            <label htmlFor="customer-name" className="col-auto col-form-label">Customer Name:</label>
            <div className="col-auto">
              <input
                type="text"
                id="customer-name"
                className="form-control"
                name="customer-name"
                value={searchInput}
                onChange={(e) => setSearchInput(e.target.value)}
                placeholder="Enter a Customer's Name"
              />
            </div>
            <div className="col-auto">
              <button
                id="search-customer"
                className="btn btn-primary"
                type="submit"
                onClick={fetchByCustomerName}
              >
                Search
              </button>
            </div>
        </div>
      </div>

      <div>
        <h1>Invoices</h1>
        <table id="table-invoice">
          <thead>
            <tr>
              <th>Customer</th>
              <th>Street</th>
              <th>City</th>
              <th>State</th>
              <th>Zipcode</th>
              <th>ItemType</th>
              <th>ItemId</th>
              <th>UnitPrice</th>
              <th>Quantity</th>
              <th>Subtotal</th>
              <th>Tax</th>
              <th>Fee</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>
            {invoices.map((r) => (
              <InvoiceCard key={r.invoiceId} invoice={r} />
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default Invoice;
