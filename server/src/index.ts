import express from "express";
import cors from "cors";
import morgan from "morgan";
import helmet from "helmet";
import compression from "compression";
import mongoose from "mongoose";

class App {
  
  app: express.Application;
  PORT: number;
  
  constructor() {
    this.app = express();
    this.PORT = process.env.PORT! as number;
  }
  
  async connectToMongo(): Promise<void> {
    try {
      const _uri: string = (process.env.MONGODB_URI || process.env.TEST_URI) as string;

      await mongoose.connect(_uri);

      console.log("Successfully connected to the database!");
    } catch (e) {
        console.log("Error connecting to the database!!!");
    }
  }
  
  setupMiddlewares () {
    this.app.use(morgan("dev"));
    this.app.use(helmet());
    this.app.use(cors());
    
    this.app.use(compression());
  }
  
  start () {
    this.connectToMongo().then(() => {
      this.app.listen(this.PORT, () => {
        console.log("Server listening on port " + this.PORT);
      })
    });
  }
}