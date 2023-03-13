<script>
  import Header from "./components/Nav.svelte";
  import {
    Router
  } from "svelte-router-spa";
  import { routes } from "./routes/router";
    import { onMount } from "svelte";
  let mobile = null;
  const checkScreen = () => {
    const windowWidth = window.innerWidth;
    if (windowWidth <= 700) {
      mobile = true; return;
    } mobile = false;
    console.log(mobile);
  }

  onMount(() => {
    checkScreen();
  })
</script>

<svelte:window on:resize={checkScreen}></svelte:window>

<div class="">
  {#if !mobile}
    <div class="bg-[#141625] min-h-screen flex-col lg:flex-row flex">
      <Header />
      <div class="py-0 px-5 flex-1 relative flex flex-col">
        <Router {routes} />
      </div> 
    </div>
  {:else}
    <div class="text-center place-content-center h-screen bg-[] flex flex-col">
      <h2> Sorry, this app is not supported on mobile devices. </h2>
      <p> To use this app, please use a tablet, or laptop. </p>
    </div>
  {/if}
     
    
</div>
