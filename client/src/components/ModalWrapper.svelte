<script>
import { invoiceModalOpen } from "../store";
import { Dialog, DialogOverlay, DialogTitle, TransitionChild, TransitionRoot } from '@rgossiaux/svelte-headlessui';
import { Icon, XMark } from "svelte-hero-icons";
import InvoiceModal from './InvoiceModal.svelte';

const toggleInvoiceModal = () => {
    invoiceModalOpen.update(value => !value);
}
</script>

<TransitionRoot as="div" show={$invoiceModalOpen}>
      <Dialog as="div" class="relative z-10" on:close={toggleInvoiceModal}>
        <TransitionChild 
            as="div" 
            enter="ease-in-out duration-500" 
            enterFrom="opacity-0" 
            enterTo="opacity-100"
            leave="ease-in-out duration-500" 
            leaveFrom="opacity-100" 
            leaveTo="opacity-0">
          <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
        </TransitionChild>
  
        <div class="fixed inset-0 overflow-hidden">
          <div class="absolute inset-0 overflow-hidden">
            <div class="pointer-events-none fixed inset-y-0 left-0 flex max-w-full pr-16">
              <TransitionChild as="div" enter="transform transition ease-in-out duration-500 sm:duration-700"
                enterFrom="-translate-x-full" enterTo="translate-x-0"
                leave="transform transition ease-in-out duration-500 sm:duration-700" 
                leaveFrom="translate-x-0"
                leaveTo="-translate-x-full"
            >
                <div class="pointer-events-auto w-full max-w-md">
                  <div class="flex h-full flex-col overflow-y-scroll bg-white shadow-xl">
                    <div class="flex-1 overflow-y-auto px-4 py-6 sm:px-6">
                      <div class="flex items-start justify-end">
                        <div class="ml-3 flex h-7 items-center">
                          <button type="button"
                            class="-m-2 p-2 text-gray-400 hover:text-gray-500 outline-none border-none focus:outline-none"
                            on:click={toggleInvoiceModal}>
                            <span class="sr-only">Close panel</span>
                            <Icon src={XMark} class="h-6 w-6" aria-hidden="true" />
                          </button>
                        </div>
                      </div>
  
                      <div class="mt-3">
                        <div class="flow-root">
                            <InvoiceModal />
                        </div>
                      </div>
                    </div>
  
                  </div>
                </div>
              </TransitionChild>
            </div>
          </div>
        </div>
      </Dialog>
    </TransitionRoot>