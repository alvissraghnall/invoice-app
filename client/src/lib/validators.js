function required(value) {
  if (value === '' || value == null) {
    return { required: { error: true, message: 'Field is required' } };
  }
  return { required: { error: false } };
}

function minLength(num) {
  return function (value) {
    if (value == null || value.length < num) {
      return {
        minLength: {
          error: true,
          value: num, 
          message: `Field minimum length is ${num}`,
        },
      };
    }
    return { minLength: { error: false } };
  };
}

export const Validators = {
  required, minLength
};