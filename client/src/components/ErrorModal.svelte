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
    <DialogTitle class="text-xl font-semibold leading-6 dark:text-white text-black">
        <slot name="title">
            Error!
        </slot>
    </DialogTitle>

    <DialogDescription class="mt-1 text-sm dark:text-white text-black font-medium">
        <slot name="description" />
    </DialogDescription>

    <div class="mt-2 text-sm dark:text-white text-black"><slot name="content" /></div>

    <Button class="mt-4 !text-butCol bg-white font-semibold hover:bg-butCol hover:!text-white text-lg shadow-md outline-none focus:outline-none" on:click={closeModal}>
        Close
    </Button>
</Modal>