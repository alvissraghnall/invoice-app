<script>
import { Button } from "./shared";
import Modal from "./Modal.svelte";
import { createEventDispatcher, } from "svelte";
import { 
    DialogDescription,
    DialogTitle, 
} from '@rgossiaux/svelte-headlessui';

const dispatch = createEventDispatcher();

export let isOpen;

const closeModal = (ev) => {
    dispatch('close');
};
</script>

<Modal {isOpen} on:close={closeModal}>
    <DialogTitle class="text-lg font-medium leading-6 text-red-800">
        <slot name="title">
            Error!
        </slot>
    </DialogTitle>

    <DialogDescription class="mt-2 text-sm text-gray-600">
        <slot name="description" />
    </DialogDescription>

    <p class="mt-2 text-sm text-gray-600"><slot name="content" /></p>

    <Button class="mt-4 bg-butCol text-white/80 text-lg shadow-md" on:click={closeModal}>
        Close
    </Button>
</Modal>