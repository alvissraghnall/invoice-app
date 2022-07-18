import type { Request, Response } from "express";
import User from "../model/User.model";
import { checkUserExistence, createUser } from "./services/user.service";

const InternalServerErrorMessage =  "Something went wromg from our end..";

export const addUser = async (req: Request, res: Response): Response {
  const newUser = req.body;
  try {
    const userExists = await checkUserExistence(newUser.email, User);
    if(userExists) return res.status(400).json({
      message: "User already exists!"
    });
    
    await createUser(newUser);
  } catch (err: unknown) {
    console.error((err as Error).message);
    return res.status(500).json({
      message: InternalServerErrorMessage
    })
  }
}