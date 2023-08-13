const defaultTheme = require("tailwindcss/defaultTheme");

const production = !process.env.ROLLUP_WATCH;

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main.js", "./src/**/*.svelte"],
  theme: {
    extend: {
      colors: {
        holderColor: "#141625",
        regColor: "#1e2139",
        butCol: "#252945",
        orange: "#ff8f01"
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
