import mongoose, { Schema } from "mongoose";

const InvoiceSchema = new Schema({
  userId: {
    type: Schema.Types.ObjectId,
    ref: "User"
  },
  
  items: [{
    name: {
      type: String,
      required: true
    },
    rate: {
      type: String,
      required: true
    },
    hours: {
      type: Number,
      required: true
    }
  }]
  
});

const Invoice = mongoose.model('Invoice', InvoiceSchema);

export default Invoice;