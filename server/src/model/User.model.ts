import { Schema, model } from "mongoose";

const userSchema = new Schema({
  name: {
        type: String,
        required: true,
        min: 6,
        max: 255
    },
    email:{
        type: String,
        required: true,
        max: 255,
        min:6
    },
    phone: {
        type: String,
        required: true,
        max: 1024,
        min:4
    },
    date: {
        type: Date,
        default: Date.now
    },
    avatar: {
      type: String
    }
});

const User = model("User", userSchema);

export default User;