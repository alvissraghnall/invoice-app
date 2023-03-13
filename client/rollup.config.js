import svelte from "rollup-plugin-svelte";
import commonjs from "@rollup/plugin-commonjs";
import resolve from "@rollup/plugin-node-resolve";
import livereload from "rollup-plugin-livereload";
import { terser } from "rollup-plugin-terser";
import css from 'rollup-plugin-css-only';


const isDev = Boolean(process.env.ROLLUP_WATCH);
const serve = () => {
	let server;

	function toExit() {
		if (server) server.kill(0);
	}

	return {
		writeBundle() {
			if (server) return;
			server = require('child_process').spawn('npm', ['run', 'start', '--', '--dev'], {
				stdio: ['ignore', 'inherit', 'inherit'],
				shell: true
			});

			process.on('SIGTERM', toExit);
			process.on('exit', toExit);
		}
	};
}

export default [
  // Browser bundle
  {
    input: "src/main.js",
    output: {
      sourcemap: true,
      format: "iife",
      name: "app",
      file: "public/build/bundle.js"
    },
    plugins: [
      svelte({
        compilerOptions: {
          // enable run-time checks when not in production
          dev: isDev,
          hydratable: true,
          // css: css => {
          //   css.write("public/bundle.css");
          // }
        }
      }),
      css({ output: 'bundle.css' }),
      resolve({
        browser: true,
        dedupe: ['svelte']
      }),
      commonjs(),

      // In dev mode, call `npm run start` once
		// the bundle has been generated
      isDev && serve(),
      // App.js will be built after bundle.js, so we only need to watch that.
      // By setting a small delay the Node server has a chance to restart before reloading.
      isDev &&
        livereload({
          watch: "public",
          delay: 200
        }),
      !isDev && terser()
    ]
  },
  // Server bundle
  // {
  //   input: "src/App.svelte",
  //   output: {
  //     exports: "default",
  //     sourcemap: false,
  //     format: "cjs",
  //     name: "app",
  //     file: "public/App.js"
  //   },
  //   plugins: [
  //     svelte({
  //       compilerOptions: {
  //         generate: "ssr"
  //       }
  //     }),
  //     css({output: false }),
  //     resolve(),
  //     commonjs(),
  //     !isDev && terser()
  //   ]
  // }
];
