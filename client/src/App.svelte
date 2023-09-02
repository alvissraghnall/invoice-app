<script>
  import { invoiceModalOpen } from "./store";
  import { InvoicesLoading, Modal, ModalWrapper, Aside } from "./components";
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
  <!-- {#if !$invoicesLoading} -->
  <Modal isOpen={$invoicesLoading} extraOverlayClasses="!bg-butCol/80" on:close={null}>
    <!-- <div class=" w-full min-h-screen flex justify-center items-center"> -->
      <div class="bg-white dark:bg-butCol flex justify-center items-center rounded-xl relative z-[808] w-full">
        <InvoicesLoading />
      </div>
    <!-- </div> -->
  </Modal>
    <!-- {/if} -->
  <!-- {:else} -->

  <div class="dark:bg-holderColor bg-mainBg min-h-screen flex items-start">
    <Aside />
    <main class="py-0 max-md:px-8 max-sm:px-2 px-5 flex-1 relative flex flex-col lg:ml-40 max-sm:mt-24 max-lg:mt-28">
      <ModalWrapper />
      <Router {routes} />
    </main>
  </div>
  
  <!-- {/if} -->
  <!-- </Router> -->
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
