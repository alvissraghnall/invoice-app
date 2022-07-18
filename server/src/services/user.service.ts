

export const checkUserExistence = async (email: string, model: Model) => await model.findOne(email);

export const createUser = async (user: object) => {
  const user = new User(newUser);
  
  await user.save();
}