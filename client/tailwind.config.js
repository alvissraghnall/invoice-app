const defaultTheme = require("tailwindcss/defaultTheme");

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main.js", "./src/**/*.svelte"],
  theme: {
    extend: {
      colors: {
        holderColor: "#141625",
        regColor: "#1e2139"
      },
      fontFamily: {
        sans: ["Graphik", "sans-serif"],
        'poppins': ['Poppins', ...defaultTheme.fontFamily.sans ],
      }
    },
    screens: {
      sm: '480px',
      md: '768px',
      lg: '960px',
      xl: '1200px',
      xxl: '1560px'
    },
  },
  plugins: [],
  darkMode: "class",

}
