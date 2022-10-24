function InvoiceRowCard({ invoice }) {

  return (
    <tr key={invoice.invoiceId}>
      <td>{invoice.customerName}</td>
      <td>{invoice.street}</td>
      <td>{invoice.city}</td>
      <td>{invoice.state}</td>
      <td>{invoice.zipcode}</td>
      <td>{invoice.itemType}</td>
      <td>{invoice.itemId}</td>
      <td>{invoice.unitPrice}</td>
      <td>{invoice.quantity}</td>
      <td>{invoice.subtotal}</td>
      <td>{invoice.tax}</td>
      <td>{invoice.processingFee}</td>
      <td>{invoice.total}</td>
    </tr>
  );
}

export default InvoiceRowCard;
