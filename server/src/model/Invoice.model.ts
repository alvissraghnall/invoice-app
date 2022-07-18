import mongoose, { Schema } from "mongoose";

const InvoiceSchema = new Schema({
  userId: {
    type: Schema.Types.ObjectId,
    ref: "User"
  },
  
});