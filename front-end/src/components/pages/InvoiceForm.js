import { useState } from "react";
import "./Style.css";

function InvoiceForm({ invoice: initialInvoice, notify }) {
  const [invoice, setInvoice] = useState(initialInvoice);
  // const isAdd = initialinvoice.invoiceId === 0;

  function handleChange(evt) {
    const clone = { ...invoice };
    clone[evt.target.name] = evt.target.value;
    setInvoice(clone);
  }

  function handleSubmit(evt) {
    evt.preventDefault();

    const url = `http://localhost:8080/invoice`;
    const method = "POST";
    const expectedStatus = 201;

    const init = {
      method,
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(invoice),
    };

    fetch(url, init)
      .then((response) => {
        if (response.status === expectedStatus) {
          return response.json();
        }
        return Promise.reject(
          `Didn't receive expected status: ${expectedStatus}`
        );
      })
      .then((result) =>
        notify({
          action: "add-invoice",
          invoice: result,
        })
      )
      .catch((error) => notify({ error: error }));
  }

  return (
    <>
      <h1>Invoice</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="customerName">Customer: </label>
          <input
            type="text"
            id="customerName"
            name="customerName"
            className="form-control"
            value={invoice.customerName}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="street">Street</label>
          <input
            type="text"
            id="street"
            name="street"
            className="form-control"
            value={invoice.street}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="city">City</label>
          <input
            type="text"
            id="city"
            name="city"
            className="form-control"
            value={invoice.city}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="state">State</label>
          <select name="state" value={invoice.state} onChange={handleChange}>
            <option value="">Select State</option>
            <option value="AL">AL</option>
            <option value="AK">AK</option>
            <option value="AR">AR</option>
            <option value="AZ">AZ</option>
            <option value="CA">CA</option>
            <option value="CO">CO</option>
            <option value="CT">CT</option>
            <option value="DE">DE</option>
            <option value="FL">FL</option>
            <option value="GA">GA</option>
            <option value="HI">HI</option>
            <option value="ID">ID</option>
            <option value="IL">IL</option>
            <option value="IN">IN</option>
            <option value="IA">IA</option>
            <option value="KS">KS</option>
            <option value="KY">KY</option>
            <option value="LA">LA</option>
            <option value="ME">ME</option>
            <option value="MD">MD</option>
            <option value="MA">MA</option>
            <option value="MI">MI</option>
            <option value="MN">MN</option>
            <option value="MS">MS</option>
            <option value="MO">MO</option>
            <option value="MT">MT</option>
            <option value="NE">NE</option>
            <option value="NV">NV</option>
            <option value="NH">NH</option>
            <option value="NJ">NJ</option>
            <option value="NM">NM</option>
            <option value="NY">NY</option>
            <option value="NC">NC</option>
            <option value="ND">ND</option>
            <option value="OH">OH</option>
            <option value="OK">OK</option>
            <option value="OR">OR</option>
            <option value="PA">PA</option>
            <option value="RI">RI</option>
            <option value="SC">SC</option>
            <option value="SD">SD</option>
            <option value="TN">TN</option>
            <option value="TX">TX</option>
            <option value="UT">UT</option>
            <option value="VT">VT</option>
            <option value="VA">VA</option>
            <option value="WA">WA</option>
            <option value="WV">WV</option>
            <option value="WI">WI</option>
            <option value="WY">WY</option>
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="zipcode">Zip Code</label>
          <input
            type="text"
            id="zipcode"
            name="zipcode"
            className="form-control"
            value={invoice.zipcode}
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
            value={invoice.quantity}
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

export default InvoiceForm;
