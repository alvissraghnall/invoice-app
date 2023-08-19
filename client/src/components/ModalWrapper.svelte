<script>
  import { invoiceModalOpen } from "../store";
  import {
    Dialog,
    DialogOverlay,
    DialogTitle,
    TransitionChild,
    TransitionRoot,
  } from "@rgossiaux/svelte-headlessui";
  import { Icon, XMark, } from "svelte-hero-icons";
  import InvoiceModal from "./InvoiceModal.svelte";

  const toggleInvoiceModal = () => {
    invoiceModalOpen.update((value) => !value);
  };

  const closeInvoiceModal = () => {
    invoiceModalOpen.set(false);
  };
</script>

<TransitionRoot as="div" show={$invoiceModalOpen}>
  <Dialog as="div" class="relative z-10" on:close={toggleInvoiceModal}>
    <TransitionChild
      as="div"
      enter="transition-opacity ease-in-out duration-300"
      enterFrom="opacity-0"
      enterTo="opacity-100"
      leave="transition-opacity ease-linear duration-300"
      leaveFrom="opacity-100"
      leaveTo="opacity-0"
    >
      <DialogOverlay class="fixed inset-0 bg-gray-500 bg-opacity-75" />
    </TransitionChild>

    <div
      class="fixed inset-0 ml-20 max-w-max overflow-y-auto my-auto h-max shadow-xl rounded-2xl"
    >
      <div class="flex min-h-full items-center justify-center">
        <TransitionChild
          as="div"
          enter="transform transition ease-in-out duration-500 sm:duration-700"
          enterFrom="-translate-x-full"
          enterTo="translate-x-0"
          leave="transform transition ease-in-out duration-500 sm:duration-700"
          leaveFrom="translate-x-0"
          leaveTo="-translate-x-full"
        >
          <div
            class="w-full transform rounded-lg align-middle shadow-xl transition-all"
          >
            <!-- <div class="flex-1 overflow-y-auto px-4 py-6 sm:px-6"> -->
              
              <div class="flow-root my-3">
                <InvoiceModal on:close={closeInvoiceModal} />
              </div>
            <!-- </div> -->
          </div>
        </TransitionChild>
      </div>
    </div>
  </Dialog>
</TransitionRoot>
