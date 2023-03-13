<script>
  import { Router, Route } from "svelte-routing";
  import NavLink from "./components/NavLink.svelte";
  import Home from "./routes/Home.svelte";
  import About from "./routes/About.svelte";
  import Blog from "./routes/Blog.svelte";
  import AddClient from "./routes/AddClient.svelte";
  import Header from "./components/Nav.svelte";

  // Used for SSR. A falsy value is ignored by the Router.
  export let url = "";
  import "./index.css" 
</script>

<Router url={url}>
  <!-- <nav>
    <NavLink to="/">Home</NavLink>
    <NavLink to="about">About</NavLink>
    <NavLink to="blog">Blog</NavLink>
  </nav> -->
  <div class="">
    <div class="whole">

      <Header />
      <div class="spread">
        <Route path="about" component="{About}" />
        <Route path="blog/*" component="{Blog}" />
        <Route path="/" component="{Home}" />
        <Route path="client/add" component={AddClient} />

      </div>
    </div>
  </div>
  <!-- <div>
      </div> -->
</Router>

<style>

  .whole {
    background-color: #141625;
    min-height: 100vh;
    flex-direction: column;
  }

  .spread {
    display: flex;
    flex-direction: column;
    padding: 0 20px;
    position: relative;
  }

  @media screen and (min-width: 992px) {
    .whole {
      flex-direction: row !important;
    }
  }
</style>