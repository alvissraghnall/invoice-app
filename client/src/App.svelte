<script>
  import { invoiceModalOpen } from "./store";
  import {InvoiceModal, ModalWrapper, Header} from "./components";
  import {
    Router
  } from "svelte-router-spa";
  import { routes } from "./routes/router";
  import { onMount } from "svelte";
  import { fly, slide } from "svelte/transition";
  import { quintOut } from "svelte/easing";

  const closeInvoiceModal = () => {
    invoiceModalOpen.set(false);
  }

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
  });

</script>

<svelte:window on:resize={checkScreen}></svelte:window>

<div class="font-poppins">
  {#if !mobile}
    <div class="bg-[#141625] min-h-screen flex-col lg:flex-row flex">
      <Header />
      <div class="py-0 px-5 flex-1 relative flex flex-col">
        {#if $invoiceModalOpen}
          <!-- <div out:fly={{
            duration: 520,
            opacity: 0.5,
            // easing: quintOut,
            x: 250
          }} in:fly={{
            x: 250, delay:80, opacity: 0, duration: 444
          }}>
            <InvoiceModal on:close={closeInvoiceModal} />
          </div> -->
          <ModalWrapper />
        {/if}
        <Router {routes} />
      </div> 
    </div>
  {:else}
    <div class="text-center place-content-center h-screen bg-holderColor flex flex-col text-white/80 font-poppins">
      <h2 class="text-lg font-bold"> Sorry, this app is not supported on mobile devices. </h2>
      <p class="mt-4"> To use this app, please use a tablet, or laptop. </p>
    </div>
  {/if}
     
    
</div>

<style global lang="postcss">

@tailwind base;
@tailwind utilities;
@tailwind components;

/* 
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
  background-color: #141625;
} */

.rounded-tbr-lg {
  @apply lg:rounded-br-[1.25rem] lg:rounded-tr-[1.25rem] lg:rounded-tl-none lg:rounded-bl-none;
}

.rounded-tbr {
  @apply rounded-br-[1.25rem] rounded-tr-[1.25rem] rounded-tl-none rounded-bl-none;
}
</style>