<script>
import { closeModalOpen, editInvoice, invoiceModalOpen } from "../store";
import Modal from "./Modal.svelte";
import { Button } from "./shared";
import { 
    DialogDescription,
    DialogTitle, 
} from '@rgossiaux/svelte-headlessui';

const closeModal = () => {
    closeModalOpen.set(false);
    console.log("attmpt to close modal");
}

const closeInvoice = () => {
    closeModal();
    console.log("closed")
    invoiceModalOpen.set(false);
    if($editInvoice) editInvoice.set(false);
}

export let isOpen;
</script>

<Modal {isOpen} on:close>
    <!-- <div class=""> -->
        <p class="text-center">Are you sure you want to exit? You will lose all changes, as they won't be saved!</p>

        <div class="mt-6 flex">
            <Button class="mt-4 bg-purple-700 text-white/80 text-lg shadow-md flex-1" on:click={closeModal}>
                Return
            </Button>

            <Button class="mt-4 bg-red-700 flex-1 text-white/80 text-lg shadow-md" on:click={closeInvoice}>
                Close
            </Button>
        </div>
    <!-- </div> -->
    
</Modal>