<script>
  import { invoiceModalOpen } from "./store";
  import { InvoicesLoading, ModalWrapper, Header } from "./components";
  import { Router } from "svelte-router-spa";
  import { routes } from "./routes/router";
  import { onMount } from "svelte";
  import { fly, slide } from "svelte/transition";
  import { quintOut } from "svelte/easing";
  import {Toaster} from "svelte-french-toast"
  import { invoices, invoicesLoading } from "./store";

  const closeInvoiceModal = () => {
    invoiceModalOpen.set(false);
  };

  let mobile = null;
  const checkScreen = () => {
    const windowWidth = window.innerWidth;
    if (windowWidth <= 700) {
      mobile = true;
      return;
    }
    mobile = false;
    console.log(mobile);
  };

  onMount(() => {
    checkScreen();
    console.log($invoices, $invoicesLoading);
  });
</script>

<svelte:window on:resize={checkScreen} />

<div class="font-poppins">
  
  <Router {routes}>

  {#if $invoicesLoading}
    <div class="bg-butCol/80 w-full min-h-screen flex justify-center items-center">
      <div class="bg-butCol/50 p-10 shadow-md rounded-xl relative">
        <InvoicesLoading />
      </div>
    </div>
  {:else}

    <!-- {#if !mobile} -->
    <div class="bg-[#141625] min-h-screen flex-col lg:flex-row flex">
      <Header />
      <div class="py-0 px-5 flex-1 relative flex flex-col">
        <ModalWrapper />

      </div>
    </div>
    <!-- {:else}
      <div
        class="text-center place-content-center h-screen bg-holderColor flex flex-col text-white/80 font-poppins"
      >
        <h2 class="text-lg font-bold">
          Sorry, this app is not supported on mobile devices.
        </h2>
        <p class="mt-4">To use this app, please use a tablet, or laptop.</p>
      </div>
    {/if} -->
  
  {/if}
  </Router>
</div>
<Toaster />

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

  @layer base {
    input[type="number"]::-webkit-outer-spin-button,
    input[type="number"]::-webkit-inner-spin-button,
    input[type="number"] {
      -webkit-appearance: none;
      margin: 0;
      -moz-appearance: textfield !important;
    }
  }

  .rounded-tbr-lg {
    @apply lg:rounded-br-[1.25rem] lg:rounded-tr-[1.25rem] lg:rounded-tl-none lg:rounded-bl-none;
  }

  .rounded-tbr {
    @apply rounded-br-[1.25rem] rounded-tr-[1.25rem] rounded-tl-none rounded-bl-none;
  }
</style>
