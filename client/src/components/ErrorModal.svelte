<script>
import { Button } from "./shared";
import { fade } from 'svelte/transition';
import { 
    Dialog, 
    DialogDescription, 
    DialogOverlay, 
    DialogTitle, 
    Transition, 
    TransitionChild,
} from '@rgossiaux/svelte-headlessui';
import { createEventDispatcher, } from "svelte";

const dispatch = createEventDispatcher();

export let isOpen;

const closeModal = (ev) => {
    dispatch('close');
};
</script>

<Transition
    show={isOpen}
    as="div" 
    
>
    <Dialog
        class="relative z-50 font-poppins"
        on:close={closeModal}
    >
        <TransitionChild
            as="div"
            enter="transition ease-out duration-300" 
            enterFrom="transform opacity-0"
            enterTo="transform opacity-100"
            leave="transition ease-in duration-200" 
            leaveFrom="transform opacity-100" 
            leaveTo="transform opacity-0"
        >
            <DialogOverlay class="fixed inset-0 bg-gray-500 bg-opacity-75" />
        </TransitionChild>

        <div class="fixed inset-0 overflow-y-auto max-w-sm bg-white mx-auto my-auto h-max shadow-xl rounded-2xl">
            <div class="flex min-h-full items-center justify-center text-center">

                <TransitionChild
                    as="div"
                    enter="transition ease-out duration-300" 
                    enterFrom="transform scale-90 opacity-0"
                    enterTo="transform scale-100 opacity-100"
                    leave="transition ease-in duration-200" 
                    leaveFrom="transform scale-100 opacity-100" 
                    leaveTo="transform scale-90 opacity-0"
                >
                    <div transition:fade={{duration: 150}} class="w-full max-w-md transform overflow-hidden rounded-lg bg-white p-6 align-middle shadow-xl transition-all">
                        <DialogTitle class="text-lg font-medium leading-6 text-gray-800">
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
                    </div>
                </TransitionChild>

            </div>
                    
        </div>
        
    </Dialog>
</Transition>